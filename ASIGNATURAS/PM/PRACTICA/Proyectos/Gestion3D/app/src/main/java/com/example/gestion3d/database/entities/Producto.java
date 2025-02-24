package com.example.gestion3d.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Define la entidad "productos" para la base de datos Room
@Entity(tableName = "productos")
public class Producto {
    @PrimaryKey(autoGenerate = true)
    public int id_producto; // Identificador único del producto

    public String nombre; // Nombre del producto
    public String dimensiones; // Dimensiones del producto (Ej: 20x20x10 cm)
    public float peso; // Peso del producto en gramos
    public int tiempo_impresion; // Tiempo estimado de impresión en minutos
    public int cantidad_stock; // Cantidad de stock disponible
    public String imagenURI; // Ruta o URI de la imagen del producto
    public String categoria; // Categoría del producto

    // Constructor vacío requerido por Room
    public Producto() {}

    // Constructor con parámetros, incluyendo la categoría
    public Producto(String nombre, String dimensiones, float peso, int tiempo_impresion, int cantidad_stock, String categoria) {
        this.nombre = nombre;
        this.dimensiones = dimensiones;
        this.peso = peso;
        this.tiempo_impresion = tiempo_impresion;
        this.cantidad_stock = cantidad_stock;
        this.categoria = categoria;
        this.imagenURI = null; // Inicialmente sin imagen asignada
    }
}
