package com.example.usobasedatos;

// Actividad con toolbar desde donde añadir peliculas
// No hace uso de la clase Pelicula, que está pendiente
// Se introducen los elementos desde un formulario
// Y luego pueden ser visualizados,
// Datos persistentes en SQLite
// No se ha modularizado el metodo DBhelper, que está pendiente

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Lista de películas ha sido reemplazada por la base de datos SQLite
    private PeliculaDbHelper dbHelper;  // Instancia de la clase de gestión de la base de datos (Helper)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuración de la Toolbar (barra de herramientas)
        Toolbar jawstoolbar = findViewById(R.id.jawstoolbar);
        setSupportActionBar(jawstoolbar);  // Establece la Toolbar como la ActionBar
        getSupportActionBar().setDisplayShowTitleEnabled(false);  // Desactiva el título predeterminado
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Habilita el botón de regreso
        jawstoolbar.setNavigationIcon(R.drawable.ic_menu_back);  // Establece un icono personalizado para el botón de regreso

        // Inicializamos la instancia de la clase de ayuda (Helper) para la gestión de la base de datos
        dbHelper = new PeliculaDbHelper(this);
    }

    // Este método se llama cuando la actividad es destruida
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Lógica para detener la música (comentada por no ser relevante en este momento)
        // if (mediaPlayer != null) {
        //     mediaPlayer.stop();
        //     mediaPlayer.release();
        //     mediaPlayer = null;
        // }
    }

    // Inflamos el menú de opciones en la Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_anadir_pelis, menu);
        return true;
    }

    // Gestiona las acciones seleccionadas en el menú de opciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuitemnuevapeli) {
            mostrarFormularioAgregarPelicula();  // Llama al método para mostrar el formulario de agregar película
            return true;
        } else if (id == R.id.menuitemverpeliculas) {
            mostrarFormularioverPeliculasAnadidas();  // Llama al método para mostrar las películas agregadas
            return true;
        } else if (id == android.R.id.home) {
            finish();  // Cierra la actividad (equivalente a presionar el botón de retroceso)
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Muestra un formulario para agregar una nueva película
    private void mostrarFormularioAgregarPelicula() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar Película");

        // Infla el formulario para agregar película
        View formularioAgregarPelicula = LayoutInflater.from(this).inflate(R.layout.formulario_agregar_pelicula, null);
        final EditText etTitulo = formularioAgregarPelicula.findViewById(R.id.etTitulo);
        final EditText etDirector = formularioAgregarPelicula.findViewById(R.id.etDirector);

        builder.setView(formularioAgregarPelicula);

        // Aquí definimos las imágenes de cartel y música (esto es solo un ejemplo, se deberían obtener de los campos de formulario si fuera necesario)
        int cartelId = R.drawable.cartel_jaws;
        int musicaId = R.raw.jaws_theme;

        // Configura los botones del cuadro de diálogo
        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String titulo = etTitulo.getText().toString();
            String director = etDirector.getText().toString();

            // Si el título no está vacío, guardamos la película en la base de datos
            if (!titulo.isEmpty()) {
                dbHelper.guardarPelicula(titulo, director, cartelId, musicaId);  // Guarda la película

                // Obtiene los nombres de los recursos (cartel y música) a partir de los IDs
                String nombreCartel = getResources().getResourceEntryName(cartelId);
                String nombreMusica = getResources().getResourceEntryName(musicaId);

                // Muestra un Toast con los detalles de la película agregada
                String mensaje = "Película agregada:\n" +
                        "Título: " + titulo + "\n" +
                        "Director: " + director + "\n" +
                        "Cartel: " + nombreCartel + "\n" +
                        "Música: " + nombreMusica;
                Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> {
            // Acción al cancelar el formulario (opcional)
        });

        builder.show();
    }

    // Muestra un formulario con todas las películas agregadas y permite eliminarlas
    // Se llama desde el menú de opciones de la Toolbar opcion icono ojo
    private void mostrarFormularioverPeliculasAnadidas() {
        // Llama al select y recoge los registros de la bd
        List<Pelicula> listaPeliculas = dbHelper.obtenerListaPeliculas();  // Obtiene todas las películas de la base de datos

        // Crea un ArrayAdapter para mostrar las películas en un ListView con opción de selección múltiple
        ArrayAdapter<Pelicula> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, listaPeliculas);

        // Infla el layout personalizado para mostrar la lista
        View dialogView = LayoutInflater.from(this).inflate(R.layout.popup_formulario_baja, null);

        // Configura el ListView
        ListView listView = dialogView.findViewById(R.id.recordListView);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);  // Permite la selección múltiple

        // Crea y muestra el AlertDialog
        new AlertDialog.Builder(this)
                .setTitle("Eliminar películas")
                .setView(dialogView)
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    SparseBooleanArray checked = listView.getCheckedItemPositions();
                    List<Pelicula> peliculasEliminadas = new ArrayList<>();

                    // Verifica las películas seleccionadas
                    for (int i = 0; i < listaPeliculas.size(); i++) {
                        if (checked.get(i)) {
                            peliculasEliminadas.add(listaPeliculas.get(i));  // Agrega la película a la lista de eliminadas
                        }
                    }

                    // Elimina las películas seleccionadas de la base de datos
                    for (Pelicula pelicula : peliculasEliminadas) {
                        dbHelper.eliminarPelicula(pelicula.getId());  // Elimina la película por su ID
                    }
                    listaPeliculas.removeAll(peliculasEliminadas);  // Elimina las películas de la lista local

                    // Muestra un mensaje Toast con las películas eliminadas
                    if (!peliculasEliminadas.isEmpty()) {
                        Toast.makeText(this, "Películas eliminadas: " + peliculasEliminadas, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "No se seleccionaron películas.", Toast.LENGTH_SHORT).show();
                    }

                    // Opcional: Log de las películas restantes
                    Log.d("Pelicula", "Lista actualizada: " + listaPeliculas);
                })
                .setNegativeButton("Cancelar", null)  // Acción al cancelar
                .show();
    }


    // Clase interna PeliculaDbHelper que gestiona la base de datos SQLite
    private static class PeliculaDbHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "peliculas.db";  // Nombre de la base de datos
        private static final int DATABASE_VERSION = 1;  // Versión de la base de datos
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
            // Crea la tabla de películas con columnas: id, titulo, director, cartel, musica
            db.execSQL(
                    "CREATE TABLE peliculas (" +
                            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "titulo TEXT NOT NULL, " +
                            "director TEXT, " +
                            "cartel INTEGER, " +
                            "musica INTEGER);"
            );

            // Muestra la ruta de la base de datos para depuración
            String path = db.getPath();
            Log.d("RutaBasedeDatos", "Path: " + path);
            //Toast.makeText(context, "Database Path: " + path, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Lógica para actualizar la base de datos si se cambia la versión (pendiente)
        }

        // Método para guardar una película en la base de datos
        public void guardarPelicula(String titulo, String director, int cartel, int musica) {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("titulo", titulo);
            values.put("director", director);
            values.put("cartel", cartel);
            values.put("musica", musica);

            db.insert("peliculas", null, values);
            db.close();
        }

        // Método para obtener todas las películas de la base de datos
        public List<Pelicula> obtenerListaPeliculas() {
            SQLiteDatabase db = getReadableDatabase();
            List<Pelicula> listaPeliculas = new ArrayList<>();

            // Ejecuta una consulta SQL para obtener todas las películas
            Cursor cursor = db.rawQuery("SELECT * FROM peliculas", null);
            if (cursor.moveToFirst()) {
                do {
                    try {
                        // Usamos getColumnIndexOrThrow para garantizar que el índice es válido
                        int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                        String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
                        String director = cursor.getString(cursor.getColumnIndexOrThrow("director"));
                        int cartel = cursor.getInt(cursor.getColumnIndexOrThrow("cartel"));
                        int musica = cursor.getInt(cursor.getColumnIndexOrThrow("musica"));

                        // Agrega la película a la lista
                        listaPeliculas.add(new Pelicula(id, titulo, director, cartel, musica));
                    } catch (IllegalArgumentException e) {
                        // En caso de que alguna columna no se encuentre, se puede loguear el error
                        Log.e("SQLite", "Error al obtener datos de la base de datos: " + e.getMessage());
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

            return listaPeliculas;  // Retorna la lista de películas
        }


        // Método para eliminar una película de la base de datos
        public void eliminarPelicula(int id) {
            SQLiteDatabase db = getWritableDatabase();
            db.delete("peliculas", "_id = ?", new String[]{String.valueOf(id)});
            db.close();
        }
    }

    // Clase Pelicula que representa el modelo de datos de una película
    // Añadiremos el uso de los atributos que no utilizamos
    public static class Pelicula {
        private final int id;
        private final String titulo;
        private final String director;
        private final int cartel;
        private final int musica;

        public Pelicula(int id, String titulo, String director, int cartel, int musica) {
            this.id = id;
            this.titulo = titulo;
            this.director = director;
            this.cartel = cartel;
            this.musica = musica;
        }

        public int getId() {
            return id;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getDirector() {
            return director;
        }

        // Sobrescribimos el método toString() para mostrar la información de la película
        @Override
        public String toString() {
            return "Título: " + titulo + "\n" +
                    "Director: " + director;
        }
    }




}
