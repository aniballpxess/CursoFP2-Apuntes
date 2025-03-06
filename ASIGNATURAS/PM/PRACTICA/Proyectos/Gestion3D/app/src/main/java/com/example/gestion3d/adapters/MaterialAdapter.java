package com.example.gestion3d.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gestion3d.R;
import com.example.gestion3d.database.AppDatabase;
import com.example.gestion3d.database.dao.MaterialDao;
import com.example.gestion3d.database.entities.Material;
import java.util.List;
import android.widget.ImageButton;
import android.util.Log;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder> {

    private Context context;
    private List<Material> materialList;
    private MaterialDao materialDao;
    private int idUsuarioActual;
    private int usuarioRol;
    private OnItemClickListener onItemClickListener;
    private static final String TAG = "MaterialAdapter"; // Etiqueta para logs

    // Interfaz para manejar eventos en los elementos de la lista
    public interface OnItemClickListener {
        void onDeleteClick(Material material);
        void onEditClick(Material material);
    }

    // Constructor del adaptador
    public MaterialAdapter(Context context, List<Material> materialList, OnItemClickListener listener) {
        this.context = context;
        this.materialList = materialList;
        this.onItemClickListener = listener;
        this.materialDao = AppDatabase.getDatabase(context).materialDao();

        // Recuperar ID del usuario y rol desde SharedPreferences
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        this.idUsuarioActual = prefs.getInt("idUsuario", -1);
        this.usuarioRol = prefs.getInt("rol", 0);
    }

    @NonNull
    @Override
    public MaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_material, parent, false);
        return new MaterialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialViewHolder holder, int position) {
        Material material = materialList.get(position);

        if (material != null) {
            holder.txtNombreMaterial.setText(material.nombre);
            holder.txtTipoMaterial.setText("Tipo: " + material.tipo);
            holder.txtMarcaMaterial.setText("Marca: " + material.marca);
            holder.txtColorMaterial.setText("Color: " + material.color);
            holder.txtCantidadMaterial.setText("Stock: " + material.cantidad_stock);

            Log.d(TAG, "Mostrando material en la lista: " + material.nombre);

            // Configurar eventos de los botones
            holder.btnEditar.setOnClickListener(v -> {
                Log.d(TAG, "Botón editar clickeado para " + material.nombre);
                onItemClickListener.onEditClick(material);
            });

            holder.btnEliminar.setOnClickListener(v -> {
                Log.d(TAG, "Botón eliminar clickeado para " + material.nombre);
                onItemClickListener.onDeleteClick(material);
            });
        }
    }

    @Override
    public int getItemCount() {
        return materialList.size();
    }

    public static class MaterialViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombreMaterial, txtTipoMaterial, txtMarcaMaterial, txtColorMaterial, txtCantidadMaterial;
        ImageButton btnEditar, btnEliminar;

        public MaterialViewHolder(View itemView) {
            super(itemView);
            txtNombreMaterial = itemView.findViewById(R.id.txtNombreMaterial);
            txtTipoMaterial = itemView.findViewById(R.id.txtTipoMaterial);
            txtMarcaMaterial = itemView.findViewById(R.id.txtMarcaMaterial);
            txtColorMaterial = itemView.findViewById(R.id.txtColorMaterial);
            txtCantidadMaterial = itemView.findViewById(R.id.txtCantidadMaterial);
            btnEditar = itemView.findViewById(R.id.btnEditarMaterial);
            btnEliminar = itemView.findViewById(R.id.btnEliminarMaterial);
        }
    }

    // Muestra un popup con opciones para editar o eliminar un material
    private void mostrarPopupOpciones(Material material) {
        Log.d(TAG, "Abriendo popup de opciones para: " + material.nombre);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_material_eliminar, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        // Referencias a los elementos del popup
        TextView txtNombre = dialogView.findViewById(R.id.txtNombreMaterial);
        TextView txtColor = dialogView.findViewById(R.id.txtColorMaterial);
        TextView txtMarca = dialogView.findViewById(R.id.txtMarcaMaterial);
        TextView txtStock = dialogView.findViewById(R.id.txtCantidadMaterial);
        Button btnEditar = dialogView.findViewById(R.id.btnEditar);
        Button btnEliminar = dialogView.findViewById(R.id.btnEliminar);
        Button btnCerrar = dialogView.findViewById(R.id.btnCerrar);

        // Cargar datos en el popup
        txtNombre.setText(material.nombre);
        txtColor.setText("Color: " + material.color);
        txtMarca.setText("Marca: " + material.marca);
        txtStock.setText("Stock: " + material.cantidad_stock);

        // Configurar botones
        btnEditar.setOnClickListener(v -> {
            mostrarDialogoEdicion(material, dialog);
        });

        btnEliminar.setOnClickListener(v -> {
            Log.d(TAG, "Eliminando material: " + material.nombre);
            materialDao.delete(material);
            materialList.remove(material);
            notifyDataSetChanged();
            dialog.dismiss();
        });

        btnCerrar.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    // Muestra un diálogo para editar un material existente
    private void mostrarDialogoEdicion(Material material, AlertDialog parentDialog) {
        Log.d(TAG, "Abriendo popup para editar el material: " + material.nombre);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_material_editar, null);
        builder.setView(dialogView);

        builder.setTitle("Editar Material");

        // Referencias a los campos de edición
        EditText edtNombre = dialogView.findViewById(R.id.edtNombre);
        EditText edtCantidadStock = dialogView.findViewById(R.id.edtCantidadStock);

        // Cargar datos actuales
        edtNombre.setText(material.nombre);
        edtCantidadStock.setText(String.valueOf(material.cantidad_stock));

        // Agregar botones al AlertDialog
        builder.setPositiveButton("Guardar", (dialog, which) -> {
            String nuevoNombre = edtNombre.getText().toString().trim();
            int nuevaCantidad = obtenerEntero(edtCantidadStock);

            if (nuevoNombre.isEmpty() || nuevaCantidad < 0) {
                Toast.makeText(context, "Todos los campos deben ser válidos", Toast.LENGTH_SHORT).show();
                return;
            }

            Log.d(TAG, "Actualizando material: " + material.nombre + " -> " + nuevoNombre);


            // Actualizar el material en la base de datos
            material.nombre = nuevoNombre;
            material.cantidad_stock = nuevaCantidad;
            materialDao.update(material);

            notifyDataSetChanged();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        builder.show();
    }

    // Convierte el texto de un EditText a un número entero seguro
    private int obtenerEntero(EditText editText) {
        try {
            return Integer.parseInt(editText.getText().toString().trim());
        } catch (NumberFormatException e) {
            Log.e(TAG, "Error al convertir a número: " + e.getMessage());
            return -1;
        }
    }
}