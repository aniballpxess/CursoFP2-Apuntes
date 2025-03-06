// Código para conectarnos a la API de Mercadona
/* Vamos a explicarlo como si se lo contáramos a niños:



Imagina que Retrofit es como un cartero muy listo que sabe cómo enviar cartas (pedidos) y recibir respuestas de una oficina (API).

1. **`Retrofit.Builder()`**
   Esto es como decir: "Voy a construir mi cartero personal". Estamos preparando todo para que funcione correctamente.

2. **`baseUrl("https://tienda.mercadona.es/api/v1_1/")`**
   Aquí le decimos al cartero a qué oficina va a llevar nuestras cartas. Esta oficina está en la dirección:
   `"https://tienda.mercadona.es/api/v1_1/"`.
   Es como decirle: *"Lleva todo lo que te pida a esta dirección."*

3. **`addConverterFactory(GsonConverterFactory.create())`**
   ¿Y qué pasa con las respuestas que llegan? Puede que sean difíciles de entender (en forma de código JSON).
   Este paso es como darle al cartero un diccionario mágico llamado **Gson** (la biblioteca )para que pueda traducir esas respuestas
   a un idioma que nosotros (el programa) podamos entender.


4. **`.build()`**
   Por último, le decimos: *"¡Ya está todo listo, cartero! Ahora estás construido y preparado para trabajar."*


*/


/*

Con respecto a GSON:
Gson es una biblioteca en Java. Técnicamente, el término "biblioteca" y "librería" son equivalentes, pero "biblioteca"
es el término más comúnmente usado en español para describir herramientas de software que contienen colecciones de clases,
 funciones y métodos que los desarrolladores pueden usar.

¿Qué hace Gson?
 Gson parsea (analiza y transforma) el JSON recibido, pero también realiza otras tareas relacionadas con la conversión
 entre JSON y objetos Java. A continuación, te lo explico en detalle:

Parsear JSON a objetos Java (Deserialización):

Gson toma un archivo o una cadena en formato JSON (el formato de datos que usa la mayoría de las APIs)
y lo convierte en objetos Java que puedes usar fácilmente en el programa.
"Parsear" significa tomar una cadena de texto (como JSON) y analizarla para convertirla en algo útil para el programa, como un objeto que puedas manipular en Java. En este caso:

Gson lee el JSON recibido.
Entiende cómo está estructurado (gracias a las clases que defines en Java).
Crea objetos Java con los datos que contiene el JSON.

Por ejemplo:

{ "nombre": "Pedro", "edad": 25 }
Gson puede transformarlo en un objeto como este:

class Persona {
    private String nombre;
    private int edad;
}
Ahora, se puede trabajar directamente con persona.getNombre() y persona.getEdad().

 */





package com.example.usobasedatos;

import android.os.Bundle;
import android.util.Log; // Import para usar los logs

import androidx.activity.EdgeToEdge; // Clase para ajustar las vistas al modo de bordes a bordes
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets; // Clase para obtener los márgenes del sistema
import androidx.core.view.ViewCompat; // Clase para compatibilidad con vistas
import androidx.core.view.WindowInsetsCompat; // Clase para manejar los insets de la ventana

import android.view.View; // Import para gestionar vistas
import android.widget.Button; // Import para usar botones
import android.widget.EditText; // Import para usar campos de texto editables
import android.widget.TextView; // Import para usar vistas de texto
import android.widget.Toast; // Import para mostrar mensajes emergentes

import com.example.usobasedatos.interfaces.CategoriaDeProductoAPI; // Interfaz de API definida para Retrofit
import com.example.usobasedatos.model.Categoria; // Clase modelo que representa una categoría

import retrofit2.Call; // Clase para realizar llamadas de Retrofit
import retrofit2.Callback; // Clase para manejar las respuestas de Retrofit
import retrofit2.Response; // Clase para representar una respuesta de Retrofit
import retrofit2.Retrofit; // Clase principal de Retrofit para configurar la conexión
import retrofit2.converter.gson.GsonConverterFactory; // Convertidor para manejar JSON con Gson

public class APIMercadonaActivity extends AppCompatActivity {
    // Declaración de componentes de la interfaz de usuario
    private EditText txtCodigoCat; // Campo para introducir el código de la categoría
    private TextView txtNombreCat; // Vista de texto para mostrar el nombre de la categoría
    private Button btnBuscarCat; // Botón para iniciar la búsqueda

