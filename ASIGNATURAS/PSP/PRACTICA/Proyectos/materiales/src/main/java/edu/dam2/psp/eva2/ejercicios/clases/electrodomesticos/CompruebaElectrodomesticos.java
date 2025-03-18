package edu.dam2.psp.eva2.ejercicios.clases.electrodomesticos;

public class CompruebaElectrodomesticos {
    public static void main(String[] args) {
        int horas = 24;
        double precio_kWh = 0.1857;
        Electrodomestico[] electrodomesticos = new Electrodomestico[5];
        electrodomesticos[0] = new Electrodomestico("Lavadora", "Bosch", 1.2);
        electrodomesticos[1] = new Electrodomestico("Frigorífico", "Balay", 0.8);
        electrodomesticos[2] = new Electrodomestico("Lavavajillas", "Siemens", 1.5);
        electrodomesticos[3] = new Electrodomestico("Secadora", "AEG", 1.8);
        electrodomesticos[4] = new Electrodomestico("Horno", "Teka", 2.0);
    
        System.out.println();
        System.out.println("Tiempo en funcionamiento: " + horas + " horas");
        System.out.println("Precio del kW/h: " + precio_kWh + " €");
        System.out.println();
        for (Electrodomestico electrodomestico : electrodomesticos) {
            System.out.println(electrodomestico);
            System.out.println("Consumo Total: " + electrodomestico.getConsumo(horas) + " kW");
            System.out.println("Coste Total: " + electrodomestico.getCosteConsumo(horas, precio_kWh) + " €");
            System.out.println();
        }
    }
}
