package com.example.gestion3d.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.gestion3d.database.dao.*;
import com.example.gestion3d.database.entities.*;

@Database(entities = {Material.class, Producto.class, CategoriaProducto.class, Cliente.class, OrdenTrabajo.class,
        ProductoMaterial.class, OrdenMaterial.class, HistorialMovimiento.class, Usuario.class, Grupo.class},
        version = 8, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    // Definicion de los DAOs (Interfaces que permiten interactuar con la BD)
    public abstract MaterialDao materialDao();
    public abstract ProductoDao productoDao();
    public abstract CategoriaProductoDao categoriaProductoDao();
    public abstract ClienteDao clienteDao();
    public abstract OrdenTrabajoDao ordenTrabajoDao();
    public abstract ProductoMaterialDao productoMaterialDao();
    public abstract OrdenMaterialDao ordenMaterialDao();
    public abstract HistorialMovimientoDao historialMovimientoDao();
    public abstract UsuarioDao usuarioDao();
    public abstract GrupoDao grupoDao();


    // Metodo para obtener la instancia de la base de datos
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) { // Asegura que solo se crea una instancia a la vez
                if (INSTANCE == null) {
                    SharedPreferences prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
                    int idGrupo = prefs.getInt("idGrupo", -1);

                    // Verificación de si hay un grupo asignado en SharedPreferences
                    if (idGrupo == -1) {
                        Log.w("AppDatabase", " No se ha asignado un grupo. La base de datos se cargará sin datos de grupo.");
                    }

                    // Creación de la base de datos con migración destructiva permitida
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class, "gestion3d_database"
                            )
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                    Log.d("AppDatabase", "✅ Base de datos inicializada correctamente");
                }
            }
        }
        return INSTANCE;
    }
}