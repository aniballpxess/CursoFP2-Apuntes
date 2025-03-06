package edu.dam2.psp.utiles;

import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

public class Parametros
{
    public static final DateTimeFormatter NORMAL_FORMAT = DateTimeFormatter.ofPattern("<yyyy-MM-dd HH:mm:ss>");

    public static final Path MATERIALES_PATH = Path.of("C:",
                                                      "CursoFP2",
                                                      "ASIGNATURAS",
                                                      "PSP",
                                                      "PRACTICA",
                                                      "Proyectos",
                                                      "materiales");

    
}
