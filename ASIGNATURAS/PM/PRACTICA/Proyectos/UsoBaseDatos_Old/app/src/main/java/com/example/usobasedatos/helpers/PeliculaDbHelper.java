package com.example.usobasedatos.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.usobasedatos.model.Pelicula;
import com.example.usobasedatos.model.Actor;
import java.util.ArrayList;
import java.util.List;

// Clase interna PeliculaDbHelper que gestiona la base de datos SQLite
public class PeliculaDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MisPeliculasEnSQLite.db";  // Nombre de la base de datos
    private static final int DATABASE_VERSION = 2;  // Versión de la base de datos actualizada
    private static boolean databaseCreated = false;  // Control de si la base de datos ya ha sido creada

    private final Context context;

    public PeliculaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

        if (!databaseCreated) {
            getWritableDatabase();  // Crea la base de datos si no existe
            databaseCreated = true;
        }
    }

    // Método llamado cuando la base de datos se crea
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crea la tabla de películas
        db.execSQL(
                "CREATE TABLE tabla_peliculas (" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "titulo TEXT NOT NULL, " +
                        "director TEXT, " +
                        "cartel INTEGER, " +
                        "musica INTEGER);"
        );

        // Crea la tabla de actores
        db.execSQL(
                "CREATE TABLE tabla_actores (" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "pelicula_id INTEGER NOT NULL, " +
                        "nombre TEXT NOT NULL, " +
                        "FOREIGN KEY (pelicula_id) REFERENCES tabla_peliculas(_id) ON DELETE CASCADE);"
        );

        // Muestra la ruta de la base de datos para depuración
        String path = db.getPath();
        Log.d("RutaBasedeDatos", "Path: " + path);
    }

    //El método onUpgrade de la clase SQLiteOpenHelper se ejecuta cuando se detecta un cambio
    // en la versión de la base de datos. Esto sucede cuando el valor del parámetro DATABASE_VERSION
    // que defines en tu clase es mayor que el valor almacenado en el archivo de base de datos actual.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS tabla_actores");
            onCreate(db); // Recrea las tablas
        }
    }


    // Método para guardar una película en la base de datos
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

    // Método para guardar un actor asociado a una película
    public void guardarActor(int peliculaId, Actor actor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("pelicula_id", peliculaId); // Relación con la película
        values.put("nombre", actor.getNombre()); // Obtiene el nombre del objeto Actor
        db.insert("tabla_actores", null, values); // Inserta el registro en la tabla "actores"
        db.close();
    }


    // Método para obtener todos los actores de una película
    public List<String> obtenerActoresPorPelicula(int peliculaId) {
        SQLiteDatabase db = getReadableDatabase();
        List<String> listaActores = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT nombre FROM tabla_actores WHERE pelicula_id = ?",
                new String[]{String.valueOf(peliculaId)}
        );

        if (cursor.moveToFirst()) {
            do {
                listaActores.add(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listaActores;
    }

    // Método para eliminar un actor por su ID
    public void eliminarActor(int actorId) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("tabla_actores", "_id = ?", new String[]{String.valueOf(actorId)});
        db.close();
    }

    // Método para eliminar todos los actores asociados con una película
    public void eliminarActoresPorPelicula(int peliculaId) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("tabla_actores", "pelicula_id = ?", new String[]{String.valueOf(peliculaId)});
        db.close();
    }

    // Método para obtener todas las películas de la base de datos
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
                    Log.e("SQLite", "Error al obtener datos de la base de datos: " + e.getMessage());
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listaPeliculas;
    }

    // Método para eliminar una película de la base de datos
    public void eliminarPelicula(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("tabla_peliculas", "_id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
