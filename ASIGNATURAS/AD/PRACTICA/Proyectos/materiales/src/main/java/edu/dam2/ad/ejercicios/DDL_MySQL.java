package edu.dam2.ad.ejercicios;

import java.sql.*;
import java.util.Scanner;

public class DDL_MySQL {
    private static final String URL = "jdbc:mysql://localhost:3306/videogames_db";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "root_pswd";
    private static Connection conexion = null;

    // Establecer conexion con la base de datos
    public static void conectar() {
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexion a la base de datos establecida.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos:%n%s%n".formatted(e.getMessage()), e.getCause());
        }
    }

    // Cerrar la conexion con la base de datos
    public static void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexion a la base de datos cerrada.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cerrar la conexion con la base de datos:%n%s%n".formatted(e.getMessage()), e.getCause());
        }
    }

    // Metodo para crear tabla
    public static void crearTabla() {
        String crearTablaSQL = """
                CREATE TABLE IF NOT EXISTS videojuegos (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    titulo VARCHAR(255) NOT NULL,
                    genero VARCHAR(100),
                    fecha_lanzamiento DATE,
                    plataforma VARCHAR(100),
                    desarrollador VARCHAR(150),
                    editor VARCHAR(150),
                    calificacion DECIMAL(3,1),
                    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                );
                """;

        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(crearTablaSQL);
            System.out.println("Tabla 'videojuegos' creada exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla: " + e.getMessage());
        }
    }

    // Metodo para eliminar tabla
    public static void eliminarTabla() {
        String eliminarTablaSQL = "DROP TABLE IF EXISTS videojuegos";

        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(eliminarTablaSQL);
            System.out.println("Tabla 'videojuegos' eliminada exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar la tabla: " + e.getMessage());
        }
    }

    // Metodo para modificar tabla (e.g., agregar una nueva columna)
    public static void modificarTabla() {
        String modificarTablaSQL = "ALTER TABLE videojuegos ADD COLUMN puntuacion_popularidad INT DEFAULT 0";

        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(modificarTablaSQL);
            System.out.println("Tabla 'videojuegos' modificada exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al modificar la tabla: " + e.getMessage());
        }
    }

    // Metodo para insertar datos iniciales
    public static void insertarDatos() {
        String insertarSQL = """
                INSERT INTO videojuegos (titulo, genero, fecha_lanzamiento, plataforma, desarrollador, editor, calificacion)
                VALUES
                    ('The Legend of Zelda: Breath of the Wild', 'Accion-aventura', '2017-03-03', 'Nintendo Switch', 'Nintendo', 'Nintendo', 9.7),
                    ('Elden Ring', 'RPG de Accion', '2022-02-25', 'PC', 'FromSoftware', 'Bandai Namco', 9.4),
                    ('Cyberpunk 2077', 'RPG de Accion', '2020-12-10', 'PC', 'CD Projekt Red', 'CD Projekt', 7.8);
                """;

        try (Statement stmt = conexion.createStatement()) {
            stmt.executeUpdate(insertarSQL);
            System.out.println("Datos iniciales insertados exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar datos: " + e.getMessage());
        }
    }

    // Metodo para mostrar datos con consulta SELECT
    public static void mostrarDatos() {
        String seleccionarSQL = "SELECT * FROM videojuegos";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(seleccionarSQL)) {

            System.out.println("Datos de la tabla 'videojuegos':");
            while (rs.next()) {
                System.out.printf("ID: %d, Titulo: %s, Genero: %s, Calificacion: %.1f%n",
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("genero"),
                        rs.getDouble("calificacion"));
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar datos: " + e.getMessage());
        }
    }

    // Menu para operaciones DDL
    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\nMenu:");
            System.out.println("1. Crear Tabla");
            System.out.println("2. Eliminar Tabla");
            System.out.println("3. Modificar Tabla (Agregar columna)");
            System.out.println("4. Insertar Datos Iniciales");
            System.out.println("5. Mostrar Datos");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    crearTabla();
                    mostrarDatos(); // Mostrar datos tras crear tabla
                }
                case 2 -> eliminarTabla();
                case 3 -> {
                    modificarTabla();
                    mostrarDatos(); // Mostrar datos tras modificar tabla
                }
                case 4 -> {
                    insertarDatos();
                    mostrarDatos(); // Mostrar datos tras insertar datos
                }
                case 5 -> mostrarDatos();
                case 6 -> salir = true;
                default -> System.out.println("Opcion invalida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        conectar();
        mostrarMenu();
        desconectar();
    }
}