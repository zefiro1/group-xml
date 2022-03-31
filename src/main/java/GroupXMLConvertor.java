import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import static java.io.File.separator;

/**
 * <li>
 * Proyecto: group-xml
 * </li>
 * <li>
 * Paquete: PACKAGE_NAME; *</li>
 * <p>
 * <li>
 * Autor: José
 * </li>
 * <li>
 * Día: 31/03/2022
 * </li>
 * <li>
 * Tiempo: 10
 * </li>
 * <li>
 * Version: 0.1
 * </li>
 * <p>
 */

public class GroupXMLConvertor {

    public static void main(String[] args) {
        File archivoGroup = new File("src" + separator + "main" + separator + "java" + separator + "archivo" + separator + "group");
        RandomAccessFile leerContenidoGroup = null;
        File archivoGroupXML = new File("src" + separator + "main" + separator + "java" + separator + "archivo" + separator + "group.xml");
        RandomAccessFile crearVersionXML = null;
        String[] contenidoFichero = null;
        try {
            leerContenidoGroup = new RandomAccessFile(archivoGroup, "r");
            crearVersionXML = new RandomAccessFile(archivoGroupXML, "rw");

            crearVersionXML.writeBytes("""
                    <groups>
                    """);

            for (int i = 0; i < 58; i++) {
                contenidoFichero = leerContenidoGroup.readLine().split(":");
                crearVersionXML.writeBytes(String.format("""
                            <group>
                                <name>%s</name>
                                <gid>%s</gid>
                            </group>
                        """, contenidoFichero[0], contenidoFichero[2]
                ));
            }


            crearVersionXML.writeBytes("""
                    </groups>
                    """);


        } catch (
                FileNotFoundException e) {
            System.err.println("La ruta del archivo especificado no se ha encontrado");
        } catch (
                IOException e) {
            System.err.println("Problemas con la entrada salida de archivos");
        } finally {
            try {
                if (leerContenidoGroup != null) {
                    leerContenidoGroup.close();
                }
            } catch (IOException e) {
                System.err.println("Problemas con la entrada salida de archivos");
                try {
                    if (crearVersionXML != null) {
                        crearVersionXML.close();
                    }
                } catch (IOException ex) {
                    System.err.println("Problemas con la entrada salida de archivos");
                }
            }
        }
    }

}