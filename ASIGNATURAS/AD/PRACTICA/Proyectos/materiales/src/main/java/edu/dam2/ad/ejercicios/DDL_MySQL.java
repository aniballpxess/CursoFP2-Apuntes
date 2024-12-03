package edu.dam2.ad.ejercicios;

import java.sql.*;
import java.util.Scanner;

public class DDL_MySQL {

    private static final String URL = "jdbc:mysql://localhost:3306/videogames_db";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "root_pswd";

    private static Connection conexion = null;

    private static String tablaActual = null;

    /**
     * Abre la conexión con la base de datos.
     */
    public static void conectarse() {
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexion a la base de datos establecida.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos:%n%s%n".formatted(e.getMessage()), e.getCause());
        }
    }

    /**
     * Cierra la conexión con la base de datos.
     */
    public static void desconectarse() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexion a la base de datos cerrada.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cerrar la conexion con la base de datos:%n%s%n".formatted(e.getMessage()), e.getCause());
        }
    }

    public static void crearTabla() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(" - nombre de la tabla: ");
        String nombreTabla = scanner.nextLine().trim();
        if (nombreTabla.isEmpty()) {
            System.out.println("El nombre de la tabla no puede estar vacio.");
            return;
        }

        String crearTablaSQL = """
            CREATE TABLE IF NOT EXISTS %s (
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
            """.formatted(nombreTabla);

        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(crearTablaSQL);
            System.out.println("Tabla '%s' creada exitosamente.".formatted(nombreTabla));
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla: " + e.getMessage());
        }
    }

    public static void eliminarTabla() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(" - nombre de la tabla: ");
        String nombreTabla = scanner.nextLine().trim();
        if (nombreTabla.isEmpty()) {
            System.out.println("El nombre de la tabla no puede estar vacio.");
            return;
        }

        String eliminarTablaSQL = "DROP TABLE IF EXISTS %s".formatted(nombreTabla);

        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(eliminarTablaSQL);
            System.out.println("Tabla '%s' eliminada exitosamente.".formatted(nombreTabla));
        } catch (SQLException e) {
            System.err.println("Error al eliminar la tabla: " + e.getMessage());
        }
    }

    public static void crearCampo() {
        if (tablaActual == null) {
            System.out.println("No se ha establecido ninguna tabla para operar.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print(" - nombre del campo: ");
        String nombreCampo = scanner.nextLine();
        System.out.print(" - tipo de dato: ");
        String tipoCampo = scanner.nextLine().toUpperCase();
        String[] tiposValidos = {"INT", "VARCHAR", "DECIMAL", "DATE", "TIMESTAMP", "BOOLEAN"};
        boolean tipoValido = false;
        for (String tipo : tiposValidos) {
            if (tipoCampo.startsWith(tipo)) { // Para permitir delimitadores como en VARCHAR(100)
                tipoValido = true;
                break;
            }
        }
        if (!tipoValido) {
            System.out.println("Tipo de dato no valido. Operacion cancelada.");
            return;
        }

        String modificarTablaSQL = "ALTER TABLE videojuegos ADD COLUMN %s %s".formatted(nombreCampo, tipoCampo);
        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(modificarTablaSQL);
            System.out.println("Campo agregado exitosamente: " + nombreCampo);
        } catch (SQLException e) {
            System.err.println("Error al modificar la tabla: " + e.getMessage());
        }
    }

    public static void eliminarCampo() {
        if (tablaActual == null) {
            System.out.println("No se ha establecido ninguna tabla para operar.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del campo que desea eliminar: ");
        String nombreCampo = scanner.nextLine();
        if ("id".equalsIgnoreCase(nombreCampo)) {
            System.out.println("No se permite eliminar el campo 'id'. Operacion cancelada.");
            return;
        }
        System.out.print("¿Esta seguro que desea eliminar el campo '" + nombreCampo + "'? (si/no): ");
        String confirmacion = scanner.nextLine().trim().toLowerCase();
        if (!confirmacion.equals("si")) {
            System.out.println("Operacion cancelada por el usuario.");
            return;
        }

        String eliminarCampoSQL = "ALTER TABLE videojuegos DROP COLUMN " + nombreCampo;
        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(eliminarCampoSQL);
            System.out.println("Campo eliminado exitosamente: " + nombreCampo);
        } catch (SQLException e) {
            System.err.println("Error al eliminar el campo: " + e.getMessage());
        }
    }

    public static void insertarDatos() {
        if (tablaActual == null) {
            System.out.println("No se ha establecido ninguna tabla para operar.");
            return;
        }

        String insertarSQL = """
                INSERT INTO videojuegos (titulo, genero, fecha_lanzamiento, plataforma, desarrollador, editor, calificacion)
                VALUES
                    ('The Legend of Zelda: Breath of the Wild', 'Accion-aventura', '2017-03-03', 'Nintendo Switch', 'Nintendo', 'Nintendo', 9.7),
                    ('Elden Ring', 'RPG de Accion', '2022-02-25', 'PC', 'FromSoftware', 'Bandai Namco', 9.4),
                    ('Cyberpunk 2077', 'RPG de Accion', '2020-12-10', 'PC', 'CD Projekt Red', 'CD Projekt', 7.8),
                    ('Super Mario Odyssey', 'Platform', '2017-10-27', 'Nintendo Switch', 'Nintendo', 'Nintendo', 9.8),
                    ('Grand Theft Auto V', 'Action-adventure', '2013-09-17', 'PC', 'Rockstar North', 'Rockstar Games', 9.6),
                    ('Red Dead Redemption 2', 'Action-adventure', '2018-10-26', 'PC', 'Rockstar Studios', 'Rockstar Games', 9.8),
                    ('The Witcher 3: Wild Hunt', 'Action RPG', '2015-05-19', 'PC', 'CD Projekt Red', 'CD Projekt', 9.9),
                    ('Hollow Knight', 'Metroidvania', '2017-02-24', 'PC', 'Team Cherry', 'Team Cherry', 9.0),
                    ('Portal 2', 'Puzzle-platform', '2011-04-19', 'PC', 'Valve', 'Valve', 9.6),
                    ('Overwatch', 'First-person shooter', '2016-05-24', 'PC', 'Blizzard Entertainment', 'Blizzard Entertainment', 9.0),
                    ('Fortnite', 'Battle Royale', '2017-09-26', 'PC', 'Epic Games', 'Epic Games', 8.5),
                    ('Stardew Valley', 'Simulation RPG', '2016-02-26', 'PC', 'ConcernedApe', 'ConcernedApe', 9.4),
                    ('Animal Crossing: New Horizons', 'Simulation', '2020-03-20', 'Nintendo Switch', 'Nintendo', 'Nintendo', 9.6),
                    ('Among Us', 'Party', '2018-06-15', 'PC', 'Innersloth', 'Innersloth', 8.8),
                    ('Dark Souls III', 'Action RPG', '2016-04-12', 'PC', 'FromSoftware', 'Bandai Namco Entertainment', 9.0),
                    ('Sekiro: Shadows Die Twice', 'Action RPG', '2019-03-22', 'PC', 'FromSoftware', 'Activision', 9.3),
                    ('Celeste', 'Platform', '2018-01-25', 'PC', 'Maddy Makes Games', 'Maddy Makes Games', 9.4),
                    ('Fire Emblem: Three Houses', 'Tactical RPG', '2019-07-26', 'Nintendo Switch', 'Intelligent Systems', 'Nintendo', 9.5),
                    ('Bloodborne', 'Action RPG', '2015-03-24', 'PlayStation', 'FromSoftware', 'Sony Computer Entertainment', 9.1),
                    ('Doom Eternal', 'First-person shooter', '2020-03-20', 'PC', 'id Software', 'Bethesda Softworks', 9.0),
                    ('God of War', 'Action-adventure', '2018-04-20', 'PlayStation', 'Santa Monica Studio', 'Sony Interactive Entertainment', 9.6),
                    ('Monster Hunter: World', 'Action RPG', '2018-01-26', 'PC', 'Capcom', 'Capcom', 9.2),
                    ('Assassins Creed Odyssey', 'Action RPG', '2018-10-05', 'PC', 'Ubisoft Quebec', 'Ubisoft', 8.9),
                    ('Fallout 4', 'Action RPG', '2015-11-10', 'PC', 'Bethesda Game Studios', 'Bethesda Softworks', 8.7);
                """;
        try (Statement stmt = conexion.createStatement()) {
            stmt.executeUpdate(insertarSQL);
            System.out.println("Datos iniciales insertados exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar datos: " + e.getMessage());
        }
    }

    public static void mostrarDatos() {
        if (tablaActual == null) {
            System.out.println("No se ha establecido ninguna tabla para operar.");
            return;
        }

        String seleccionarSQL = "SELECT * FROM %s".formatted(tablaActual);
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(seleccionarSQL))
        {
            System.out.println("Datos de la tabla '%s':".formatted(tablaActual));
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.print("*  " + metaData.getColumnName(i) + "  *");
            }
            System.out.println();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print("*  " + rs.getObject(i) + "  *");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar datos: " + e.getMessage());
        }
    }

    public static void mostrarTablas() {
        String mostrarTablasSQL = "SHOW TABLES";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(mostrarTablasSQL))
        {
            System.out.println("Tablas en la base de datos:");
            while (rs.next()) {
                System.out.println("- " + rs.getString(1));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar las tablas: " + e.getMessage());
        }
    }

    public static void establecerTablaActual() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre de la tabla para operar: ");
        String nombreTabla = scanner.nextLine().trim();
        if (nombreTabla.isEmpty()) {
            System.out.println("El nombre de la tabla no puede estar vacio.");
            return;
        }

        tablaActual = nombreTabla;
        System.out.println("Tabla actual establecida en: " + tablaActual);
    }

    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\nMenu:");
            System.out.println("1. Crear Tabla");
            System.out.println("2. Eliminar Tabla");
            System.out.println("3. Crear Campo");
            System.out.println("4. Eliminar Campo");
            System.out.println("5. Insertar Datos Iniciales");
            System.out.println("6. Mostrar Datos de la Tabla Actual");
            System.out.println("7. Mostrar Todas las Tablas");
            System.out.println("8. Establecer Tabla Actual");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> crearTabla();
                case 2 -> eliminarTabla();
                case 3 -> crearCampo();
                case 4 -> eliminarCampo();
                case 5 -> insertarDatos();
                case 6 -> mostrarDatos();
                case 7 -> mostrarTablas();
                case 8 -> establecerTablaActual();
                case 9 -> salir = true;
                default -> System.out.println("Opcion invalida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        conectarse();
        mostrarMenu();
        desconectarse();
    }
}