package com.example.gestion3d.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import com.example.gestion3d.database.entities.Cliente;

@Dao
public interface ClienteDao {
    @Insert
    void insert(Cliente cliente);

    @Query("SELECT * FROM clientes")
    List<Cliente> getAllClientes();

    @Query("SELECT * FROM clientes WHERE nombre LIKE '%' || :nombre || '%'")
    List<Cliente> buscarPorNombre(String nombre);

    @Update
    void update(Cliente cliente);

    @Delete
    void delete(Cliente cliente);
}