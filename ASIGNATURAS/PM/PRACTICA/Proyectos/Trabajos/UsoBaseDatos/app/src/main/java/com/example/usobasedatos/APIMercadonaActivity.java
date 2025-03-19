package com.example.usobasedatos;

import android.os.Bundle;
import android.util.Log; // Import para usar los logs

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usobasedatos.interfaces.CategoriaDeProductoAPI;
import com.example.usobasedatos.model.Categoria;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIMercadonaActivity extends AppCompatActivity {
    // Declaración de componentes de la interfaz de usuario
    private EditText txtCodigoCat;
    private TextView txtNombreCat;
    private Button btnBuscarCat;

    // Constante para la etiqueta de los logs
    private static final String LOG_TAG = "APIM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_apimercadona);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mencabrona), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtCodigoCat = findViewById(R.id.IdIntroCodigo);
        txtNombreCat = findViewById(R.id.IdNombreCategoria);
        btnBuscarCat = findViewById(R.id.btnBuscar);

        btnBuscarCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = txtCodigoCat.getText().toString().trim();
                if (codigo.isEmpty()) {
                    Toast.makeText(APIMercadonaActivity.this, "Por favor, introduce un código válido.", Toast.LENGTH_SHORT).show();
                    Log.w(LOG_TAG, "El campo de código está vacío.");
                } else {
                    find(codigo);
                }
            }
        });
    }

    private void find(String codigo) {
        Log.i(LOG_TAG, "Iniciando búsqueda para el código: " + codigo);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tienda.mercadona.es/api/v1_1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoriaDeProductoAPI productoAPI = retrofit.create(CategoriaDeProductoAPI.class);
        Call<Categoria> call = productoAPI.find(Integer.parseInt(codigo));

        call.enqueue(new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                Log.d(LOG_TAG, "Respuesta recibida: Código HTTP " + response.code());

                try {
                    if (response.isSuccessful()) {
                        Categoria c = response.body();
                        if (c != null) {
                            txtNombreCat.setText(c.getName());
                            Log.i(LOG_TAG, "Categoría encontrada: " + c.getName());
                        } else {
                            txtNombreCat.setText("Sin datos disponibles");
                            Log.w(LOG_TAG, "La respuesta es exitosa, pero el cuerpo está vacío.");
                        }
                    } else {
                        txtNombreCat.setText("Error: " + response.message());
                        Log.e(LOG_TAG, "Error en la respuesta: " + response.errorBody().toString());
                    }
                } catch (Exception ex) {
                    Toast.makeText(APIMercadonaActivity.this, "Error al procesar la respuesta", Toast.LENGTH_SHORT).show();
                    Log.e(LOG_TAG, "Excepción al procesar la respuesta: " + ex.getMessage(), ex);
                }
            }

            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {
                Toast.makeText(APIMercadonaActivity.this, "Error de conexión. Inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
                Log.e(LOG_TAG, "Fallo en la conexión: " + t.getMessage(), t);
            }
        });
    }
}
