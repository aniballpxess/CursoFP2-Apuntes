package edu.dam2.ad.ejercicios;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SQLite_Terminal {
    private static final String URL = "jdbc:sqlite:C:\\CursoFP2\\ASIGNATURAS\\AD\\PRACTICA\\Recursos\\sqlite-db\\db-prueba.db";

    private static Connection conexion = null;

    private static String tablaActual = null;

    /**
     * <p>Establece una conexión a la base de datos utilizando las credenciales
     * configuradas. Si la conexión es exitosa, se imprime un mensaje en consola.
     * En caso de error, se lanza una excepción con el mensaje detallado.</p>
     */
    private static void conectarse() {
        try {
            conexion = DriverManager.getConnection(URL);
            System.out.println("Conexion a la base de datos establecida.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos:%n%s%n".formatted(e.getMessage()), e.getCause());
        }
    }

    /**
     * <p>Cierra la conexión activa con la base de datos, si existe. Si la
     * conexión está abierta, se cierra y se imprime un mensaje en consola. Si
     * ya está cerrada o no se ha establecido, no se realiza ninguna acción.</p>
     */
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

    /**
     * <p>Muestra el menú de opciones que permite al usuario interactuar con la
     * base de datos, con la posibilidad de realizar operaciones como mostrar
     * tablas, insertar registros, crear campos, entre otras.</p>
     * <br />
     * TODO - Hacer mejor gestión de la entrada de usuario
     */
    private static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.printf("""
                    ************* OPERACIONES *************
                    --------------- Básicas ---------------
                    0. Salir
                    1. Mostrar Tablas
                    2. Establecer Tabla Activa
                    3. Mostrar Campos de Tabla Activa
                    4. Mostrar Datos de Tabla Activa
                    5. Ejecutar instrucción SQL
                    --------------- Control ---------------
                    6. Info de Alumnos de Tutor
                    ---------------------------------------
                    Tabla activa: %s
                    ***************************************
                    """, tablaActual);
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 0 -> salir = true;
                case 1 -> mostrarTablas();
                case 2 -> establecerTablaActiva();
                case 3 -> mostrarCamposTablaActiva();
                case 4 -> mostrarDatosTablaActiva();
                case 5 -> ejecutarInstruccion();
                case 6 -> mostrarAlumnosTutor();
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }


    /**
     * <p>Recupera y muestra todas las tablas de la base de datos utilizando
     * una consulta SQL <code>SHOW TABLES</code>. Muestra los resultados de
     * manera ordenada en la consola.</p>
     */
    private static void mostrarTablas() {
        String mostrarTablasSQL = "SELECT name FROM sqlite_master WHERE type = 'table';";

        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(mostrarTablasSQL)) {
            System.out.println("Tablas en la base de datos:");
            while (rs.next()) {
                System.out.println(" - " + rs.getString(1));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar las tablas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * <p>Permite al usuario establecer la tabla activa sobre la que se
     * realizarán las operaciones. El usuario debe ingresar el nombre de la
     * tabla que desea establecer como activa.</p>
     * <br />
     * TODO - Añadir comprobación de la existencia de la tabla
     */
    private static void establecerTablaActiva() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre de la tabla: ");
        String nombreTabla = scanner.nextLine().trim();
        if (nombreTabla.isEmpty()) {
            System.out.println("El nombre de la tabla no puede estar vacio.");
            return;
        }
        tablaActual = nombreTabla;
    }

    /**
     * <p>Obtiene y muestra los detalles de los campos (columnas) de la tabla
     * activa. Esto incluye el nombre, tipo de datos, si permite nulos, si es
     * clave, entre otros.</p>
     * <br />
     * <p>Requiere de que se haya establecido una tabla activa para funcionar.</p>
     */
    private static void mostrarCamposTablaActiva() {
        if (tablaActual == null) {
            System.out.println("No se ha establecido ninguna tabla para operar.");
            return;
        }
        String consulta = "PRAGMA table_info(%s);";
        consulta = consulta.formatted(tablaActual);

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(consulta)) {
            System.out.printf("Campos de la tabla '%s':%n", tablaActual);
            imprimirDatos(rs);
        } catch (SQLException e) {
            System.err.println("Error al mostrar los campos de la tabla: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * <p>Muestra todos los registros de la tabla activa. Se realiza una
     * consulta para obtener los datos de todos los campos de todos los
     * registros de la tabla activa y los imprime en consola.</p>
     *
     * <p>Requiere de que se haya establecido una tabla activa para funcionar.</p>
     */
    private static void mostrarDatosTablaActiva() {
        if (tablaActual == null) {
            System.out.println("No se ha establecido ninguna tabla para operar.");
            return;
        }
        String seleccionarSQL = "SELECT * FROM %s;";
        seleccionarSQL = seleccionarSQL.formatted(tablaActual);

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(seleccionarSQL)) {
            System.out.printf("Datos de la tabla '%s':%n", tablaActual);
            imprimirDatos(rs);
        } catch (SQLException e) {
            System.err.println("Error al mostrar los registros de la tabla: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void ejecutarInstruccion() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Instrucción: ");
        String instruccion = sc.nextLine().trim();
        if (instruccion.isEmpty()) {
            System.out.println("No has introducido una instrucción.");
            return;
        }

        try (Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(instruccion)) {
            imprimirDatos(rs);
        } catch (SQLException e) {
            System.err.println("Error al mostrar los registros de la tabla: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void mostrarAlumnosTutor() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nombre del tutor: ");
        String nombreTutor = sc.nextLine().trim();
        if (nombreTutor.isEmpty()) {
            System.out.println("No has introducido ningún nombre.");
            return;
        }
        String instruccion = "SELECT id, nombre, apellidos FROM estudiantes WHERE id_tutor = (SELECT id FROM profesores WHERE nombre = '%s');";
        instruccion = instruccion.formatted(nombreTutor);

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(instruccion)) {
            imprimirDatos(rs);
        } catch (SQLException e) {
            System.err.printf("Error al mostrar los estudiantes que tienen de tutor a '%s': %s%n", nombreTutor, e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * <p>Imprime los resultados obtenidos de una consulta a la base de datos.
     * El formato de impresión garantiza que cada columna se alinea
     * uniformemente, ajustándose al ancho del contenido más largo de cada una,
     * incluyendo los nombres estas.</p>
     *
     * <p>Los nombres de las columnas se imprimen como cabecera, seguidos de una
     * separación horizontal y, a continuación, las filas de datos obtenidas de
     * la consulta.</p>
     * <br />
     * TODO - Dividir la función internamente en dos para mejorar el significado
     *  de las variables (leer -> campo, registro) (imprimir -> columna, fila)
     * @param rs el resultado de la consulta del que se obtendrán los datos a
     *           imprimir.
     * @throws SQLException si se produce algún error durante la extracción de
     * datos del <code>rs</code>
     */
    private static void imprimirDatos(ResultSet rs) throws SQLException {
        ResultSetMetaData metaDatos = rs.getMetaData();
        int numeroCampos = metaDatos.getColumnCount();
        String[] nombresCampos = new String[numeroCampos];
        List<String[]> registros = new ArrayList<>();
        // Recibir datos
        for (int i = 0; i < numeroCampos; i++) {
            nombresCampos[i] = metaDatos.getColumnName(i + 1);
        }
        while (rs.next()) {
            String[] registro = new String[numeroCampos];
            for (int i = 0; i < numeroCampos; i++) {
                String valorCampo = rs.getString(i + 1);
                registro[i] = valorCampo != null ? valorCampo : "NULL";
            }
            registros.add(registro);
        }
        // Calcular ancho de cada columna
        int[] anchosColumnas = new int[numeroCampos];
        for (int i = 0; i < numeroCampos; i++) {
            anchosColumnas[i] = nombresCampos[i].length();
        }
        for (String[] registro : registros) {
            for (int i = 0; i < numeroCampos; i++) {
                anchosColumnas[i] = Math.max(anchosColumnas[i], registro[i].length());
            }
        }
        // Imprimir nombres de campos
        for (int i = 0; i < numeroCampos; i++) {
            System.out.printf("%-" + anchosColumnas[i] + "s ", nombresCampos[i]);
        }
        System.out.println();
        // Imprimir separación de cabecera
        for (int ancho : anchosColumnas) {
            System.out.print("-".repeat(ancho) + " ");
        }
        System.out.println();
        // Imprimir filas
        for (String[] registro : registros) {
            for (int i = 0; i < numeroCampos; i++) {
                System.out.printf("%-" + anchosColumnas[i] + "s ", registro[i]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        conectarse();
        mostrarMenu();
        desconectarse();
    }

}
