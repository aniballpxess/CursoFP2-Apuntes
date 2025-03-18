package edu.dam2.psp.eva2.ejercicios.clases.pizzas;

public class Pizza
{
    private String tamaño;
    private String tipo;
    private String estado;

    private static int totalPedidas = 0;
    private static int totalServidas = 0;

    public Pizza(String tipo, String tamaño)
    {
        this.tipo = tipo;
        this.tamaño = tamaño;
        this.estado = "pedida";
        totalPedidas++;
    }

    public void sirve()
    {
        if (estado.equals("pedida"))
        {
            estado = "servida";
            totalServidas++;
            return;
        }
        System.out.println("Esa pizza ya se ha servido");
    }

    @Override
    public String toString()
    {
        return "pizza " + tipo + " " + tamaño + ", " + estado;
    }

    public static int getTotalPedidas()
    {
        return totalPedidas;
    }

    public static int getTotalServidas()
    {
        return totalServidas;
    }
}
