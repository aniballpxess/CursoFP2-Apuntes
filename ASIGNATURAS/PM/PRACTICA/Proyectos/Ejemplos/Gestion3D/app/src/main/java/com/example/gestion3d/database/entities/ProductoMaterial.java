package com.example.gestion3d.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Define la entidad "productos_materiales" para la base de datos Room
@Entity(tableName = "productos_materiales")
public class ProductoMaterial {
    @PrimaryKey(autoGenerate = true)
    public int id_producto_material; // Identificador único de la relación producto-material

    public int id_producto; // ID del producto asociado
    public int id_material; // ID del material requerido para fabricar el producto
    public int cantidad_utilizada; // Cantidad del material utilizada por unidad de producto
}
