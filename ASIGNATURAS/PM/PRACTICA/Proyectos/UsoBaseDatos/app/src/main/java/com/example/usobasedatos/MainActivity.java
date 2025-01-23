package com.example.usobasedatos;

// Actividad con toolbar desde donde añadir peliculas
// No hace uso de la clase Pelicula, que está pendiente
// Se introducen los elementos desde un formulario
// Y luego pueden ser visualizados,
// Datos persistentes en SQLite
// No se ha modularizado el metodo DBhelper, que está pendiente

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Lista de películas ha sido reemplazada por la base de datos SQLite
    private PeliculaDbHelper dbHelper;

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

        if (id == R.id.menu_item_nuevapeli) {
            mostrarFormularioAgregarPelicula();
            return true;
        } else if (id == R.id.menu_item_verpelis) {
            mostrarFormularioVerPeliculasAnadidas();
            return true;
        } else if (id == R.id.menu_item_modpelis) {
            //mostrarFormularioModificarPeliculas();
            return true;
        } else if (id == android.R.id.home) {
            finish();
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
    private void mostrarFormularioVerPeliculasAnadidas() {
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
                .setTitle("Películas Favoritas")
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
                .setNeutralButton("Detalles", (dialog, which) -> {
                    SparseBooleanArray checked = listView.getCheckedItemPositions();
                    List<Pelicula> peliculasSeleccionadas = new ArrayList<>();

                    // Recopila las películas seleccionadas
                    for (int i = 0; i < listaPeliculas.size(); i++) {
                        if (checked.get(i)) {
                            peliculasSeleccionadas.add(listaPeliculas.get(i));
                        }
                    }

                    if (peliculasSeleccionadas.isEmpty()) {
                        Toast.makeText(this, "No se seleccionaron películas.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Abre un nuevo cuadro de diálogo para agregar descripciones
                    AlertDialog.Builder detallesDialog = new AlertDialog.Builder(this);
                    detallesDialog.setTitle("Agregar Descripción");

                    // Infla el formulario para agregar descripciones
                    View formularioDescripcion = LayoutInflater.from(this).inflate(R.layout.formulario_agregar_descripcion, null);
                    final EditText etDescripcion = formularioDescripcion.findViewById(R.id.etDescripcion);
                    detallesDialog.setView(formularioDescripcion);

                    // Configura el botón para guardar la descripción
                    detallesDialog.setPositiveButton("Guardar", (descDialog, descWhich) -> {
                        String descripcion = etDescripcion.getText().toString();

                        if (!descripcion.isEmpty()) {
                            for (Pelicula pelicula : peliculasSeleccionadas) {
                                dbHelper.guardarDescripcion(pelicula.getId(), descripcion);  // Guarda la descripción en una tabla separada
                            }

                            Toast.makeText(this, "Descripción guardada para las películas seleccionadas.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "La descripción está vacía.", Toast.LENGTH_SHORT).show();
                        }
                    });

                    detallesDialog.setNegativeButton("Cancelar", null);
                    detallesDialog.show();
                })
                .setNegativeButton("Cancelar", null) // Acción al cancelar
                .show();
    }
}