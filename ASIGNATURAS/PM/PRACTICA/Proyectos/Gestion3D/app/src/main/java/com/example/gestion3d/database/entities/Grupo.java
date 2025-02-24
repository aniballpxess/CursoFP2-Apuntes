package com.example.gestion3d.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Define la entidad "grupos" para la base de datos Room
@Entity(tableName = "grupos")
public class Grupo {
    @PrimaryKey(autoGenerate = true)
    public int id_grupo; // Identificador único del grupo

    public String nombreGrupo; // Nombre del grupo
    public int id_admin; // ID del administrador del grupo
    public String codigo_inv; // Código de invitación único del grupo

    // Constructor para inicializar un grupo con valores
    public Grupo(String nombreGrupo, int id_admin, String codigo_inv) {
        this.nombreGrupo = nombreGrupo;
        this.id_admin = id_admin;
        this.codigo_inv = codigo_inv;
    }

    // Constructor vacío requerido por Room
    public Grupo() {}
}
