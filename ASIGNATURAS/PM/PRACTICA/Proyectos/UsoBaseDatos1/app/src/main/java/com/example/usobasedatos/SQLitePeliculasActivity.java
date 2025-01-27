// Actividad con toolbar desde donde añadir peliculas y actores
// Es asi para que se tengan que utilizar dos tablas de base de datos relacionadas
// Se hace uso de la clase Pelicula,y Actor que está pendiente
// Se introducen los elementos desde un formulario
// Y luego pueden ser visualizados,
// Datos persistentes en SQLite

package com.example.usobasedatos;

// Actividad principal que gestiona la Toolbar, formularios y las interacciones con SQLite
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

// Creo carpetas para diferencia Modelo Vista y Controlador
import com.example.usobasedatos.helpers.PeliculaDbHelper;
import com.example.usobasedatos.model.Pelicula; // Importa la clase Actor
import com.example.usobasedatos.model.Actor; // Importa la clase Actor

import java.util.List;

public class SQLitePeliculasActivity extends AppCompatActivity {

    // El sgte es el Objeto para interactuar con la base de datos SQLite
    // Se denomina Helper y tiene su propia clase
    // la cual contiene todos los metodos para trabajar con base de datos
    private PeliculaDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Carga el diseño principal para esta actividad
        setContentView(R.layout.activity_peliculas_sqlite);
        // Configuración de la Toolbar personalizada
        Toolbar jawstoolbar = findViewById(R.id.jawstoolbar);
        setSupportActionBar(jawstoolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // Oculta el título por defecto
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Habilita el botón de navegación hacia atrás
        jawstoolbar.setNavigationIcon(R.drawable.ic_menu_back); // Icono para el botón "Atrás"

        // Inicialización del ayudante de base de datos para gestionar SQLite
        dbHelper = new PeliculaDbHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // A continuación se infla el menú de opciones (ubicadoen res/menu/menu_anadir_pelis.xml)
        getMenuInflater().inflate(R.menu.menu_anadir_pelis, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // A continuación se manejan la selección de elementos del menú
        int id = item.getItemId();

        if (id == R.id.menuitemnuevapeli) {
            mostrarFormularioAgregarPelicula(); // Muestra el formulario para agregar películas
            return true;
        } else if (id == R.id.menuitemverpeliculas) {
            mostrarFormularioverPeliculasAnadidas(); // Muestra el listado de películas
            return true;
        } else if (id == android.R.id.home) {
            finish(); // Cierra la actividad actual
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Muestra un formulario en un AlertDialog para agregar una nueva película
    private void mostrarFormularioAgregarPelicula() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar Película");

        // Infla el diseño XML para el formulario (res/layout/formulario_agregar_pelicula.xml)
        View formularioAgregarPelicula = LayoutInflater.from(this).inflate(R.layout.formulario_agregar_pelicula, null);
        final EditText etTitulo = formularioAgregarPelicula.findViewById(R.id.etTitulo); // Campo para el título
        final EditText etDirector = formularioAgregarPelicula.findViewById(R.id.etDirector); // Campo para el director

        builder.setView(formularioAgregarPelicula); // Añade el diseño al AlertDialog

        // Los siguientes atributos de Pelicula en este ejemplo están fijos, pero podrían ser dinámicos
        // En el futuro los cogeré de la web o el almacenamiento externo del movil
        int cartelId = R.drawable.cartel_jaws;
        int musicaId = R.raw.jaws_theme;

        // El alertdialog tiene dos botones que se gestionan a continuación
        // Sus botones son agregar y cancelar
        // Que pasa cuando se pulsa cada uno, agregar pelicula
        builder.setPositiveButton("Añadir", (dialog, which) -> {
            // Recoge los datos del formulario
            String titulo = etTitulo.getText().toString();
            String director = etDirector.getText().toString();

            if (!titulo.isEmpty()) {
                // Guarda los datos de la película en la base de datos
                // Esta linea es una de las claves de este ejercicio
                dbHelper.guardarPelicula(titulo, director, cartelId, musicaId);

                // Muestra un mensaje de confirmación con los detalles
                String nombreCartel = getResources().getResourceEntryName(cartelId);
                String nombreMusica = getResources().getResourceEntryName(musicaId);

                // A continuación se monta un Toast con los datos de la película
                String mensaje = "Película agregada:\n" +
                        "Título: " + titulo + "\n" +
                        "Director: " + director + "\n" +
                        "Cartel: " + nombreCartel + "\n" +
                        "Música: " + nombreMusica;
                Toast.makeText(SQLitePeliculasActivity.this, mensaje, Toast.LENGTH_SHORT).show();
            }
        });
        //El siguiente boton, cancelar, no hace nada
        builder.setNegativeButton("Cancelar", null); // Opción para cancelar
        builder.show(); // Muestra el AlertDialog
    }

    // El siguiente metodo sirve para mostrar el listado de películas
    // Muestra el listado de películas existentes en la bbdd con opciones para gestionarlas
    private void mostrarFormularioverPeliculasAnadidas() {
        // Recupera la lista de películas desde la base de datos y las vuelca en una interfaz de tipo Lista
        //List<Pelicula> es una interfaz que representa una lista de objetos Pelicula.
        // Esta interfaz es implementada por clases como ArrayList, que te permiten gestionar
        // dinámicamente los elementos (agregar, quitar, ordenar, etc.).
        //List ofrece más funcionalidad y flexibilidad que los arrays, permitiendo que el tamaño
        // de la colección cambie y ofreciendo muchos métodos útiles para gestionar los datos.
        //El método dbHelper.obtenerListaPeliculas() devuelve una lista (de tipo List<Pelicula>)
        // que contiene objetos de tipo Pelicula. Es decir, la función recupera todos los
        // registros de películas almacenados en la base de datos y los guarda en una lista
        // para que puedas acceder a ellos. Ve a la clase Helper y busca el metodo, lee su codigo
        List<Pelicula> listaPeliculas = dbHelper.obtenerListaPeliculas();

        // Adaptador para mostrar los datos volcados en la lista en un ListView predefinido en android
        ArrayAdapter<Pelicula> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, listaPeliculas);

        // Infla el diseño para el listado (res/layout/popup_formulario_gestionar_pelis.xml)
        View dialogView = LayoutInflater.from(this).inflate(R.layout.popup_formulario_gestionar_pelis, null);
        ListView listView = dialogView.findViewById(R.id.recordListView); // ListView del XML
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE); // Permite seleccionar una única película

        // Crea y muestra un AlertDialog para gestionar películas
        new AlertDialog.Builder(this)
                .setTitle("Gestionar películas")
                .setView(dialogView) // Añade el ListView al AlertDialog
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    // Elimina la película seleccionada
                    int checkedPosition = listView.getCheckedItemPosition();
                    if (checkedPosition != ListView.INVALID_POSITION) {
                        Pelicula peliculaSeleccionada = listaPeliculas.get(checkedPosition);
                        dbHelper.eliminarPelicula(peliculaSeleccionada.getId()); // Borra de la base de datos
                        listaPeliculas.remove(peliculaSeleccionada); // Actualiza la lista
                        Toast.makeText(this, "Película eliminada: " + peliculaSeleccionada, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "No se seleccionó ninguna película.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null) // Cierra el AlertDialog
                .setNeutralButton("Añadir actores", (dialog, which) -> {
                    // Muestra el formulario para agregar actores a la película seleccionada
                    int checkedPosition = listView.getCheckedItemPosition();
                    if (checkedPosition != ListView.INVALID_POSITION) {
                        Pelicula peliculaSeleccionada = listaPeliculas.get(checkedPosition);
                        mostrarFormularioAgregarActores(peliculaSeleccionada.getId());
                    } else {
                        Toast.makeText(this, "Selecciona una película para añadir actores.", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
    

    // Muestra un formulario para agregar actores a una película
    private void mostrarFormularioAgregarActores(int peliculaId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar Actor");

        // Infla el diseño XML para el formulario de actores (res/layout/formulario_agregar_actor.xml)
        View formularioAgregarActor = LayoutInflater.from(this).inflate(R.layout.formulario_agregar_actor, null);
        final EditText etNombreActor = formularioAgregarActor.findViewById(R.id.etNombreActor); // Campo para el nombre del actor

        builder.setView(formularioAgregarActor); // Añade el diseño al AlertDialog

        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String nombreActor = etNombreActor.getText().toString();
            if (!nombreActor.isEmpty()) {
                // Guarda el actor en la base de datos
                Actor nuevoActor = new Actor(0, nombreActor); // Crea el objeto Actor
                dbHelper.guardarActor(peliculaId, nuevoActor); // Lo guarda en la base de datos
                Toast.makeText(this, "Actor agregado: " + nuevoActor.getNombre(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El nombre del actor no puede quedar vacío.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", null); // Cierra el formulario sin hacer nada
        builder.show(); // Muestra el AlertDialog
    }
}
