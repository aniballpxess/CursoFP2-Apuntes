package com.example.gestion3d.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gestion3d.database.entities.OrdenTrabajo;

import java.util.List;

@Dao
public interface OrdenTrabajoDao {
    @Insert
    long insert(OrdenTrabajo orden);

    @Update
    void update(OrdenTrabajo orden);

    @Delete
    void delete(OrdenTrabajo orden);

    @Query("SELECT * FROM ordenes_trabajo")
    List<OrdenTrabajo> getAllOrdenes();

    @Query("SELECT * FROM ordenes_trabajo WHERE estado = :estado")
    List<OrdenTrabajo> getOrdenesPorEstado(String estado);

    @Query("SELECT * FROM ordenes_trabajo WHERE id_cliente = :idCliente")
    List<OrdenTrabajo> getOrdenesPorCliente(int idCliente);

    @Query("SELECT * FROM ordenes_trabajo WHERE fecha_creacion BETWEEN :fechaInicio AND :fechaFin")
    List<OrdenTrabajo> getOrdenesPorRangoFechas(String fechaInicio, String fechaFin);
}