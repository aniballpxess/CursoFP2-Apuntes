package com.example.gestion3d.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Define la entidad "ordenes_materiales" para la base de datos Room
@Entity(tableName = "ordenes_materiales")
public class OrdenMaterial {
    @PrimaryKey(autoGenerate = true)
    public int id_orden_material; // Identificador único de la orden de material

    public int id_orden; // ID de la orden de trabajo asociada
    public int id_material; // ID del material utilizado
    public int cantidad_utilizada; // Cantidad de material utilizada en la orden
}
