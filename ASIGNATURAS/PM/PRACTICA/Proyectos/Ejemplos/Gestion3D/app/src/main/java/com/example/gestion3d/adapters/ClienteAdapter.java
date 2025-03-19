package com.example.gestion3d.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gestion3d.R;
import com.example.gestion3d.database.entities.Cliente;
import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {

    private Context context;
    private List<Cliente> listaClientes;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Cliente cliente);
        void onDeleteClick(Cliente cliente);
    }

    public ClienteAdapter(Context context, List<Cliente> listaClientes, OnItemClickListener listener) {
        this.context = context;
        this.listaClientes = listaClientes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cliente, parent, false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        Cliente cliente = listaClientes.get(position);

        holder.txtNombre.setText(cliente.nombre);
        holder.txtCorreo.setText("Correo: " + cliente.correo);
        holder.txtTelefono.setText("Teléfono: " + cliente.telefono);

        // Click en la card para editar
        holder.itemView.setOnClickListener(v -> listener.onItemClick(cliente));

        // Botón eliminar
        holder.btnEliminar.setOnClickListener(v -> listener.onDeleteClick(cliente));
    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    // Método para actualizar la lista y refrescar la UI
    public void actualizarLista(List<Cliente> nuevaLista) {
        this.listaClientes.clear();
        this.listaClientes.addAll(nuevaLista);
        notifyDataSetChanged();
    }

    public static class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtCorreo, txtTelefono;
        ImageButton btnEliminar;

        public ClienteViewHolder(View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombreCliente);
            txtCorreo = itemView.findViewById(R.id.txtCorreoCliente);
            txtTelefono = itemView.findViewById(R.id.txtTelefonoCliente);
            btnEliminar = itemView.findViewById(R.id.btnEliminarCliente);
        }
    }
}
