
import FicheDAO.FicheDAO;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import FicheDAO.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mejias Gonzalez Francisco
 * @Correcciones Mejias Gonzalez Francisco
 */
public class ListadoNombreYSueldo {

    public static void main(String[] args) {
        List<Empleado> listaEmple;
        File fiche = new File("fiche.dat");
        FicheDAO fdao = new FicheDAO(fiche);
        float maxSueldo;

        try {
            FileInputStream fis = new FileInputStream(fiche);
            DataInputStream dis = new DataInputStream(fis);
            listaEmple = new LinkedList<>();
            Empleado emple;

            while (!FicheDAO.ff) {
                emple = fdao.leerRegistro();
                if (emple != null) {
                    listaEmple.add(emple);
                }
            }
            for (Empleado empleado : listaEmple) {
                System.out.println("Apellidos Nombre: " + empleado.getNomApe()
                        + "\nSueldo: " + empleado.getSueldo() + " €");
                System.out.println("");
            }
            Empleado empleadoMaxSueldo = Collections.max(listaEmple, Empleado.BY_SUELDO);
            System.out.println("Empleado(s) con el mayor sueldo: ");
            maxSueldo = empleadoMaxSueldo.getSueldo();
            for (Empleado empleado : listaEmple) {
                if (empleado.getSueldo() == maxSueldo) {
                    System.out.println(empleado.getNomApe() + " | " + empleado.getSueldo() + " €");
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Fichero no encontrado");
        } catch (IOException ioe) {
            System.out.println("Error de E/S con fichero");
        }

    }
}
