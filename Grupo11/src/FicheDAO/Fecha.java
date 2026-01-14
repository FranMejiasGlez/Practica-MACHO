package FicheDAO;


import java.util.Calendar;
import java.util.GregorianCalendar;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author andyj
 * @Revision Mejias Gonzalez Francisco
 * @Correcciones Mejias Gonzalez Francisco
 */
public class Fecha implements Comparable<Fecha> {

    short yyyy;
    byte mm;
    byte dd;

    public Fecha() {//Crea la fecha de hoy
        GregorianCalendar fecha = new GregorianCalendar();
        int yyyy = fecha.get(Calendar.YEAR);
        int mm = fecha.get(Calendar.MONTH) + 1;
        int dd = fecha.get(Calendar.DAY_OF_MONTH);
    }

    public Fecha(short yyyy, byte mm, byte dd) {
        this.yyyy = yyyy;
        this.mm = mm;
        this.dd = dd;
    }

    public int aniosTranscurridos() {
        GregorianCalendar hoy = new GregorianCalendar();

        int yyyyHoy = hoy.get(Calendar.YEAR);
        int mmHoy = hoy.get(Calendar.MONTH) + 1;
        int ddHoy = hoy.get(Calendar.DAY_OF_MONTH);

        int aniosTranscurridos = yyyyHoy - this.yyyy;

        if (mmHoy < this.mm || (mmHoy == this.mm && ddHoy < this.dd)) {
            aniosTranscurridos--;
        }

        return aniosTranscurridos;
    }
public int aniosTranscurridos(GregorianCalendar fecha1, GregorianCalendar fecha2) {
    // Asegurar que fecha1 es la más reciente
    GregorianCalendar fechaMayor, fechaMenor;
    if (fecha1.after(fecha2)) {
        fechaMayor = fecha1;
        fechaMenor = fecha2;
    } else {
        fechaMayor = fecha2;
        fechaMenor = fecha1;
    }
    
    int aniosTranscurridos = fechaMayor.get(Calendar. YEAR) - fechaMenor.get(Calendar.YEAR);
    
    // Ajustar si aún no se ha cumplido el aniversario en el año actual
    int mesMayor = fechaMayor.get(Calendar.MONTH);
    int mesMenor = fechaMenor. get(Calendar.MONTH);
    int diaMayor = fechaMayor.get(Calendar.DAY_OF_MONTH);
    int diaMenor = fechaMenor.get(Calendar.DAY_OF_MONTH);
    
    if (mesMayor < mesMenor || (mesMayor == mesMenor && diaMayor < diaMenor)) {
        aniosTranscurridos--;
    }
    
    return aniosTranscurridos;
}

    @Override
    public String toString() {
        return String.format("%02d-%02d-%04d", dd, mm, yyyy);
    }


    public static boolean esValida(short yyyy, byte mm, byte dd) {
        int diasDeMes;

        if (mm > 0 && mm <= 12 && dd > 0 && dd <= 31) {
            switch (mm) {
                case 4:
                case 6:
                case 9:
                case 11:
                    diasDeMes = 30;
                    break;
                case 2:
                    if (Fecha.esBisiesto(yyyy, mm, dd)) {
                        diasDeMes = 29;
                    } else {
                        diasDeMes = 28;
                    }
                    break;
                default:
                    diasDeMes = 31;
            }
            return dd <= diasDeMes;
        }
        return false;
    }

    public boolean equals(Fecha otra) {
        return this.yyyy == otra.yyyy && this.mm == otra.mm && this.dd == otra.dd;
    }

    public boolean esBisiesto() {
        return (yyyy % 4 == 0 && yyyy % 100 != 0) || (yyyy % 400 == 0);
    }

    public static boolean esBisiesto(short yyyy, byte mm, byte dd) {
        return (yyyy % 4 == 0 && yyyy % 100 != 0) || (yyyy % 400 == 0);
    }

    public short getAnio() {
        return yyyy;
    }

    public byte getMes() {
        return mm;
    }

    public byte getDia() {
        return dd;
    }

    @Override
    public int compareTo(Fecha otra) {
        GregorianCalendar thisFecha = new GregorianCalendar(this.yyyy, this.mm - 1, this.dd);
        GregorianCalendar otraFecha = new GregorianCalendar(otra.yyyy, otra.mm - 1, otra.dd);

        if (thisFecha.before(otraFecha)) {
            return -1;
        }
        if (this.equals(otra)) {
            return 0;
        }
        return 1;
    }
}
