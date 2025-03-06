package edu.dam2.psp.eva2.ejercicios.clases.gestionevento;

import java.util.Scanner;

public class GestionEvento
{
    private static Scanner sc = new Scanner(System.in);
    private static Zona principal = new Zona(1000);
    private static Zona compraVenta = new Zona(200);
    private static Zona vip = new Zona(25);

    private static void cargarMenuPrincipal()
    {
        while (true)
        {
            try
            {
                System.out.println("======== MADCOOL 2025 =========");
                System.out.println("1. Mostrar número de entradas libres");
                System.out.println("2. Vender entradas");
                System.out.println("3. Salir");
                System.out.print("Elige una opción: ");
                int opcion = Integer.parseInt(sc.nextLine());
                if (opcion == 1)
                {
                    System.out.println("Número de entradas libres:");
                    System.out.println("Zona principal: " + principal.getEntradasPorVender());
                    System.out.println("Zona compra-venta: " + compraVenta.getEntradasPorVender());
                    System.out.println("Zona VIP: " + vip.getEntradasPorVender());
                    continue;
                }
                if (opcion == 2)
                {
                    cargarMenuVentaEntradas();
                    continue;
                }
                if (opcion == 3)
                {
                    System.out.println("\nGracias por usar nuestro servicio de venta de entradas.");
                    break;
                }
                throw new NumberFormatException();
            }
            catch (NumberFormatException nfe)
            {
                System.out.println("Por favor, escoge una opción válida.");
            }
        }
    }

    private static void cargarMenuVentaEntradas()
    {
        while (true)
        {
            try
            {
                System.out.println("====== VENTA DE ENTRADAS ======");
                System.out.println("¿Qué zona deseas elegir?");
                System.out.println("1. Zona principal");
                System.out.println("2. Zona compra-venta");
                System.out.println("3. Zona VIP");
                System.out.println("4. Volver al menú principal");
                System.out.print("Elige una opción: ");
                int opcion = Integer.parseInt(sc.nextLine());
                if (opcion == 1)
                {

                    System.out.print("¿Cuántas entradas deseas comprar? ");
                    int entradas = Integer.parseInt(sc.nextLine());
                    principal.vender(entradas);
                    continue;
                }
                if (opcion == 2)
                {

                    System.out.print("¿Cuántas entradas deseas comprar? ");
                    int entradas = Integer.parseInt(sc.nextLine());
                    compraVenta.vender(entradas);
                    continue;
                }
                if (opcion == 3)
                {

                    System.out.print("¿Cuántas entradas deseas comprar? ");
                    int entradas = Integer.parseInt(sc.nextLine());
                    vip.vender(entradas);
                    continue;
                }
                if (opcion == 4)
                {
                    break;
                }
                throw new NumberFormatException();
            }
            catch (NumberFormatException nfe)
            {
                System.out.println("Por favor, introduce solo opciones o valores válidos.");
            }
        }
    }

    public static void main(String[] args)
    {
        cargarMenuPrincipal();
    }
}
