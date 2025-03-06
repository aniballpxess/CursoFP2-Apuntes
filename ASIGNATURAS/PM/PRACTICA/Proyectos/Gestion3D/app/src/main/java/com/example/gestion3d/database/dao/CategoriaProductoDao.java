package com.example.gestion3d.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import com.example.gestion3d.database.entities.CategoriaProducto;

@Dao
public interface CategoriaProductoDao {
    @Insert
    void insert(CategoriaProducto categoria);

    @Query("SELECT * FROM categorias_productos")
    List<CategoriaProducto> getAllCategorias();

    @Delete
    void delete(CategoriaProducto categoria);
}
