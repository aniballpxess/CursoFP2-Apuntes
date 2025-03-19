package com.example.gestion3d.activities.gestion;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;
import com.example.gestion3d.database.dao.ClienteDao;
import com.example.gestion3d.database.dao.ProductoDao;
import com.example.gestion3d.database.entities.Cliente;
import com.example.gestion3d.database.entities.Producto;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestion3d.R;
import com.example.gestion3d.adapters.OrdenTrabajoAdapter;
import com.example.gestion3d.database.AppDatabase;
import com.example.gestion3d.database.dao.OrdenTrabajoDao;
import com.example.gestion3d.database.entities.OrdenTrabajo;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;

import java.util.Calendar;
import java.util.List;

public class GestionOrdenesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrdenTrabajoAdapter ordenAdapter;
    private OrdenTrabajoDao ordenDao;
    private static final String TAG = "GestionOrdenes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_ordenes);

        Log.d(TAG, "Iniciando Gestión de Órdenes...");

        // Configuración RecyclerView
        recyclerView = findViewById(R.id.recyclerViewOrdenes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar DAO
        ordenDao = AppDatabase.getDatabase(this).ordenTrabajoDao();

        // Referenciar botones
        ImageButton imgAgregarOrden = findViewById(R.id.imgAgregarOrden);
        ImageButton imgFiltrarOrden = findViewById(R.id.imgFiltrarOrden);

        // Eventos de los botones
        imgAgregarOrden.setOnClickListener(v -> {
            Log.d(TAG, "Botón Agregar Orden presionado");
            mostrarPopupAgregarOrden();
        });

        imgFiltrarOrden.setOnClickListener(v -> {
            Log.d(TAG, "Botón Filtrar Orden presionado");
            mostrarPopupFiltrarOrden();
        });

        // Cargar órdenes en la lista
        cargarOrdenes();
    }

    private void cargarOrdenes() {
        Log.d(TAG, "Cargando lista de órdenes...");

        List<OrdenTrabajo> listaOrdenes = ordenDao.getAllOrdenes();

        // Se agrega el tercer parámetro (OnItemClickListener)
        ordenAdapter = new OrdenTrabajoAdapter(this, listaOrdenes, new OrdenTrabajoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(OrdenTrabajo orden) {
                mostrarPopupEditarOrden(orden);
            }

            @Override
            public void onDeleteClick(OrdenTrabajo orden) {
                mostrarPopupEliminarOrden(orden);
            }
        });

        recyclerView.setAdapter(ordenAdapter);
    }


    private void mostrarPopupAgregarOrden() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar Nueva Orden");

        // Inflar el layout del diálogo
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_orden_info, null);
        builder.setView(viewInflated);

        // Referencias UI
        ChipGroup chipGroupClientes = viewInflated.findViewById(R.id.chipGroupClientes);
        ChipGroup chipGroupProductos = viewInflated.findViewById(R.id.chipGroupProductos);
        Spinner spinnerEstado = viewInflated.findViewById(R.id.spinnerEstado);
        Button btnSeleccionarFecha = viewInflated.findViewById(R.id.btnSeleccionarFecha);
        EditText inputCostoEstimado = viewInflated.findViewById(R.id.inputCostoEstimado);

        // 🔹 Configurar opciones del Spinner de estado
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.estados_orden,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapter);

        // 🔹 Cargar clientes en chips
        ClienteDao clienteDao = AppDatabase.getDatabase(this).clienteDao();
        List<Cliente> listaClientes = clienteDao.getAllClientes();
        final int[] idClienteSeleccionado = {-1};

        for (Cliente cliente : listaClientes) {
            Chip chip = new Chip(this);
            chip.setText(cliente.nombre);
            chip.setCheckable(true);
            chip.setId(View.generateViewId());
            chipGroupClientes.addView(chip);
        }

        chipGroupClientes.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chipSeleccionado = group.findViewById(checkedId);
            if (chipSeleccionado != null) {
                for (Cliente cliente : listaClientes) {
                    if (cliente.nombre.equals(chipSeleccionado.getText().toString())) {
                        idClienteSeleccionado[0] = cliente.id_cliente;
                        break;
                    }
                }
            }
        });

        // 🔹 Cargar productos en chips
        ProductoDao productoDao = AppDatabase.getDatabase(this).productoDao();
        List<Producto> listaProductos = productoDao.getAllProductos();
        final StringBuilder productosSeleccionados = new StringBuilder();

        for (Producto producto : listaProductos) {
            Chip chip = new Chip(this);
            chip.setText(producto.nombre);
            chip.setCheckable(true);
            chip.setId(producto.id_producto);
            chipGroupProductos.addView(chip);
        }

        chipGroupProductos.setOnCheckedChangeListener((group, checkedId) -> {
            productosSeleccionados.setLength(0);
            for (int i = 0; i < chipGroupProductos.getChildCount(); i++) {
                Chip chip = (Chip) chipGroupProductos.getChildAt(i);
                if (chip.isChecked()) {
                    if (productosSeleccionados.length() > 0) {
                        productosSeleccionados.append(",");
                    }
                    productosSeleccionados.append(chip.getId());
                }
            }
        });

        // Selección de fecha con DatePicker
        btnSeleccionarFecha.setOnClickListener(v -> {
            Calendar calendario = Calendar.getInstance();
            DatePickerDialog datePicker = new DatePickerDialog(
                    this,
                    (view, year, month, dayOfMonth) -> {
                        String fechaSeleccionada = dayOfMonth + "/" + (month + 1) + "/" + year;
                        btnSeleccionarFecha.setText(fechaSeleccionada);
                    },
                    calendario.get(Calendar.YEAR),
                    calendario.get(Calendar.MONTH),
                    calendario.get(Calendar.DAY_OF_MONTH)
            );
            datePicker.show();
        });

        builder.setPositiveButton("Aceptar", (dialog, which) -> {
            // 🔹 Verificar cliente seleccionado
            if (idClienteSeleccionado[0] == -1) {
                Toast.makeText(this, "Debes seleccionar un cliente", Toast.LENGTH_SHORT).show();
                return;
            }

            // 🔹 Obtener estado seleccionado
            String estadoSeleccionado = spinnerEstado.getSelectedItem().toString();

            // 🔹 Obtener datos ingresados
            String fechaEntrega = btnSeleccionarFecha.getText().toString();
            String costoText = inputCostoEstimado.getText().toString();
            float costoEstimado = costoText.isEmpty() ? 0 : Float.parseFloat(costoText);

            // 🔹 Crear nueva orden
            OrdenTrabajo nuevaOrden = new OrdenTrabajo();
            nuevaOrden.id_cliente = idClienteSeleccionado[0];
            nuevaOrden.estado = estadoSeleccionado;
            nuevaOrden.fecha_creacion = obtenerFechaActual();
            nuevaOrden.fecha_entrega = fechaEntrega;
            nuevaOrden.costo_estimado = costoEstimado;
            nuevaOrden.ids_productos = productosSeleccionados.toString();

            // 🔹 Guardar en la base de datos
            ordenDao.insert(nuevaOrden);
            cargarOrdenes();
            Toast.makeText(this, "Orden agregada con éxito", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    // Método para obtener la fecha actual en formato dd/MM/yyyy
    private String obtenerFechaActual() {
        Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH) + 1;
        int anio = calendario.get(Calendar.YEAR);
        return dia + "/" + mes + "/" + anio;
    }

    private void mostrarPopupEditarOrden(OrdenTrabajo orden) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Editar Orden");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_orden_info, null);
        builder.setView(viewInflated);

        Spinner spinnerEstado = viewInflated.findViewById(R.id.spinnerEstado);
        Button btnSeleccionarFecha = viewInflated.findViewById(R.id.btnSeleccionarFecha);
        EditText inputCostoEstimado = viewInflated.findViewById(R.id.inputCostoEstimado);

        // 🔹 Configurar opciones del Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.estados_orden,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapter);

        // 🔹 Preseleccionar estado
        int posicionEstado = adapter.getPosition(orden.estado);
        spinnerEstado.setSelection(posicionEstado);

        // 🔹 Mostrar la fecha actual de entrega
        btnSeleccionarFecha.setText(orden.fecha_entrega);

        // 🔹 Cargar costo actual
        inputCostoEstimado.setText(String.valueOf(orden.costo_estimado));

        // 🔹 Selección de fecha
        btnSeleccionarFecha.setOnClickListener(v -> {
            Calendar calendario = Calendar.getInstance();
            DatePickerDialog datePicker = new DatePickerDialog(
                    this,
                    (view, year, month, dayOfMonth) -> {
                        String fechaSeleccionada = dayOfMonth + "/" + (month + 1) + "/" + year;
                        btnSeleccionarFecha.setText(fechaSeleccionada);
                        orden.fecha_entrega = fechaSeleccionada;
                    },
                    calendario.get(Calendar.YEAR),
                    calendario.get(Calendar.MONTH),
                    calendario.get(Calendar.DAY_OF_MONTH)
            );
            datePicker.show();
        });

        builder.setPositiveButton("Guardar", (dialog, which) -> {
            orden.estado = spinnerEstado.getSelectedItem().toString();
            orden.costo_estimado = Float.parseFloat(inputCostoEstimado.getText().toString());

            ordenDao.update(orden);
            cargarOrdenes();
            Toast.makeText(this, "Orden actualizada", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void mostrarPopupFiltrarOrden() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Filtrar Órdenes");

        // Inflar el layout del diálogo
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_filtro_ordenes, null);
        builder.setView(viewInflated);

        // Referencias UI
        Spinner spinnerEstado = viewInflated.findViewById(R.id.spinnerEstadoFiltro);
        ChipGroup chipGroupClientes = viewInflated.findViewById(R.id.chipGroupClientesFiltro);
        Button btnFechaInicio = viewInflated.findViewById(R.id.btnFechaInicio);
        Button btnFechaFin = viewInflated.findViewById(R.id.btnFechaFin);

        // Configurar Spinner de estados (usamos estados_orden_filtro)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.estados_orden_filtro, // Usamos el nuevo array
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapter);

        // Cargar clientes en chips
        ClienteDao clienteDao = AppDatabase.getDatabase(this).clienteDao();
        List<Cliente> listaClientes = clienteDao.getAllClientes();
        final int[] idClienteSeleccionado = {-1};

        for (Cliente cliente : listaClientes) {
            Chip chip = new Chip(this);
            chip.setText(cliente.nombre);
            chip.setCheckable(true);
            chip.setId(View.generateViewId());
            chipGroupClientes.addView(chip);
        }

        chipGroupClientes.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chipSeleccionado = group.findViewById(checkedId);
            if (chipSeleccionado != null) {
                for (Cliente cliente : listaClientes) {
                    if (cliente.nombre.equals(chipSeleccionado.getText().toString())) {
                        idClienteSeleccionado[0] = cliente.id_cliente;
                        break;
                    }
                }
            }
        });

        // Variables para fechas seleccionadas
        final String[] fechaInicio = {""};
        final String[] fechaFin = {""};

        // Selección de fecha de inicio
        btnFechaInicio.setOnClickListener(v -> {
            Calendar calendario = Calendar.getInstance();
            DatePickerDialog datePicker = new DatePickerDialog(
                    this,
                    (view, year, month, dayOfMonth) -> {
                        fechaInicio[0] = dayOfMonth + "/" + (month + 1) + "/" + year;
                        btnFechaInicio.setText(fechaInicio[0]);
                    },
                    calendario.get(Calendar.YEAR),
                    calendario.get(Calendar.MONTH),
                    calendario.get(Calendar.DAY_OF_MONTH)
            );
            datePicker.show();
        });

        // Selección de fecha de fin
        btnFechaFin.setOnClickListener(v -> {
            Calendar calendario = Calendar.getInstance();
            DatePickerDialog datePicker = new DatePickerDialog(
                    this,
                    (view, year, month, dayOfMonth) -> {
                        fechaFin[0] = dayOfMonth + "/" + (month + 1) + "/" + year;
                        btnFechaFin.setText(fechaFin[0]);
                    },
                    calendario.get(Calendar.YEAR),
                    calendario.get(Calendar.MONTH),
                    calendario.get(Calendar.DAY_OF_MONTH)
            );
            datePicker.show();
        });

        builder.setPositiveButton("Aplicar", (dialog, which) -> {
            // Obtener valores seleccionados
            String estadoSeleccionado = spinnerEstado.getSelectedItem().toString();
            boolean filtrarEstado = !estadoSeleccionado.equals("Todos");

            boolean filtrarCliente = (idClienteSeleccionado[0] != -1);
            boolean filtrarFecha = !fechaInicio[0].isEmpty() && !fechaFin[0].isEmpty();

            // Filtrar órdenes
            List<OrdenTrabajo> ordenesFiltradas;

            if (filtrarEstado && filtrarCliente && filtrarFecha) {
                ordenesFiltradas = ordenDao.getOrdenesPorEstado(estadoSeleccionado);
                ordenesFiltradas.retainAll(ordenDao.getOrdenesPorCliente(idClienteSeleccionado[0]));
                ordenesFiltradas.retainAll(ordenDao.getOrdenesPorRangoFechas(fechaInicio[0], fechaFin[0]));
            } else if (filtrarEstado && filtrarCliente) {
                ordenesFiltradas = ordenDao.getOrdenesPorEstado(estadoSeleccionado);
                ordenesFiltradas.retainAll(ordenDao.getOrdenesPorCliente(idClienteSeleccionado[0]));
            } else if (filtrarEstado) {
                ordenesFiltradas = ordenDao.getOrdenesPorEstado(estadoSeleccionado);
            } else if (filtrarCliente) {
                ordenesFiltradas = ordenDao.getOrdenesPorCliente(idClienteSeleccionado[0]);
            } else if (filtrarFecha) {
                ordenesFiltradas = ordenDao.getOrdenesPorRangoFechas(fechaInicio[0], fechaFin[0]);
            } else {
                ordenesFiltradas = ordenDao.getAllOrdenes();
            }

            // Actualizar la lista con los filtros aplicados
            ordenAdapter.actualizarLista(ordenesFiltradas);
            Toast.makeText(this, "Filtros aplicados", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }


    private void mostrarPopupEliminarOrden(OrdenTrabajo orden) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar Orden");
        builder.setMessage("¿Estás seguro de que deseas eliminar esta orden?");

        builder.setPositiveButton("Eliminar", (dialog, which) -> {
            ordenDao.delete(orden);
            cargarOrdenes();
            Toast.makeText(this, "Orden eliminada", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        cargarOrdenes();
    }
}
