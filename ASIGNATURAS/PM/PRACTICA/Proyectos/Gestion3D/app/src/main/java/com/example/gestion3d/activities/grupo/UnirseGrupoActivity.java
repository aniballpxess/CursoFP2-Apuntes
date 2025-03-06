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

public class UnirseGrupoActivity extends AppCompatActivity {
    private EditText edtCodigoGrupo;
    private Button btnUnirseGrupo;
    private GrupoDao grupoDao;
    private UsuarioDao usuarioDao;
    private Usuario usuarioActual;
    private static final String TAG = "UnirseGrupoActivity"; // Etiqueta para logs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grupo_gestion);

        // Inicializar vistas
        edtCodigoGrupo = findViewById(R.id.edtCodigoGrupo);
        btnUnirseGrupo = findViewById(R.id.btnUnirseGrupo);

        // Inicializar DAOs
        grupoDao = AppDatabase.getDatabase(this).grupoDao();
        usuarioDao = AppDatabase.getDatabase(this).usuarioDao();

        // Obtener ID del usuario desde SharedPreferences
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        int idUsuario = prefs.getInt("idUsuario", -1);

        if (idUsuario == -1) {
            Toast.makeText(this, "Error: Usuario no identificado", Toast.LENGTH_LONG).show();
            Log.e(TAG, "❌ No se encontró ID de usuario en SharedPreferences");
            finish();
            return;
        }
        // Obtener usuario actual de la BD
        usuarioActual = usuarioDao.getCurrentUser(idUsuario);
        if (usuarioActual == null) {
            Toast.makeText(this, "Error: No se encontró el usuario en la base de datos", Toast.LENGTH_LONG).show();
            Log.e(TAG, "❌ Usuario no encontrado en la BD");
            finish();
            return;
        }
        // Configurar botón para unirse a un grupo
        btnUnirseGrupo.setOnClickListener(v -> unirseGrupo());
    }

    private void actualizarDatosSesion(int idGrupo, int rol) {
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("idGrupo", idGrupo);  // Guardamos el ID del grupo
        editor.putInt("rol", rol);  // Guardamos el rol del usuario
        editor.apply();  // Aplicamos los cambios
        Log.d(TAG, "Datos de sesión actualizados en SharedPreferences: idGrupo=" + idGrupo + ", rol=" + rol);
    }

    // Método para unirse a un grupo
    private void unirseGrupo() {
        if (usuarioActual == null) {
            Toast.makeText(this, "Error: No se encontró el usuario", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "❌ usuarioActual es NULL");
            return;
        }

        String codigoGrupo = edtCodigoGrupo.getText().toString().trim();

        if (codigoGrupo.isEmpty()) {
            Toast.makeText(this, "Ingrese un código de grupo", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "⚠️ Intento de unión con código vacío");
            return;
        }

        // Verificar si el grupo existe en la BD
        Grupo grupoExistente = grupoDao.getGrupoByCodigo(codigoGrupo);

        if (grupoExistente == null) {
            Toast.makeText(this, "Código de grupo inválido", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "❌ Código de grupo no encontrado: " + codigoGrupo);
            return;
        }

        // Verificar si el usuario ya pertenece a un grupo
        if (usuarioActual.idGrupo != -1) {
            Toast.makeText(this, "Ya perteneces a un grupo. Debes salir antes de unirte a otro.", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "⚠️ Usuario intentó unirse a otro grupo sin salir del actual");
            return;
        }

        // Asignar usuario al grupo con rol de usuario normal
        usuarioDao.updateGrupo(usuarioActual.id_usuario, grupoExistente.id_grupo);
        usuarioDao.actualizarRol(usuarioActual.id_usuario, 0);
        Log.d(TAG, "✅ Usuario " + usuarioActual.nombre + " se unió al grupo " + grupoExistente.nombreGrupo);

        // Guardar los datos del grupo en SharedPreferences
        actualizarDatosSesion(grupoExistente.id_grupo, 0);  // Guardamos el ID del grupo y el rol

        Toast.makeText(this, "Te has unido al grupo exitosamente", Toast.LENGTH_SHORT).show();

        // Redirigir a la gestión del grupo
        Intent intent = new Intent(this, GestionGrupoActivity.class);
        startActivity(intent);
        finish();
    }
}