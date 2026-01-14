package FicheDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author andyj
 * @Revision Mejias Gonzalez Francisco
 * @Correcciones Mejias Gonzalez Francisco -->Quitar getCodigo
 * 
 */
public enum Provincia {
    ALMERIA,
    CADIZ,
    CORDOBA,
    GRANADA,
    HUELVA,
    JAEN,
    MALAGA,
    SEVILLA;

    public static Provincia fromCodigo(byte codigo) {
        for (Provincia p : values()) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return name();
    }
}
