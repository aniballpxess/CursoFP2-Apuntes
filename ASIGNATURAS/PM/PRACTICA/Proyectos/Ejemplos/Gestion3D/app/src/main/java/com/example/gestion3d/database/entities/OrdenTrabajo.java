package com.example.gestion3d.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Define la entidad "ordenes_trabajo" para la base de datos Room
@Entity(tableName = "ordenes_trabajo")
public class OrdenTrabajo {
    @PrimaryKey(autoGenerate = true)
    public int id_orden; // Identificador único de la orden de trabajo

    public int id_cliente; // ID del cliente asociado a la orden
    public String estado; // Estado de la orden (Ej: Pendiente, En Proceso, Finalizado)
    public String fecha_creacion; // Fecha en la que se creó la orden
    public String fecha_entrega; // Fecha estimada de entrega
    public float costo_estimado; // Costo estimado de la orden

    // Almacenamos los IDs de productos como String (Ejemplo: "1,3,5")
    public String ids_productos; // Lista de productos asociados en formato CSV

}
