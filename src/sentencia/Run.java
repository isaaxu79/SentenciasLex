/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentencia;

import java.io.File;

/**
 *
 * @author Isaac A. Marin
 */
public class Run {
    public static void main(String[] args) {
        //C:\Users\Isaac A. Marin\Documents\Lenguajes y Automatas\AnalizadorLexico
        String ruta = "C:/Users/Isaac A. Marin/Documents/Lenguajes y Automatas/sentencias/src/sentencia/Lexer.flex";
        generarLexer(ruta);
    }
    public static void generarLexer(String ruta){
        File archivo = new File(ruta);
        JFlex.Main.generate(archivo);
    }
}
