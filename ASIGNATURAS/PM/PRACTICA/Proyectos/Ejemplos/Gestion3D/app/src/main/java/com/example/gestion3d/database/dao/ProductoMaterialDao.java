package com.example.gestion3d.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import com.example.gestion3d.database.entities.ProductoMaterial;

@Dao
public interface ProductoMaterialDao {
    @Insert
    void insert(ProductoMaterial productoMaterial);

    @Query("SELECT * FROM productos_materiales")
    List<ProductoMaterial> getAllProductoMateriales();
}