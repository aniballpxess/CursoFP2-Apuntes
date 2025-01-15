package edu.dam2.psp.eva2.ejercicios.hilos.fichajeempleados;

import java.time.LocalTime;

public class FicharEmpleado implements Runnable
{
    private final LocalTime HORA_DE_ENTRADA = LocalTime.of(9, 0, 0);
    private final LocalTime horaFichaje;
    private final String nombreEmpleado;

    public FicharEmpleado(String nombreEmpleado, LocalTime horaFichaje)
    {
        this.nombreEmpleado = nombreEmpleado;
        this.horaFichaje = horaFichaje;
    }

    @Override
    public void run()
    {
        String infoFichaje;
        if (HORA_DE_ENTRADA.isAfter(horaFichaje))
        {
            infoFichaje = """

                    |---- FICHAJE ----------------------------|
                    | Nombre: %-31s |
                    | Hora de fichaje: %-22s |
                    | Hora de entrada: %-22s |
                    |                                         |
                    | ¡Que tenga un buen y productivo día!    |
                    |-----------------------------------------|

                    """;
            System.out.printf(infoFichaje, nombreEmpleado, horaFichaje, HORA_DE_ENTRADA);
        }
        else
        {
            long dif_nanos = horaFichaje.toNanoOfDay() - HORA_DE_ENTRADA.toNanoOfDay();
            LocalTime dif_LT = LocalTime.ofNanoOfDay(dif_nanos);
            int dif_hora = dif_LT.getHour();
            int dif_min = dif_LT.getMinute();
            infoFichaje = """

                    |---- FICHAJE ----------------------------|
                    | Nombre: %-31s |
                    | Hora de fichaje: %-22s |
                    | Hora de entrada: %-22s |
                    |                                         |
                    | Llegas tarde por %02d horas y %02d minutos. |
                    |-----------------------------------------|

                    """;
            System.out.printf(infoFichaje, nombreEmpleado, horaFichaje, HORA_DE_ENTRADA, dif_hora, dif_min);
        }
    }

}
