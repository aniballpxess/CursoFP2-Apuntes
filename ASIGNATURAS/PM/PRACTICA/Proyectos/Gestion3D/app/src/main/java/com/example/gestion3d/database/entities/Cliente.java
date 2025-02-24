package com.example.gestion3d.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "clientes")
public class Cliente {
    @PrimaryKey(autoGenerate = true)
    public int id_cliente;

    public String nombre;
    public String correo;
    public String telefono;
    public String direccion;

    // 🔹 Constructor vacío requerido por Room
    public Cliente() {}

    // 🔹 Constructor con parámetros
    public Cliente(String nombre, String correo, String telefono, String direccion) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
    }
}
