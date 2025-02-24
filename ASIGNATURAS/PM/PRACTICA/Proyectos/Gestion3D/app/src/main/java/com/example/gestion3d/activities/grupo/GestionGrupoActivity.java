package com.example.gestion3d.activities.grupo;

import android.content.ClipboardManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import com.example.gestion3d.R;
import com.example.gestion3d.activities.main.MainActivity;
import com.example.gestion3d.adapters.MiembrosAdapter;
import com.example.gestion3d.database.AppDatabase;
import com.example.gestion3d.database.dao.GrupoDao;
import com.example.gestion3d.database.dao.UsuarioDao;
import com.example.gestion3d.database.entities.Grupo;
import com.example.gestion3d.database.entities.Usuario;
import java.util.List;
import android.widget.EditText;
import android.net.Uri;

public class GestionGrupoActivity extends AppCompatActivity {
    private TextView txtNombreGrupo, txtCodigoInvitacion;
    private ListView listViewMiembros;
    private Button btnUnirseGrupo, btnSalirGrupo;
    private ImageView btnEditarNombreGrupo, btnCopiarCodigo;
    private UsuarioDao usuarioDao;
    private GrupoDao grupoDao;
    private Usuario usuarioActual;
    private Grupo grupoActual;
    private List<Usuario> listaMiembros;
    private int idGrupoUsuario;
    private ImageView imgPerfilUsuario;
    private static final String TAG = "GestionGrupoActivity"; // Etiqueta para Logs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grupo_unirse);

        Log.d(TAG, "onCreate: Iniciando GestionGrupoActivity");

        // Inicializar vistas
        txtNombreGrupo = findViewById(R.id.txtNombreGrupo);
        txtCodigoInvitacion = findViewById(R.id.txtCodigoInvitacion);
        listViewMiembros = findViewById(R.id.listViewMiembros);
        btnUnirseGrupo = findViewById(R.id.btnUnirseGrupo);
        btnSalirGrupo = findViewById(R.id.btnSalirGrupo);
        btnEditarNombreGrupo = findViewById(R.id.btnEditarNombreGrupo);
        btnCopiarCodigo = findViewById(R.id.btnCopiarCodigo);
        imgPerfilUsuario = findViewById(R.id.imgPerfil);

        // Obtener DAOs
        usuarioDao = AppDatabase.getDatabase(this).usuarioDao();
        grupoDao = AppDatabase.getDatabase(this).grupoDao();

        // Recuperar ID del usuario desde SharedPreferences
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        int idUsuario = prefs.getInt("idUsuario", -1);

        if (idUsuario == -1) {
            Toast.makeText(this, "Error: Usuario no identificado", Toast.LENGTH_LONG).show();
            Log.e(TAG, "Error: Usuario no identificado en SharedPreferences");
            finish();
            return;
        }

        usuarioActual = usuarioDao.getCurrentUser(idUsuario);
        if (usuarioActual == null) {
            Toast.makeText(this, "Error: No se encontró el usuario en la base de datos", Toast.LENGTH_LONG).show();
            Log.e(TAG, "Error: usuarioActual es NULL");
            finish();
            return;
        }

        // Obtener grupo actual después de haber recuperado el usuario
        idGrupoUsuario = prefs.getInt("idGrupo", -1);

        if (idGrupoUsuario == -1) {
            Toast.makeText(this, "Aún no perteneces a un grupo.", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "El usuario no pertenece a ningún grupo");
            finish();
            return;
        }

