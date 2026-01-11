package FicheDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author andyj
 * @Revision Mejias Gonzalez Francisco
 * @Correcciones Mejias Gonzalez Francisco -->Quitar los numeros de las provincias
 * Uso ordinal()+1 ( +1 porque empieza en 0) para que devuelva el numero de la provincia 
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
    
    public byte getCodigo() {
        return (byte) (ordinal() + 1);
    }
    
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