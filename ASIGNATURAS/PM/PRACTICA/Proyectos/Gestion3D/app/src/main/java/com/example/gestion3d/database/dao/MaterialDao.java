package com.example.gestion3d.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;
import java.util.List;
import com.example.gestion3d.database.entities.Material;

@Dao
public interface MaterialDao {
    @Insert
    void insert(Material material);

    @Query("SELECT * FROM materiales ORDER BY id_material DESC")
    List<Material> getAllMateriales();

    @Query("SELECT * FROM materiales WHERE idUsuarioCreador = :idUsuario ORDER BY id_material DESC")
    List<Material> getMaterialesByUsuario(int idUsuario);

    @Query("SELECT * FROM materiales WHERE idGrupo = :idGrupo ORDER BY id_material DESC")
    List<Material> getMaterialesByGrupo(int idGrupo);

    @Query("SELECT * FROM materiales WHERE id_material = :idMaterial LIMIT 1")
    Material getMaterialById(int idMaterial);

    @Update
    void update(Material material);

    @Delete
    void delete(Material material);
}
