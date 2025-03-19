package com.example.gestion3d.activities.gestion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestion3d.R;
import com.example.gestion3d.adapters.ProductoAdapter;
import com.example.gestion3d.database.AppDatabase;
import com.example.gestion3d.database.dao.CategoriaProductoDao;
import com.example.gestion3d.database.dao.ProductoDao;
import com.example.gestion3d.database.entities.CategoriaProducto;
import com.example.gestion3d.database.entities.Producto;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;
import java.util.List;

public class GestionProductosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductoAdapter productoAdapter;
    private ProductoDao productoDao;
    private ImageButton imgAgregarProducto;
    private int userRol, idUsuario, idGrupo;
    private String filtroSeleccionado = null;
    private List<Producto> listaProductos = new ArrayList<>();
    private static final int PICK_IMAGE_REQUEST = 1;
    private Producto productoActual;
    private static final String TAG = "GestionProductos"; // Etiqueta para logs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_productos);

        Log.d(TAG, "onCreate: Iniciando Gestión de Productos");

        // Configuración del RecyclerView
        recyclerView = findViewById(R.id.recyclerViewProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar DAO
        productoDao = AppDatabase.getDatabase(this).productoDao();

        // Obtener datos del usuario desde SharedPreferences
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        userRol = prefs.getInt("rol", 0);
        idUsuario = prefs.getInt("idUsuario", -1);
        idGrupo = prefs.getInt("idGrupo", -1);

        // Referenciar los botones
        imgAgregarProducto = findViewById(R.id.imgAgregarProducto);
        ImageButton imgFiltrarProducto = findViewById(R.id.imgFiltrarProducto);

        Log.d(TAG, "Usuario ID: " + idUsuario + ", Grupo ID: " + idGrupo + ", Rol: " + userRol);

        // Asegurar visibilidad de los botones
        imgAgregarProducto.setVisibility(View.VISIBLE);
        imgFiltrarProducto.setVisibility(View.VISIBLE);

        // Eventos de los botones
        imgAgregarProducto.setOnClickListener(v -> {
            Log.d(TAG, "Botón Agregar Producto presionado");
            mostrarPopupAgregarProducto();
        });

        imgFiltrarProducto.setOnClickListener(v -> {
            Log.d(TAG, "Botón Filtrar Producto presionado");
            mostrarPopupFiltrarProducto();
        });

        // Cargar productos en la lista
        cargarProductos();
    }

    private void seleccionarImagen() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            if (productoActual != null) {
                productoActual.imagenURI = imageUri.toString(); // 🔹 Guardamos la URI en el producto
                productoDao.update(productoActual); // 🔹 Guardamos en la base de datos
                Log.d(TAG, "Imagen agregada al producto: " + imageUri.toString());
            }
        }
    }

    // Cargar productos desde la base de datos
    private void cargarProductos() {
        Log.d(TAG, "Cargando productos...");

        List<Producto> listaProductos = productoDao.getAllProductos();

        // Aplicar filtro si está seleccionado
        if (filtroSeleccionado != null) {
            List<Producto> listaFiltrada = new ArrayList<>();
            for (Producto producto : listaProductos) {
                if (producto.dimensiones.equalsIgnoreCase(filtroSeleccionado)) {
                    listaFiltrada.add(producto);
                }
            }
            listaProductos = listaFiltrada;
        }

        // Configurar adaptador con la lista de productos
        productoAdapter = new ProductoAdapter(this, listaProductos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto producto) {
                Log.d(TAG, "Producto seleccionado: " + producto.nombre);
                mostrarPopupEditarProducto(producto);
            }

            @Override
            public void onEditClick(Producto producto) {
                Log.d(TAG, "Editar producto: " + producto.nombre);
                mostrarPopupEditarProducto(producto);
            }

            @Override
            public void onDeleteClick(Producto producto) {
                Log.d(TAG, "Eliminar producto: " + producto.nombre);
                mostrarPopupEliminarProducto(producto);
            }
        });

        recyclerView.setAdapter(productoAdapter);
    }

    // Mostrar popup para agregar un nuevo producto
    private void mostrarPopupAgregarProducto() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar Nuevo Producto");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_producto_info, null);
        builder.setView(viewInflated);

        EditText inputNombre = viewInflated.findViewById(R.id.inputNombreProducto);
        EditText inputDimensiones = viewInflated.findViewById(R.id.inputDimensionesProducto);
        EditText inputPeso = viewInflated.findViewById(R.id.inputPesoProducto);
        Slider sliderStock = viewInflated.findViewById(R.id.sliderStockProducto);
        TextView txtStock = viewInflated.findViewById(R.id.txtStockProducto);
        TextView txtTiempoImpresion = viewInflated.findViewById(R.id.txtTiempoImpresion);
        NumberPicker numberPickerDias = viewInflated.findViewById(R.id.numberPickerDias);
        NumberPicker numberPickerHoras = viewInflated.findViewById(R.id.numberPickerHoras);
        NumberPicker numberPickerMinutos = viewInflated.findViewById(R.id.numberPickerMinutos);
        ChipGroup chipGroupCategoria = viewInflated.findViewById(R.id.chipGroupCategoriaProducto);
        ImageButton btnAgregarCategoria = viewInflated.findViewById(R.id.btnAgregarCategoria);

        CategoriaProductoDao categoriaDao = AppDatabase.getDatabase(this).categoriaProductoDao();
        List<CategoriaProducto> categorias = categoriaDao.getAllCategorias();
        final String[] categoriaSeleccionada = {""};

        // Cargar categorías dinámicamente en el ChipGroup
        chipGroupCategoria.removeAllViews();
        for (CategoriaProducto categoria : categorias) {
            Chip chip = new Chip(this);
            chip.setText(categoria.nombre_categoria);
            chip.setCheckable(true);
            chip.setId(View.generateViewId());
            chipGroupCategoria.addView(chip);
        }

        chipGroupCategoria.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chipSeleccionado = group.findViewById(checkedId);
            if (chipSeleccionado != null) {
                categoriaSeleccionada[0] = chipSeleccionado.getText().toString();
            }
        });

        // Configurar el botón "+" para agregar categoría
        btnAgregarCategoria.setOnClickListener(v -> mostrarPopupAgregarCategoria(categoriaDao, chipGroupCategoria));

        // Configurar NumberPicker para actualizar el tiempo dinámicamente
        numberPickerDias.setMinValue(0);
        numberPickerDias.setMaxValue(3);
        numberPickerHoras.setMinValue(0);
        numberPickerHoras.setMaxValue(23);
        numberPickerMinutos.setMinValue(0);
        numberPickerMinutos.setMaxValue(59);

        numberPickerDias.setOnValueChangedListener((picker, oldVal, newVal) -> actualizarTiempoImpresion(numberPickerDias, numberPickerHoras, numberPickerMinutos, txtTiempoImpresion));
        numberPickerHoras.setOnValueChangedListener((picker, oldVal, newVal) -> actualizarTiempoImpresion(numberPickerDias, numberPickerHoras, numberPickerMinutos, txtTiempoImpresion));
        numberPickerMinutos.setOnValueChangedListener((picker, oldVal, newVal) -> actualizarTiempoImpresion(numberPickerDias, numberPickerHoras, numberPickerMinutos, txtTiempoImpresion));

        // Configurar el slider de stock
        sliderStock.addOnChangeListener((slider, value, fromUser) -> {
            txtStock.setText("Cantidad de Stock: " + (int) value);
        });

        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String nombre = inputNombre.getText().toString().trim();
            String dimensiones = inputDimensiones.getText().toString().trim();
            float peso = Float.parseFloat(inputPeso.getText().toString());
            int stock = (int) sliderStock.getValue();

            int dias = numberPickerDias.getValue();
            int horas = numberPickerHoras.getValue();
            int minutos = numberPickerMinutos.getValue();
            int tiempoTotalMinutos = (dias * 1440) + (horas * 60) + minutos;

            Log.d("DEBUG_PRODUCTO", "Nombre: " + nombre);
            Log.d("DEBUG_PRODUCTO", "Dimensiones: " + dimensiones);
            Log.d("DEBUG_PRODUCTO", "Peso: " + peso);
            Log.d("DEBUG_PRODUCTO", "Stock: " + stock);
            Log.d("DEBUG_PRODUCTO", "Tiempo Impresión (min): " + tiempoTotalMinutos);
            Log.d("DEBUG_PRODUCTO", "Categoría seleccionada: " + categoriaSeleccionada[0]);

            if (nombre.isEmpty() || dimensiones.isEmpty() || categoriaSeleccionada[0].isEmpty()) {
                Toast.makeText(this, "Todos los campos deben estar llenos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (categoriaSeleccionada[0].isEmpty()) {
                Toast.makeText(this, "Debes seleccionar una categoría", Toast.LENGTH_SHORT).show();
                return;
            }



            Producto nuevoProducto = new Producto(nombre, dimensiones, peso, tiempoTotalMinutos, stock, categoriaSeleccionada[0]);
            productoDao.insert(nuevoProducto);
            cargarProductos();
            Toast.makeText(this, "Producto agregado con éxito", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Producto agregado: " + nombre);
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();

        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String nombre = inputNombre.getText().toString().trim();
            String dimensiones = inputDimensiones.getText().toString().trim();
            float peso = Float.parseFloat(inputPeso.getText().toString());
            int stock = (int) sliderStock.getValue();

            int dias = numberPickerDias.getValue();
            int horas = numberPickerHoras.getValue();
            int minutos = numberPickerMinutos.getValue();
            int tiempoTotalMinutos = (dias * 1440) + (horas * 60) + minutos;

            if (nombre.isEmpty() || dimensiones.isEmpty() || categoriaSeleccionada[0].isEmpty()) {
                Toast.makeText(this, "Todos los campos deben estar llenos", Toast.LENGTH_SHORT).show();
                return;
            }

            productoActual = new Producto(nombre, dimensiones, peso, tiempoTotalMinutos, stock, categoriaSeleccionada[0]);
            long idProducto = productoDao.insert(productoActual);
            productoActual.id_producto = (int) idProducto;


            cargarProductos();
            Toast.makeText(this, "Producto agregado con éxito", Toast.LENGTH_SHORT).show();

            // 🔹 Mostrar opción para agregar imagen
            new AlertDialog.Builder(this)
                    .setTitle("Imagen del Producto")
                    .setMessage("¿Quieres agregar una imagen a este producto?")
                    .setPositiveButton("Sí", (dialog2, which2) -> seleccionarImagen())
                    .setNegativeButton("No", (dialog2, which2) -> dialog2.dismiss())
                    .show();
        });
    }

    // Método para mostrar el popup de agregar categoría
    private void mostrarPopupAgregarCategoria(CategoriaProductoDao categoriaDao, ChipGroup chipGroup) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nueva Categoría");

        EditText inputCategoria = new EditText(this);
        inputCategoria.setHint("Nombre de la categoría");
        builder.setView(inputCategoria);

        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String nombreCategoria = inputCategoria.getText().toString().trim();
            if (nombreCategoria.isEmpty()) {
                Toast.makeText(this, "El nombre de la categoría no puede estar vacío", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Intento de agregar categoría vacía");
                return;
            }

            // Insertar en la base de datos
            CategoriaProducto nuevaCategoria = new CategoriaProducto(nombreCategoria);
            categoriaDao.insert(nuevaCategoria);
            Log.d(TAG, "Categoría agregada: " + nombreCategoria);

            // Agregar al ChipGroup dinámicamente
            Chip chip = new Chip(this);
            chip.setText(nombreCategoria);
            chip.setCheckable(true);
            chip.setId(View.generateViewId());
            chipGroup.addView(chip);
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    // Método para mostrar el popup de edición de un producto (Solo edición de stock)
    private void mostrarPopupEditarProducto(Producto producto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Editar Producto");

        // Inflar la vista del popup desde el XML
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_producto_editar, null);
        builder.setView(viewInflated);

        // Referencias a los elementos de la UI
        TextView txtNombreProducto = viewInflated.findViewById(R.id.txtNombreProducto);
        TextView txtCantidadStockProducto = viewInflated.findViewById(R.id.txtCantidadStockProducto);
        Button btnRestarStock = viewInflated.findViewById(R.id.btnRestarStockProducto);
        Button btnSumarStock = viewInflated.findViewById(R.id.btnSumarStockProducto);

        // Cargar datos actuales
        txtNombreProducto.setText(producto.nombre);
        txtCantidadStockProducto.setText(String.valueOf(producto.cantidad_stock));

        // Botón Restar Stock
        btnRestarStock.setOnClickListener(v -> {
            int cantidad = Integer.parseInt(txtCantidadStockProducto.getText().toString());
            if (cantidad > 0) {
                cantidad--;
                txtCantidadStockProducto.setText(String.valueOf(cantidad));
            } else {
                Toast.makeText(this, "El stock no puede ser negativo", Toast.LENGTH_SHORT).show();
            }
        });

        // Botón Sumar Stock
        btnSumarStock.setOnClickListener(v -> {
            int cantidad = Integer.parseInt(txtCantidadStockProducto.getText().toString());
            cantidad++;
            txtCantidadStockProducto.setText(String.valueOf(cantidad));
        });

        // Botón de Guardar
        builder.setPositiveButton("Guardar", (dialog, which) -> {
            int nuevaCantidad = Integer.parseInt(txtCantidadStockProducto.getText().toString());
            producto.cantidad_stock = nuevaCantidad;
            productoDao.update(producto);
            cargarProductos();
            Toast.makeText(this, "Stock actualizado", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    // Método para mostrar el popup de eliminación de un producto
    private void mostrarPopupEliminarProducto(Producto producto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar Producto");
        builder.setMessage("¿Estás seguro de que deseas eliminar este producto?");
        builder.setPositiveButton("Eliminar", (dialog, which) -> {
            productoDao.delete(producto);
            cargarProductos();
            Toast.makeText(this, "Producto eliminado", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Producto eliminado: " + producto.nombre);
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    // Método para mostrar el popup de filtrado de productos
    private void mostrarPopupFiltrarProducto() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Filtrar Productos");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_producto_filtro, null);
        builder.setView(viewInflated);

        ChipGroup chipGroupFiltroCategorias = viewInflated.findViewById(R.id.chipGroupFiltroCategorias);

        CategoriaProductoDao categoriaDao = AppDatabase.getDatabase(this).categoriaProductoDao();
        List<CategoriaProducto> categorias = categoriaDao.getAllCategorias();

        final String[] categoriaSeleccionada = {null};

        // Cargar categorías en el ChipGroup dinámicamente
        chipGroupFiltroCategorias.removeAllViews();
        for (CategoriaProducto categoria : categorias) {
            Chip chip = new Chip(this);
            chip.setText(categoria.nombre_categoria);
            chip.setCheckable(true);
            chip.setId(View.generateViewId());
            chipGroupFiltroCategorias.addView(chip);
        }

        // Manejar la selección de una categoría
        chipGroupFiltroCategorias.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chipSeleccionado = group.findViewById(checkedId);
            if (chipSeleccionado != null) {
                categoriaSeleccionada[0] = chipSeleccionado.getText().toString();
                Log.d(TAG, "Categoría filtrada seleccionada: " + categoriaSeleccionada[0]);
            } else {
                categoriaSeleccionada[0] = null; // Si se deselecciona, no aplicar filtro
            }
        });

        builder.setPositiveButton("Aplicar", (dialog, which) -> {
            List<Producto> listaFiltrada;

            if (categoriaSeleccionada[0] != null) {
                Log.d(TAG, "Aplicando filtro: Solo Categoría");
                listaFiltrada = productoDao.buscarPorCategoria(categoriaSeleccionada[0]);
            } else {
                Log.d(TAG, "Sin filtro aplicado, mostrando todos los productos");
                listaFiltrada = productoDao.getAllProductos();
            }

            Log.d(TAG, "Total productos filtrados: " + listaFiltrada.size());

            productoAdapter.actualizarLista(listaFiltrada);
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    // Método para actualizar el tiempo de impresión dinámicamente
    private void actualizarTiempoImpresion(NumberPicker dias, NumberPicker horas, NumberPicker minutos, TextView txtTiempo) {
        int totalMinutos = (dias.getValue() * 1440) + (horas.getValue() * 60) + minutos.getValue();

        Log.d(TAG, "Actualizando tiempo de impresión: " + totalMinutos + " minutos");

        if (totalMinutos == 0) {
            txtTiempo.setText("Tiempo de impresión: No definido");
        } else {
            txtTiempo.setText("Tiempo de impresión: " + formatearTiempo(totalMinutos));
        }
    }

    // Método para formatear minutos en Días, Horas y Minutos
    private String formatearTiempo(int minutos) {
        int dias = minutos / 1440;
        int horas = (minutos % 1440) / 60;
        int min = minutos % 60;

        Log.d(TAG, "Formateando tiempo: " + minutos + " minutos -> " + dias + " días, " + horas + " horas, " + min + " minutos");

        if (dias > 0) {
            return dias + " días, " + horas + " h, " + min + " min";
        } else if (horas > 0) {
            return horas + " h, " + min + " min";
        } else {
            return min + " min";
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Reanudando actividad, recargando productos");
        cargarProductos();
    }
}
