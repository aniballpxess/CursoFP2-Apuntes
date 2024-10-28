package psp.utiles;

import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

public class Parametros
{
    public static final DateTimeFormatter normalFormat = DateTimeFormatter.ofPattern("<yyyy-MM-dd HH:mm:ss>");

    public static final Path materialesPath = Path.of("C:",
                                                      "CursoFP2",
                                                      "ASIGNATURAS",
                                                      "PSP",
                                                      "PRACTICA",
                                                      "Proyectos",
                                                      "materiales");

    
}
