
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Mejias Gonzalez Francisco / Andy Jan
 */
public class FicheDAO {

    public static boolean ff;
    public File fiche;
    private DataInputStream flujoLectura = null;

    public FicheDAO(File fiche) {
        this.fiche = fiche;
    }

    private void cerrarFlujo() {
        if (flujoLectura != null) {
            try {
                flujoLectura.close();
            } catch (IOException e) {
                // Ignorar error al cerrar
            }
            flujoLectura = null;
            ff = false;
        }
    }

    public List<Empleado> leerFichero() {
        List<Empleado> lista = new LinkedList<>();

        // Limpieza inicial
        cerrarFlujo();

        try {
            boolean fin = false;
            while (!fin) {
                Empleado emple = leerRegistro();

                if (emple != null) {
                    lista.add(emple);
                } else {
                    fin = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer fichero completo: " + e.getMessage());
        } finally {
            // Aseguramos que se cierra al terminar de leer todo el fichero
            cerrarFlujo();
        }

        return lista;
    }

    public Empleado leerRegistro() throws IOException {
        // Si el flujo no está abierto, lo abrimos
        if (flujoLectura == null) {
            if (!fiche.exists()) {
                return null;
            }
            flujoLectura = new DataInputStream(new FileInputStream(fiche));
            FicheDAO.ff = false;
        }

        String nombreApes;
        char sexo, tipoEmple;
        Provincia provincia;
        float salario;
        byte mes, dia;
        short anio;
        Sexo sexoFromChar;
        Tipo tipoEmpleFromChar;
        Fecha fechaIngreso;
        Empleado emple = null;

        try {
            nombreApes = flujoLectura.readUTF().trim();
            sexo = flujoLectura.readChar();
            sexoFromChar = Sexo.fromCodigo(sexo);
            salario = flujoLectura.readFloat();
            anio = flujoLectura.readShort();
            mes = flujoLectura.readByte();
            dia = flujoLectura.readByte();

            fechaIngreso = new Fecha(anio, mes, dia);

            tipoEmple = flujoLectura.readChar();
            tipoEmpleFromChar = Tipo.fromCodigo(tipoEmple);
            provincia = Provincia.fromCodigo(flujoLectura.readByte());

            emple = new Empleado(nombreApes, sexoFromChar,
                    salario, fechaIngreso, tipoEmpleFromChar, provincia);

        } catch (EOFException eofe) {

            cerrarFlujo();

        }
        return emple;
    }

    public void escribir(Empleado reg) {
        cerrarFlujo();

        DataOutputStream data = null;
        try {
            data = new DataOutputStream(new FileOutputStream(fiche, true));
            
            data.writeUTF(reg.getNomApe());
            data.writeChar(reg.getSexo().getCodigo());
            data.writeFloat(reg.getSalario());
            data.writeShort(reg.getFechaIngreso().getAnio());
            data.writeByte(reg.getFechaIngreso().getMes());
            data.writeByte(reg.getFechaIngreso().getDia());
            data.writeChar(reg.getTipo().getCodigo());
            data.writeByte(reg.getProvincia().getCodigo());

        } catch (IOException ioe) {
            System.out.println("Error de E/S al escribir empleado en fichero: " + ioe.getMessage());
        } finally {
            if (data != null) {
                try {
                    data.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar flujo de escritura: " + e.getMessage());
                }
            }
        }
    }

    public int getNumeroRegistros() {
        int numRegistros = 0;
        boolean finFichero = false;

        cerrarFlujo();

        try (DataInputStream data = new DataInputStream(
                new java.io.FileInputStream(fiche))) {

            while (!finFichero) {
                try {
                    // Intentar leer un registro completo
                    data.readUTF();              // nombre
                    data.readChar();             // sexo
                    data.readFloat();            // salario
                    data.readShort();            // año
                    data.readByte();             // mes
                    data.readByte();             // día
                    data.readChar();             // tipo
                    data.readByte();             // provincia

                    numRegistros++;
                } catch (EOFException eofe) {
                    finFichero = true;
                }
            }
        } catch (IOException ioe) {
            System.out.println("Error al contar registros: " + ioe.getMessage());
        }

        return numRegistros;
    }
}