    // Constante para la etiqueta de los logs
    private static final String LOG_TAG = "APIM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Activar el modo de diseño de bordes a bordes
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_apimercadona); // Asocia el diseño con la actividad

        // Ajusta los márgenes de la vista principal para incluir los márgenes del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mencabrona), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Vincular los componentes de la interfaz de usuario con sus IDs en el XML
        txtCodigoCat = findViewById(R.id.IdIntroCodigo);
        txtNombreCat = findViewById(R.id.IdNombreCategoria);
        btnBuscarCat = findViewById(R.id.btnBuscar);

        // Configurar la acción del botón para buscar categorías
        btnBuscarCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener el código introducido y eliminar espacios en blanco
                String codigo = txtCodigoCat.getText().toString().trim();
                if (codigo.isEmpty()) { // Comprobar si el campo está vacío
                    Toast.makeText(APIMercadonaActivity.this, "Por favor, introduce un código válido.", Toast.LENGTH_SHORT).show();
                    Log.w(LOG_TAG, "El campo de código está vacío.");
                } else {
                    // A continuación se llama al método que va a  buscar la categoría de productos de Mercadona
                    buscarCategoriaMercadona(codigo);
                }
            }
        });
    }

    // Método para buscar la categoría de producto correspondiente al codigo introducido en el servidor
    private void buscarCategoriaMercadona(String codigo) {
        Log.i(LOG_TAG, "Se va a iniciar búsqueda para el código: " + codigo);

        // Configuración del cliente Retrofit con la URL base y el convertidor JSON
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tienda.mercadona.es/api/v1_1/") // URL base de la API
                .addConverterFactory(GsonConverterFactory.create()) // Se indica que se va a usar GSON como Convertidor para manejar el JSON retornado
                .build();

        // Crear una implementación de la interfaz API
        //Esta instrucción usa la biblioteca Retrofit para crear una instancia
        // de la interfaz CategoriaDeProductoAPI.


        // la instruccion
        //retrofit.create(CategoriaDeProductoAPI.class) le dice a Retrofit que cree
        // una implementación
        // de la interfaz CategoriaDeProductoAPI. Retrofit usará esta interfaz para generar código que se encargue
        // de hacer las solicitudes HTTP correspondientes (en este caso, una solicitud GET a la API).

        //CategoriaDeProductoAPI categoriaAPI: La variable categoriaAPI contendrá la implementación
        //generada por Retrofit que puede ser utilizada para hacer las llamadas a los endpoints de la API
        //definidos en la interfaz.

        CategoriaDeProductoAPI categoriaAPI = retrofit.create(CategoriaDeProductoAPI.class);

        // Realizar una llamada a la API con el código introducido
        Call<Categoria> call = categoriaAPI.find(Integer.parseInt(codigo));

        // Encolar la llamada para realizarla de forma asíncrona
        call.enqueue(new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                Log.d(LOG_TAG, "Respuesta recibida: Código HTTP " + response.code());

                try {
                    if (response.isSuccessful()) { // Verificar si la respuesta fue exitosa
                        Categoria c = response.body(); // Obtener el cuerpo de la respuesta
                        if (c != null) { // Comprobar si el cuerpo no está vacío
                            txtNombreCat.setText(c.getName()); // Mostrar el nombre de la categoría
                            Log.i(LOG_TAG, "Categoría encontrada: " + c.getName());
                        } else {
                            txtNombreCat.setText("Sin datos disponibles");
                            Log.w(LOG_TAG, "La respuesta es exitosa, pero el cuerpo está vacío.");
                        }
                    } else {
                        // Mostrar un mensaje de error si la respuesta no fue exitosa
                        txtNombreCat.setText("Error: " + response.message());
                        Log.e(LOG_TAG, "Error en la respuesta: " + response.errorBody().toString());
                    }
                } catch (Exception ex) {
                    // Manejar posibles excepciones al procesar la respuesta
                    Toast.makeText(APIMercadonaActivity.this, "Error al procesar la respuesta", Toast.LENGTH_SHORT).show();
                    Log.e(LOG_TAG, "Excepción al procesar la respuesta: " + ex.getMessage(), ex);
                }
            }

            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {
                // Manejar errores de conexión o de llamada
                Toast.makeText(APIMercadonaActivity.this, "Error de conexión. Inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
                Log.e(LOG_TAG, "Fallo en la conexión: " + t.getMessage(), t);
            }
        });
    }
}
