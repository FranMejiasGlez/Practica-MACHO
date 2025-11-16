
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mejias Gonzalez Francisco
 */
public class ListarProvinciasConMasEmpleados {

    public static void main(String[] args) {
        List<Empleado> listaEmple = new LinkedList<Empleado>();
        File fiche = new File("fiche.dat");
        FicheDAO fdao = new FicheDAO(fiche);
        Map<Provincia, Integer> conteoProvincias = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream(fiche);
            DataInputStream dis = new DataInputStream(fis);
            listaEmple = fdao.leerFichero(dis);

            System.out.println("Provincia(s) con mayor numero de empleados: ");
            for (Empleado empleado : listaEmple) {
                Provincia prov = empleado.getProvincia();
                if (conteoProvincias.containsKey(prov)) {
                    conteoProvincias.put(prov, conteoProvincias.get(prov) + 1);
                } else {
                    conteoProvincias.put(prov, 1);
                }
            }
            for (Map.Entry<Provincia, Integer> entry : conteoProvincias.entrySet()) {
                System.out.println("Provincia: " + entry.getKey()
                        + " | " + "Empleados: " + entry.getValue());
            }

        } catch (IOException ioe) {
            System.out.println("Error de E/S en fichero");
        }
    }
}
