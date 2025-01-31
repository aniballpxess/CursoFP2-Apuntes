package com.example.usobasedatos;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.Spinner;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class DosAPIsMercadonaActivity extends AppCompatActivity {

    private Spinner spinnerPpalProductoCategories, spinnerN2categories;
    private ListView listViewProducts;

    // Creación y configuración de adaptadores para los spinners
    private ArrayAdapter<String> categoriaPpalAdapter, categoriaNivel2Adapter, productosAdapter;

    // Listas que van a almacenar las categorías principales, las subcategorías y los productos
    private Map<Integer, String> list_categoriasPrincipales;
    private Map<String,JSONArray> categoriasNivel2List;
    private Map<Integer, String> productosList;
    private List<JSONArray> list_categoriasSecundarias_byCategoryId; // Lista para almacenar las subcategorías de cada categoría

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos_apis);

        // 1. Inicialización de componentes de la UI
        spinnerPpalProductoCategories = findViewById(R.id.spinnerCategories);
        spinnerN2categories = findViewById(R.id.spinnerSubcategories);
        listViewProducts = findViewById(R.id.listViewProducts);

        // 2. Inicialización de listas
        list_categoriasPrincipales = new HashMap<>();
        categoriasNivel2List = new HashMap<>();
        productosList = new HashMap<>();
        list_categoriasSecundarias_byCategoryId = new ArrayList<>(); // Lista de subcategorías por cada ID de categoría

        // 3. Creación y configuración de adaptadores para los spinners
        categoriaPpalAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list_categoriasPrincipales);
        categoriaPpalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPpalProductoCategories.setAdapter(categoriaPpalAdapter);

        categoriaNivel2Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriasNivel2List);
        categoriaNivel2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerN2categories.setAdapter(categoriaNivel2Adapter);

        productosAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productosList);
        listViewProducts.setAdapter(productosAdapter);

        // 4. Aquí obtendremos las categorías principales desde la API
        new Thread(() -> {
            try {
                // 4.1 Realizamos la conexión con la API de Mercadona
                URL url = new URL("https://tienda.mercadona.es/api/categories/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);

                // 4.2 Leer la respuesta de la API
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                // 4.3 Parsear la respuesta JSON
                JSONObject nodoRespuesta_dbMencabrona = new JSONObject(response.toString());
                JSONArray ja_categoriasPrincipales = nodoRespuesta_dbMencabrona.getJSONArray("results");

                // 4.4 Limpiar listas antes de agregar nuevos datos
                list_categoriasPrincipales.clear();
                list_categoriasSecundarias_byCategoryId.clear();

                List<String> tempCategoriesList = new ArrayList<>();
                for (int i = 0; i < ja_categoriasPrincipales.length(); i++) {
                    // 4.5 Extraer las categorías principales del JSON
                    JSONObject categoriaPrincipal = ja_categoriasPrincipales.getJSONObject(i);
                    String categoryName = categoriaPrincipal.getString("name");
                    int categoryId = categoriaPrincipal.getInt("id");
                    tempCategoriesList.add(categoryId + " - " + categoryName);

                    // 4.7 Extraer las categorias secundarias del JSON
                    JSONArray atributo_categories = categoriaPrincipal.getJSONArray("categories");
                    list_categoriasSecundarias_byCategoryId.add(atributo_categories);
                }

                // 4.9 Ordenar las categorias principales
                Collections.sort(tempCategoriesList, (o1, o2) -> {
                    int id1 = Integer.parseInt(o1.split(" - ")[0]);
                    int id2 = Integer.parseInt(o2.split(" - ")[0]);
                    return Integer.compare(id1, id2);
                });

                list_categoriasPrincipales.addAll(tempCategoriesList);
                runOnUiThread(() -> {
                    categoriaPpalAdapter.notifyDataSetChanged(); // ¿Por qué no notificar por ambos Adapters?
                });

            } catch (Exception e) {
                // 4.12 Manejo de errores de API
                Log.e("API Error", "Error al obtener categorías: " + e.getMessage());
                runOnUiThread(() -> {
                    Toast.makeText(DosAPIsMercadonaActivity.this, "Error al obtener categorías", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();

        // 5. Listener para el cambio de categoría en el primer spinner
        spinnerPpalProductoCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // 5.1 Aquí identificamos que categoría principal se ha seleccionado
                String selectedCategory = list_categoriasPrincipales.get(position);
                String categoryId = selectedCategory.split(" - ")[0];

                // 5.2 Limpiar la lista de subcategorías
                categoriasNivel2List.clear();

                // 5.3 Llenar el array de subcategorías según el ID de la categoría seleccionada
                try {
                    // 5.4 Obtener las subcategorías correspondientes al ID seleccionado
                    JSONArray subCategories = list_categoriasSecundarias_byCategoryId.get(position);

                    // 5.5 Añadir las subcategorías a la lista del segundo spinner
                    for (int i = 0; i < subCategories.length(); i++) {
                        JSONObject subCategory = subCategories.getJSONObject(i);
                        String subCategoryName = subCategory.getString("name");
                        categoriasNivel2List.add(subCategoryName);
                    }

                    // 5.6 Notificar al adaptador que los datos de subcategorías han cambiado
                    categoriaNivel2Adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.e("API Error", "Error al obtener subcategorías: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(DosAPIsMercadonaActivity.this, "Error al obtener subcategorías", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No hacer nada
            }
        });

        // 6. Listener para el cambio de subcategoría en el segundo spinner

    }
}
