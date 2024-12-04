package edu.dam2.ad.ejercicios;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DDL_DML_MySQL {

    private static final String URL = "jdbc:mysql://localhost:3306/videogames_db";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "root_pswd";

    private static Connection conexion = null;

    private static String tablaActual = null;

    /**
     * <p>Establece una conexión a la base de datos utilizando las credenciales
     * configuradas. Si la conexión es exitosa, se imprime un mensaje en consola.
     * En caso de error, se lanza una excepción con el mensaje detallado.</p>
     */
    private static void conectarse() {
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
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
            System.out.print("""
                    ************* MENÚ  PRINCIPAL *************
                    ------------ Operaciones Extra ------------
                    0. Salir
                    1. Mostrar Tablas
                    2. Establecer Tabla Activa
                    3. Mostrar Campos de Tabla Activa
                    4. Mostrar Datos de Tabla Activa
                    ------------- Operaciones DDL -------------
                    5. Crear Tabla
                    6. Eliminar Tabla
                    7. Crear Campo en Tabla Activa
                    8. Eliminar Campo en Tabla Activa
                    ------------- Operaciones DML -------------
                    9. Rellenar Tabla Activa
                    10. Consultar Tabla Activa
                    11. Insertar Registro en Tabla Activa
                    12. Borrar Registro de Tabla Activa
                    13. Actualizar Registro de Tabla Activa
                    *******************************************
                    """);
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 0 -> salir = true;
                case 1 -> mostrarTablas();
                case 2 -> establecerTablaActiva();
                case 3 -> mostrarCampos();
                case 4 -> mostrarDatos();
                case 5 -> crearTabla();
                case 6 -> eliminarTabla();
                case 7 -> crearCampo();
                case 8 -> eliminarCampo();
                case 9 -> rellenarTabla();
                case 10 -> consultarTabla();
                case 11 -> insertarRegistro();
                case 12 -> borrarRegistro();
                case 13 -> actualizarRegistro();
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
        String mostrarTablasSQL = "SHOW TABLES;";

        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(mostrarTablasSQL)) {
            System.out.println("Tablas en la base de datos:");
            while (rs.next()) {
                System.out.println(" - " + rs.getString(1));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar las tablas: " + e.getMessage());
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
        System.out.print("Ingrese el nombre de la tabla para operar: ");
        String nombreTabla = scanner.nextLine().trim();
        if (nombreTabla.isEmpty()) {
            System.out.println("El nombre de la tabla no puede estar vacio.");
            return;
        }
        tablaActual = nombreTabla;
        System.out.println("Tabla actual establecida en: " + tablaActual);
    }

    /**
     * <p>Obtiene y muestra los detalles de los campos (columnas) de la tabla
     * activa. Esto incluye el nombre, tipo de datos, si permite nulos, si es
     * clave, entre otros.</p>
     * <br />
     * <p>Requiere de que se haya establecido una tabla activa para funcionar.</p>
     */
    private static void mostrarCampos() {
        if (tablaActual == null) {
            System.out.println("No se ha establecido ninguna tabla para operar.");
            return;
        }
        String consulta = "DESCRIBE %s;";
        consulta = consulta.formatted(tablaActual);

        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(consulta)) {
            System.out.printf("Campos de la tabla '%s':%n", tablaActual);
            while (rs.next()) {
                String campo = rs.getString("Field");
                String tipo = rs.getString("Type");
                String nulo = rs.getString("Null");
                String clave = rs.getString("Key");
                String porDefecto = rs.getString("Default");
                String extra = rs.getString("Extra");
                System.out.printf("Campo: %-20s Tipo: %-15s Nulo: %-6s Clave: %-6s Default: %-20s Extra: %-20s%n", campo, tipo, nulo, clave, porDefecto, extra);
            }
        } catch (SQLException e) {
            System.err.println("Error al mostrar los campos de la tabla: " + e.getMessage());
        }
    }

    /**
     * <p>Muestra todos los registros de la tabla activa. Se realiza una
     * consulta para obtener los datos de todos los campos de todos los
     * registros de la tabla activa y los imprime en consola.</p>
     * <p>Requiere de que se haya establecido una tabla activa para funcionar.</p>
     */
    private static void mostrarDatos() {
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
            System.err.println("Error al consultar datos: " + e.getMessage());
        }
    }

    /**
     * <p>Crea una tabla con un nombre proporcionado por el usuario. La
     * estructura de la tabla está predefinida y no puede ser modificada.</p>
     * <br />
     * TODO - Hacer una version más general
     */
    private static void crearTabla() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(" - Nombre de la tabla: ");
        String nombreTabla = scanner.nextLine().trim();
        if (nombreTabla.isEmpty()) {
            System.out.println("El nombre de la tabla no puede estar vacio.");
            return;
        }
        String crearTablaSQL = """
                CREATE TABLE IF NOT EXISTS %s (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    title VARCHAR(255) NOT NULL,
                    genre VARCHAR(100),
                    launch_date DATE,
                    platform VARCHAR(100),
                    developer VARCHAR(150),
                    publisher VARCHAR(150),
                    rating DECIMAL(3,1),
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                );
                """;
        crearTablaSQL = crearTablaSQL.formatted(nombreTabla);

        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(crearTablaSQL);
            System.out.printf("Tabla '%s' creada exitosamente.%n", nombreTabla);
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla: " + e.getMessage());
        }
    }

    /**
     * <p>Elimina la tabla cuyo nombre es introducido por el usuario. Si la
     * tabla existe, será eliminada. Si no se ha proporcionado un nombre válido,
     * o si la tabla no existe, se mostrará un mensaje de error.</p>
     * <br />
     * TODO - Añadir confirmación de la eliminación (como en <code>eliminarCampo()</code>)
     */
    private static void eliminarTabla() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(" - Nombre de la tabla: ");
        String nombreTabla = scanner.nextLine().trim();
        if (nombreTabla.isEmpty()) {
            System.out.println("El nombre de la tabla no puede estar vacio.");
            return;
        }
        String eliminarTablaSQL = "DROP TABLE IF EXISTS %s";
        eliminarTablaSQL = eliminarTablaSQL.formatted(nombreTabla);
        
        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(eliminarTablaSQL);
            System.out.printf("Tabla '%s' eliminada exitosamente.%n", nombreTabla);
        } catch (SQLException e) {
            System.err.println("Error al eliminar la tabla: " + e.getMessage());
        }
    }

    /**
     * <p>Añade un campo a la tabla activa. El usuario debe proporcionar el
     * nombre y tipo de dato del campo. Los tipos de datos permitidos son
     * <code>INT</code>, <code>VARCHAR</code>, <code>DECIMAL</code>,
     * <code>DATE</code>, <code>TIMESTAMP</code> y <code>BOOLEAN</code>.</p>
     * <p>Requiere de que se haya establecido una tabla activa para funcionar.</p>
     * <br />
     * TODO - Incluir parámetros opcionales para el campo (PK, NN, etc)
     */
    private static void crearCampo() {
        if (tablaActual == null) {
            System.out.println("No se ha establecido ninguna tabla para operar.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print(" - Nombre del campo: ");
        String nombreCampo = scanner.nextLine();
        System.out.print(" - Tipo de dato: ");
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
        String modificarTablaSQL = "ALTER TABLE %s ADD COLUMN %s %s";
        modificarTablaSQL = modificarTablaSQL.formatted(tablaActual, nombreCampo, tipoCampo);

        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(modificarTablaSQL);
            System.out.println("Campo agregado exitosamente: " + nombreCampo);
        } catch (SQLException e) {
            System.err.println("Error al modificar la tabla: " + e.getMessage());
        }
    }

    /**
     * <p>Elimina un campo de la tabla activa. El usuario debe proporcionar el
     * nombre del campo a eliminar. El campo <code>id</code> no se puede
     * eliminar. Se requiere confirmación antes de ejecutar la operación.</p>
     * <p>Requiere de que se haya establecido una tabla activa para funcionar.</p>
     * <br />
     * TODO - Hacer comprobación de la validez del campo antes de ejecutar el comando
     */
    private static void eliminarCampo() {
        if (tablaActual == null) {
            System.out.println("No se ha establecido ninguna tabla para operar.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print(" - Nombre del campo: ");
        String nombreCampo = scanner.nextLine();
        if ("id".equalsIgnoreCase(nombreCampo)) {
            System.out.println("No se permite eliminar el campo 'id'. Operacion cancelada.");
            return;
        }
        String eliminarCampoSQL = "ALTER TABLE %s DROP COLUMN %s";
        eliminarCampoSQL = eliminarCampoSQL.formatted(tablaActual, nombreCampo);

        System.out.print("¿Esta seguro que desea eliminar el campo '" + nombreCampo + "'? (si/no): ");
        String confirmacion = scanner.nextLine().trim().toLowerCase();
        if (!confirmacion.equals("si")) {
            System.out.println("Operación cancelada por el usuario.");
            return;
        }

        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(eliminarCampoSQL);
            System.out.println("Campo eliminado exitosamente: " + nombreCampo);
        } catch (SQLException e) {
            System.err.println("Error al eliminar el campo: " + e.getMessage());
        }
    }

    /**
     * <p>Rellena la tabla activa con datos predefinidos para simular un volumen
     * de registros aceptable.</p>
     * <br />
     * <p>Requiere de que se haya establecido una tabla activa para funcionar.</p>
     * <br />
     * TODO - Añadir control para solo rellenar una vez una misma tabla
     */
    private static void rellenarTabla() {
        if (tablaActual == null) {
            System.out.println("No se ha establecido ninguna tabla para operar.");
            return;
        }
        String insertarSQL = """
                INSERT INTO %s (title, genre, launch_date, platform, developer, publisher, rating)
                VALUES
                    ('The Legend of Zelda: Breath of the Wild', 'Action-adventure', '2017-03-03', 'Nintendo Switch', 'Nintendo', 'Nintendo', 9.7),
                    ('Elden Ring', 'Action RPG', '2022-02-25', 'PC', 'FromSoftware', 'Bandai Namco', 9.4),
                    ('Cyberpunk 2077', 'Action RPG', '2020-12-10', 'PC', 'CD Projekt Red', 'CD Projekt', 7.8),
                    ('Super Mario Odyssey', 'Platform', '2017-10-27', 'Nintendo Switch', 'Nintendo', 'Nintendo', 9.8),
                    ('Grand Theft Auto V', 'Action-adventure', '2013-09-17', 'PC', 'Rockstar North', 'Rockstar Games', 9.6),
                    ('Red Dead Redemption 2', 'Action-adventure', '2018-10-26', 'PC', 'Rockstar Studios', 'Rockstar Games', 9.8),
                    ('The Witcher 3: Wild Hunt', 'Action RPG', '2015-05-19', 'PC', 'CD Projekt Red', 'CD Projekt', 9.9),
                    ('Hollow Knight', 'Metroidvania', '2017-02-24', 'PC', 'Team Cherry', 'Team Cherry', 9.0),
                    ('Portal 2', 'Puzzle-platform', '2011-04-19', 'PC', 'Valve', 'Valve', 9.6),
                    ('Overwatch', 'FPS', '2016-05-24', 'PC', 'Blizzard Entertainment', 'Blizzard Entertainment', 9.0),
                    ('Fortnite', 'Battle Royale', '2017-09-26', 'PC', 'Epic Games', 'Epic Games', 8.5),
                    ('Stardew Valley', 'Simulation RPG', '2016-02-26', 'PC', 'ConcernedApe', 'ConcernedApe', 9.4),
                    ('Animal Crossing: New Horizons', 'Simulation', '2020-03-20', 'Nintendo Switch', 'Nintendo', 'Nintendo', 9.6),
                    ('Among Us', 'Party', '2018-06-15', 'PC', 'Innersloth', 'Innersloth', 8.8),
                    ('Dark Souls III', 'Action RPG', '2016-04-12', 'PC', 'FromSoftware', 'Bandai Namco Entertainment', 9.0),
                    ('Sekiro: Shadows Die Twice', 'Action RPG', '2019-03-22', 'PC', 'FromSoftware', 'Activision', 9.3),
                    ('Celeste', 'Platform', '2018-01-25', 'PC', 'Maddy Makes Games', 'Maddy Makes Games', 9.4),
                    ('Fire Emblem: Three Houses', 'Tactical RPG', '2019-07-26', 'Nintendo Switch', 'Intelligent Systems', 'Nintendo', 9.5),
                    ('Bloodborne', 'Action RPG', '2015-03-24', 'PlayStation', 'FromSoftware', 'Sony Computer Entertainment', 9.1),
                    ('Doom Eternal', 'FPS', '2020-03-20', 'PC', 'id Software', 'Bethesda Softworks', 9.0),
                    ('God of War', 'Action-adventure', '2018-04-20', 'PlayStation', 'Santa Monica Studio', 'Sony Interactive Entertainment', 9.6),
                    ('Monster Hunter: World', 'Action RPG', '2018-01-26', 'PC', 'Capcom', 'Capcom', 9.2),
                    ('Assassins Creed Odyssey', 'Action RPG', '2018-10-05', 'PC', 'Ubisoft Quebec', 'Ubisoft', 8.9),
                    ('Fallout 4', 'Action RPG', '2015-11-10', 'PC', 'Bethesda Game Studios', 'Bethesda Softworks', 8.7);
                """;
        insertarSQL = insertarSQL.formatted(tablaActual);
        
        try (Statement stmt = conexion.createStatement()) {
            stmt.executeUpdate(insertarSQL);
            System.out.println("Datos iniciales insertados exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar datos: " + e.getMessage());
        }
    }

    /**
     * <p>Consulta los registros de la tabla activa según un campo y valor de
     * filtro especificado. Muestra los registros obtenidos por consola en
     * formato de tabla.</p>
     * <p>Requiere de que se haya establecido una tabla activa para funcionar.</p>
     * <br />
     * TODO - Incluir la opción de múltiples filtros
     */
    private static void consultarTabla() {
        if (tablaActual == null) {
            System.out.println("No se ha establecido ninguna tabla para operar.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Campo para filtrar: ");
        String campoFiltro = scanner.nextLine();
        System.out.print("Valor del filtro: ");
        String valorFiltro = scanner.nextLine();
        String consulta = "SELECT (title, genre, platform, developer, rating) FROM %s WHERE %s LIKE ?";
        consulta = consulta.formatted(tablaActual, campoFiltro);

        try (PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
            pstmt.setString(1, "%" + valorFiltro + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                imprimirDatos(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener videojuegos por filtro: " + e.getMessage());
        }
    }


    /**
     * <p>Elimina los registros de la tabla activa que coincidan con el filtro
     * especificado por el usuario. Este introduce un campo y valor para el
     * filtro. Muestra un mensaje con el número de registros eliminados.</p>
     * <p>Requiere de que se haya establecido una tabla activa para funcionar.</p>
     * <br />
     * TODO - Incluir la opción de múltiples filtros
     */
    private static void borrarRegistro() {
        if (tablaActual == null) {
            System.out.println("No se ha establecido ninguna tabla para operar.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Campo para filtrar: ");
        String campo = scanner.nextLine();
        System.out.print("Valor del filtro: ");
        String valor = scanner.nextLine();
        String consulta = "DELETE FROM %s WHERE %s LIKE ?";
        consulta = consulta.formatted(tablaActual, campo);

        try (PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
            pstmt.setString(1, "%" + valor + "%");
            int registrosAfectados = pstmt.executeUpdate();
            System.out.printf("Se borraron %d registros donde %s contiene '%s'.%n", registrosAfectados, campo, valor);
        } catch (SQLException e) {
            System.err.println("Error al borrar videojuegos: " + e.getMessage());
        }
    }

    /**
     * <p>Inserta un nuevo registro en la tabla activa utilizando los datos
     * proporcionados por el usuario. Pide al usuario los valores para los
     * siguientes campos de la tabla:</p>
     * <ul>
     *     <li>Título</li>
     *     <li>Género</li>
     *     <li>Fecha de Lanzamiento</li>
     *     <li>Plataforma</li>
     *     <li>Desarrollador</li>
     *     <li>Productora</li>
     *     <li>Calificación</li>
     * </ul>
     * <p>Requiere de que se haya establecido una tabla activa para funcionar.</p>
     * <br />
     * TODO - Hacer mejor gestión de la entrada de usuario
     */
    private static void insertarRegistro() {
        if (tablaActual == null) {
            System.out.println("No se ha establecido ninguna tabla para operar.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        System.out.print("Fecha de lanzamiento (YYYY-MM-DD): ");
        String fechaLanzamiento = scanner.nextLine();
        System.out.print("Plataforma: ");
        String plataforma = scanner.nextLine();
        System.out.print("Desarrollador: ");
        String desarrollador = scanner.nextLine();
        System.out.print("Productora: ");
        String productora = scanner.nextLine();
        System.out.print("Calificación: ");
        double calificacion = scanner.nextDouble();
        String consulta = """
            INSERT INTO %s (title, genre, launch_date, platform, developer, publisher, rating)
                VALUES (?, ?, ?, ?, ?, ?, ?);
        """;
        consulta = consulta.formatted(tablaActual);

        try (PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, genero);
            pstmt.setDate(3, Date.valueOf(fechaLanzamiento));
            pstmt.setString(4, plataforma);
            pstmt.setString(5, desarrollador);
            pstmt.setString(6, productora);
            pstmt.setDouble(7, calificacion);
            pstmt.executeUpdate();
            System.out.println("Videojuego agregado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar videojuego: " + e.getMessage());
        }

    }

    /**
     * <p>Actualiza un campo en los registros de la tabla activa que coincidan
     * con el filtro especificado por el usuario. Este introduce los campos y
     * valores tanto para la modificación como para el filtro. El filtro es
     * parcial, no exacto.</p>
     * <p>Requiere de que se haya establecido una tabla activa para funcionar.</p>
     * <br />
     * TODO - Añadir elección de tipo de filtro (parcial/exacto)
     */
    private static void actualizarRegistro() {
        if (tablaActual == null) {
            System.out.println("No se ha establecido ninguna tabla para operar.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Campo a actualizar: ");
        String campoObjetivo = scanner.nextLine();
        System.out.print("Nuevo valor: ");
        String nuevoValor = scanner.nextLine();
        System.out.print("Campo para filtrar: ");
        String campoFiltro = scanner.nextLine();
        System.out.print("Valor del filtro: ");
        String valorFiltro = scanner.nextLine();
        String consulta = "UPDATE %s SET %s = ? WHERE %s LIKE ?";
        consulta = consulta.formatted(tablaActual, campoObjetivo, campoFiltro);

        try (PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
            pstmt.setObject(1, nuevoValor);
            pstmt.setString(2, "%" + valorFiltro + "%");
            int registrosAfectados = pstmt.executeUpdate();
            System.out.printf("Se actualizaron %d registros: '%s' cambiado a '%s' donde '%s' contiene '%s'.%n",
                    registrosAfectados, campoObjetivo, nuevoValor, campoFiltro, valorFiltro);
        } catch (SQLException e) {
            System.err.println("Error al actualizar videojuegos: " + e.getMessage());
        }
    }

    private static void imprimirDatos(ResultSet rs) throws SQLException {
        // Recibir datos
        ResultSetMetaData metaDatos = rs.getMetaData();
        int numeroColumnas = metaDatos.getColumnCount();
        List<String[]> registros = new ArrayList<>();
        while (rs.next()) {
            String[] registro = new String[numeroColumnas];
            for (int i = 0; i < numeroColumnas; i++) {
                String valorCampo = rs.getString(i + 1);
                registro[i] = valorCampo != null ? valorCampo : "NULL";
            }
            registros.add(registro);
        }
        // Calcular ancho de cada columna
        int[] anchosColumnas = new int[numeroColumnas];
        for (int i = 0; i < numeroColumnas; i++) {
            anchosColumnas[i] = metaDatos.getColumnName(i + 1).length();
        }
        for (String[] registro : registros) {
            for (int i = 0; i < numeroColumnas; i++) {
                anchosColumnas[i] = Math.max(anchosColumnas[i], registro[i].length());
            }
        }
        // Imprimir nombres de campos
        for (int i = 0; i < numeroColumnas; i++) {
            System.out.printf("%-" + anchosColumnas[i] + "s ", metaDatos.getColumnName(i + 1));
        }
        System.out.println();
        // Imprimir separación de cabecera
        for (int ancho : anchosColumnas) {
            System.out.print("-".repeat(ancho) + " ");
        }
        System.out.println();
        // Imprimir filas
        for (String[] registro : registros) {
            for (int i = 0; i < numeroColumnas; i++) {
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