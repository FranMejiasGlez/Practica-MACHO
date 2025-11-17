
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Mejias Gonzalez Francisco
 */
public class EscribeEmpleados {

    public static void main(String[] args) {
        File fiche = new File("fiche.dat");
        FicheDAO fdao = new FicheDAO(fiche);
        try {
            FileOutputStream fos = new FileOutputStream(fiche);
            DataOutputStream dos = new DataOutputStream(fos);

            // Crear empleados
            Empleado[] empleados = {
                new Empleado("García López Juan", Sexo.HOMBRE, 1500, new Fecha((short) 2015, (byte) 3, (byte) 15), Tipo.FIJO, Provincia.SEVILLA),
                new Empleado("Martínez Ruiz María", Sexo.MUJER, 1800, new Fecha((short) 2018, (byte) 7, (byte) 22), Tipo.FIJO, Provincia.JAEN),
                new Empleado("Rodríguez González Carlos de la Montoya negra", Sexo.HOMBRE, 1600, new Fecha((short) 2019, (byte) 1, (byte) 10), Tipo.COMISION, Provincia.HUELVA),
                new Empleado("Fernández Díaz Ana", Sexo.MUJER, 1700, new Fecha((short) 2017, (byte) 5, (byte) 8), Tipo.DOMICILIO, Provincia.ALMERIA),
                new Empleado("López Sánchez Miguel", Sexo.HOMBRE, 1900, new Fecha((short) 2016, (byte) 9, (byte) 20), Tipo.FIJO, Provincia.CORDOBA),
                new Empleado("Jiménez Moreno Laura", Sexo.MUJER, 1550, new Fecha((short) 2020, (byte) 2, (byte) 14), Tipo.COMISION, Provincia.GRANADA),
                new Empleado("Castillo Navarro David", Sexo.HOMBRE, 1750, new Fecha((short) 2014, (byte) 11, (byte) 3), Tipo.FIJO, Provincia.MALAGA),
                new Empleado("Ramírez Torres Sofía", Sexo.MUJER, 1650, new Fecha((short) 2019, (byte) 6, (byte) 25), Tipo.DOMICILIO, Provincia.CADIZ),
                new Empleado("Peña Vega Roberto", Sexo.HOMBRE, 1820, new Fecha((short) 2013, (byte) 4, (byte) 18), Tipo.FIJO, Provincia.SEVILLA),
                new Empleado("Morales Cabrera Patricia", Sexo.MUJER, 1680, new Fecha((short) 2017, (byte) 8, (byte) 9), Tipo.COMISION, Provincia.JAEN),
                new Empleado("Soto Herrera Francisco", Sexo.HOMBRE, 1540, new Fecha((short) 2020, (byte) 10, (byte) 12), Tipo.DOMICILIO, Provincia.HUELVA),
                new Empleado("Velasco Campos Isabel", Sexo.MUJER, 1920, new Fecha((short) 2015, (byte) 12, (byte) 5), Tipo.FIJO, Provincia.GRANADA),
                new Empleado("Ramos Ortiz Andrés", Sexo.HOMBRE, 1770, new Fecha((short) 2018, (byte) 3, (byte) 28), Tipo.COMISION, Provincia.CORDOBA),
                new Empleado("Guerrero Álvarez Beatriz", Sexo.MUJER, 1610, new Fecha((short) 2019, (byte) 9, (byte) 7), Tipo.FIJO, Provincia.GRANADA),
                new Empleado("Carrillo Rubio Enrique", Sexo.HOMBRE, 1680, new Fecha((short) 2016, (byte) 6, (byte) 21), Tipo.DOMICILIO, Provincia.MALAGA),
                new Empleado("Domínguez Parra Cristina", Sexo.MUJER, 1890, new Fecha((short) 2014, (byte) 2, (byte) 14), Tipo.FIJO, Provincia.GRANADA),
                new Empleado("Navarro Suárez Elena", Sexo.MUJER, 1710, new Fecha((short) 2018, (byte) 4, (byte) 11), Tipo.FIJO, Provincia.SEVILLA),
                new Empleado("Flores Romero Javier", Sexo.HOMBRE, 1620, new Fecha((short) 2019, (byte) 8, (byte) 30), Tipo.COMISION, Provincia.SEVILLA),
                new Empleado("Cano Blanco Marta", Sexo.MUJER, 1580, new Fecha((short) 2020, (byte) 5, (byte) 19), Tipo.DOMICILIO, Provincia.SEVILLA),
                new Empleado("Medina Gómez Ricardo", Sexo.HOMBRE, 1850, new Fecha((short) 2015, (byte) 11, (byte) 2), Tipo.FIJO, Provincia.CORDOBA),
                new Empleado("Ochoa Linares Verónica", Sexo.MUJER, 1720, new Fecha((short) 2017, (byte) 7, (byte) 13), Tipo.COMISION, Provincia.JAEN),
                new Empleado("Vargas Ibáñez Raúl", Sexo.HOMBRE, 1650, new Fecha((short) 2019, (byte) 2, (byte) 27), Tipo.FIJO, Provincia.HUELVA),
                new Empleado("Rosario Fuentes Claudia", Sexo.MUJER, 1800, new Fecha((short) 2016, (byte) 10, (byte) 8), Tipo.DOMICILIO, Provincia.ALMERIA),
                new Empleado("Montoya Vázquez Santiago", Sexo.HOMBRE, 1690, new Fecha((short) 2018, (byte) 6, (byte) 16), Tipo.FIJO, Provincia.MALAGA),
                new Empleado("Beltrán Nuñez Rosario", Sexo.MUJER, 1740, new Fecha((short) 2014, (byte) 9, (byte) 24), Tipo.COMISION, Provincia.GRANADA)
            };

            // Escribir empleados en el fichero
            for (Empleado emp : empleados) {
                fdao.escribir(dos, emp);
            }

            dos.close();
            fos.close();

            System.out.println(empleados.length + " empleados escritos correctamente en fiche.dat");

        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: No se puede crear el fichero");
        } catch (IOException ioe) {
            System.out.println("Error de E/S: " + ioe.getMessage());
        }
    }
}