package FicheDAO;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Mejias Gonzalez Francisco
 */
public class LeerFichero {
    public static void main(String[] args) {
        File fiche = new File("fiche.dat");
        Empleado emple;
        FileInputStream fis = null;
        String nombreApes;
        Sexo sexoFromChar;
        Provincia provincia;
        float salario;
        short anio;
        byte dia, mes;
        Fecha fechaIngreso;
        Tipo tipoEmpleFromChar;
        char sexo, tipoEmple;
        boolean finFichero = false;
        
        try {
            fis = new FileInputStream(fiche);
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado");
            return;
        }
        
        DataInputStream dis = new DataInputStream(fis);
        try {
            while (!finFichero) {
                try {
                    //Leer nombreApes
                    nombreApes = dis.readUTF().trim();
                    //Leer sexo
                    sexo = dis.readChar();
                    sexoFromChar = Sexo.fromCodigo(sexo);
                    //Leer salario
                    salario = dis.readFloat();
                    //Leer anio ingreso
                    anio = dis.readShort();
                    //Leer mes ingreso
                    mes = dis.readByte();
                    //Leer dia ingreso
                    dia = dis.readByte();
                    //Construir fechaIngreso
                    fechaIngreso = new Fecha(anio, mes, dia);
                    //Leer tipo emple
                    tipoEmple = dis.readChar();
                    tipoEmpleFromChar = Tipo.fromCodigo(tipoEmple);
                    //Leer provincia emple
                    provincia = Provincia.fromCodigo(dis.readByte());
                    //Construir el empleado con los datos leidos
                    emple = new Empleado(nombreApes, sexoFromChar,
                            salario, fechaIngreso, tipoEmpleFromChar, provincia);
                    System.out.println(emple.toString());
                } catch (EOFException eof) {
                    // Fin de fichero alcanzado correctamente
                    finFichero = true;
                }
            }
        } catch (IOException ex) {
            System.out.println("Error de E/S leyendo empleado: " + ex.getMessage());
        } finally {
            try {
                dis.close();
                fis.close();
            } catch (IOException ex) {
                System.out.println("Error cerrando fichero");
            }
        }
    }
}