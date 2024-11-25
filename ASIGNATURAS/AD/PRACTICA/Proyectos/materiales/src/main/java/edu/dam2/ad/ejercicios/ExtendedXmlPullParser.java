package edu.dam2.ad.ejercicios;

import java.io.FileInputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class ExtendedXmlPullParser
{
    public static void main(String[] args)
    {

        try
        {
            // Configuración del parser
            XmlPullParserFactory f = XmlPullParserFactory.newInstance();
            XmlPullParser parser = f.newPullParser();
            // Carga del archivo XML
            FileInputStream fileInputStream = new FileInputStream("C:\\CursoFP2\\ASIGNATURAS\\AD\\PRACTICA\\temp\\Pizza.xml");
            parser.setInput(fileInputStream, "UTF-8");
            // Procesamiento del XML
            int eventType = parser.getEventType();
            while (true)
            {
                switch (eventType)
                {
                    case XmlPullParser.START_DOCUMENT:
                        System.out.println("Inicio del documento XML");
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        System.out.println("Fin del documento XML");
                        break;
                    case XmlPullParser.START_TAG:
                        System.out.println("Inicio de etiqueta: " + parser.getName());
                        // Leer atributos de la etiqueta
                        int attributeCount = parser.getAttributeCount();
                        for (int i = 0; i < attributeCount; i++)
                        {
                            String attributeName = parser.getAttributeName(i);
                            String attributeValue = parser.getAttributeValue(i);
                            System.out.println("    Atributo: " + attributeName + " = " + attributeValue);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        System.out.println("Fin de etiqueta: " + parser.getName());
                        break;
                    case XmlPullParser.TEXT:
                        String content = parser.getText().trim();
                        if (!content.isEmpty())
                        {
                            System.out.println("Contenido: " + content);
                        }
                        break;
                    case XmlPullParser.CDSECT:
                        System.out.println("Sección CDATA: " + parser.getText());
                        break;
                    case XmlPullParser.COMMENT:
                        String comment = parser.getName();
                        // Esto debería ser parte de un manejo alternativo
                        System.out.println("Comentario encontrado");
                        System.out.println("Comentario: " + comment);
                        break;
                    case XmlPullParser.DOCDECL:
                        System.out.println("Declaración de documento (DOCTYPE): " + parser.getText());
                        break;
                    case XmlPullParser.PROCESSING_INSTRUCTION:
                        System.out.println("Instrucción de procesamiento: " + parser.getText());
                        break;
                    case XmlPullParser.ENTITY_REF:
                        System.out.println("Referencia de entidad: " + parser.getName());
                        break;
                    default:
                        System.out.println("Evento desconocido: " + eventType);
                        break;
                }
                if (eventType == XmlPullParser.END_DOCUMENT)
                {
                    break;
                }
                eventType = parser.next(); // Avanza al siguiente evento
            }
            fileInputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}