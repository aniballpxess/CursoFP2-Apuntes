package com.example.gestion3d.activities.gestion;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gestion3d.R;
import com.example.gestion3d.adapters.ClienteAdapter;
import com.example.gestion3d.database.AppDatabase;
import com.example.gestion3d.database.dao.ClienteDao;
import com.example.gestion3d.database.entities.Cliente;
import java.util.List;

public class GestionClientesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ClienteAdapter clienteAdapter;
    private ClienteDao clienteDao;
    private ImageButton imgAgregarCliente;
    private EditText inputBuscarCliente; // 🔹 Campo de búsqueda
    private List<Cliente> listaClientes;
    private static final String TAG = "GestionClientes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_clientes);

        // Configuración RecyclerView
        recyclerView = findViewById(R.id.recyclerViewClientes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar DAO
        clienteDao = AppDatabase.getDatabase(this).clienteDao();

        // Referenciar botones
        imgAgregarCliente = findViewById(R.id.imgAgregarCliente);
        ImageButton imgFiltrarCliente = findViewById(R.id.imgFiltrarCliente);

        imgAgregarCliente.setVisibility(View.VISIBLE);
        imgFiltrarCliente.setVisibility(View.VISIBLE);

        imgAgregarCliente.setOnClickListener(v -> mostrarPopupAgregarCliente());
        imgFiltrarCliente.setOnClickListener(v -> mostrarPopupFiltrarCliente());

        // Cargar clientes
        cargarClientes();
    }

    // Método para filtrar clientes
    private void filtrarClientes(String query) {
        List<Cliente> listaFiltrada = clienteDao.buscarPorNombre(query);
        if (clienteAdapter != null) {
            clienteAdapter.actualizarLista(listaFiltrada);
        }
    }

    // Método para mostrar el popup de búsqueda de clientes
    private void mostrarPopupFiltrarCliente() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Buscar Cliente");

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_cliente_filtro, null);
        builder.setView(view);

        EditText inputBuscar = view.findViewById(R.id.inputBuscarCliente);

        builder.setPositiveButton("Buscar", (dialog, which) -> {
            String filtro = inputBuscar.getText().toString().trim();
            filtrarClientes(filtro);
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void cargarClientes() {
        Log.d(TAG, "Cargando lista de clientes...");
        List<Cliente> listaClientes = clienteDao.getAllClientes();

        clienteAdapter = new ClienteAdapter(this, listaClientes, new ClienteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Cliente cliente) {
                mostrarPopupEditarCliente(cliente);
            }

            @Override
            public void onDeleteClick(Cliente cliente) {
                mostrarPopupEliminarCliente(cliente);
            }
        });

        recyclerView.setAdapter(clienteAdapter);
    }

    private void mostrarPopupAgregarCliente() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar Cliente");

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_cliente_info, null);
        builder.setView(view);

        EditText inputNombre = view.findViewById(R.id.inputNombreCliente);
        EditText inputCorreo = view.findViewById(R.id.inputCorreoCliente);
        EditText inputTelefono = view.findViewById(R.id.inputTelefonoCliente);
        EditText inputDireccion = view.findViewById(R.id.inputDireccionCliente);

        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String nombre = inputNombre.getText().toString().trim();
            String correo = inputCorreo.getText().toString().trim();
            String telefono = inputTelefono.getText().toString().trim();
            String direccion = inputDireccion.getText().toString().trim();

            if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
                Toast.makeText(this, "Todos los campos deben estar completos", Toast.LENGTH_SHORT).show();
                return;
            }

            Cliente nuevoCliente = new Cliente(nombre, correo, telefono, direccion);
            clienteDao.insert(nuevoCliente);
            cargarClientes();
            Toast.makeText(this, "Cliente agregado con éxito", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void mostrarPopupEditarCliente(Cliente cliente) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Editar Cliente");

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_cliente_editar, null);
        builder.setView(view);

        EditText inputNombre = view.findViewById(R.id.inputNombreCliente);
        EditText inputCorreo = view.findViewById(R.id.inputCorreoCliente);
        EditText inputTelefono = view.findViewById(R.id.inputTelefonoCliente);
        EditText inputDireccion = view.findViewById(R.id.inputDireccionCliente);

        inputNombre.setText(cliente.nombre);
        inputCorreo.setText(cliente.correo);
        inputTelefono.setText(cliente.telefono);
        inputDireccion.setText(cliente.direccion);

        builder.setPositiveButton("Guardar", (dialog, which) -> {
            cliente.nombre = inputNombre.getText().toString().trim();
            cliente.correo = inputCorreo.getText().toString().trim();
            cliente.telefono = inputTelefono.getText().toString().trim();
            cliente.direccion = inputDireccion.getText().toString().trim();

            clienteDao.update(cliente);
            cargarClientes();
            Toast.makeText(this, "Cliente actualizado", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void mostrarPopupEliminarCliente(Cliente cliente) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar Cliente");
        builder.setMessage("¿Seguro que quieres eliminar este cliente?");

        builder.setPositiveButton("Eliminar", (dialog, which) -> {
            clienteDao.delete(cliente);
            cargarClientes();
            Toast.makeText(this, "Cliente eliminado", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarClientes();
    }
}