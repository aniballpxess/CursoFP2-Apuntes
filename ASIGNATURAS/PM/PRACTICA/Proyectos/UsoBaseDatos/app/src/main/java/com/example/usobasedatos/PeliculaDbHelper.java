package com.example.usobasedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PeliculaDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "peliculas.db";
    private static final int DATABASE_VERSION = 1;
    private static boolean databaseCreated = false;

    private final Context context;

    public PeliculaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

        if (!databaseCreated) {
            getWritableDatabase();
            databaseCreated = true;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE tabla_peliculas (" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "titulo TEXT NOT NULL, " +
                        "director TEXT, " +
                        "cartel INTEGER, " +
                        "musica INTEGER);"
        );
        db.execSQL(
                "CREATE TABLE tabla_descripciones (" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "pelicula_id INTEGER NOT NULL, " +
                        "descripcion TEXT NOT NULL, " +
                        "FOREIGN KEY (pelicula_id) REFERENCES tabla_peliculas(_id) ON DELETE CASCADE);"
        );
        // Muestra la ruta de la base de datos para depuración
        String path = db.getPath();
        Log.d("RutaBaseDeDatos", "Path: " + path);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (DATABASE_VERSION == oldVersion) {
            db.setVersion(newVersion);
            // TODO - Lógica para actualizar la base de datos si se cambia la versión
        }
        else {
            Log.e("SQLite Version", "La versión antigua no coincide con la version real de la base de datos. Imposible realizar mejora.");
        }
    }

    public void guardarPelicula(String titulo, String director, int cartel, int musica) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("titulo", titulo);
        values.put("director", director);
        values.put("cartel", cartel);
        values.put("musica", musica);

        db.insert("tabla_peliculas", null, values);
        db.close();
    }

    public List<Pelicula> obtenerListaPeliculas() {
        SQLiteDatabase db = getReadableDatabase();
        List<Pelicula> listaPeliculas = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM tabla_peliculas", null);
        if (cursor.moveToFirst()) {
            do {
                try {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                    String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
                    String director = cursor.getString(cursor.getColumnIndexOrThrow("director"));
                    int cartel = cursor.getInt(cursor.getColumnIndexOrThrow("cartel"));
                    int musica = cursor.getInt(cursor.getColumnIndexOrThrow("musica"));

                    listaPeliculas.add(new Pelicula(id, titulo, director, cartel, musica));
                } catch (IllegalArgumentException e) {
                    Log.e("SQLite Access", "Error al obtener datos de la base de datos: " + e.getMessage());
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return listaPeliculas;
    }

    public void eliminarPelicula(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("tabla_peliculas", "_id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void modificarPeliculas(String[] ids, ContentValues cambios) {
        SQLiteDatabase db = getWritableDatabase();

        if (cambios.isEmpty()) {
            Log.d("SQLite update", "No se ha solicitado ningún cambio");
            return;
        }
        int registrosActualizados = db.update("tabla_peliculas", cambios, "_id = ?", ids);
        int registrosNoActualizados = ids.length - registrosActualizados;
        if (registrosActualizados > 0) {
            Log.d("SQLite update",  registrosActualizados + " registros actualizados exitosamente.");
        }
        if (registrosNoActualizados > 0) {
            Log.e("SQLite update", "No se encontraron " + registrosNoActualizados + " registros.");
        }

        db.close();
    }

    public void guardarDescripcion(int id_pelicula, String descripcion)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_pelicula", id_pelicula);
        values.put("descripcion", descripcion);

        db.insert("tabla_descripciones", null, values);
        db.close();
    }
}