package com.example.gestion3d.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import com.example.gestion3d.database.entities.OrdenMaterial;

@Dao
public interface OrdenMaterialDao {
    @Insert
    void insert(OrdenMaterial ordenMaterial);

    @Query("SELECT * FROM ordenes_materiales")
    List<OrdenMaterial> getAllOrdenMateriales();
}