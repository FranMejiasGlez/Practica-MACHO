/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author andyj
 */
public enum Provincia {
    ALMERIA(1),
    CADIZ(2),
    CORDOBA(3),
    GRANADA(4),
    HUELVA(5),
    JAEN(6),
    MALAGA(7),
    SEVILLA(8);

    private final int codigo;

    Provincia(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static Provincia fromCodigo(int codigo) {
        for (Provincia p : values()) {
            if (p.codigo == codigo) return p;
        }
        return null; 
    }

    @Override
    public String toString() {
        
        return name();
    }
}