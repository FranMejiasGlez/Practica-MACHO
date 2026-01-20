
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
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
public class ListadoNombreYSueldo {

    public static void main(String[] args) {
        List<Empleado> listaEmple = new LinkedList<Empleado>();
        File fiche = new File("fiche.dat");
        FicheDAO fdao = new FicheDAO(fiche);
        float maxSueldo;


        listaEmple = fdao.leerFichero();
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


    }
}
