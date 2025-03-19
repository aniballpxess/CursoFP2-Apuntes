package com.example.gestion3d.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import com.example.gestion3d.database.entities.Producto;

@Dao
public interface ProductoDao {
    @Insert
    long insert(Producto producto); // ✅ Ahora devuelve el ID insertado

    @Query("SELECT * FROM productos")
    List<Producto> getAllProductos();

    @Update
    void update(Producto producto);

    @Delete
    void delete(Producto producto);

    @Query("SELECT * FROM productos WHERE nombre LIKE '%' || :nombre || '%'")
    List<Producto> buscarPorNombre(String nombre);

    @Query("SELECT * FROM productos WHERE nombre LIKE '%' || :nombre || '%' AND categoria = :categoria")
    List<Producto> buscarPorNombreYCategoria(String nombre, String categoria);

    @Query("SELECT * FROM productos WHERE categoria = :categoria")
    List<Producto> buscarPorCategoria(String categoria);

}