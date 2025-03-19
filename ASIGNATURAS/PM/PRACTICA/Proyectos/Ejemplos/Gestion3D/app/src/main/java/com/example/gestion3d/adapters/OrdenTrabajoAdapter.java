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
import com.example.gestion3d.database.entities.OrdenTrabajo;

import java.util.List;

public class OrdenTrabajoAdapter extends RecyclerView.Adapter<OrdenTrabajoAdapter.OrdenViewHolder> {


    private Context context;
    private List<OrdenTrabajo> listaOrdenes;
    private OnItemClickListener listener;

    // Interfaz para los eventos de clic
    public interface OnItemClickListener {
        void onItemClick(OrdenTrabajo orden);
        void onDeleteClick(OrdenTrabajo orden);
    }



    public OrdenTrabajoAdapter(Context context, List<OrdenTrabajo> listaOrdenes, OnItemClickListener listener) {
        this.context = context;
        this.listaOrdenes = listaOrdenes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OrdenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_orden, parent, false);
        return new OrdenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdenViewHolder holder, int position) {
        OrdenTrabajo orden = listaOrdenes.get(position);

        holder.txtIdOrden.setText("Orden #" + orden.id_orden);
        holder.txtEstado.setText("Estado: " + orden.estado);
        holder.txtFechaEntrega.setText("Entrega: " + orden.fecha_entrega);
        holder.txtCosto.setText("Costo: $" + orden.costo_estimado);

        // Eventos de botones
        holder.itemView.setOnClickListener(v -> listener.onItemClick(orden));
        holder.btnEliminar.setOnClickListener(v -> listener.onDeleteClick(orden));
    }

    @Override
    public int getItemCount() {
        return listaOrdenes.size();
    }

    public static class OrdenViewHolder extends RecyclerView.ViewHolder {
        TextView txtIdOrden, txtEstado, txtFechaEntrega, txtCosto;
        ImageButton btnEliminar;

        public OrdenViewHolder(View itemView) {
            super(itemView);
            txtIdOrden = itemView.findViewById(R.id.txtIdOrden);
            txtEstado = itemView.findViewById(R.id.txtEstadoOrden);
            txtFechaEntrega = itemView.findViewById(R.id.txtFechaEntrega);
            txtCosto = itemView.findViewById(R.id.txtCostoOrden);
            btnEliminar = itemView.findViewById(R.id.btnEliminarOrden);
        }
    }

    // Método para actualizar la lista de órdenes
    public void actualizarLista(List<OrdenTrabajo> nuevaLista) {
        this.listaOrdenes.clear();
        this.listaOrdenes.addAll(nuevaLista);
        notifyDataSetChanged();
    }
}
