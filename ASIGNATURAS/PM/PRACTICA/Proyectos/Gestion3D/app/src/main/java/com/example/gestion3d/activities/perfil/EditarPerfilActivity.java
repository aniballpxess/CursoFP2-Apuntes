package com.example.gestion3d.activities.perfil;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import com.example.gestion3d.R;
import com.example.gestion3d.database.AppDatabase;
import com.example.gestion3d.database.dao.UsuarioDao;
import com.example.gestion3d.database.entities.Usuario;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class EditarPerfilActivity extends AppCompatActivity {
    private static final String TAG = "EditarPerfilActivity"; // Etiqueta para Logs

    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_GALLERY = 2;
    private static final int PERMISSION_REQUEST_CODE = 100;


    private void solicitarPermisos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Para Android 6+
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            } else {
                abrirGaleria(); // Si ya tiene permiso, abrir la galería directamente
            }
        } else {
            abrirGaleria(); // En versiones antiguas, abrir directamente
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                abrirGaleria();
            } else {
                Toast.makeText(this, "Permiso denegado. No se puede acceder a la galería.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_GALLERY);
    }



    private EditText edtNombre, edtCorreo;
    private ImageView imgPerfil, btnEditarImagen;
    private Button btnGuardarCambios, btnCambiarContrasena;
    private UsuarioDao usuarioDao;
    private Usuario usuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_editar);
        Log.d(TAG, "onCreate: EditarPerfilActivity iniciada");

        // Inicializar vistas
        edtNombre = findViewById(R.id.edtNombre);
        edtCorreo = findViewById(R.id.edtCorreo);
        imgPerfil = findViewById(R.id.imgPerfil);
        btnEditarImagen = findViewById(R.id.btnEditarImagen);
        btnGuardarCambios = findViewById(R.id.btnGuardarCambios);
        btnCambiarContrasena = findViewById(R.id.btnCambiarContrasena);

        // Inicializar DAO
        usuarioDao = AppDatabase.getDatabase(this).usuarioDao();

        // Recuperar ID del usuario desde SharedPreferences
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        int idUsuario = prefs.getInt("idUsuario", -1);

        if (idUsuario == -1) {
            Log.e(TAG, "Error: No se encontró el usuario en SharedPreferences");
            Toast.makeText(this, "Error: No se encontró el usuario", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // 🔹 Obtener usuario desde la base de datos
        usuarioActual = usuarioDao.getUsuarioById(idUsuario);

        if (usuarioActual != null) {
            // Cargar datos del usuario en los campos
            edtNombre.setText(usuarioActual.nombre);
            edtCorreo.setText(usuarioActual.correo);
            cargarImagenPerfil();

            Log.d(TAG, "Usuario cargado: " + usuarioActual.nombre);

            // 🔹 Cargar icono guardado o el predeterminado
            String iconoActual = usuarioActual.iconoPerfil != null ? usuarioActual.iconoPerfil : "ic_perfil";
            int iconResId = getResources().getIdentifier(iconoActual, "drawable", getPackageName());
            imgPerfil.setImageResource(iconResId);

            Log.d("EditarPerfilActivity", "✅ Usuario cargado: " + usuarioActual.nombre + " | Icono: " + usuarioActual.iconoPerfil);
        } else {
            Log.e(TAG, "Error: usuarioActual es NULL. No se pudo cargar el usuario.");
            Toast.makeText(this, "Error al cargar el perfil", Toast.LENGTH_SHORT).show();
            finish();
        }

        // 🔹 Configurar eventos de los botones
        btnGuardarCambios.setOnClickListener(v -> guardarCambios());
        btnCambiarContrasena.setOnClickListener(v -> mostrarDialogoCambiarContrasena());
        btnEditarImagen.setOnClickListener(v -> mostrarOpcionesSeleccionImagen());
    }

    private void guardarImagenPerfil(Bitmap bitmap) {
        FileOutputStream fos = null;
        try {
            File dir = new File(getFilesDir(), "perfil_imagen");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, "perfil_" + usuarioActual.id_usuario + ".jpg");
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // Método para cargar la imagen de perfil desde SharedPreferences o la base de datos
    private void cargarImagenPerfil() {
        if (usuarioActual == null) return;

        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String iconoPerfilUri = prefs.getString("iconoPerfil_" + usuarioActual.id_usuario, null);

        if (iconoPerfilUri == null || iconoPerfilUri.isEmpty()) {
            iconoPerfilUri = usuarioActual.iconoPerfil; // Obtener de la BD si no está en SharedPreferences
        }

        if (iconoPerfilUri != null && !iconoPerfilUri.isEmpty()) {
            imgPerfil.setImageURI(Uri.parse(iconoPerfilUri));
            Log.d(TAG, "Imagen de perfil cargada desde: " + iconoPerfilUri);
        } else {
            imgPerfil.setImageResource(R.drawable.ic_perfil); // Imagen predeterminada
            Log.d(TAG, "Se cargó la imagen predeterminada");
        }
    }

    // Maneja el resultado de la actividad de la cámara o la galería
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Uri imageUri = null;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                imageUri = MediaStore.setRequireOriginal(data.getData());
            } else {
                imageUri = data.getData();
            }
            if (requestCode == REQUEST_CAMERA && data.getExtras() != null) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                imageUri = getImageUri(getApplicationContext(), imageBitmap);
            } else if (requestCode == REQUEST_GALLERY) {
                imageUri = data.getData();
            }

            if (imageUri != null) {
                imgPerfil.setImageURI(imageUri);
                Log.d(TAG, "✅ Imagen seleccionada: " + imageUri.toString());

                if (usuarioActual == null) {
                    Log.e(TAG, "⚠️ ERROR: usuarioActual es NULL. No se puede actualizar la imagen.");
                    return;
                }

                // Guardar la imagen en la BD y en SharedPreferences
                usuarioDao.actualizarIconoPerfil(usuarioActual.id_usuario, imageUri.toString());
                usuarioActual.iconoPerfil = imageUri.toString();

                SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("iconoPerfil_" + usuarioActual.id_usuario, imageUri.toString());
                editor.apply();

                Log.d(TAG, "✅ Imagen de perfil actualizada en la base de datos para el usuario ID: " + usuarioActual.id_usuario);
            } else {
                Log.e(TAG, "⚠️ ERROR: No se pudo obtener la URI de la imagen.");
            }
        }
    }

    // Método para convertir Bitmap en Uri
    private Uri getImageUri(Context context, Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Perfil_" + System.currentTimeMillis(), null);
        return Uri.parse(path);
    }

    // Guarda los cambios en la base de datos
    private void guardarCambios() {
        String nuevoNombre = edtNombre.getText().toString().trim();
        String nuevoCorreo = edtCorreo.getText().toString().trim();

        if (nuevoNombre.isEmpty() || nuevoCorreo.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(nuevoCorreo).matches()) {
            Toast.makeText(this, "Ingrese un correo válido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Actualizar usuario en la base de datos
        usuarioActual.nombre = nuevoNombre;
        usuarioActual.correo = nuevoCorreo;
        usuarioDao.update(usuarioActual);

        // 🔹 Guardar datos actualizados en SharedPreferences
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("nombre", nuevoNombre);
        editor.putString("correo", nuevoCorreo);
        editor.apply();

        Toast.makeText(this, "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Perfil actualizado: " + nuevoNombre + " - " + nuevoCorreo);
        finish();
    }

    // Abre la cámara para tomar una foto y usarla como imagen de perfil.
    private void abrirCamara() {
        Log.d(TAG, "Intentando abrir la cámara...");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CAMERA);
            Log.d(TAG, "Cámara abierta con éxito.");
        } else {
            Log.e(TAG, "No se encontró una aplicación de cámara.");
            Toast.makeText(this, "No se encontró una aplicación de cámara", Toast.LENGTH_SHORT).show();
        }
    }

    // Muestra un diálogo con opciones para cambiar la imagen de perfil.
    private void mostrarOpcionesSeleccionImagen() {
        Log.d(TAG, "Mostrando opciones para seleccionar imagen...");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar Imagen");

        String[] opciones = {"Tomar Foto", "Elegir de Galería", "Seleccionar un Icono"};
        builder.setItems(opciones, (dialog, which) -> {
            switch (which) {
                case 0:
                    abrirCamara();
                    break;
                case 1:
                    abrirGaleria();
                    break;
                case 2:
                    seleccionarIcono();
                    break;
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> {
            Log.d(TAG, "Selección de imagen cancelada.");
            dialog.dismiss();
        });

        builder.show();
    }

    // Muestra un diálogo para seleccionar un icono de perfil en lugar de una imagen personalizada.
    private void seleccionarIcono() {
        Log.d(TAG, "Mostrando diálogo de selección de icono...");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_seleccion_icono, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        // Asigna eventos a los iconos disponibles.
        dialogView.findViewById(R.id.card_icono1).setOnClickListener(v -> actualizarIcono(dialog, "among_us1"));
        dialogView.findViewById(R.id.card_icono2).setOnClickListener(v -> actualizarIcono(dialog, "among_us2"));
        dialogView.findViewById(R.id.card_icono3).setOnClickListener(v -> actualizarIcono(dialog, "among_us3"));
        dialogView.findViewById(R.id.card_icono4).setOnClickListener(v -> actualizarIcono(dialog, "among_us4"));
        dialogView.findViewById(R.id.card_icono5).setOnClickListener(v -> actualizarIcono(dialog, "chikorita"));
        dialogView.findViewById(R.id.card_icono6).setOnClickListener(v -> actualizarIcono(dialog, "bulbasaur"));
        dialogView.findViewById(R.id.card_icono7).setOnClickListener(v -> actualizarIcono(dialog, "goku"));
        dialogView.findViewById(R.id.card_icono8).setOnClickListener(v -> actualizarIcono(dialog, "kitana"));
        dialogView.findViewById(R.id.card_icono9).setOnClickListener(v -> actualizarIcono(dialog, "squirtle"));

        dialog.show();
    }

    // Actualiza la imagen de perfil con un icono seleccionado y lo guarda en la base de datos.
    private void actualizarIcono(AlertDialog dialog, String nuevoIcono) {
        Log.d(TAG, "Actualizando icono de perfil: " + nuevoIcono);
        usuarioDao.actualizarIconoPerfil(usuarioActual.id_usuario, nuevoIcono);
        usuarioActual.iconoPerfil = nuevoIcono;

        int iconResId = getResources().getIdentifier(nuevoIcono, "drawable", getPackageName());
        imgPerfil.setImageResource(iconResId);

        dialog.dismiss();
        Toast.makeText(this, "Icono de perfil actualizado", Toast.LENGTH_SHORT).show();
    }

    // Muestra un diálogo para cambiar la contraseña del usuario.
    private void mostrarDialogoCambiarContrasena() {
        Log.d(TAG, "Mostrando diálogo de cambio de contraseña...");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_cambiar_contrasena, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        // Referencias a los campos del diálogo
        EditText edtActual = dialogView.findViewById(R.id.edtContrasenaActual);
        EditText edtNueva = dialogView.findViewById(R.id.edtNuevaContrasena);
        EditText edtConfirmar = dialogView.findViewById(R.id.edtConfirmarContrasena);
        Button btnConfirmar = dialogView.findViewById(R.id.btnConfirmar);
        Button btnCancelar = dialogView.findViewById(R.id.btnCancelar);

        // Configura el botón de confirmación para actualizar la contraseña.
        btnConfirmar.setOnClickListener(v -> {
            String contrasenaActual = edtActual.getText().toString().trim();
            String nuevaContrasena = edtNueva.getText().toString().trim();
            String confirmarContrasena = edtConfirmar.getText().toString().trim();

            if (contrasenaActual.isEmpty() || nuevaContrasena.isEmpty() || confirmarContrasena.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Error: Alguno de los campos de contraseña está vacío.");
                return;
            }

            // Verificar si la contraseña actual ingresada es correcta
            if (!usuarioActual.contrasena.equals(contrasenaActual)) {
                Toast.makeText(this, "La contraseña actual es incorrecta", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Error: La contraseña actual ingresada no coincide.");
                return;
            }

            if (nuevaContrasena.length() < 6) {
                Toast.makeText(this, "La nueva contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Error: La nueva contraseña es demasiado corta.");
                return;
            }

            if (!nuevaContrasena.equals(confirmarContrasena)) {
                Toast.makeText(this, "Las nuevas contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Error: Las nuevas contraseñas no coinciden.");
                return;
            }

            // Actualizar la contraseña en la base de datos
            usuarioDao.cambiarContrasena(usuarioActual.correo, nuevaContrasena);
            usuarioActual.contrasena = nuevaContrasena;
            Log.d(TAG, "Contraseña actualizada correctamente.");

            Toast.makeText(this, "Contraseña actualizada correctamente", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        // Configura el botón de cancelar para cerrar el diálogo sin cambios.
        btnCancelar.setOnClickListener(v -> {
            Log.d(TAG, "Cambio de contraseña cancelado.");
            dialog.dismiss();
        });

        dialog.show();
    }
}