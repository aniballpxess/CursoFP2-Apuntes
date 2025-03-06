package com.example.gestion3d.activities.gestion;

import android.graphics.Typeface;
import android.view.Gravity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gestion3d.R;
import com.example.gestion3d.adapters.MaterialAdapter;
import com.example.gestion3d.database.AppDatabase;
import com.example.gestion3d.database.dao.MaterialDao;
import com.example.gestion3d.database.entities.Material;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.slider.Slider;
import java.util.ArrayList;
import java.util.List;

public class GestionMaterialesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MaterialAdapter materialAdapter;
    private MaterialDao materialDao;
    private ImageButton imgAgregarMaterial;
    private int userRol, idUsuario, idGrupo;
    private ImageButton imgFiltrarMaterial;
    private String filtroTipoSeleccionado = null;
    private EditText edtNombre, edtColor, edtMarca, edtCantidadStock, edtUmbralAlerta, edtMaterialBase, edtTipoResina;
    private String tipoSeleccionado = "Filamento"; // Valor por defecto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_materiales);

        // Inicializar UI
        inicializarUI();

        // Configurar RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cargarMateriales();

        // Configurar eventos de botones
        configurarEventos();

        // Obtener datos del usuario desde SharedPreferences
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        userRol = prefs.getInt("rol", 0);
        idUsuario = prefs.getInt("idUsuario", -1);
        idGrupo = prefs.getInt("idGrupo", -1);

        Log.d("GestionMateriales", "Usuario ID: " + idUsuario + ", Grupo ID: " + idGrupo + ", Rol: " + userRol);

        // Inicializar la base de datos
        materialDao = AppDatabase.getDatabase(this).materialDao();

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewMateriales);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cargarMateriales();

        // Configurar los campos de entrada
        edtNombre = findViewById(R.id.edtNombre);
        edtColor = findViewById(R.id.edtColor);
        edtMarca = findViewById(R.id.edtMarca);
        edtCantidadStock = findViewById(R.id.edtCantidadStock);
        edtUmbralAlerta = findViewById(R.id.edtUmbralAlerta);
        edtMaterialBase = findViewById(R.id.edtMaterialBase);
        edtTipoResina = findViewById(R.id.edtTipoResina);
        imgAgregarMaterial = findViewById(R.id.imgAgregarMaterial);


        // Botón para agregar material
        if (userRol > 0 || idGrupo == -1) {
            imgAgregarMaterial.setVisibility(View.VISIBLE);
            imgAgregarMaterial.setOnClickListener(v -> mostrarPopupAgregarMaterial());
        } else {
            imgAgregarMaterial.setVisibility(View.GONE);
        }

        // Botón para filtrar materiales
        ImageButton imgFiltrarMaterial = findViewById(R.id.imgFiltrarMaterial);
        imgFiltrarMaterial.setOnClickListener(v -> mostrarPopupFiltrarMaterial());
    }

    // Método para configurar eventos de botones
    private void configurarEventos() {
        imgAgregarMaterial.setOnClickListener(v -> Toast.makeText(this, "Agregar Material (Pendiente)", Toast.LENGTH_SHORT).show());
        imgFiltrarMaterial.setOnClickListener(v -> Toast.makeText(this, "Filtrar Material (Pendiente)", Toast.LENGTH_SHORT).show());
    }

    // Método para cargar la lista de materiales
    private void cargarMateriales() {
        List<Material> listaMateriales = materialDao.getMaterialesByUsuario(idUsuario);
        materialAdapter = new MaterialAdapter(this, listaMateriales, null);
        recyclerView.setAdapter(materialAdapter);
    }

    private void inicializarUI() {
        // Obtener datos del usuario desde SharedPreferences
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        userRol = prefs.getInt("rol", 0);
        idUsuario = prefs.getInt("idUsuario", -1);
        idGrupo = prefs.getInt("idGrupo", -1);

        Log.d("GestionMateriales", "Usuario ID: " + idUsuario + ", Grupo ID: " + idGrupo + ", Rol: " + userRol);

        // Inicializar la base de datos
        materialDao = AppDatabase.getDatabase(this).materialDao();

        // Inicializar vistas
        recyclerView = findViewById(R.id.recyclerViewMateriales);
        imgAgregarMaterial = findViewById(R.id.imgAgregarMaterial);
        imgFiltrarMaterial = findViewById(R.id.imgFiltrarMaterial);
    }

    // Método para guardar un material en la base de datos
    private void guardarMaterial() {
        String nombre = edtNombre.getText().toString().trim();
        String color = edtColor.getText().toString().trim();
        String marca = edtMarca.getText().toString().trim();
        int cantidadStock = obtenerEntero(edtCantidadStock);
        int umbralAlerta = obtenerEntero(edtUmbralAlerta);
        String materialBase = tipoSeleccionado.equals("Filamento") ? edtMaterialBase.getText().toString().trim() : null;
        String tipoResina = tipoSeleccionado.equals("Resina") ? edtTipoResina.getText().toString().trim() : null;

        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        int idGrupoUsuario = prefs.getInt("idGrupo", -1);
        int idUsuario = prefs.getInt("idUsuario", -1);

        if (nombre.isEmpty() || color.isEmpty() || marca.isEmpty()) {
            Toast.makeText(this, "Todos los campos deben completarse", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cantidadStock < 0 || umbralAlerta < 0) {
            Toast.makeText(this, "Los valores deben ser positivos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (tipoSeleccionado.equals("Filamento") && (materialBase == null || materialBase.isEmpty())) {
            Toast.makeText(this, "Ingrese el material base", Toast.LENGTH_SHORT).show();
            return;
        }

        if (tipoSeleccionado.equals("Resina") && (tipoResina == null || tipoResina.isEmpty())) {
            Toast.makeText(this, "Ingrese el tipo de resina", Toast.LENGTH_SHORT).show();
            return;
        }

        Material nuevoMaterial = new Material(nombre, tipoSeleccionado, color, marca, cantidadStock, umbralAlerta, idGrupoUsuario, idUsuario);
        materialDao.insert(nuevoMaterial);

        Toast.makeText(this, "Material agregado", Toast.LENGTH_SHORT).show();
        cargarMateriales();
    }

    // Método para cargar la lista de materiales



    // Método auxiliar para convertir valores
    private int obtenerEntero(EditText editText) {
        try {
            return Integer.parseInt(editText.getText().toString().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Método para mostrar un popup y editar un material
    private void mostrarPopupEditarMaterial(Material material) {
        Log.d("GestionMateriales", "Editando material: " + material.getNombre());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_material_editar, null); // Asegurar que el XML existe
        builder.setView(view);
        EditText edtNombre = view.findViewById(R.id.edtNombre);
        EditText edtCantidadStock = view.findViewById(R.id.edtCantidadStock);
        Button btnGuardarCambios = view.findViewById(R.id.btnGuardarCambios);

        edtNombre.setText(material.getNombre());
        edtCantidadStock.setText(String.valueOf(material.getCantidadStock()));

        AlertDialog dialog = builder.create();

        btnGuardarCambios.setOnClickListener(v -> {
            String nuevoNombre = edtNombre.getText().toString().trim();
            int nuevaCantidad;

            try {
                nuevaCantidad = Integer.parseInt(edtCantidadStock.getText().toString().trim());
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Cantidad inválida", Toast.LENGTH_SHORT).show();
                return;
            }

            if (nuevoNombre.isEmpty()) {
                Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
                return;
            }

            material.setNombre(nuevoNombre);
            material.setCantidadStock(nuevaCantidad);
            materialDao.update(material);
            cargarMateriales();

            Toast.makeText(this, "Material actualizado", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.show();
    }


    // Método para mostrar un popup de confirmación para eliminar un material
    private void mostrarPopupEliminarMaterial(Material material) {
        Log.d("GestionMateriales", "Eliminando material: " + material.getNombre());

        if (userRol == 0) {
            Toast.makeText(this, "No tienes permiso para eliminar materiales", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar Material");
        builder.setMessage("¿Estás seguro de que deseas eliminar este material?");

        builder.setPositiveButton("Eliminar", (dialog, which) -> {
            materialDao.delete(material);
            cargarMateriales();
            Toast.makeText(this, "Material eliminado", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }


    // Método para mostrar un popup y agregar un nuevo material
    private void mostrarPopupAgregarMaterial() {
        Log.d("GestionMateriales", "Agregando un nuevo material");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_material_info, null);
        builder.setView(view);

        ChipGroup chipGroupTipo = view.findViewById(R.id.chipGroupTipo);
        Spinner spinnerEspecificacion = view.findViewById(R.id.spinnerEspecificacion);
        EditText inputColor = view.findViewById(R.id.inputColor);
        EditText inputMarca = view.findViewById(R.id.inputMarca);
        Slider sliderStock = view.findViewById(R.id.sliderStock);
        Slider sliderUmbral = view.findViewById(R.id.sliderUmbral);
        TextView txtStock = view.findViewById(R.id.txtStock);
        TextView txtUmbral = view.findViewById(R.id.txtUmbral);

        final String[] tipo = {""};
        String[] opcionesFilamento = {"PLA", "ABS", "PETG", "TPU", "Nylon"};
        String[] opcionesResina = {"UV Standard", "Lavable con Agua", "Flexible", "Alta Resistencia", "Biocompatible"};

        txtStock.setText("Stock: " + (int) sliderStock.getValue());
        txtUmbral.setText("Umbral: " + (int) sliderUmbral.getValue());

        sliderStock.addOnChangeListener((slider, value, fromUser) -> txtStock.setText("Stock: " + (int) value));
        sliderUmbral.addOnChangeListener((slider, value, fromUser) -> txtUmbral.setText("Umbral: " + (int) value));

        chipGroupTipo.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.chipFilamento) {
                tipo[0] = "Filamento";
                actualizarOpcionesSpinner(spinnerEspecificacion, opcionesFilamento);
            } else if (checkedId == R.id.chipResina) {
                tipo[0] = "Resina";
                actualizarOpcionesSpinner(spinnerEspecificacion, opcionesResina);
            }
            spinnerEspecificacion.setVisibility(View.VISIBLE);
        });

        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String especificacion = spinnerEspecificacion.getSelectedItem().toString();
            String color = inputColor.getText().toString().trim();
            String marca = inputMarca.getText().toString().trim();
            int stock = (int) sliderStock.getValue();
            int umbral = (int) sliderUmbral.getValue();

            if (color.isEmpty() || marca.isEmpty() || tipo[0].isEmpty() || especificacion.isEmpty()) {
                Toast.makeText(this, "Todos los campos deben estar llenos", Toast.LENGTH_SHORT).show();
                return;
            }

            int grupoAsignado = (idGrupo == -1) ? 0 : idGrupo;

            Material nuevoMaterial = new Material(especificacion, tipo[0], color, marca, stock, umbral, grupoAsignado, idUsuario);
            materialDao.insert(nuevoMaterial);
            cargarMateriales();

            Toast.makeText(this, "Material agregado", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    // Método para mostrar un popup y filtrar materiales
    private void mostrarPopupFiltrarMaterial() {
        Log.d("GestionMateriales", "Filtrando materiales");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_material_filtro, null);
        builder.setView(view);

        ChipGroup chipGroupFiltroTipo = view.findViewById(R.id.chipGroupFiltroTipo);

        if (filtroTipoSeleccionado != null) {
            if (filtroTipoSeleccionado.equals("Filamento")) {
                chipGroupFiltroTipo.check(R.id.chipFiltroFilamento);
            } else if (filtroTipoSeleccionado.equals("Resina")) {
                chipGroupFiltroTipo.check(R.id.chipFiltroResina);
            }
        }

        builder.setPositiveButton("Aplicar", (dialog, which) -> {
            int selectedChipId = chipGroupFiltroTipo.getCheckedChipId();
            filtroTipoSeleccionado = (selectedChipId == R.id.chipFiltroFilamento) ? "Filamento" :
                    (selectedChipId == R.id.chipFiltroResina) ? "Resina" : null;
            cargarMateriales();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    // Método para actualizar dinámicamente las opciones del Spinner
    private void actualizarOpcionesSpinner(Spinner spinner, String[] opciones) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, opciones);
        spinner.setAdapter(adapter);
    }

    // Método para recargar la lista de materiales al reanudar la actividad
    @Override
    protected void onResume() {
        super.onResume();
        cargarMateriales();
    }
}