        grupoActual = grupoDao.getGrupoById(idGrupoUsuario);
        if (grupoActual != null) {
            txtNombreGrupo.setText(grupoActual.nombreGrupo);
            txtCodigoInvitacion.setText("Código de Invitación: " + (grupoActual != null ? grupoActual.codigo_inv : "No disponible"));
        } else {
            Toast.makeText(this, "Error al cargar el grupo", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Error: grupoActual es NULL");
            finish();
            return;
        }

        // Mostrar el código y botón solo si el usuario es admin o superior
        if (usuarioActual.rol >= 1) {
            txtCodigoInvitacion.setVisibility(View.VISIBLE);
            btnCopiarCodigo.setVisibility(View.VISIBLE);
        } else {
            txtCodigoInvitacion.setVisibility(View.GONE);
            btnCopiarCodigo.setVisibility(View.GONE);
        }

        btnUnirseGrupo.setVisibility(View.GONE);
        btnSalirGrupo.setVisibility(View.VISIBLE);

        // Determinar el rol del usuario actual
        determinarRolUsuario();

        // Cargar lista de miembros
        cargarListaMiembros();

        // Actualizar la imagen de perfil
        actualizarImagenPerfil();

        // Configurar eventos de botones
        btnEditarNombreGrupo.setOnClickListener(v -> editarNombreGrupo());
        btnCopiarCodigo.setOnClickListener(v -> copiarCodigoInvitacion());
        btnSalirGrupo.setOnClickListener(v -> salirDelGrupo());
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizarImagenPerfil();
    }

