package com.example.gestion3d.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.example.gestion3d.R;
import com.example.gestion3d.activities.perfil.EditarPerfilActivity;
import com.example.gestion3d.activities.grupo.GestionGrupoActivity;
import com.example.gestion3d.activities.auth.LoginActivity;
import com.example.gestion3d.database.AppDatabase;
import com.example.gestion3d.database.dao.GrupoDao;
import com.example.gestion3d.database.dao.UsuarioDao;
import com.example.gestion3d.database.entities.Grupo;
import com.example.gestion3d.database.entities.Usuario;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class PerfilFragment extends Fragment {

    private ImageView imageViewPerfil;
    private TextView txtNombreUsuario, txtCorreoUsuario;
    private Button btnEditarPerfil, btnUnirseGrupo, btnCrearGrupo, btnGestionGrupo, btnCerrarSesion;
    private UsuarioDao usuarioDao;
    private GrupoDao grupoDao;
    private Usuario usuarioActual;
    private static final String TAG = "PerfilFragment"; // Etiqueta para logs

    public PerfilFragment() {
        // Constructor vacío requerido
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        Log.d(TAG, "onCreateView: PerfilFragment inflado correctamente");

        // Inicializar vistas
        imageViewPerfil = view.findViewById(R.id.imageViewPerfil);
        txtNombreUsuario = view.findViewById(R.id.txtNombreUsuario);
        txtCorreoUsuario = view.findViewById(R.id.txtCorreoUsuario);
        btnEditarPerfil = view.findViewById(R.id.btnEditarPerfil);
        btnCrearGrupo = view.findViewById(R.id.btnCrearGrupo);
        btnUnirseGrupo = view.findViewById(R.id.btnUnirseGrupo);
        btnGestionGrupo = view.findViewById(R.id.btnGestionGrupo);
        btnCerrarSesion = view.findViewById(R.id.btnCerrarSesion);

        // 🔹 Inicializar DAOs
        usuarioDao = AppDatabase.getDatabase(getContext()).usuarioDao();
        grupoDao = AppDatabase.getDatabase(getContext()).grupoDao();

        // 🔹 Recuperar usuario desde SharedPreferences
        SharedPreferences prefs = getActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        int idUsuario = prefs.getInt("idUsuario", -1);

        if (idUsuario == -1) {
            Log.e(TAG, "No hay usuario en sesión.");
            return view;
        }

        usuarioActual = usuarioDao.getUsuarioById(idUsuario);
        if (usuarioActual == null) {
            Log.e(TAG, "Usuario no encontrado en la base de datos.");
            return view;
        }

        // 🔹 Configurar botones según el estado del usuario
        actualizarUI();

        btnEditarPerfil.setOnClickListener(v -> editarUsuario());
        btnCrearGrupo.setOnClickListener(v -> mostrarDialogoCrearGrupo());
        btnUnirseGrupo.setOnClickListener(v -> mostrarDialogoUnirseGrupo());
        btnGestionGrupo.setOnClickListener(v -> gestionarGrupo());
        btnCerrarSesion.setOnClickListener(v -> cerrarSesion());

        return view;
    }

    private boolean toastMostrado = false; // Boolean para evitar Toasts duplicados

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "🔄 onResume: Recargando datos del usuario...");

        SharedPreferences prefs = getActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        int idUsuario = prefs.getInt("idUsuario", -1);

        if (idUsuario == -1) {
            Log.e(TAG, "No se pudo obtener el usuario actual.");
            return;
        }

        usuarioActual = usuarioDao.getUsuarioById(idUsuario);
        if (usuarioActual == null) {
            Log.e(TAG, "No se pudo recuperar el usuario desde la base de datos.");
            return;
        }

        cargarImagenPerfil();
        actualizarUI();
        if (!toastMostrado) { // Solo mostrar los Toasts una vez
            actualizarUI();
            toastMostrado = true; // Marcar que el Toast ya se mostró
        } else {
            actualizarUI(); // Sigue actualizando la UI pero sin mostrar Toasts
        }
    }

    //Método para editar el perfil del usuario.
    private void editarUsuario() {
        if (usuarioActual == null) {
            Toast.makeText(getActivity(), "Error: No se pudo cargar el perfil", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(requireActivity(), EditarPerfilActivity.class);
        startActivity(intent);
    }

    private void actualizarDatosUsuario() {
        if (usuarioActual == null) {
            SharedPreferences prefs = getActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
            int idUsuario = prefs.getInt("idUsuario", -1);
            usuarioActual = usuarioDao.getUsuarioById(idUsuario);
        }
        if (usuarioActual != null) {
            actualizarUI();
        }
    }

    // Método para actualizar el icono de perfil del usuario.
    private void actualizarIconoPerfil() {
        if (usuarioActual == null) {
            Log.e(TAG, "❌ actualizarIconoPerfil: usuarioActual es null.");
            return;
        }

        // Obtener el icono de perfil desde SharedPreferences
        String iconoPerfil = getIconoPerfilDesdePreferencias();
        Log.d(TAG, "🔍 Valor en usuarioActual.iconoPerfil: " + iconoPerfil);

        if (iconoPerfil != null && !iconoPerfil.isEmpty()) {
            // Buscar el ID del recurso en drawable
            int iconResId = getResources().getIdentifier(iconoPerfil, "drawable", getActivity().getPackageName());

            if (iconResId != 0) {  // Si el recurso existe en drawable
                imageViewPerfil.setImageResource(iconResId);
                Log.d(TAG, "✅ Icono de perfil actualizado correctamente: " + iconoPerfil);
            } else {
                imageViewPerfil.setImageResource(R.drawable.ic_perfil); // Imagen predeterminada
                Log.e(TAG, "⚠️ No se encontró el icono en drawable: " + iconoPerfil);
            }
        } else {
            imageViewPerfil.setImageResource(R.drawable.ic_perfil); // Imagen predeterminada
            Log.d(TAG, "⚠️ No se encontró icono de perfil guardado, se usa imagen predeterminada.");
        }
    }

    // Método para obtener el icono de perfil desde SharedPreferences
    private String getIconoPerfilDesdePreferencias() {
        SharedPreferences prefs = getActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        String iconoGuardado = prefs.getString("iconoPerfil_" + usuarioActual.id_usuario, null);

        if (iconoGuardado == null || iconoGuardado.isEmpty()) {
            return usuarioActual.iconoPerfil; // Si no está en SharedPreferences, obtenerlo de la BD
        }

        return iconoGuardado;
    }

    // Método para mostrar el AlertDialog donde el usuario ingresará el nombre del grupo.
    private void mostrarDialogoCrearGrupo() {
        if (usuarioActual.idGrupo != -1) {
            Toast.makeText(getActivity(), "Ya perteneces a un grupo. No puedes crear otro.", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Crear Grupo");

        final EditText input = new EditText(getActivity());
        input.setHint("Nombre del grupo");
        builder.setView(input);

        builder.setPositiveButton("Crear", (dialog, which) -> {
            String nombreGrupo = input.getText().toString().trim();
            if (!nombreGrupo.isEmpty()) {
                crearGrupo(nombreGrupo);
            } else {
                Toast.makeText(getActivity(), "El nombre del grupo no puede estar vacío", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    // Método para crear un grupo en la base de datos y asignarlo al usuario actual.
    private void crearGrupo(String nombreGrupo) {
        if (grupoDao.getGrupoByNombre(nombreGrupo) != null) {
            Toast.makeText(getActivity(), "Ya existe un grupo con ese nombre", Toast.LENGTH_SHORT).show();
            return;
        }
        if (nombreGrupo.trim().isEmpty()) {
            Toast.makeText(getActivity(), "El nombre del grupo no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        String codigoInvitacion = UUID.randomUUID().toString().substring(0, 6);
        Grupo nuevoGrupo = new Grupo(nombreGrupo, usuarioActual.id_usuario, codigoInvitacion);
        long idGrupo = grupoDao.insertGrupo(nuevoGrupo);

        if (idGrupo == -1) {
            Log.e(TAG, "Error al insertar grupo en la base de datos");
            Toast.makeText(getActivity(), "Error al crear el grupo", Toast.LENGTH_SHORT).show();
            return;
        }

        usuarioActual.idGrupo = (int) idGrupo;
        usuarioActual.rol = 2;
        usuarioDao.update(usuarioActual);

        SharedPreferences prefs = getActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("idGrupo", usuarioActual.idGrupo);
        editor.putInt("rol", usuarioActual.rol);
        editor.apply();

        actualizarUI();
    }

    // Método para mostrar el AlertDialog donde el usuario ingresará el código de invitación.
    private void mostrarDialogoUnirseGrupo() {
        Log.d(TAG, "mostrarDialogoUnirseGrupo: Mostrando diálogo para unirse a un grupo");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Unirse a un Grupo");

        final EditText input = new EditText(getActivity());
        input.setHint("Ingrese el código de invitación");
        builder.setView(input);

        builder.setPositiveButton("Unirse", (dialog, which) -> {
            String codigoGrupo = input.getText().toString().trim();
            if (!codigoGrupo.isEmpty()) {
                Log.d(TAG, "Usuario intenta unirse con código: " + codigoGrupo);
                unirseAGrupo(codigoGrupo);
            } else {
                Toast.makeText(getActivity(), "Debe ingresar un código de grupo", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "mostrarDialogoUnirseGrupo: Código de invitación vacío.");
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    // Método para unirse a un grupo existente.
    private void unirseAGrupo(String codigoGrupo) {
        Log.d(TAG, "unirseAGrupo: Verificando código de grupo: " + codigoGrupo);
        Grupo grupoExistente = grupoDao.getGrupoByCodigo(codigoGrupo);

        if (grupoExistente == null) {
            Toast.makeText(getActivity(), "Código de grupo inválido", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "unirseAGrupo: Código de grupo no encontrado en la base de datos.");
            return;
        }

        // Asignar el usuario al grupo y actualizar la BD
        usuarioActual.idGrupo = grupoExistente.id_grupo;
        usuarioActual.rol = 0; // Asignar rol de miembro
        usuarioDao.update(usuarioActual);
        Log.d(TAG, "unirseAGrupo: Usuario " + usuarioActual.nombre + " se unió al grupo: " + grupoExistente.nombreGrupo);


        // Guardar el grupo en SharedPreferences
        SharedPreferences prefs = getActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("idGrupo", grupoExistente.id_grupo);
        editor.putInt("rol", 0);
        editor.apply();

        Toast.makeText(getActivity(), "Te has unido al grupo exitosamente", Toast.LENGTH_SHORT).show();
        actualizarUI();
    }

    // Método para actualizar la UI con los datos del usuario actual.
    private void actualizarUI() {
        if (usuarioActual == null) {
            Log.e(TAG, "actualizarUI: usuarioActual es null, no se puede actualizar la UI.");
            return;
        }

        // Actualizar los datos en la UI
        txtNombreUsuario.setText("Nombre: " + usuarioActual.nombre);
        txtCorreoUsuario.setText("Correo: " + usuarioActual.correo);
        Log.d(TAG, "actualizarUI: Datos actualizados en la interfaz.");

        // Mostrar u ocultar botones dependiendo del estado del grupo
        if (usuarioActual.idGrupo == -1) {
            btnCrearGrupo.setVisibility(View.VISIBLE);
            btnGestionGrupo.setVisibility(View.GONE);
            btnUnirseGrupo.setVisibility(View.VISIBLE);
            Log.d(TAG, "actualizarUI: Usuario no pertenece a un grupo, se muestran opciones para crear o unirse.");
        } else {
            btnCrearGrupo.setVisibility(View.GONE);
            btnGestionGrupo.setVisibility(View.VISIBLE);
            btnUnirseGrupo.setVisibility(View.GONE);
            Log.d(TAG, "actualizarUI: Usuario pertenece a un grupo, se oculta opción de unirse o crear.");
        }

        // Asegurar que la imagen de perfil se actualiza
        actualizarIconoPerfil();
    }

    // Método para guardar la imagen en la base de datos y SharedPreferences.
    private void guardarImagenEnBD(String uriImagen) {
        if (usuarioActual == null){
            Log.e(TAG, "guardarImagenEnBD: usuarioActual es null, no se puede guardar imagen.");
            return;
        }

        usuarioActual.iconoPerfil = uriImagen;
        usuarioDao.actualizarIconoPerfil(usuarioActual.id_usuario, uriImagen);
        Log.d(TAG, "guardarImagenEnBD: Imagen de perfil actualizada en la base de datos.");
        Log.d(TAG, "🔍 Valor en usuarioActual.iconoPerfil: " + usuarioActual.iconoPerfil);


        SharedPreferences prefs = getActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("iconoPerfil_" + usuarioActual.id_usuario, uriImagen);
        editor.apply();
    }

    // Método para cargar la imagen de perfil desde SharedPreferences o la BD.
    private void cargarImagenPerfil() {
        if (usuarioActual == null) {
            Log.e(TAG, "❌ cargarImagenPerfil: usuarioActual es null, no se puede cargar imagen.");
            return;
        }

        SharedPreferences prefs = getActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        String iconoPerfilUri = prefs.getString("iconoPerfil_" + usuarioActual.id_usuario, null);

        if (iconoPerfilUri == null || iconoPerfilUri.isEmpty()) {
            iconoPerfilUri = usuarioActual.iconoPerfil; // Obtener de la BD si no está en SharedPreferences
        }

        if (iconoPerfilUri != null && !iconoPerfilUri.isEmpty()) {
            int iconResId = getResources().getIdentifier(iconoPerfilUri, "drawable", getActivity().getPackageName());

            if (iconResId != 0) {
                imageViewPerfil.setImageResource(iconResId);
                Log.d(TAG, "✅ Imagen de perfil cargada correctamente: " + iconoPerfilUri);
            } else {
                imageViewPerfil.setImageResource(R.drawable.ic_perfil); // Imagen predeterminada
                Log.e(TAG, "⚠️ No se encontró el icono en drawable: " + iconoPerfilUri);
            }
        } else {
            imageViewPerfil.setImageResource(R.drawable.ic_perfil); // Imagen predeterminada
            Log.d(TAG, "⚠️ No se encontró icono guardado, usando imagen predeterminada.");
        }
    }



    // Método para gestionar el grupo.
    private void gestionarGrupo() {
        Log.d(TAG, "gestionarGrupo: Navegando a la gestión del grupo.");
        Intent intent = new Intent(getActivity(), GestionGrupoActivity.class);
        startActivity(intent);
    }

    // Método para borrar los datos de sesión en SharedPreferences
    private void borrarDatosSesion() {
        SharedPreferences prefs = getActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear(); // Borra todos los datos de sesión
        editor.apply();
        Log.d(TAG, "borrarDatosSesion: Datos de sesión eliminados.");
    }

    // Método para cerrar sesión y redirigir al Login
    private void cerrarSesion() {
        Log.d(TAG, "cerrarSesion: Cierre de sesión iniciado.");

        // Llamar al método que borra los datos de sesión
        borrarDatosSesion();

        Toast.makeText(getActivity(), "Sesión cerrada correctamente", Toast.LENGTH_SHORT).show();

        // Redirigir al login
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        requireActivity().finish(); // Finaliza la actividad actual
        Log.d(TAG, "cerrarSesion: Redirigiendo al LoginActivity.");
    }
}