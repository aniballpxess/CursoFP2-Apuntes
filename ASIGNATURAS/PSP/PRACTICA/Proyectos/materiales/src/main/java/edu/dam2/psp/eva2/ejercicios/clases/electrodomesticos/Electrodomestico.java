package edu.dam2.psp.eva2.ejercicios.clases.electrodomesticos;

public class Electrodomestico
{
    private String tipo;
    private String marca;
    private double potencia;

    public Electrodomestico(String tipo, String marca, double potencia)
    {
        this.tipo = tipo;
        this.marca = marca;
        this.potencia = potencia;
    }

    public String getTipo()
    {
        return tipo;
    }

    public String getMarca()
    {
        return marca;
    }

    public double getPotencia()
    {
        return potencia;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public void setMarca(String marca)
    {
        this.marca = marca;
    }
    
    public void setPotencia(double potencia)
    {
        this.potencia = potencia;
    }

    @Override
    public String toString()
    {
        return "Tipo: " + tipo + " - Marca: " + marca + " - Potencia: " + potencia + " kW";
    }

    public double getConsumo(int horas)
    {
        return potencia * horas;
    }

    public double getCosteConsumo(int horas, double precio_kWh)
    {
        return getConsumo(horas) * precio_kWh;
    }
}
