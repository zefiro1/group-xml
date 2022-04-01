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
 * Version: 0.2
 * </li>
 * <p>
 */

public class GroupXMLConvertor {

    public static void main(String[] args) {
        generandoXML();
    }

    private static void generandoXML() {
        File archivoGroup = new File(
                "src" + separator + "main" + separator + "java" + separator + "archivo" + separator + "group");
        RandomAccessFile leerContenidoGroup = null;
        File archivoGroupXML = new File(
                "src" + separator + "main" + separator + "java" + separator + "archivo" + separator + "group.xml");
        RandomAccessFile crearVersionXML = null;
        String[] contenidoFichero;
        String[] contenidoFicheroUsuarios;
        try {
            leerContenidoGroup = new RandomAccessFile(archivoGroup, "r");
            crearVersionXML = new RandomAccessFile(archivoGroupXML, "rw");

            crearVersionXML.writeBytes("""
                    <groups>
                    """);
            while (leerContenidoGroup.getFilePointer() < leerContenidoGroup.length()){
                contenidoFichero = leerContenidoGroup.readLine().split(":");
                crearVersionXML.writeBytes(String.format("""
                            <group>
                                <name>%s</name>
                                <gid>%s</gid>
                            </group>
                        """, contenidoFichero[0], contenidoFichero[2]
                ));
                if(contenidoFichero.length>3){
                  contenidoFicheroUsuarios = contenidoFichero[3].replace("[","").replace("]","").split(",");
                    System.out.println(Arrays.toString(new String[]{contenidoFicheroUsuarios[0]}));
                  crearVersionXML.writeBytes(String.format("""
                            <group>
                                <name>%s</name>
                                <gid>%s</gid>
                                <users>
                                     <user>%s</user>
                                </users>
                            </group>
                        """, contenidoFichero[0], contenidoFichero[2],contenidoFicheroUsuarios[0]
                    ));
                  if(contenidoFicheroUsuarios.length>1){
                      crearVersionXML.writeBytes(String.format("""
                            <group>
                                <name>%s</name>
                                <gid>%s</gid>
                                <users>
                                     <user>%s</user>
                                     <user>%s</user>
                                </users>
                            </group>
                        """, contenidoFichero[0], contenidoFichero[2],contenidoFicheroUsuarios[0],contenidoFicheroUsuarios[1]
                      ));
                  }
                }
            }



            crearVersionXML.writeBytes("""
                    </groups>
                    """);

            System.out.println("Archivo creado "+archivoGroupXML.getCanonicalPath());
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