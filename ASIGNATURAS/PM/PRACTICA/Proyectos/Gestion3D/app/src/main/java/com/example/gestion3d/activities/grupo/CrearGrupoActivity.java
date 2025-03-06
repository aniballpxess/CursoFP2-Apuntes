package com.example.gestion3d.activities.grupo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gestion3d.R;
import com.example.gestion3d.database.AppDatabase;
import com.example.gestion3d.database.dao.GrupoDao;
import com.example.gestion3d.database.dao.UsuarioDao;
import com.example.gestion3d.database.entities.Grupo;
import com.example.gestion3d.database.entities.Usuario;
import java.util.UUID;

public class CrearGrupoActivity extends AppCompatActivity {
    private EditText edtNombreGrupo;
    private Button btnCrearGrupo;
    private GrupoDao grupoDao;
    private UsuarioDao usuarioDao;
    private Usuario usuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grupo_crear);

        // Inicializar vistas
        edtNombreGrupo = findViewById(R.id.edtNombreGrupo);
        btnCrearGrupo = findViewById(R.id.btnCrearGrupo);

        // Inicializar DAOs
        grupoDao = AppDatabase.getDatabase(this).grupoDao();
        usuarioDao = AppDatabase.getDatabase(this).usuarioDao();

        // Obtener ID del usuario desde SharedPreferences
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        int idUsuario = prefs.getInt("idUsuario", -1);

        if (idUsuario == -1) {
            Toast.makeText(this, "Error: No se encontró el usuario", Toast.LENGTH_SHORT).show();
            Log.e("CrearGrupoActivity", "❌ No se encontró ID de usuario en SharedPreferences");
            finish();
            return;
        }

        // Obtener usuario actual de la BD
        usuarioActual = usuarioDao.getCurrentUser(idUsuario);

        if (usuarioActual == null) {
            Toast.makeText(this, "Error: Usuario no encontrado", Toast.LENGTH_SHORT).show();
            Log.e("CrearGrupoActivity", "❌ Usuario no encontrado en la base de datos");
            finish();
            return;
        }

        // Evento para crear grupo
        btnCrearGrupo.setOnClickListener(v -> crearGrupo());
    }

    // Método para crear un nuevo grupo
    private void crearGrupo() {
        String nombreGrupo = edtNombreGrupo.getText().toString().trim();

        if (nombreGrupo.isEmpty()) {
            Toast.makeText(this, "Ingrese un nombre para el grupo", Toast.LENGTH_SHORT).show();
            Log.w("CrearGrupoActivity", "⚠️ Intento de creación de grupo con nombre vacío");
            return;
        }

        // Generar un código único para el grupo
        String codigoInvitacion = UUID.randomUUID().toString().substring(0, 8);

        // Crear y guardar el nuevo grupo en la base de datos
        Grupo nuevoGrupo = new Grupo(nombreGrupo, usuarioActual.id_usuario, codigoInvitacion);
        int idGrupo = (int) grupoDao.insertGrupo(nuevoGrupo);
        Log.d("CrearGrupoActivity", "✅ Grupo creado con ID: " + idGrupo);

        // Actualizar usuario: asignarlo al grupo y convertirlo en SuperAdmin
        usuarioActual.idGrupo = idGrupo;
        usuarioActual.rol = 2;
        usuarioDao.update(usuarioActual);

        // Guardar datos en SharedPreferences
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("idGrupo", idGrupo);
        editor.putInt("rol", 2);
        editor.apply();

        Toast.makeText(this, "Grupo creado exitosamente", Toast.LENGTH_SHORT).show();
        Log.d("CrearGrupoActivity", "🚀 Redirigiendo a GestionGrupoActivity");

        // Ir a la pantalla de gestión del grupo
        Intent intent = new Intent(this, GestionGrupoActivity.class);
        startActivity(intent);
        finish();
    }
}