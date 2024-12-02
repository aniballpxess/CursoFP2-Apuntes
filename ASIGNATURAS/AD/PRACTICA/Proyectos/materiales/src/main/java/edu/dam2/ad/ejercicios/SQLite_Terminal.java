package edu.dam2.ad.ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite_Terminal {
    private static final String URL = "jdbc:sqlite:database.db";

    private static Connection conexion = null;

    public static void conectarse() {
        try {
            conexion = DriverManager.getConnection(URL);
            System.out.println("Conexion a la base de datos establecida.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos:%n%s%n".formatted(e.getMessage()), e.getCause());
        }
    }

    private static void desconectarse() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexion a la base de datos cerrada.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cerrar la conexion con la base de datos:%n%s%n".formatted(e.getMessage()), e.getCause());
        }
    }

    public static void main(String[] args) {
        conectarse();

        desconectarse();
    }

}
