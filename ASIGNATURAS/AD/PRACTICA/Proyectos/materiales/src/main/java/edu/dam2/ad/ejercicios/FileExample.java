package edu.dam2.ad.ejercicios;

// Importa las clases necesarias para trabajar con archivos y manejar excepciones
import java.io.File; // Para crear y manejar archivos y directorios
import java.io.FileInputStream; // Para leer datos de un archivo
import java.io.FileOutputStream; // Para escribir datos en un archivo
import java.io.IOException; // Para manejar excepciones de entrada/salida

class FileExample {

    public static void main(String[] args) {
        // Ruta del directorio base
        // Ojo, pon tu directorio de trabajo a continuación
        String basePath = "C:\\CursoFP2\\ASIGNATURAS\\AD\\PRACTICA"; // Esta es tu ruta donde se creará el directorio
        String directoryPath = basePath + "\\temp"; // Nombre del subdirectorio
        String filePath = directoryPath + "\\miFicheroFile.txt"; // Ruta del archivo, pon el nombre que te guste

        // Contenido a escribir en el archivo
        String contenido = "¡Hola, este es un ejemplo de texto para incorporar a un archivo."; // Variable de contenido

        // Crear el directorio si no existe
        System.out.println("Ahora voy a crear el directorio si no existe ya: " + directoryPath); // Mensaje anticipando la operación
        createDirectoryIfNotExists(directoryPath);

        // Verifica si el archivo existe, si no, lo crea
        System.out.println("Ahora voy a crear el archivo: " + filePath); // Mensaje anticipando la operación
        createFileIfNotExists(filePath);

        // Escribir en el archivo
        System.out.println("Ahora voy a escribir en el archivo."); // Mensaje anticipando la operación
        writeToFile(filePath, contenido); // Usamos la variable llamada contenido que hemos volcado previamente con texto

        // Leer del archivo
        System.out.println("Ahora voy a leer del archivo."); // Mensaje anticipando la operación
        readFromFile(filePath);
    }

    // Método para crear el directorio si no existe
    private static void createDirectoryIfNotExists(String directoryPath) {
        File directory = new File(directoryPath); // Crea un objeto File para el directorio
        if (directory.mkdirs()) { // Intenta crear el directorio
            System.out.println("Directorio creado: " + directoryPath); // Mensaje de éxito
        } else {
            System.out.println("El directorio ya existe o no se pudo crear: " + directoryPath); // Mensaje si ya existe
        }
    }

    // Método para crear el archivo si no existe
    private static void createFileIfNotExists(String filePath) {
        File file = new File(filePath); // Crea un objeto File con la ruta especificada
        try {
            if (file.createNewFile()) { // Intenta crear el archivo
                System.out.println("Archivo creado: " + filePath); // Mensaje de éxito
            } else {
                System.out.println("El archivo ya existe: " + filePath); // Mensaje si ya existe
            }
        } catch (IOException e) {
            e.printStackTrace(); // Imprime cualquier error
        }
    }

    // Método para escribir en un archivo
    private static void writeToFile(String filePath, String content) {
        try (FileOutputStream fos = new FileOutputStream(filePath, true)) { // 'true' para agregar contenido
            // Escribir en el Archivo
            fos.write(content.getBytes()); // Convierte el String content en un array de bytes.
            // Esto es necesario porque FileOutputStream trabaja con bytes, no con String.
            // Escribe los bytes en el archivo. Si el archivo no existía, se creará.
            // Si ya existía y se abrió en modo "append", el contenido se añadirá al final.

            // Agregar un Salto de Línea
            fos.write(System.lineSeparator().getBytes()); // Devuelve la cadena que representa el separador de línea del sistema operativo (por ejemplo, \n en Unix/Linux y \r\n en Windows).
            // Esto es útil para asegurarse de que el contenido se formatee correctamente al ser leído más tarde.
            // Convierte este separador de línea en un array de bytes.
            // Escribe el salto de línea en el archivo, asegurando que cada vez que se agregue nuevo contenido, comience en una nueva línea.

            System.out.println("Contenido escrito en el archivo: " + filePath); // Mensaje de éxito
        } catch (IOException e) {
            e.printStackTrace(); // Imprime cualquier error
        }
    }

    // Método para leer de un archivo
    private static void readFromFile(String filePath) {
        File file = new File(filePath); // Crea un objeto File con la ruta especificada
        try (FileInputStream fis = new FileInputStream(file)) { // Abre un FileInputStream para leer el archivo
            byte[] data = new byte[(int) file.length()]; // Crea un array de bytes del tamaño del archivo
            fis.read(data); // Lee el contenido del archivo
            String content = new String(data); // Convierte los bytes leídos a String
            System.out.println("Contenido del archivo:\n\n" + content); // Muestra el contenido
        } catch (IOException e) {
            e.printStackTrace(); // Imprime cualquier error
        }
    }
}