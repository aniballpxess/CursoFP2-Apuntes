package com.example.gestion3d.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.gestion3d.database.entities.Grupo;

@Dao
public interface GrupoDao {

    // Insertar un nuevo grupo en la base de datos
    @Insert
    long insertGrupo(Grupo grupo);

    // Obtener un grupo por su ID
    @Query("SELECT * FROM grupos WHERE id_grupo = :idGrupo LIMIT 1")
    Grupo getGrupoById(int idGrupo);

    // Obtener un grupo por su código de invitación
    @Query("SELECT * FROM grupos WHERE codigo_inv = :codigoInv LIMIT 1")
    Grupo getGrupoByCodigo(String codigoInv);

    // Cambiar el administrador de un grupo
    @Query("UPDATE grupos SET id_admin = :nuevoAdmin WHERE id_grupo = :idGrupo")
    void cambiarAdmin(int idGrupo, int nuevoAdmin);

    // Eliminar un grupo de la base de datos
    @Query("DELETE FROM grupos WHERE id_grupo = :idGrupo")
    void eliminarGrupo(int idGrupo);

    // Actualizar los datos de un grupo
    @Update
    void updateGrupo(Grupo grupo);

    // Transferir el rol de SuperAdmin a otro usuario
    @Query("UPDATE grupos SET id_admin = :nuevoSuperAdmin WHERE id_grupo = :idGrupo")
    void transferirSuperAdmin(int idGrupo, int nuevoSuperAdmin);

    @Query("SELECT * FROM grupos WHERE nombreGrupo = :nombreGrupo LIMIT 1")
    Grupo getGrupoByNombre(String nombreGrupo);
}