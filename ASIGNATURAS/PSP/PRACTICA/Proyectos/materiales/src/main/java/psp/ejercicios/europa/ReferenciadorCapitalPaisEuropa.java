package psp.ejercicios.europa;

import java.util.Map;
import java.util.Scanner;

public class ReferenciadorCapitalPaisEuropa
{
    private static final Map<String, String> mapaPaisesCapitales = Map.ofEntries
    (
        Map.entry("Alemania", "Berlín"),
        Map.entry("Andorra", "Andorra la Vieja"),
        Map.entry("Austria", "Viena"),
        Map.entry("Bélgica", "Bruselas"),
        Map.entry("Bielorrusia", "Minsk"),
        Map.entry("Bosnia y Herzegovina", "Sarajevo"),
        Map.entry("Bulgaria", "Sofía"),
        Map.entry("Chipre", "Nicosia"),
        Map.entry("Croacia", "Zagreb"),
        Map.entry("Dinamarca", "Copenhague"),
        Map.entry("Eslovaquia", "Bratislava"),
        Map.entry("Eslovenia", "Liubliana"),
        Map.entry("España", "Madrid"),
        Map.entry("Estonia", "Tallin"),
        Map.entry("Finlandia", "Helsinki"),
        Map.entry("Francia", "París"),
        Map.entry("Grecia", "Atenas"),
        Map.entry("Hungría", "Budapest"),
        Map.entry("Irlanda", "Dublín"),
        Map.entry("Islandia", "Reikiavik"),
        Map.entry("Italia", "Roma"),
        Map.entry("Letonia", "Riga"),
        Map.entry("Lituania", "Vilna"),
        Map.entry("Luxemburgo", "Luxemburgo"),
        Map.entry("Macedonia del Norte", "Skopie"),
        Map.entry("Malta", "La Valeta"),
        Map.entry("Moldavia", "Chisináu"),
        Map.entry("Mónaco", "Mónaco"),
        Map.entry("Montenegro", "Podgorica"),
        Map.entry("Noruega", "Oslo"),
        Map.entry("Países Bajos", "Ámsterdam"),
        Map.entry("Polonia", "Varsovia"),
        Map.entry("Portugal", "Lisboa"),
        Map.entry("Reino Unido", "Londres"),
        Map.entry("República Checa", "Praga"),
        Map.entry("Rumanía", "Bucarest"),
        Map.entry("Rusia", "Moscú"),
        Map.entry("San Marino", "San Marino"),
        Map.entry("Serbia", "Belgrado"),
        Map.entry("Suecia", "Estocolmo"),
        Map.entry("Suiza", "Berna"),
        Map.entry("Ucrania", "Kiev"),
        Map.entry("Vaticano", "Ciudad del Vaticano")
    );

    public static void main(String[] args)
    {
        Scanner escaner = new Scanner(System.in);
        String pais = escaner.nextLine();
        String capital = mapaPaisesCapitales.get(pais);
        if (capital != null) 
        {
            System.out.println(capital);
        }
        escaner.close();
    }
}
