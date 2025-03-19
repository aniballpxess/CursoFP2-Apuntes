package com.example.gestion3d.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import com.example.gestion3d.database.entities.HistorialMovimiento;

@Dao
public interface HistorialMovimientoDao {
    @Insert
    void insert(HistorialMovimiento movimiento);

    @Query("SELECT * FROM historial_movimientos")
    List<HistorialMovimiento> getAllMovimientos();
}