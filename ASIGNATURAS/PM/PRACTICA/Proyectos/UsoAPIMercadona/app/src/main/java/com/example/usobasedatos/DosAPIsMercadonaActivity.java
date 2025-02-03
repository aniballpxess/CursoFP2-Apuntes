package com.example.usobasedatos;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.Spinner;

import android.util.Log;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class DosAPIsMercadonaActivity extends AppCompatActivity {

    private Spinner spinnerPpalProductoCategories, spinnerN2categories;
    private ListView listViewProducts;

    private ArrayAdapter<String> categoriaPpalAdapter, categoriaNivel2Adapter, productosAdapter;
    private List<String> categoriasPpalesList, categoriasNivel2List, productosList;
    private List<JSONArray> categoriasNivel2ListByCategoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos_apis);

        spinnerPpalProductoCategories = findViewById(R.id.spinnerCategories);
        spinnerN2categories = findViewById(R.id.spinnerSubcategories);
        listViewProducts = findViewById(R.id.listViewProducts);

        categoriasPpalesList = new ArrayList<>();
        categoriasNivel2List = new ArrayList<>();
        productosList = new ArrayList<>();
        categoriasNivel2ListByCategoryId = new ArrayList<>();

        categoriaPpalAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriasPpalesList);
        categoriaPpalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPpalProductoCategories.setAdapter(categoriaPpalAdapter);

        categoriaNivel2Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriasNivel2List);
        categoriaNivel2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerN2categories.setAdapter(categoriaNivel2Adapter);

        productosAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productosList);
        listViewProducts.setAdapter(productosAdapter);

        new Thread(() -> {
            try {
                URL url = new URL("https://tienda.mercadona.es/api/categories/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                JSONObject jsonResponseNodoPpal = new JSONObject(response.toString());
                JSONArray categoriasPpalesArray = jsonResponseNodoPpal.getJSONArray("results");

                categoriasPpalesList.clear();
                categoriasNivel2ListByCategoryId.clear();

                List<CategoriaConSubcategorias> categoriasTempList = new ArrayList<>();

                for (int i = 0; i < categoriasPpalesArray.length(); i++) {
                    JSONObject categoriaPpal = categoriasPpalesArray.getJSONObject(i);
                    String categoryName = categoriaPpal.getString("name");
                    int categoryId = categoriaPpal.getInt("id");
                    JSONArray subcategoriesArray = categoriaPpal.getJSONArray("categories");

                    categoriasTempList.add(new CategoriaConSubcategorias(categoryId, categoryName, subcategoriesArray));
                }

                Collections.sort(categoriasTempList, Comparator.comparingInt(c -> c.categoryId));

                for (CategoriaConSubcategorias categoria : categoriasTempList) {
                    categoriasPpalesList.add(categoria.categoryId + " - " + categoria.categoryName);
                    categoriasNivel2ListByCategoryId.add(categoria.subcategories);
                }

                runOnUiThread(() -> categoriaPpalAdapter.notifyDataSetChanged());

            } catch (Exception e) {
                Log.e("API Error", "Error al obtener categorías: " + e.getMessage());
                runOnUiThread(() -> Toast.makeText(DosAPIsMercadonaActivity.this, "Error al obtener categorías", Toast.LENGTH_SHORT).show());
            }
        }).start();

        spinnerPpalProductoCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                categoriasNivel2List.clear();
                try {
                    JSONArray subCategories = categoriasNivel2ListByCategoryId.get(position);
                    // 5.5 Añadir las subcategorías con su ID a la lista del segundo spinner
                    for (int i = 0; i < subCategories.length(); i++) {
                        JSONObject subCategory = subCategories.getJSONObject(i);
                        int subCategoryId = subCategory.getInt("id"); // Obtener el ID de la subcategoría
                        String subCategoryName = subCategory.getString("name"); // Obtener el nombre
                        categoriasNivel2List.add(subCategoryId + " - " + subCategoryName); // Guardarlo con el ID
                    }

                    categoriaNivel2Adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.e("API Error", "Error al obtener subcategorías: " + e.getMessage());
                    runOnUiThread(() -> Toast.makeText(DosAPIsMercadonaActivity.this, "Error al obtener subcategorías", Toast.LENGTH_SHORT).show());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        spinnerN2categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Obtener la subcategoría seleccionada y su ID
                String selectedSubCategory = categoriasNivel2List.get(position);
                String subCategoryId = selectedSubCategory.split(" - ")[0]; // Extraer solo el ID

                Log.d("DEBUG", "Subcategoría seleccionada: " + selectedSubCategory);
                Log.d("DEBUG", "ID de subcategoría: " + subCategoryId);

                // Limpiar la lista de productos antes de hacer la petición
                productosList.clear();
                productosAdapter.notifyDataSetChanged();

                // Hacer la petición a la API en un hilo secundario
                new Thread(() -> {
                    try {
                        URL url = new URL("https://tienda.mercadona.es/api/categories/" + subCategoryId);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setConnectTimeout(10000);
                        connection.setReadTimeout(10000);

                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();

                        Log.d("DEBUG", "Respuesta API: " + response.toString());

                        // Parsear la respuesta JSON
                        JSONObject jsonResponse = new JSONObject(response.toString());

                        JSONArray productsArray = null;

                        // Verificar si hay un array de "results" y si dentro está "products"
                        if (jsonResponse.has("categories")) {
                            JSONArray resultsArray = jsonResponse.getJSONArray("categories");

                            for (int i = 0; i < resultsArray.length(); i++) {
                                JSONObject categoryData = resultsArray.getJSONObject(i);
                                if (categoryData.has("products")) {
                                    productsArray = categoryData.getJSONArray("products");
                                    break; // Salimos del bucle al encontrar "products"
                                }
                            }
                        }

                        if (productsArray == null) {
                            Log.d("DEBUG", "No se encontró la clave 'products' en 'results'");
                        } else {
                            if (productsArray.length() == 0) {
                                Log.d("DEBUG", "No hay productos en esta subcategoría");
                            }

                            // Extraer solo "id" y "slug" de cada producto
                            for (int i = 0; i < productsArray.length(); i++) {
                                JSONObject product = productsArray.getJSONObject(i);
                                int productId = product.getInt("id");
                                String productSlug = product.getString("slug");
                                String productInfo = productId + " - " + productSlug;
                                productosList.add(productInfo);
                                Log.d("DEBUG", "Producto agregado: " + productInfo);
                            }
                        }

                        // Notificar cambios en el adaptador en el hilo principal
                        runOnUiThread(() -> {
                            productosAdapter.notifyDataSetChanged();
                            Log.d("DEBUG", "Adaptador de productos actualizado");
                        });

                    } catch (Exception e) {
                        Log.e("API Error", "Error al obtener productos: " + e.getMessage());
                        runOnUiThread(() -> Toast.makeText(DosAPIsMercadonaActivity.this, "Error al obtener productos", Toast.LENGTH_SHORT).show());
                    }
                }).start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No hacer nada
            }
        });



    }

    static class CategoriaConSubcategorias {
        int categoryId;
        String categoryName;
        JSONArray subcategories;

        public CategoriaConSubcategorias(int id, String name, JSONArray subcategories) {
            this.categoryId = id;
            this.categoryName = name;
            this.subcategories = subcategories;
        }
    }
}
