package com.example.gestion3d.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestion3d.R;
import com.example.gestion3d.database.entities.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private Context context;
    private List<Producto> listaProductos;
    private OnItemClickListener listener;

    // Interfaz para eventos de clic
    public interface OnItemClickListener {
        void onItemClick(Producto producto);
        void onDeleteClick(Producto producto);
        void onEditClick(Producto producto);
    }

    // Constructor
    public ProductoAdapter(Context context, List<Producto> listaProductos, OnItemClickListener listener) {
        this.context = context;
        this.listaProductos = listaProductos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);

        holder.txtNombre.setText(producto.nombre);
        holder.txtCategoria.setText("Categoría: " + producto.categoria); // 🔹 Ya no será null
        holder.txtDimensiones.setText("Dimensiones: " + producto.dimensiones);
        holder.txtStock.setText("Stock: " + producto.cantidad_stock);

        // Eventos de botones
        holder.btnEditar.setOnClickListener(v -> listener.onItemClick(producto));
        holder.btnEliminar.setOnClickListener(v -> listener.onDeleteClick(producto));
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    // Esta es la única definición correcta de ProductoViewHolder
    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtCategoria, txtDimensiones, txtStock;
        ImageButton btnEditar, btnEliminar;

        public ProductoViewHolder(View itemView) {
            super(itemView);

            txtNombre = itemView.findViewById(R.id.txtNombreProducto);
            txtCategoria = itemView.findViewById(R.id.txtCategoriaProducto); // 🔹 Referenciar correctamente
            txtDimensiones = itemView.findViewById(R.id.txtDimensionesProducto);
            txtStock = itemView.findViewById(R.id.txtStockProducto);
            btnEditar = itemView.findViewById(R.id.btnEditarProducto);
            btnEliminar = itemView.findViewById(R.id.btnEliminarProducto);
        }
    }


    // Método para actualizar la lista y refrescar la UI
    public void actualizarLista(List<Producto> nuevaLista) {
        Log.d("ProductoAdapter", "Actualizando lista con " + nuevaLista.size() + " elementos");
        this.listaProductos = new ArrayList<>(nuevaLista); // Reemplazo seguro
        notifyDataSetChanged(); // Refrescar la UI
    }
}
