
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Mejias Gonzalez Francisco
 */
public class Listado10oMasAnios {

    public static void main(String[] args) {
        List<Empleado> listadoEmple, listadoEmpleViejos;
        File fiche;
        FicheDAO fdao;
        FileInputStream fis;
        DataInputStream dis;
        fiche = new File("fiche.dat");
        boolean seguir;
        BufferedReader teclado;
        int iterator = 0;
        short contador = 0;
        float porcentaje;

        try {
            fdao = new FicheDAO(fiche);
            fis = new FileInputStream(fdao.fiche);
            dis = new DataInputStream(fis);
            //Inicializar ff
            FicheDAO.ff = false;
            //  System.out.println("numero de empleados" + fdao.getNumeroRegistros(dis));
            System.out.println("Leyendo empleados...\n");
            //Leer empleados
            listadoEmple = fdao.leerFichero(dis);
            System.out.println("Listando empleados...");
            teclado = new BufferedReader(new InputStreamReader(System.in));
            seguir = true;
            listadoEmpleViejos = new LinkedList();
            //Guardar los empleados que ya son viejos.
            for (Empleado empleado : listadoEmple) {
                if (empleado != null
                        && empleado.getFechaIngreso().aniosTranscurridos() >= 10) {
                    listadoEmpleViejos.add(empleado);
                }
            }
            //Mostrar paginadamente los empleados de la listaEmpleViejos
            do {

                for (int i = 0; i < 2; i++) {
                    try {
                        Empleado emple = listadoEmpleViejos.get(iterator);

                        System.out.println(emple.toString());


                        iterator++;
                        if (iterator >= listadoEmpleViejos.size()) {
                            seguir = false;
                        }
                    } catch (IndexOutOfBoundsException ioobe) {
                        seguir = false;
                    }
                }
                if (seguir) {
                    System.out.println("Pulsa Enter para mostrar los siguientes..");
                    teclado.readLine();
                }
            } while (seguir);
            System.out.println("Lista terminada..");
            dis.close();
            fis.close();

            System.out.println("Numero de empleados mayores o igual a 10 años: "
                    + listadoEmpleViejos.size());

            porcentaje = (listadoEmpleViejos.size() / (float) fdao.getNumeroRegistros()) * 100;
            System.out.println("Porcentaje de empleados con respecto al total --> " + porcentaje + " %");


        } catch (FileNotFoundException fnfe) {
            System.out.println("Fichero no encontrado");
        } catch (IOException ioe) {
        }



    }
}
