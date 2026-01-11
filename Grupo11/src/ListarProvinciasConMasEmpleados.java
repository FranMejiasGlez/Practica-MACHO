


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import FicheDAO.*;
/**
 *
 * @author Mejias Gonzalez Francisco
 * @Correcciones Mejias Gonzalez Francisco
 */
public class ListarProvinciasConMasEmpleados {

    public static void main(String[] args) {
        List<Empleado> listaEmple;
        File fiche = new File("fiche.dat");
        FicheDAO fdao = new FicheDAO();
        Map<Provincia, Integer> conteoProvincias = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream(fiche);
            DataInputStream dis = new DataInputStream(fis);
            listaEmple = new LinkedList<>();
            Empleado emple;
            while (FicheDAO.isFf() == false) {
                emple = fdao.leerRegistro(dis);
                if (emple != null) {
                    listaEmple.add(emple);
                }
            }

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
            dis.close();
            fis.close();

        } catch (IOException ioe) {
            System.out.println("Error de E/S en fichero");
        }
    }
}
