package FicheDAO;

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

        }
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
            FicheDAO.ff = true;
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
            data.writeByte(reg.getProvincia().ordinal() + 1);//Ordinal mas 1 para que empieze en 1 si es la provincia 0

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
}