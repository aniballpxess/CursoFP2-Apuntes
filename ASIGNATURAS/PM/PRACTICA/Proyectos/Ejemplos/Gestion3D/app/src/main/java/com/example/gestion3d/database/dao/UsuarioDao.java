package com.example.gestion3d.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.gestion3d.database.entities.Usuario;
import java.util.List;

@Dao
public interface UsuarioDao {

    // Insertar un nuevo usuario en la base de datos
    @Insert
    void insert(Usuario usuario);

    // Verifica el inicio de sesión con correo y contraseña
    @Query("SELECT * FROM usuarios WHERE correo = :correo AND contrasena = :contrasena LIMIT 1")
    Usuario login(String correo, String contrasena);

    // Verifica si un usuario ya existe en la base de datos
    @Query("SELECT * FROM usuarios WHERE correo = :correo LIMIT 1")
    Usuario checkUserExists(String correo);

    // Obtener todos los usuarios de un grupo específico
    @Query("SELECT * FROM usuarios WHERE idGrupo = :idGrupo")
    List<Usuario> getUsuariosByGrupo(int idGrupo);

    // Obtener los usuarios filtrados que pertenecen a un grupo o están sin grupo
    @Query("SELECT * FROM usuarios WHERE idGrupo = :idGrupo OR (idGrupo = -1 AND id_usuario = :idUsuario)")
    List<Usuario> getUsuariosFiltrados(int idGrupo, int idUsuario);

    // Actualizar el rol de un usuario
    @Query("UPDATE usuarios SET rol = :nuevoRol WHERE id_usuario = :idUsuario")
    void actualizarRol(int idUsuario, int nuevoRol);

    // Obtener los administradores de un grupo
    @Query("SELECT * FROM usuarios WHERE idGrupo = :idGrupo AND rol = 1")
    List<Usuario> getAdminsByGrupo(int idGrupo);

    // Actualizar el rol de un usuario específico
    @Query("UPDATE usuarios SET rol = :nuevoRol WHERE id_usuario = :idUsuario")
    void actualizarRolUsuario(int idUsuario, int nuevoRol);

    // Asignar un grupo a un usuario
    @Query("UPDATE usuarios SET idGrupo = :idGrupo WHERE id_usuario = :idUsuario")
    void updateGrupo(int idUsuario, int idGrupo);

    // Eliminar un usuario por su ID
    @Query("DELETE FROM usuarios WHERE id_usuario = :idUsuario")
    void eliminarUsuario(int idUsuario);

    // Obtener los datos de un usuario por su ID
    @Query("SELECT * FROM usuarios WHERE id_usuario = :idUsuario LIMIT 1")
    Usuario getCurrentUser(int idUsuario);

    // Obtener usuario por ID
    @Query("SELECT * FROM usuarios WHERE id_usuario = :idUsuario LIMIT 1")
    Usuario getUsuarioById(int idUsuario);

    // Actualizar información de un usuario
    @Update
    void update(Usuario usuario);

    // Cambiar la contraseña de un usuario
    @Query("UPDATE usuarios SET contrasena = :nuevaContrasena WHERE correo = :correo")
    void cambiarContrasena(String correo, String nuevaContrasena);

    // Actualizar el icono de perfil de un usuario
    @Query("UPDATE usuarios SET iconoPerfil = :nuevoIcono WHERE id_usuario = :idUsuario")
    void actualizarIconoPerfil(int idUsuario, String nuevoIcono);

    // Eliminar todos los usuarios de un grupo específico
    @Query("DELETE FROM usuarios WHERE idGrupo = :idGrupo")
    void eliminarUsuariosDeGrupo(int idGrupo);
}