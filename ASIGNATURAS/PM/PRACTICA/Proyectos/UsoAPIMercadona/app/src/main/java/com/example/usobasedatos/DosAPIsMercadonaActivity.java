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
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class DosAPIsMercadonaActivity extends AppCompatActivity {

    private Spinner spin_categories;
    private Spinner spin_subcategories;
    private ListView lv_products;

    private ArrayAdapter<String> category_adp;
    private ArrayAdapter<String> subcategory_adp;
    private ArrayAdapter<String> product_adp;

    private List<String> view_categories;
    private List<String> view_subcategories;
    private List<String> view_products;

    private List<JSONArray> subcategories_byCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos_apis);
        // Activar el modo de diseño de bordes a bordes
        EdgeToEdge.enable(this);

        spin_categories = findViewById(R.id.spinnerCategories);
        spin_subcategories = findViewById(R.id.spinnerSubcategories);
        lv_products = findViewById(R.id.listViewProducts);

        view_categories = new ArrayList<>();
        view_subcategories = new ArrayList<>();
        view_products = new ArrayList<>();
        subcategories_byCategory = new ArrayList<>();

        category_adp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, view_categories);
        category_adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_categories.setAdapter(category_adp);

        subcategory_adp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, view_subcategories);
        subcategory_adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_subcategories.setAdapter(subcategory_adp);

        product_adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, view_products);
        lv_products.setAdapter(product_adp);

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
                JSONObject responseNode = new JSONObject(response.toString());

                JSONArray resultsNodeArray = responseNode.getJSONArray("results");
                List<ProductCategory> tmp_categories = new ArrayList<>();
                for (int i = 0; i < resultsNodeArray.length(); i++) {
                    JSONObject jsonCategory = resultsNodeArray.getJSONObject(i);
                    String categoryName = jsonCategory.getString("name");
                    int categoryId = jsonCategory.getInt("id");
                    JSONArray jsonSubcategories = jsonCategory.getJSONArray("categories");

                    tmp_categories.add(new ProductCategory(categoryId, categoryName, jsonSubcategories));
                }
                tmp_categories.sort(Comparator.comparingInt(c -> c.categoryId));

                view_categories.clear();
                subcategories_byCategory.clear();
                for (ProductCategory categoria : tmp_categories) {
                    view_categories.add(categoria.categoryId + " - " + categoria.categoryName);
                    subcategories_byCategory.add(categoria.subcategories);
                }

                runOnUiThread(() -> category_adp.notifyDataSetChanged());
            } catch (Exception e) {
                Log.e("API Error", "Error al obtener categorías: " + e.getMessage());
                runOnUiThread(() -> Toast.makeText(DosAPIsMercadonaActivity.this, "Error al obtener categorías", Toast.LENGTH_SHORT).show());
            }
        }).start();

        spin_categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                view_subcategories.clear();
                try {
                    JSONArray subCategories = subcategories_byCategory.get(position);
                    for (int i = 0; i < subCategories.length(); i++) {
                        JSONObject subCategory = subCategories.getJSONObject(i);
                        int subCategoryId = subCategory.getInt("id");
                        String subCategoryName = subCategory.getString("name");
                        view_subcategories.add(subCategoryId + " - " + subCategoryName);
                    }

                    subcategory_adp.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.e("API Error", "Error al obtener subcategorías: " + e.getMessage());
                    runOnUiThread(() -> Toast.makeText(DosAPIsMercadonaActivity.this, "Error al obtener subcategorías", Toast.LENGTH_SHORT).show());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        spin_subcategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedSubcategory = view_subcategories.get(position);
                String subcategoryId = selectedSubcategory.split(" - ")[0];
                Log.d("DEBUG", "Subcategoría seleccionada: " + selectedSubcategory);
                Log.d("DEBUG", "ID de subcategoría: " + subcategoryId);

                view_products.clear();
                product_adp.notifyDataSetChanged();

                new Thread(() -> {
                    try {
                        URL url = new URL("https://tienda.mercadona.es/api/categories/" + subcategoryId);
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
                        Log.d("DEBUG", "Respuesta API: " + response);
                        JSONObject responseNode = new JSONObject(response.toString());

                        // Verificación de la respuesta de la llamada a la API
                        JSONArray tmp_products = null;
                        if (responseNode.has("categories")) {
                            JSONArray resultsNodeArray = responseNode.getJSONArray("categories");
                            for (int i = 0; i < resultsNodeArray.length(); i++) {
                                JSONObject categoryData = resultsNodeArray.getJSONObject(i);
                                if (categoryData.has("products")) {
                                    tmp_products = categoryData.getJSONArray("products");
                                    break;
                                }
                            }
                        }
                        if (tmp_products == null) {
                            Log.d("DEBUG", "No se encontró la clave 'products' en 'results'");
                            return;
                        }
                        if (tmp_products.length() == 0) {
                            Log.d("DEBUG", "No hay productos en esta subcategoría");
                            return;
                        }

                        for (int i = 0; i < tmp_products.length(); i++) {
                            JSONObject product = tmp_products.getJSONObject(i);
                            int productId = product.getInt("id");
                            String productSlug = product.getString("slug");
                            String productInfo = productId + " - " + productSlug;
                            view_products.add(productInfo);
                            Log.d("DEBUG", "Producto agregado: " + productInfo);
                        }

                        runOnUiThread(() -> {
                            product_adp.notifyDataSetChanged();
                            Log.d("DEBUG", "Adaptador de productos actualizado");
                        });
                    } catch (Exception e) {
                        Log.e("API Error", "Error al obtener productos: " + e.getMessage());
                        runOnUiThread(() -> Toast.makeText(DosAPIsMercadonaActivity.this, "Error al obtener productos", Toast.LENGTH_SHORT).show());
                    }
                }).start();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });
    }

    static class ProductCategory {
        int categoryId;
        String categoryName;
        JSONArray subcategories;

        public ProductCategory(int id, String name, JSONArray subcategories) {
            this.categoryId = id;
            this.categoryName = name;
            this.subcategories = subcategories;
        }
    }
}
