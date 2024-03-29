/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;

/**
 *
 * @author Isaac A. Marin
 */
public class Analizador {
    
    private String sentencia;
    private final ArrayList<String> sentenciaPartes;
    private String caracter;
    private String[] auxiliar;
    private int index = 0;
    private boolean correctgb=true;
    private boolean correctg=true;
    private boolean digit=true;
    private final char c = (char)34;
    
    ArrayList<Palabra> sentenciaValidada;
    ArrayList<String> regreso;
    
    public Analizador(String sentencia){
        this.sentencia = sentencia;
        sentenciaPartes = new ArrayList<>();
        sentenciaValidada = new ArrayList<>();
    }

    public void setSentencia(String sentencia) {
        this.sentencia = sentencia;
    }
    
    public ArrayList<Palabra> validateSentence(){
        separed();
        imprimir();
        validacion();
        return sentenciaValidada;
    }
    
    private void separed(){
        String[] aux = sentencia.split("");
        String valor= "";
        char c = (char)34;
        for(int i = 0;i<aux.length;i++){
            if(aux[i].equals(" ")){
                if(valor.equals("")){
                }else{
                    sentenciaPartes.add(valor);
                }
                valor="";
            }else if(aux[i].equals("=")){
                if(valor.equals(" ")){
                }else{
                    if(valor.equals("")){
                    }else{
                        sentenciaPartes.add(valor);
                    }
                    sentenciaPartes.add("=");
                    valor="";
                }
            }else if(aux[i].equals(">")){
                if(valor.equals(" ")){
                }else{
                    if(valor.equals("")){
                    }else{
                        sentenciaPartes.add(valor);
                    }
                    sentenciaPartes.add(">");
                    valor="";
                }
            }else if(aux[i].equals(".")){
                if(valor.equals(" ")){
                }else{
                    if(valor.equals("")){
                    }else{
                        sentenciaPartes.add(valor);
                    }
                    sentenciaPartes.add(".");
                    valor="";
                }
            }else if(aux[i].equals("<")){
                if(valor.equals(" ")){
                }else{
                    if(valor.equals("")){
                    }else{
                        sentenciaPartes.add(valor);
                    }
                    sentenciaPartes.add("<");
                    valor="";
                }
            }else if(aux[i].equals("!")){
                if(valor.equals(" ")){
                }else{
                    if(valor.equals("")){
                    }else{
                        sentenciaPartes.add(valor);
                    }
                    sentenciaPartes.add("!");
                    valor="";
                }
            }else if(aux[i].equals("'")){
                if(valor.equals(" ")){
                }else{
                    if(valor.equals("")){
                    }else{
                        sentenciaPartes.add(valor);
                    }
                    sentenciaPartes.add("'");
                    valor="";
                }
            }else if(aux[i].charAt(0) == c){
                if(valor.equals(" ")){
                }else{
                    if(valor.equals("")){
                    }else{
                        sentenciaPartes.add(valor);
                    }
                    String com = String.valueOf(c);
                    sentenciaPartes.add(com);
                    valor="";
                }
            }else if(aux[i].equals(",")){
                if(valor.equals(" ")){
                }else{
                    if(valor.equals("")){
                    }else{
                        sentenciaPartes.add(valor);
                    }
                    sentenciaPartes.add(",");
                    valor="";
                }
            }else{
                valor+=aux[i];
            }
            if(aux.length-1 == i){
                sentenciaPartes.add(valor);
            }
        }
        
    }
    
    public ArrayList<String> getArrayListMe(){
        return sentenciaPartes;
    }
    
    private void imprimir() {
        System.out.println("Esto hay en la cadena");
        sentenciaPartes.forEach((aux) -> {
            System.out.println(aux);
        });
    }
    
    public void validacion(){
        sentenciaPartes.forEach((aux) -> {
            if(aux.equals(",")){
                sentenciaValidada.add(new Palabra(aux, 0, 0, "orange")); 
            }else if(aux.equals("'")){
                sentenciaValidada.add(new Palabra(aux, 0, 0, "orange"));
            }else if(aux.charAt(0) == c){
                sentenciaValidada.add(new Palabra(aux, 0, 0, "orange"));
            }else{
                if(aux.contains("%") || aux.contains("#")|| aux.contains("&") || aux.contains("$") 
                        || aux.contains("/") || aux.contains("(") || aux.contains(")") || aux.contains("?") 
                        || aux.contains("¿") || aux.contains("¡") || aux.contains("*") || aux.contains("{") 
                        || aux.contains("}")){
                    sentenciaValidada.add(new Palabra(aux, 0, 0, "red"));
                }else{
                    File archivo = new File("archivo.txt");
                    PrintWriter escribir;
                    try {
                        escribir = new PrintWriter(archivo);
                        escribir.print(aux);
                        escribir.close();
                    } catch (Exception e) {
                        System.err.println("Paso Algoo wee");
                    }
                    try {
                        Reader lector = new BufferedReader(new FileReader("archivo.txt"));
                        Lexer lexer = new Lexer(lector);
                        String resultado = "";
                        Tokens tokens;
                        while (true) {
                            try {
                                tokens = lexer.yylex();
                                if (tokens == null) {
                                return;
                                }
                                switch (tokens) {
                                    case Identificador:
                                        sentenciaValidada.add(new Palabra(aux, 0, 0, "white")); 
                                        break;
                                    case Numero:
                                        sentenciaValidada.add(new Palabra(aux, 0, 0, "blue")); 
                                        break;
                                    case Reservadas:
                                        sentenciaValidada.add(new Palabra(aux, 0, 0, "green")); 
                                        break;
                                    case Signos:
                                        sentenciaValidada.add(new Palabra(aux, 0, 0, "orange")); 
                                        //resultado += lexer.lexeme + ": Es un " + tokens + "\n";
                                        break;
                                    case Comparadores:
                                        sentenciaValidada.add(new Palabra(aux, 0, 0, "vio")); 
                                        //resultado += lexer.lexeme + ": Es un " + tokens + "\n";
                                        break;
                                    default:
                                        sentenciaValidada.add(new Palabra(aux, 0, 0, "white")); 
                                        //resultado += "Token: " + tokens + "\n";
                                        break;
                                }
                            } catch (IOException e) {
                                sentenciaValidada.add(new Palabra(aux, 0, 0, "red"));
                            }
                        }
                    } catch (Exception e) {}
                }
            }   
        });
    }
    
    public boolean comparaciones(String datos){
        return datos.equals("<=") | datos.equals("=") | datos.equals("<>") | datos.equals("<") | datos.equals(">") | datos.equals(">=");
    } 
    
    private String whatIs(String valor){
        String tipo;
        switch(valor){
            case "0":
                tipo = "digito";
                break;
            case "1":
                tipo = "digito";
                break;
            case "2":
                tipo = "digito";
                break;
            case "3":
                tipo = "digito";
                break;
            case "4":
                tipo = "digito";
                break;
            case "5":
                tipo = "digito";
                break;
            case "6":
                tipo = "digito";
                break;
            case "7":
                tipo = "digito";
                break;
            case "8":
                tipo = "digito";
                break;
            case "9":
                tipo = "digito";
                break;
            case ".":
                tipo = "punto";
                break;
            case "_":
                tipo = "gb";
                break;
            case "-":
                tipo = "g";
                break;
            default:
                tipo = "letra";
                break;
        }
        return tipo;
    }
}
