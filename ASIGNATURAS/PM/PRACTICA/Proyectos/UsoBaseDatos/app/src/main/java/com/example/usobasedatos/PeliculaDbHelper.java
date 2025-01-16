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

    public void modificarPelicula(int id, int tipo_de_modificacion, String valor) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        switch (tipo_de_modificacion) {
            case 1:
                values.put("titulo", valor);
                break;
            case 2:
                values.put("director", valor);
                break;
            case 4:
                try {
                    int referenciaCartel = Integer.parseInt(valor);
                    values.put("cartel", referenciaCartel);
                } catch (NumberFormatException e) {
                    Log.e("SQLite update error", "Invalid value for 'cartel': " + valor);
                    db.close();
                    return;
                }
                break;
            case 8:
                try {
                    int referenciaCancion = Integer.parseInt(valor);
                    values.put("musica", referenciaCancion);
                } catch (NumberFormatException e) {
                    Log.e("SQLite update error", "Invalid value for 'musica': " + valor);
                    db.close();
                    return;
                }
                break;
            default:
                Log.e("SQLite update type", "El tipo de modificación solicitada no está soportada.");
                db.close();
                return;
        }

        if (values.size() > 0) {
            int rowsUpdated = db.update("tabla_peliculas", values, "_id = ?", new String[]{String.valueOf(id)});
            if (rowsUpdated > 0) {
                Log.d("SQLite update", "Registro actualizado exitosamente. ID: " + id);
            } else {
                Log.e("SQLite update", "No se encontró ningún registro con ID: " + id);
            }
        }

        db.close();
    }
}