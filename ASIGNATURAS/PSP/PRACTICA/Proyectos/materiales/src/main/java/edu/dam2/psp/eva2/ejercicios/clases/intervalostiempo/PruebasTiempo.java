package edu.dam2.psp.eva2.ejercicios.clases.intervalostiempo;

public class PruebasTiempo {
    public static void main(String[] args) {
        Tiempo t1 = new Tiempo(0, 35, 20);
        Tiempo t2 = new Tiempo(0, 30, 40);

        Tiempo t3 = t1.sumar(t2);
        Tiempo t4 = t1.restar(t2);
        System.out.println(t1 + " + " + t2 + " = " + t3);
        System.out.println(t1 + " - " + t2 + " = " + t4);

        System.out.println("--------------------");
        
        Tiempo t5 = new Tiempo(10, 35, 5);
        System.out.println("Tiempo inicial: " + t5);
        for (int i = 0; i < 100; i++) {
            t5 = t5.restar(t1);
            t5 = t5.sumar(t2);
        }
        System.out.println("Tiempo final: " + t5);
    }
}
