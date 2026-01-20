
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

        listaEmple = fdao.leerFichero();

        System.out.println("Provincia(s) con mayor numero de empleados: ");
        for (Empleado empleado : listaEmple) {
            Provincia prov = empleado.getProvincia();
            if (conteoProvincias.containsKey(prov)) {
                conteoProvincias.put(prov, conteoProvincias.get(prov) + 1);
            } else {
                conteoProvincias.put(prov, 1);
            }
        }
        int maxEmpleados = Collections.max(conteoProvincias.values());

        for (Map.Entry<Provincia, Integer> entry : conteoProvincias.entrySet()) {
            if (entry.getValue() == maxEmpleados) {
                System.out.printf("Provincia: %s - %d empleados%n",
                        entry.getKey(), entry.getValue());
            }
        }

    }
}
