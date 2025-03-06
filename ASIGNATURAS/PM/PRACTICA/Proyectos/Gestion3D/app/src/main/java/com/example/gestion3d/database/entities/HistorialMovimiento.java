package com.example.gestion3d.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Define la entidad "historial_movimientos" para la base de datos Room
@Entity(tableName = "historial_movimientos")
public class HistorialMovimiento {
    @PrimaryKey(autoGenerate = true)
    public int id_movimiento; // Identificador único del movimiento

    public String tipo; // Tipo de movimiento (Ej: Entrada, Salida, Ajuste de stock)
    public int id_relacionado; // ID de la entidad relacionada (Ej: ID de material o producto)
    public int cantidad; // Cantidad de unidades afectadas en el movimiento
    public String fecha_movimiento; // Fecha en la que ocurrió el movimiento
    public String responsable; // Nombre del usuario que realizó el movimiento
}