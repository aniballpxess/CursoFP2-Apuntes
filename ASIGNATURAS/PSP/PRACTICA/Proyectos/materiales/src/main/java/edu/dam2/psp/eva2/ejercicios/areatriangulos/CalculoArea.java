package edu.dam2.psp.eva2.ejercicios.areatriangulos;

public class CalculoArea implements Runnable {
    private final double baseTriangulo;
    private final double alturaTriangulo;

    public CalculoArea(double baseTriangulo, double alturaTriangulo)
    {
        this.baseTriangulo = baseTriangulo;
        this.alturaTriangulo = alturaTriangulo;
    }

    @Override
    public void run()
    {
        double areaTriangulo = baseTriangulo * alturaTriangulo / 2;
        String mensajeResultado = """
                %s
                 - base:   %s
                 - altura: %s
                 - area:   %s
                
                """;
        System.out.printf(
            mensajeResultado, 
            Thread.currentThread().getName(), 
            baseTriangulo, 
            alturaTriangulo, 
            areaTriangulo
        );
    }

}
