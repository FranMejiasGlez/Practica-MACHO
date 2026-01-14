
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import FicheDAO.*;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @author Mejias Gonzalez Francisco
 * @Correcciones Mejias Gonzalez Francisco --> No existia este archivo,
 * utilizabamos el script directamente.
 *
 */
public class EscribeEmpleados {

    public static Empleado pedirDatos() {
        BufferedReader teclado;
        String entrada, nombre, dni;
        boolean valido;
        float salario = 0;
        char sexo, tipoEmple;
        Fecha fecha;
        String[] campos;
        short anio;
        byte provincia = 0, mes, dia;
        Empleado emple = null;

        try {

            teclado = new BufferedReader(new InputStreamReader(System.in));

            do {
                System.out.print("Introduce un nombre y apellidos: ");
                entrada = teclado.readLine();
            } while (!EscribeEmpleados.esValido(entrada));
            nombre = entrada;
            do {
                System.out.print("Sexo(M/H): ");
                entrada = teclado.readLine();
            } while (!entrada.matches("[MH]"));
            sexo = entrada.charAt(0);
            do {
                valido = true;
                System.out.print("Introduce un salario >0: ");
                entrada = teclado.readLine();
                try {
                    salario = Float.parseFloat(entrada);
                } catch (NumberFormatException nfe) {
                    System.out.println("Tiene que ser un numérico real");
                    valido = false;
                }
            } while (!valido || salario < 0);
            do {
                do {
                    System.out.print("Introduce una fecha de ingreso valida año/mes/dia(xxxx/xx/xx): ");
                    entrada = teclado.readLine();
                } while (!entrada.matches("\\d{4}\\/\\d{2}\\/\\d{2}"));
                campos = entrada.split("[/]");
                anio = Short.parseShort(campos[0]);
                mes = Byte.parseByte(campos[1]);
                dia = Byte.parseByte(campos[2]);
            } while (!Fecha.esValida(anio, mes, dia));



            do {
                System.out.print("Introduce una letra del tipo del empleado: ");
                for (Tipo tipo : Tipo.values()) {
                    System.out.println(tipo.getCodigo() + "-" + Tipo.fromCodigo(tipo.getCodigo()));
                }
                entrada = teclado.readLine();
            } while (!entrada.matches("[CFD]"));
            tipoEmple = entrada.charAt(0);
            do {
                valido = true;
                System.out.print("Introduce el número de una provincia: ");
                for (Provincia prov : Provincia.values()) {
                    System.out.println(prov.ordinal() + 1 + "-" + Provincia.fromCodigo(prov.ordinal() + 1));
                }
                try {
                    entrada = teclado.readLine();
                    provincia = Byte.parseByte(entrada);
                } catch (NumberFormatException nfe) {
                    System.out.println("Debe ser un número entre 1 y 8");
                    valido = false;
                }
            } while (!valido || !entrada.matches("[1-8]"));
            fecha = new Fecha(anio, mes, dia);
            emple = new Empleado(nombre, Sexo.fromCodigo(sexo), salario,
                    fecha, Tipo.fromCodigo(tipoEmple), Provincia.fromCodigo(provincia));
        } catch (IOException ioe) {
            System.out.println("Error de E/S pidiendo datos");
        }

        return emple;
    }

    public static boolean esValido(String cadena) {

        if (cadena == null) {
            return false;
        }
        if (cadena.trim().isEmpty()) {
            return false;
        }

        for (char c : cadena.toCharArray()) {

            if (Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        File fichero = new File("fiche.dat");
        FileOutputStream fos;
        DataOutputStream dos;
        FicheDAO fdao;
        BufferedReader teclado;
        String entrada;
        Empleado emple;
        boolean seguir = true;

        try {
            teclado = new BufferedReader(new InputStreamReader(System.in));
            fos = new FileOutputStream(fichero);
            dos = new DataOutputStream(fos);
            fdao = new FicheDAO();

            System.out.println("=== ESCRITURA DE EMPLEADOS ===\n");

            while (seguir) {
                emple = pedirDatos();

                if (emple != null) {
                    fdao.escribir(dos, emple);
                    System.out.println("Empleado guardado correctamente.\n");
                }

                System.out.print("¿Deseas añadir otro empleado? (S/N): ");
                entrada = teclado.readLine();
                if (!entrada.matches("[Ss]")) {
                    seguir = false;
                }
            }

            dos.close();
            fos.close();
            System.out.println("\nArchivo guardado en: " + fichero.getAbsolutePath());

        } catch (IOException ioe) {
            System.out.println("Error de E/S: " + ioe.getMessage());
        }
    }
}
