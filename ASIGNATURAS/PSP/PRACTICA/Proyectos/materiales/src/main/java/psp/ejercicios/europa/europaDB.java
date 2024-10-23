package psp.ejercicios.europa;

import java.util.HashMap;
import java.util.Map;

public class europaDB
{
    private static final Map<String, String> countriesAndCapitals = new HashMap<>();

    public static void main(String[] args)
    {
        cargarMapa();
        
    }

    private static void cargarMapa()
    {
        countriesAndCapitals.put("Alemania", "Berlín");
        countriesAndCapitals.put("Andorra", "Andorra la Vieja");
        countriesAndCapitals.put("Austria", "Viena");
        countriesAndCapitals.put("Bélgica", "Bruselas");
        countriesAndCapitals.put("Bielorrusia", "Minsk");
        countriesAndCapitals.put("Bosnia y Herzegovina", "Sarajevo");
        countriesAndCapitals.put("Bulgaria", "Sofía");
        countriesAndCapitals.put("Chipre", "Nicosia");
        countriesAndCapitals.put("Croacia", "Zagreb");
        countriesAndCapitals.put("Dinamarca", "Copenhague");
        countriesAndCapitals.put("Eslovaquia", "Bratislava");
        countriesAndCapitals.put("Eslovenia", "Liubliana");
        countriesAndCapitals.put("España", "Madrid");
        countriesAndCapitals.put("Estonia", "Tallin");
        countriesAndCapitals.put("Finlandia", "Helsinki");
        countriesAndCapitals.put("Francia", "París");
        countriesAndCapitals.put("Grecia", "Atenas");
        countriesAndCapitals.put("Hungría", "Budapest");
        countriesAndCapitals.put("Irlanda", "Dublín");
        countriesAndCapitals.put("Islandia", "Reikiavik");
        countriesAndCapitals.put("Italia", "Roma");
        countriesAndCapitals.put("Letonia", "Riga");
        countriesAndCapitals.put("Lituania", "Vilna");
        countriesAndCapitals.put("Luxemburgo", "Luxemburgo");
        countriesAndCapitals.put("Macedonia del Norte", "Skopie");
        countriesAndCapitals.put("Malta", "La Valeta");
        countriesAndCapitals.put("Moldavia", "Chisináu");
        countriesAndCapitals.put("Mónaco", "Mónaco");
        countriesAndCapitals.put("Montenegro", "Podgorica");
        countriesAndCapitals.put("Noruega", "Oslo");
        countriesAndCapitals.put("Países Bajos", "Ámsterdam");
        countriesAndCapitals.put("Polonia", "Varsovia");
        countriesAndCapitals.put("Portugal", "Lisboa");
        countriesAndCapitals.put("Reino Unido", "Londres");
        countriesAndCapitals.put("República Checa", "Praga");
        countriesAndCapitals.put("Rumanía", "Bucarest");
        countriesAndCapitals.put("Rusia", "Moscú");
        countriesAndCapitals.put("San Marino", "San Marino");
        countriesAndCapitals.put("Serbia", "Belgrado");
        countriesAndCapitals.put("Suecia", "Estocolmo");
        countriesAndCapitals.put("Suiza", "Berna");
        countriesAndCapitals.put("Ucrania", "Kiev");
        countriesAndCapitals.put("Vaticano", "Ciudad del Vaticano");
    }
}
