package com.example.gestion3d.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Define la entidad "usuarios" para la base de datos Room
@Entity(tableName = "usuarios")
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    public int id_usuario; // Identificador único del usuario

    public String nombre; // Nombre del usuario
    public String correo; // Correo electrónico del usuario
    public String contrasena; // Contraseña del usuario
    public int idGrupo; // ID del grupo al que pertenece
    public int rol; // 0 = Usuario normal, 1 = Admin, 2 = SuperAdmin
    public String fechaRegistro; // Fecha de registro del usuario
    public String iconoPerfil; // Ruta o identificador del icono de perfil

    // Constructor de la clase Usuario
    public Usuario(String nombre, String correo, String contrasena, int idGrupo, int rol, String fechaRegistro, String iconoPerfil) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.idGrupo = idGrupo;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
        this.iconoPerfil = iconoPerfil;
    }
}