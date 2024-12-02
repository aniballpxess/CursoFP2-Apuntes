package edu.dam2.ad.ejercicios;

import java.sql.*;
import java.util.Scanner;

public class DML_MySQL {

    private static final String URL = "jdbc:mysql://localhost:3306/videogames_db";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "root_pswd";

    private static Connection conexion = null;

    public static void conectar() {
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexion a la base de datos establecida.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos:%n%s%n".formatted(e.getMessage()), e.getCause());
        }
    }

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

    public static void obtenerVideojuegosPorFiltro(String campo, String valor) {
        String consulta = String.format("SELECT * FROM videogames WHERE %s LIKE ?", campo);
        try (PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
            pstmt.setString(1, valor);
            try (ResultSet rs = pstmt.executeQuery()) {
                ResultSetMetaData metaDatos = rs.getMetaData();
                int numeroCampos = metaDatos.getColumnCount();

                // Imprimir nombres de campos
                for (int i = 1; i <= numeroCampos; i++) {
                    System.out.print(metaDatos.getColumnName(i) + "\t");
                }
                System.out.println();

                // Imprimir filas
                while (rs.next()) {
                    for (int i = 1; i <= numeroCampos; i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener videojuegos por filtro: " + e.getMessage());
        }
    }

    public static void borrarVideojuegosPorFiltro(String campo, String valor) {
        String consulta = String.format("DELETE FROM videogames WHERE %s LIKE ?", campo);
        try (PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
            pstmt.setString(1, valor);
            int filasAfectadas = pstmt.executeUpdate();
            System.out.printf("Se borraron %d videojuegos donde %s coincide con '%s'.%n", filasAfectadas, campo, valor);
        } catch (SQLException e) {
            System.err.println("Error al borrar videojuegos: " + e.getMessage());
        }
    }

    public static void actualizarVideojuegosPorFiltro(String campoObjetivo, Object nuevoValor, String campoFiltro, String valorFiltro) {
        String consulta = String.format("UPDATE videogames SET %s = ? WHERE %s LIKE ?", campoObjetivo, campoFiltro);
        try (PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
            pstmt.setObject(1, nuevoValor);
            pstmt.setString(2, valorFiltro);
            int filasAfectadas = pstmt.executeUpdate();
            System.out.printf("Se actualizaron %d videojuegos: se cambio %s a '%s' donde %s coincide con '%s'.%n",
                    filasAfectadas, campoObjetivo, nuevoValor, campoFiltro, valorFiltro);
        } catch (SQLException e) {
            System.err.println("Error al actualizar videojuegos: " + e.getMessage());
        }
    }

    public static void obtenerTodosLosVideojuegos() {
        String consulta = "SELECT * FROM videogames";
        try (PreparedStatement pstmt = conexion.prepareStatement(consulta);
             ResultSet rs = pstmt.executeQuery()) {
            ResultSetMetaData metaDatos = rs.getMetaData();
            int numeroCampos = metaDatos.getColumnCount();

            // Imprimir nombres de campos
            for (int i = 1; i <= numeroCampos; i++) {
                System.out.print(metaDatos.getColumnName(i) + "\t");
            }
            System.out.println();

            // Imprimir filas
            while (rs.next()) {
                for (int i = 1; i <= numeroCampos; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener videojuegos: " + e.getMessage());
        }
    }

    public static void insertarVideojuego(String titulo, String genero, String fechaLanzamiento, String plataforma, String desarrollador, String editor, double calificacion) {
        String consulta = """
            INSERT INTO videogames (title, genre, release_date, platform, developer, publisher, rating)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;
        try (PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, genero);
            pstmt.setDate(3, Date.valueOf(fechaLanzamiento)); // Formato: YYYY-MM-DD
            pstmt.setString(4, plataforma);
            pstmt.setString(5, desarrollador);
            pstmt.setString(6, editor);
            pstmt.setDouble(7, calificacion);
            pstmt.executeUpdate();
            System.out.println("Videojuego agregado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar videojuego: " + e.getMessage());
        }
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Menu Base de Datos de Videojuegos ---");
            System.out.println("1. Ver todos los videojuegos");
            System.out.println("2. Ver videojuegos por filtro");
            System.out.println("3. Agregar un nuevo videojuego");
            System.out.println("4. Borrar videojuegos por filtro");
            System.out.println("5. Actualizar videojuegos por filtro");
            System.out.println("6. Salir");
            System.out.print("Elige una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de linea

            switch (opcion) {
                case 1 -> obtenerTodosLosVideojuegos();
                case 2 -> {
                    System.out.print("Ingresa el campo por el que filtrar (ej. genre, developer): ");
                    String campo = scanner.nextLine();
                    System.out.print("Ingresa el valor a filtrar: ");
                    String valor = scanner.nextLine();
                    obtenerVideojuegosPorFiltro(campo, valor);
                }
                case 3 -> {
                    System.out.print("Ingresa el titulo: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingresa el genero: ");
                    String genero = scanner.nextLine();
                    System.out.print("Ingresa la fecha de lanzamiento (YYYY-MM-DD): ");
                    String fechaLanzamiento = scanner.nextLine();
                    System.out.print("Ingresa la plataforma: ");
                    String plataforma = scanner.nextLine();
                    System.out.print("Ingresa el desarrollador: ");
                    String desarrollador = scanner.nextLine();
                    System.out.print("Ingresa el editor: ");
                    String editor = scanner.nextLine();
                    System.out.print("Ingresa la calificacion: ");
                    double calificacion = scanner.nextDouble();
                    insertarVideojuego(titulo, genero, fechaLanzamiento, plataforma, desarrollador, editor, calificacion);
                }
                case 4 -> {
                    System.out.print("Ingresa el campo por el que filtrar (ej. genre, developer): ");
                    String campo = scanner.nextLine();
                    System.out.print("Ingresa el valor a filtrar: ");
                    String valor = scanner.nextLine();
                    borrarVideojuegosPorFiltro(campo, valor);
                }
                case 5 -> {
                    System.out.print("Ingresa el campo a actualizar (ej. rating): ");
                    String campoObjetivo = scanner.nextLine();
                    System.out.print("Ingresa el nuevo valor: ");
                    Object nuevoValor = scanner.nextLine();
                    System.out.print("Ingresa el campo por el que filtrar (ej. genre, developer): ");
                    String campoFiltro = scanner.nextLine();
                    System.out.print("Ingresa el valor a filtrar: ");
                    String valorFiltro = scanner.nextLine();
                    actualizarVideojuegosPorFiltro(campoObjetivo, nuevoValor, campoFiltro, valorFiltro);
                }
                case 6 -> {
                    System.out.println("Saliendo del programa...");
                    desconectar();
                    return;
                }
                default -> System.out.println("Opcion invalida! Intenta de nuevo.");
            }
        }
    }

    public static void main(String[] args) {
        conectar();
        menu();
        desconectar();
    }
}