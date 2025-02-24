package com.example.gestion3d.activities.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gestion3d.R;
import com.example.gestion3d.activities.main.MainActivity;
import com.example.gestion3d.database.AppDatabase;
import com.example.gestion3d.database.dao.GrupoDao;
import com.example.gestion3d.database.dao.UsuarioDao;
import com.example.gestion3d.database.entities.Grupo;
import com.example.gestion3d.database.entities.Usuario;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtNombre, edtCorreo, edtContraseña, edtConfirmarContraseña, edtCodigoInvitacion;
    private Button btnRegistrar;
    private TextView btnIrLogin;
    private ImageView btnTogglePassword, btnToggleConfirmPassword;
    private UsuarioDao usuarioDao;
    private GrupoDao grupoDao;
    private static final String TAG = "RegisterActivity"; // Etiqueta para logs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_register);

        // Oculta la ActionBar si está presente
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Inicializa las vistas
        edtNombre = findViewById(R.id.edtNombre);
        edtCorreo = findViewById(R.id.edtCorreo);
        edtContraseña = findViewById(R.id.edtContraseña);
        edtConfirmarContraseña = findViewById(R.id.edtConfirmarContraseña);
        edtCodigoInvitacion = findViewById(R.id.edtCodigoInvitacion);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnIrLogin = findViewById(R.id.btnIrLogin);
        btnTogglePassword = findViewById(R.id.btnTogglePassword);
        btnToggleConfirmPassword = findViewById(R.id.btnToggleConfirmPassword);

        // Inicializa DAOs para manejar usuarios y grupos
        usuarioDao = AppDatabase.getDatabase(this).usuarioDao();
        grupoDao = AppDatabase.getDatabase(this).grupoDao();

        // Configura los botones para mostrar/ocultar contraseñas
        btnTogglePassword.setOnClickListener(v -> togglePasswordVisibility(edtContraseña, btnTogglePassword));
        btnToggleConfirmPassword.setOnClickListener(v -> togglePasswordVisibility(edtConfirmarContraseña, btnToggleConfirmPassword));

        // Configura el botón de registro
        btnRegistrar.setOnClickListener(v -> registrarUsuario());

        // Configura el botón para ir a la pantalla de inicio de sesión
        btnIrLogin.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }

    // Alterna la visibilidad de la contraseña
    private void togglePasswordVisibility(EditText passwordField, ImageView toggleButton) {
        if (passwordField.getTransformationMethod() instanceof PasswordTransformationMethod) {
            passwordField.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); // Muestra la contraseña
            toggleButton.setImageResource(R.drawable.ic_eye_off);
        } else {
            passwordField.setTransformationMethod(PasswordTransformationMethod.getInstance()); // Oculta la contraseña
            toggleButton.setImageResource(R.drawable.ic_eye);
        }
        passwordField.setSelection(passwordField.getText().length()); // Mantiene el cursor al final
    }

    // Registra un nuevo usuario con validaciones
    private void registrarUsuario() {
        String nombre = edtNombre.getText().toString().trim();
        String correo = edtCorreo.getText().toString().trim();
        String contraseña = edtContraseña.getText().toString().trim();
        String confirmarContraseña = edtConfirmarContraseña.getText().toString().trim();
        String codigoInvitacion = edtCodigoInvitacion.getText().toString().trim();

        // Validaciones con mensajes específicos
        if (nombre.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese su nombre.", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Error: Campo de nombre vacío");
            return;
        }
        if (correo.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese su correo electrónico.", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Error: Campo de correo vacío");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "Ingrese un correo electrónico válido.", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Error: Correo electrónico inválido - " + correo);
            return;
        }
        if (contraseña.isEmpty() || confirmarContraseña.isEmpty()) {
            Toast.makeText(this, "Ingrese y confirme su contraseña.", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Error: Campos de contraseña vacíos");
            return;
        }
        if (contraseña.length() < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres.", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Error: Contraseña demasiado corta");
            return;
        }
        if (!contraseña.equals(confirmarContraseña)) {
            Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Error: Las contraseñas no coinciden");
            return;
        }
        if (usuarioDao.checkUserExists(correo) != null) {
            Toast.makeText(this, "El correo ya está registrado.", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Error: Usuario ya registrado - " + correo);
            return;
        }

        // Obtiene la fecha actual
        String fechaCreacion = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        int idGrupo = -1; // Valor por defecto para usuarios sin grupo

        // Si el usuario ingresa un código de invitación, valida el grupo
        if (!codigoInvitacion.isEmpty()) {
            Grupo grupoExistente = grupoDao.getGrupoByCodigo(codigoInvitacion);
            if (grupoExistente == null) {
                Toast.makeText(this, "El código de invitación no es válido.", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Error: Código de invitación inválido - " + codigoInvitacion);
                return;
            }
            idGrupo = grupoExistente.id_grupo;
            Log.d(TAG, "Usuario se unió al grupo ID: " + idGrupo);
        }

        // Crea el usuario en la base de datos
        int rol = 0; // Rol por defecto (usuario normal)
        Usuario usuario = new Usuario(nombre, correo, contraseña, idGrupo, rol, fechaCreacion, "ic_perfil");
        usuarioDao.insert(usuario);
        Log.d(TAG, "Usuario registrado: " + nombre + " - " + correo);

        // Obtiene el usuario recién creado
        usuario = usuarioDao.login(correo, contraseña);
        if (usuario == null) {
            Log.d(TAG, "Error al recuperar el usuario recién registrado.");
            Toast.makeText(this, "Error al registrar el usuario, intente nuevamente.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guarda los datos del usuario en SharedPreferences
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.putInt("idUsuario", usuario.id_usuario);
        editor.putInt("idGrupo", usuario.idGrupo);
        editor.putInt("rol", usuario.rol);
        editor.putString("correo", usuario.correo);
        editor.putString("nombre", usuario.nombre);
        editor.putBoolean("sesionIniciada", true);
        editor.apply();

        Log.d(TAG, "Registro exitoso: Usuario ID " + usuario.id_usuario);
        Toast.makeText(this, "Registro exitoso. Bienvenido " + usuario.nombre, Toast.LENGTH_SHORT).show();

        // Redirige al usuario a la pantalla principal
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
