package edu.dam2.psp.eva2.ejercicios.areatriangulos;

public class AreaTriangulos
{
    public static void main(String[] args)
    {
        for (int i = 0; i < 10; i++)
        {
            double baseTriangulo = Math.random() * 100;
            double alturaTriangulo = Math.random() * 100;

            Runnable tareaCalculoArea = new CalculoArea(baseTriangulo, alturaTriangulo);
            Thread hiloCalculoArea = new Thread(tareaCalculoArea, "Triangulo " + i);

            hiloCalculoArea.start();
        }
    }
}