    // Determinar el rol del usuario actual.
    private void determinarRolUsuario() {
        if (usuarioActual == null || grupoActual == null) {
            Toast.makeText(this, "Error al determinar el rol del usuario", Toast.LENGTH_SHORT).show();
            return;
        }

        if (grupoActual.id_admin == usuarioActual.id_usuario) {
            usuarioActual.rol = 2; // SuperAdmin
            Log.d(TAG, "Usuario es SuperAdmin");
        } else if (usuarioActual.rol == 1) {
            Toast.makeText(this, "Eres Admin del grupo", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Usuario es Admin");
        } else {
            usuarioActual.rol = 0; // Usuario normal
            Log.d(TAG, "Usuario es miembro normal");
        }
    }

    // Promover a Administrador.
    private void promoverAAdmin(Usuario usuario) {
        if (usuario.rol == 0) { // Solo promover usuarios normales
            usuarioDao.actualizarRol(usuario.id_usuario, 1);
            Toast.makeText(this, usuario.nombre + " ahora es Administrador", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Usuario promovido a Administrador: " + usuario.nombre);
            cargarListaMiembros();
        } else {
            Toast.makeText(this, "Este usuario ya es Administrador o SuperAdmin", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "Intento fallido de promoción. Usuario ya es Admin o SuperAdmin: " + usuario.nombre);
        }
    }

    // Método para actualizar la lista de miembros del grupo.
    public void actualizarListaUsuarios() {
        if (idGrupoUsuario == -1) {
            Toast.makeText(this, "Error: No se encontró el grupo", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "No se pudo actualizar la lista de usuarios. idGrupoUsuario = -1");
            return;
        }

        listaMiembros = usuarioDao.getUsuariosByGrupo(idGrupoUsuario);

        if (listaMiembros == null || listaMiembros.isEmpty()) {
            Toast.makeText(this, "No hay miembros en el grupo", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "No se encontraron miembros en el grupo.");
            return;
        }

        MiembrosAdapter adapter = new MiembrosAdapter(this, listaMiembros, usuarioActual, usuarioDao, grupoDao);
        listViewMiembros.setAdapter(adapter);
        Log.d(TAG, "Lista de miembros actualizada. Total: " + listaMiembros.size());
    }

    // Degradar a Usuario normal.
    private void degradarAUsuario(Usuario usuario) {
        if (usuario.rol == 1) { // Solo degradar Admins a usuarios normales
            usuarioDao.actualizarRol(usuario.id_usuario, 0);
            Toast.makeText(this, usuario.nombre + " ahora es Usuario normal", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Usuario degradado a Usuario normal: " + usuario.nombre);
            cargarListaMiembros();
        } else {
            Toast.makeText(this, "Solo los Admins pueden ser degradados", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "Intento fallido de degradación. Usuario no es Admin: " + usuario.nombre);
        }
    }

    // Expulsar a un usuario del grupo
    private void expulsarUsuario(Usuario usuario) {
        if (usuario.rol == 2) {
            Toast.makeText(this, "No puedes expulsar al SuperAdmin", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "Intento de expulsar al SuperAdmin bloqueado.");
            return;
        }

        usuarioDao.updateGrupo(usuario.id_usuario, -1); // Eliminar del grupo
        usuarioDao.actualizarRol(usuario.id_usuario, 0); // Volver a Usuario normal
        Toast.makeText(this, usuario.nombre + " ha sido expulsado del grupo", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Usuario expulsado del grupo: " + usuario.nombre);
        cargarListaMiembros();
    }

    // Mostrar diálogo para confirmar la expulsión de un miembro.
    private void confirmarExpulsion(Usuario usuario) {
        AlertDialog.Builder builder = new AlertDialog.Builder(GestionGrupoActivity.this);
        builder.setTitle("Confirmar Expulsión");
        builder.setMessage("¿Seguro que quieres expulsar a " + usuario.nombre + "?");
        builder.setPositiveButton("Sí", (dialog, which) -> expulsarUsuario(usuario));
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
        Log.d(TAG, "Diálogo de confirmación de expulsión mostrado para " + usuario.nombre);
    }

    // Cargar la lista de miembros del grupo.
    private void cargarListaMiembros() {
        if (idGrupoUsuario == -1) {
            Toast.makeText(this, "Error: No se encontró el grupo", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Error al cargar miembros. idGrupoUsuario = -1");
            return;
        }

        listaMiembros = usuarioDao.getUsuariosByGrupo(idGrupoUsuario);

        if (listaMiembros == null || listaMiembros.isEmpty()) {
            Toast.makeText(this, "No hay miembros en el grupo", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "El grupo no tiene miembros.");
            return;
        }

        MiembrosAdapter adapter = new MiembrosAdapter(this, listaMiembros, usuarioActual, usuarioDao, grupoDao);
        listViewMiembros.setAdapter(adapter);
        Log.d(TAG, "Miembros del grupo cargados. Total: " + listaMiembros.size());
    }

    // Editar el nombre del grupo a través de un AlertDialog.
    private void editarNombreGrupo() {
        if (usuarioActual.rol != 2) {
            Toast.makeText(this, "Solo el SuperAdmin puede cambiar el nombre del grupo", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "Intento de cambio de nombre bloqueado. Usuario no es SuperAdmin.");
            return;
        }

        if (grupoActual == null) {
            Toast.makeText(this, "Error: No se encontró el grupo", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Intento de edición de grupo fallido. grupoActual = null");
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Editar Nombre del Grupo");

        final EditText input = new EditText(this);
        input.setText(grupoActual.nombreGrupo);
        builder.setView(input);

        builder.setPositiveButton("Guardar", (dialog, which) -> {
            String nuevoNombre = input.getText().toString().trim();
            if (!nuevoNombre.isEmpty()) {
                grupoActual.nombreGrupo = nuevoNombre;
                grupoDao.updateGrupo(grupoActual);
                txtNombreGrupo.setText(nuevoNombre);
                Toast.makeText(this, "Nombre actualizado", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Nombre del grupo actualizado a: " + nuevoNombre);
            } else {
                Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
                Log.w(TAG, "Intento fallido de cambiar nombre. Campo vacío.");
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    // Copiar el código de invitación al portapapeles.
    private void copiarCodigoInvitacion() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Código de Invitación", grupoActual != null ? grupoActual.codigo_inv : "No disponible");
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Código copiado al portapapeles", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Código de invitación copiado: " + grupoActual.codigo_inv);
    }

    // Salir del grupo.
    private void salirDelGrupo() {
        if (usuarioActual.rol == 2) { // Si el usuario es SuperAdmin
            List<Usuario> miembrosGrupo = usuarioDao.getUsuariosByGrupo(usuarioActual.idGrupo);

            if (miembrosGrupo.size() == 1) { // Si solo queda él en el grupo
                eliminarGrupoYSalir();
                return;
            } else {
                Toast.makeText(this, "Debes transferir el rol de SuperAdmin antes de salir", Toast.LENGTH_LONG).show();
                return;
            }
        }

        // Para cualquier otro usuario normal o Admin
        usuarioDao.updateGrupo(usuarioActual.id_usuario, -1);
        usuarioDao.actualizarRol(usuarioActual.id_usuario, 0);

        // Actualizar SharedPreferences
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("idGrupo", -1);
        editor.apply();

        Toast.makeText(this, "Has salido del grupo", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Usuario salió del grupo");

        // Redirigir a la pantalla principal
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // Método para eliminar el grupo si el SuperAdmin es el único miembro.
    private void eliminarGrupoYSalir() {
        // Eliminar el grupo de la base de datos
        grupoDao.eliminarGrupo(usuarioActual.idGrupo);
        usuarioDao.updateGrupo(usuarioActual.id_usuario, -1);
        usuarioDao.actualizarRol(usuarioActual.id_usuario, 0);

        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("idGrupo", -1);
        editor.apply();

        Toast.makeText(this, "Has salido del grupo y el grupo ha sido eliminado", Toast.LENGTH_LONG).show();
        Log.d(TAG, "Grupo eliminado y usuario salió.");

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // Transferir el rol de SuperAdmin a otro miembro del grupo.
    private void transferirSuperAdmin() {
        List<Usuario> admins = usuarioDao.getAdminsByGrupo(idGrupoUsuario);
        if (admins.isEmpty()) {
            Toast.makeText(this, "No hay otros administradores para transferir el rol", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "No se encontró otro Admin para transferir el rol.");
            return;
        }

        String[] nombresAdmins = new String[admins.size()];
        for (int i = 0; i < admins.size(); i++) {
            nombresAdmins[i] = admins.get(i).nombre;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona el nuevo SuperAdmin");
        builder.setItems(nombresAdmins, (dialog, which) -> {
            Usuario nuevoSuperAdmin = admins.get(which);
            usuarioDao.actualizarRol(nuevoSuperAdmin.id_usuario, 2); // Asignar SuperAdmin
            usuarioDao.actualizarRol(usuarioActual.id_usuario, 1); // Degradar al actual a Admin
            Toast.makeText(this, "Rol transferido a " + nuevoSuperAdmin.nombre, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Rol de SuperAdmin transferido a " + nuevoSuperAdmin.nombre);
            finish();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    // Mostrar diálogo para crear o unirse a un grupo.
    private void mostrarDialogoSeleccionGrupo() {
        Log.d(TAG, "Mostrando diálogo para seleccionar entre crear o unirse a un grupo.");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona una opción");

        // Verificar si el usuario ya pertenece a un grupo antes de mostrar las opciones
        if (usuarioActual.idGrupo != -1) {
            Toast.makeText(this, "Ya perteneces a un grupo. Debes salir antes de unirte o crear otro.", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "Intento de crear o unirse a un grupo bloqueado. Usuario ya pertenece a un grupo.");
            return;
        }

        // Opción para crear un nuevo grupo
        builder.setPositiveButton("Crear Grupo", (dialog, which) -> {
            Log.d(TAG, "El usuario ha seleccionado 'Crear Grupo'. Redirigiendo a CrearGrupoActivity.");
            Intent intent = new Intent(GestionGrupoActivity.this, CrearGrupoActivity.class);
            startActivity(intent);
            finish();
        });

        // Opción para unirse a un grupo existente
        builder.setNegativeButton("Unirse a Grupo", (dialog, which) -> {
            Log.d(TAG, "El usuario ha seleccionado 'Unirse a Grupo'. Redirigiendo a UnirseGrupoActivity.");
            Intent intent = new Intent(GestionGrupoActivity.this, UnirseGrupoActivity.class);
            startActivity(intent);
            finish();
        });

        builder.setCancelable(true);
        builder.show();
        Log.d(TAG, "Diálogo de selección de grupo mostrado.");

    }

    // Actualizar la imagen de perfil
    private void actualizarImagenPerfil() {
        if (imgPerfilUsuario == null) {
            Log.e("GestionGrupoActivity", "❌ imgPerfilUsuario es NULL");
            return;
        }

        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String iconoPerfilUri = prefs.getString("iconoPerfil", null);

        if (iconoPerfilUri != null && !iconoPerfilUri.isEmpty()) {
            Uri imageUri = Uri.parse(iconoPerfilUri);
            imgPerfilUsuario.setImageURI(imageUri);
            Log.d(TAG, "✅ Imagen de perfil cargada: " + iconoPerfilUri);
        } else {
            imgPerfilUsuario.setImageResource(R.drawable.ic_perfil);
            Log.d(TAG, "🔹 Usando imagen de perfil predeterminada");
        }
    }
}
