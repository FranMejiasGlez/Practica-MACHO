
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mejias Gonzalez Francisco
 */
public class Listado10oMasAnios {

    public static void main(String[] args) {
        List<Empleado> listadoEmple;
        File fiche;
        FicheDAO fdao;
        FileInputStream fis;
        DataInputStream dis;
        fiche = new File("fiche.dat");
        listadoEmple = new LinkedList();
        Empleado emple = null;
        short contador = 0;
        float porcentaje;
        try {
            fdao = new FicheDAO(fiche);
            fis = new FileInputStream(fdao.fiche);
            dis = new DataInputStream(fis);
            //Inicializar bandera
            FicheDAO.ff = false;
            //  System.out.println("numero de empleados" + fdao.getNumeroRegistros(dis));
            System.out.println("Leyendo empleados...\n");
            //Leer empleados
            listadoEmple = fdao.leerFichero(dis);
            System.out.println("Listando empleados...");
            for (Empleado empleado : listadoEmple) {
                if (empleado != null && empleado.getFechaIngreso().aniosTranscurridos() >= 10) {
                    System.out.println(empleado.toString());
                    contador++;
                }
            }
            dis.close();
            fis.close();

            System.out.println("Numero de empleados: " + contador);

            porcentaje = (contador / (float) fdao.getNumeroRegistros()) * 100;
            System.out.println("Porcentaje de empleados --> " + porcentaje + " %");


        } catch (FileNotFoundException fnfe) {
            System.out.println("Fichero no encontrado");
        } catch (IOException ioe) {
        }



    }
}
