package com.example.gestion3d.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.BaseAdapter;
import com.example.gestion3d.R;
import com.example.gestion3d.database.dao.GrupoDao;
import com.example.gestion3d.database.dao.UsuarioDao;
import com.example.gestion3d.database.entities.Usuario;
import com.example.gestion3d.activities.main.MainActivity;
import java.util.List;
import android.net.Uri;

public class MiembrosAdapter extends BaseAdapter {
    private Context context;
    private List<Usuario> miembros;
    private Usuario usuarioActual;
    private UsuarioDao usuarioDao;
    private GrupoDao grupoDao;
    private static final String TAG = "MiembrosAdapter"; // Etiqueta para logs

    public MiembrosAdapter(Context context, List<Usuario> miembros, Usuario usuarioActual, UsuarioDao usuarioDao, GrupoDao grupoDao) {
        this.context = context;
        this.miembros = miembros;
        this.usuarioActual = usuarioActual;
        this.usuarioDao = usuarioDao;
        this.grupoDao = grupoDao;
    }

    @Override
    public int getCount() {
        return miembros.size();
    }

    @Override
    public Object getItem(int position) {
        return miembros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_miembro, parent, false);
        }

        Usuario usuario = miembros.get(position);

        // Referencias a los elementos de la vista
        TextView txtNombre = convertView.findViewById(R.id.txtNombre);
        TextView txtCorreo = convertView.findViewById(R.id.txtCorreo);
        ImageView imgPerfil = convertView.findViewById(R.id.imgPerfil);
        ImageView btnOpciones = convertView.findViewById(R.id.btnOpciones);

        // Determinar rol del usuario
        String rol = usuario.rol == 2 ? " (SuperAdmin)" : usuario.rol == 1 ? " (Admin)" : "";
        txtNombre.setText(usuario.nombre + rol);
        txtCorreo.setText(usuario.correo);

        Log.d("ListAdapter", "Cargando usuario: " + usuario.nombre + " | ID: " + usuario.id_usuario + " | Rol: " + usuario.rol);

        // Cargar imagen de perfil desde SharedPreferences
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        String iconoPerfilUri = prefs.getString("iconoPerfil_" + usuario.id_usuario, null);

        boolean imagenCargada = false;

        if (iconoPerfilUri != null && !iconoPerfilUri.isEmpty()) {
            imgPerfil.setImageURI(Uri.parse(iconoPerfilUri));
            imagenCargada = true;
            Log.d("ListAdapter", "🔹 Imagen de perfil cargada desde SharedPreferences para usuario ID: " + usuario.id_usuario + " | URI: " + iconoPerfilUri);
        } else {
            Log.d("ListAdapter", "⚠️ No se encontró imagen en SharedPreferences para usuario ID: " + usuario.id_usuario);
        }

        // Si no se cargó la imagen desde SharedPreferences, usar la de la base de datos o la predeterminada
        if (!imagenCargada) {
            String iconoActual = usuario.iconoPerfil != null ? usuario.iconoPerfil : "ic_perfil";
            int iconResId = context.getResources().getIdentifier(iconoActual, "drawable", context.getPackageName());
            imgPerfil.setImageResource(iconResId);
            Log.d("ListAdapter", "🖼️ Cargando imagen desde la BD o recurso predeterminado para usuario ID: " + usuario.id_usuario + " | Recurso: " + iconoActual);
        }

        // Ocultar botón de opciones en el mismo usuario o si es SuperAdmin
        if (usuario.id_usuario == usuarioActual.id_usuario || usuario.rol == 2) {
            btnOpciones.setVisibility(View.GONE);
            Log.d("ListAdapter", "🔒 Ocultando botón de opciones para usuario ID: " + usuario.id_usuario + " (mismo usuario o SuperAdmin)");
        } else {
            btnOpciones.setVisibility(View.VISIBLE);
            btnOpciones.setOnClickListener(v -> mostrarMenuOpciones(usuario, v));
            Log.d("ListAdapter", "✅ Mostrando botón de opciones para usuario ID: " + usuario.id_usuario);
        }

        return convertView;
    }

    private void mostrarMenuOpciones(Usuario usuario, View view) {
        PopupMenu popup = new PopupMenu(context, view);
        popup.getMenuInflater().inflate(R.menu.menu_miembro, popup.getMenu());

        // Ocultar la opción de promover si el usuario ya es Admin o SuperAdmin
        if (usuario.rol >= 1) {
            popup.getMenu().findItem(R.id.menu_promover_admin).setVisible(false);
        }

        // Ocultar la opción de degradar si el usuario ya es Usuario Normal
        if (usuario.rol == 0) {
            popup.getMenu().findItem(R.id.menu_degradar_usuario).setVisible(false);
        }

        // No permitir que los usuarios normales expulsen a otros
        if (usuarioActual.rol < 1 || usuario.rol >= usuarioActual.rol) {
            popup.getMenu().findItem(R.id.menu_expulsar).setVisible(false);
        }

        // Evitar que el usuario actual se expulse a sí mismo
        if (usuarioActual.id_usuario == usuario.id_usuario) {
            popup.getMenu().findItem(R.id.menu_expulsar).setVisible(false);
        }

        Log.d("MiembrosAdapter", " Menú de opciones mostrado para: " + usuario.nombre);

        popup.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.menu_promover_admin) {
                promoverAAdmin(usuario);
                return true;
            } else if (itemId == R.id.menu_degradar_usuario) {
                degradarAUsuario(usuario);
                return true;
            } else if (itemId == R.id.menu_expulsar) {
                expulsarUsuario(usuario);
                return true;
            }

            return false;
        });

        popup.show();
    }

    // Método para expulsar a un miembro del grupo.
    private void expulsarMiembro(Usuario usuario) {
        usuarioDao.eliminarUsuario(usuario.id_usuario);
        miembros.remove(usuario);
        notifyDataSetChanged();

        Toast.makeText(context, usuario.nombre + " ha sido expulsado", Toast.LENGTH_SHORT).show();
        Log.w("MiembrosAdapter", "❌ Usuario expulsado: " + usuario.nombre);
    }

    private void promoverAAdmin(Usuario usuario) {
        if (usuario.rol == 0) { // Solo promover usuarios normales
            usuarioDao.actualizarRol(usuario.id_usuario, 1);
            Toast.makeText(context, usuario.nombre + " ahora es Administrador", Toast.LENGTH_SHORT).show();
            Log.d("MiembrosAdapter", "✅ Usuario promovido a Admin: " + usuario.nombre);
            actualizarListaMiembros();
        } else {
            Toast.makeText(context, "Este usuario ya es Administrador o superior", Toast.LENGTH_SHORT).show();
            Log.w("MiembrosAdapter", "⚠️ Intento de promover a un usuario que ya es Admin");
        }
    }

    // Método para degradar a un usuario a usuario normal
    private void degradarAUsuario(Usuario usuario) {
        if (usuario.rol == 1) { // Solo degradar admins a usuarios normales
            usuarioDao.actualizarRol(usuario.id_usuario, 0);
            Toast.makeText(context, usuario.nombre + " ahora es Usuario normal", Toast.LENGTH_SHORT).show();
            Log.d("MiembrosAdapter", "🔽 Usuario degradado a normal: " + usuario.nombre);
            actualizarListaMiembros();
        } else {
            Toast.makeText(context, "Este usuario ya es un usuario normal", Toast.LENGTH_SHORT).show();
            Log.w("MiembrosAdapter", "⚠️ Intento de degradar a un usuario que ya es normal");
        }
    }

    // Método para expulsar a un usuario del grupo
    private void expulsarUsuario(Usuario usuario) {
        if (usuario.id_usuario == usuarioActual.id_usuario) {
            Toast.makeText(context, "No puedes expulsarte a ti mismo", Toast.LENGTH_SHORT).show();
            Log.w("MiembrosAdapter", "⚠️ Intento de autoexpulsión detectado");
            return;
        }
        if (usuario.rol == 2) {
            Toast.makeText(context, "No puedes expulsar al SuperAdmin", Toast.LENGTH_SHORT).show();
            Log.e("MiembrosAdapter", "❌ Intento de expulsar al SuperAdmin");
            return;
        }

        usuarioDao.updateGrupo(usuario.id_usuario, -1); // Lo saca del grupo
        usuarioDao.actualizarRol(usuario.id_usuario, 0); // Se vuelve usuario normal
        miembros.remove(usuario);
        notifyDataSetChanged();

        Toast.makeText(context, usuario.nombre + " ha sido expulsado del grupo", Toast.LENGTH_SHORT).show();
        Log.d("MiembrosAdapter", "🚀 Usuario expulsado: " + usuario.nombre);
    }

    // Método para salir del grupo
    private void salirDelGrupo() {
        if (usuarioActual.rol == 2) { // Si es SuperAdmin
            List<Usuario> miembrosGrupo = usuarioDao.getUsuariosByGrupo(usuarioActual.idGrupo);

            if (miembrosGrupo.size() == 1) { // Solo queda él en el grupo
                eliminarGrupoYSalir();
                return;
            }
        }

        // Si no es el único miembro o no es SuperAdmin, simplemente salir del grupo
        usuarioDao.updateGrupo(usuarioActual.id_usuario, -1);
        usuarioDao.actualizarRol(usuarioActual.id_usuario, 0); // Se vuelve usuario normal

        // Actualizar SharedPreferences
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("idGrupo", -1);
        editor.apply();

        Toast.makeText(context, "Has salido del grupo", Toast.LENGTH_SHORT).show();

        // Redirigir a la pantalla principal
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    // Método para eliminar el grupo y salir
    private void eliminarGrupoYSalir() {
        usuarioDao.eliminarUsuariosDeGrupo(usuarioActual.idGrupo);
        grupoDao.eliminarGrupo(usuarioActual.idGrupo);

        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("idGrupo", -1);
        editor.apply();

        Toast.makeText(context, "El grupo ha sido eliminado", Toast.LENGTH_LONG).show();
        Log.w("MiembrosAdapter", "❌ Grupo eliminado: ID " + usuarioActual.idGrupo);

        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    // Método para actualizar la lista de miembros
    private void actualizarListaMiembros() {
        miembros.clear();
        miembros.addAll(usuarioDao.getUsuariosByGrupo(usuarioActual.idGrupo));
        notifyDataSetChanged();
        Log.d("MiembrosAdapter", "Actualizando lista de miembros");
    }
}