package edu.dam2.psp.eva2.ejercicios.mesesestaciones;

import static edu.dam2.psp.utiles.Funciones.printErrorLine;

import java.util.List;
import java.util.Map;

public class EscogerMesesEstacion implements Runnable
{
    private static final Map<String, List<String>> ESTACIONES = Map.ofEntries(
            Map.entry("invierno", List.of("Diciembre", "Enero", "Febrero")),
            Map.entry("primavera", List.of("Marzo", "Abril", "Mayo")),
            Map.entry("verano", List.of("Junio", "Julio", "Agosto")),
            Map.entry("otoño", List.of("Septiembre", "Octubre", "Noviembre")));
    private final String estacion;

    public EscogerMesesEstacion(String estacion)
    {
        this.estacion = estacion;
    }

    @Override
    public void run()
    {
        List<String> mesesEstacion = ESTACIONES.get(estacion.toLowerCase());
        System.out.println("\nMeses de " + estacion + ":");
        for (String mes : mesesEstacion)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                for (StackTraceElement element : e.getStackTrace())
                {
                    printErrorLine(element.toString());
                }
            }
            System.out.println(" - " + mes);
        }
    }

}
