package psp.ejemplos.procesos;

import java.util.Map;

public class UsoDeMapas
{
    public static void main(String[] args)
    {
        ProcessBuilder pBuilder = new ProcessBuilder("Notepad.exe", "datos.txt");
        Map<String, String> env = pBuilder.environment();
        for (Map.Entry<String, String> ent : env.entrySet())
        {
            System.out.println(ent.getKey() + " = " + ent.getValue());
        }
    }
}
