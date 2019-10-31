/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentencia;

/**
 *
 * @author Isaac A. Marin
 */
public class Palabra {
    String partePalabra;
    int posInicial;
    int posFinal;
    String color;

    public Palabra(String partePalabra, int posInicial, int posFinal, String color) {
        this.partePalabra = partePalabra;
        this.posInicial = posInicial;
        this.posFinal = posFinal;
        this.color = color;
    }
    
}
