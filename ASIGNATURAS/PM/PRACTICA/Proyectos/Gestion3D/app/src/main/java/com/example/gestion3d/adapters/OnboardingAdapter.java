package com.example.gestion3d.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gestion3d.R;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.ViewHolder> {
    private final Context context; // Contexto de la aplicación

    // Lista de layouts para cada pantalla del Onboarding
    private final int[] layouts = {
            R.layout.onboarding_slide1,
            R.layout.onboarding_slide2,
            R.layout.onboarding_slide3,
            R.layout.onboarding_slide4,
            R.layout.onboarding_slide5,
            R.layout.onboarding_slide6,
            R.layout.onboarding_slide7,
            R.layout.onboarding_slide8
    };

    // Constructor que recibe el contexto
    public OnboardingAdapter(Context context) {
        this.context = context;
    }

    // Infla la vista del layout correspondiente para cada pantalla del Onboarding
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Crea una vista a partir del layout específico de la posición actual
        View view = LayoutInflater.from(context).inflate(layouts[viewType], parent, false);
        return new ViewHolder(view);
    }

    // Método que se usa si es necesario personalizar cada pantalla del Onboarding
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // No se requiere vincular datos adicionales, ya que cada slide tiene su propio layout estático
    }

    // Devuelve el número total de pantallas en el Onboarding
    @Override
    public int getItemCount() {
        return layouts.length;
    }

    // Devuelve el tipo de vista basado en la posición, es decir, qué layout se debe mostrar
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    // Clase interna ViewHolder que se encarga de representar cada slide del Onboarding
    static class ViewHolder extends RecyclerView.ViewHolder {
        // Constructor del ViewHolder que recibe la vista correspondiente a un slide
        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}