package com.example.gestion3d.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "materiales")
public class Material {

    @PrimaryKey(autoGenerate = true)
    public int id_material;

    public String nombre;
    public String tipo;
    public String color;
    public String marca;
    public int cantidad_stock;
    public int umbral_alerta;
    public int idGrupo;
    public int idUsuarioCreador;

    public Material(String nombre, String tipo, String color, String marca, int cantidad_stock, int umbral_alerta, int idGrupo, int idUsuarioCreador) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.color = color;
        this.marca = marca;
        this.cantidad_stock = cantidad_stock;
        this.umbral_alerta = umbral_alerta;
        this.idGrupo = idGrupo;
        this.idUsuarioCreador = idUsuarioCreador;
    }

    // Métodos Getters
    public String getNombre() { return nombre; }
    public int getCantidadStock() { return cantidad_stock; }

    // Métodos Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCantidadStock(int cantidad_stock) { this.cantidad_stock = cantidad_stock; }
}
