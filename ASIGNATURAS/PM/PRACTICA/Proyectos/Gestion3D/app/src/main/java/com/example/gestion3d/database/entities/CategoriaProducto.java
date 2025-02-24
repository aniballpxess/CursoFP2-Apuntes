package com.example.gestion3d.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Define la entidad "categorias_productos" para la base de datos Room
@Entity(tableName = "categorias_productos")
public class CategoriaProducto {
    @PrimaryKey(autoGenerate = true)
    public int id_categoria; // Identificador único de la categoría

    public String nombre_categoria; // Nombre de la categoría del producto

    // Constructor de la categoría del producto
    public CategoriaProducto(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }
}
