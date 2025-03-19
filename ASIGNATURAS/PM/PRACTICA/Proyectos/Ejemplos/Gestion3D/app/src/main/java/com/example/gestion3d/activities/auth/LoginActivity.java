package com.example.gestion3d.activities.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gestion3d.R;
import com.example.gestion3d.activities.main.MainActivity;
import com.example.gestion3d.database.AppDatabase;
import com.example.gestion3d.database.dao.UsuarioDao;
import com.example.gestion3d.database.entities.Usuario;

public class LoginActivity extends AppCompatActivity {
    private EditText edtCorreo, edtContraseña; // Campos de entrada para correo y contraseña
    private ImageView btnTogglePassword; // Botón para mostrar/ocultar contraseña
    private Button btnIniciarSesion; // Botón para iniciar sesión
    private TextView btnIrRegistro; // Texto para ir a la pantalla de registro
    private UsuarioDao usuarioDao; // DAO para acceder a la base de datos de usuarios
    private static final String TAG = "LoginActivity"; // Etiqueta para logs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Oculta la ActionBar si está presente
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Establece el diseño de la actividad
        setContentView(R.layout.auth_login);

        // Inicializa las vistas
        edtCorreo = findViewById(R.id.edtCorreo);
        edtContraseña = findViewById(R.id.edtContraseña);
        btnTogglePassword = findViewById(R.id.btnTogglePassword);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnIrRegistro = findViewById(R.id.btnIrRegistro);

        // Obtiene la instancia del DAO de usuarios
        usuarioDao = AppDatabase.getDatabase(this).usuarioDao();

        // Configura el botón para alternar la visibilidad de la contraseña
        btnTogglePassword.setOnClickListener(v -> togglePasswordVisibility(edtContraseña, btnTogglePassword));

        // Configura el botón de inicio de sesión
        btnIniciarSesion.setOnClickListener(v -> iniciarSesion());

        // Configura el botón para ir a la pantalla de registro
        btnIrRegistro.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }

    // Alterna la visibilidad de la contraseña
    private void togglePasswordVisibility(EditText passwordField, ImageView toggleButton) {
        if (passwordField.getTransformationMethod() instanceof PasswordTransformationMethod) {
            // Muestra la contraseña
            passwordField.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            toggleButton.setImageResource(R.drawable.ic_eye_off);
        } else {
            // Oculta la contraseña
            passwordField.setTransformationMethod(PasswordTransformationMethod.getInstance());
            toggleButton.setImageResource(R.drawable.ic_eye);
        }
        passwordField.setSelection(passwordField.getText().length()); // Mantiene el cursor al final
    }

    // Valida las credenciales del usuario e inicia sesión
    private void iniciarSesion() {
        String correo = edtCorreo.getText().toString().trim();
        String contraseña = edtContraseña.getText().toString().trim();

        // Verificación de campos vacíos con mensajes de error específicos
        if (correo.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese su correo electrónico.", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Error: Campo de correo vacío");
            return;
        }
        if (!correo.contains("@") || !correo.contains(".")) {
            Toast.makeText(this, "Ingrese un correo electrónico válido.", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Error: Formato de correo inválido - " + correo);
            return;
        }
        if (contraseña.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese su contraseña.", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Error: Campo de contraseña vacío");
            return;
        }
        if (contraseña.length() < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres.", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Error: Contraseña demasiado corta");
            return;
        }

        // Consulta en la base de datos
        Usuario usuario = usuarioDao.login(correo, contraseña);

        if (usuario != null) {
            // Log de inicio de sesión exitoso
            Log.d(TAG, "Inicio de sesión exitoso - Usuario: " + usuario.correo);

            // Guarda la sesión del usuario en SharedPreferences
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

            // Mensaje de bienvenida
            Toast.makeText(this, "Bienvenido " + usuario.nombre, Toast.LENGTH_SHORT).show();

            // Redirige a la pantalla principal
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            // Log de error si las credenciales son incorrectas
            Log.d(TAG, "Error: Credenciales incorrectas para " + correo);
            Toast.makeText(this, "Correo o contraseña incorrectos. Verifique sus datos.", Toast.LENGTH_SHORT).show();
        }
    }
}
