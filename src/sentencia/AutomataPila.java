/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentencia;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Isaac A. Marin
 */
public class AutomataPila {
    private Stack<String> pila = new Stack<>();
    private ArrayList<String> sentencia;
    private boolean correcto = true;
    private Stack<String> pilaaux1;
    
    public AutomataPila(ArrayList<String> abc){
        pila.push("$");
        pila.push("sentencia");
        sentencia = abc;
    }
    
    public boolean validacion() {
        if(pila.peek().equals("sentencia")){
            pila.pop();
            pila.push("Z");
            pila.push("delete");
        }
        valida();
        if(!pila.isEmpty()){
            System.out.println("salida "+pila.peek());
        }else{
            System.out.println("vacio");
        }
        return correcto;
    }
    
    private void valida(){
        if(sentencia.size()>0){
            if(pila.pop().equals(getAndDelete())){
                validaTablas();
            }else{
                correcto=false;
            }
        }else {
            correcto = false;
        }
    }
    
    private String getAndDelete(){
        String dato = sentencia.get(0);
        sentencia.remove(0);
        return dato;
    }
    
    private String seeP(){
        return sentencia.get(0);
    }

    private void validaTablas() {
        if(sentencia.size() > 0){
            if(pila.pop().equals("Z")){
                pilaaux1=pila;
                sinTabla(pila);
                
            }
        }else {
            correcto = false;
        }
    }

    private void sinTabla(Stack<String> pila1) {
        Stack<String> pilaaux2 = pila1;
        if (sentencia.size() > 0) {
            pilaaux2.push("tablas");
            pilaaux2.push("from");
            if (pilaaux2.pop().equals(seeP())) {
                getAndDelete();
                System.out.println("aqui esta la banda");
                tablas(pilaaux2);
            }else {
                System.out.println("aqui no");
                pilaaux2.pop();
                multiples(pilaaux2);
            }
        }else{
            correcto = false;
        }
    }
    
    private void multiples(Stack<String> pila1){
        Stack<String> pilaAux=pila1;
        if(sentencia.size()> 0){
            pilaAux.push("muchastablas");
            
            muchas(pilaAux);
            if(sentencia.size()>0){
                System.out.println("usame:  "+seeP());
                pilaAux.push("from");
                if(seeP().equals(pilaAux.pop())){
                    getAndDelete();
                    masTablas(pilaAux);
                }else{
                    correcto=false;
                }
            }
        }else{
            correcto=false;
        }
    }

    private void masTablas(Stack<String> pila1) {
        Stack<String> pilaAux=pila1;
        pilaAux.push("masTablas");
        if(sentencia.size() > 0) {
            String tabala = getAndDelete();
            String[] auxiliar = tabala.split("");
            pilaAux.pop();
            pilaAux.push("resto");
            pilaAux.push("condicion");
            pilaAux.push("where");
            pilaAux.push("name");
            for (int i = 0; i < auxiliar.length; i++) {
                pilaAux.pop();
                pilaAux.push("contenido");
                String tipo = whatIs(auxiliar[i]);
                if ((tipo.equals("letra")) || (tipo.equals("digito")) || (tipo.equals("gb"))) {
                    
                }else{
                    correcto = false;
                    i = auxiliar.length;
                }
            }
            pilaAux.pop();
            if(sentencia.size() > 0){
                if(seeP().equals(".")){
                    pilaAux.push(".");
                    getAndDelete();
                    pilaAux.pop();
                    if(sentencia.size() > 0){
                        if(seeP().equals("where")){
                            correcto = false;
                        } else {
                            String as = getAndDelete();
                            String[] pala = as.split("");
                            for (String vs : pala) {
                                String tipo = whatIs(vs);
                                if ((tipo.equals("letra")) || (tipo.equals("digito")) || (tipo.equals("gb"))) {
                                } else {
                                    correcto = false;
                                    break;
                                }
                            }
                        }
                    } else {
                        correcto=false;
                    }
                }else if(seeP().equals(",")){
                    getAndDelete();
                    System.err.println("tracos1"+pilaAux.peek());
                    
                    pilaAux.push("muchastablas");
                    moreTables(pilaAux);
                    
                }
            } 
            if(sentencia.size()>0){
                if(seeP().equals(",")){
                    getAndDelete();
                    System.err.println("tracos2"+pilaAux.peek());
                    
                    pilaAux.push("muchastablas");
                    moreTables(pilaAux);
                    
                }
            }
            
            
            if((sentencia.size()>0) && correcto){
                System.out.println(pilaAux.peek()+"-----"+seeP());
                if(pilaAux.pop().equals(seeP())){
                    getAndDelete();
                    condicion(pilaAux);
                    if(correcto){
                        resto(pilaAux);
                    }
                }else{
                    resto(pilaAux);
                }
            }
        }else {
            correcto = false;
        }
        
    }

    private void tablas(Stack<String> pilaaux2) {
        Stack<String> pila2=pilaaux2;
        if(sentencia.size() > 0){
            if(pila2.pop().equals("tablas")){
                Stack<String> pilaaux=pila2;
                oneTable(pila2);     
            }
        }else{
            correcto = false;
        }
    }

    private void oneTable(Stack<String> pila2) {
        System.out.println("llegue aqui brp");
        Stack<String> pilaAux=pila2;
        pilaAux.push("sinTablas");
        if(sentencia.size() > 0) {
            String tabala = getAndDelete();
            System.out.println(tabala);
            String[] auxiliar = tabala.split("");
            pilaAux.pop();
            pilaAux.push("resto");
            pilaAux.push("condicion");
            pilaAux.push("where");
            pilaAux.push("name");
            System.out.println("entonce????");
            for (int i = 0; i < auxiliar.length; i++) {
                pilaAux.pop();
                pilaAux.push("contenido");
                String tipo = whatIs(auxiliar[i]);
                if ((tipo.equals("letra")) || (tipo.equals("digito")) || (tipo.equals("gb"))) {
                    
                }else{
                    correcto = false;
                    i = auxiliar.length;
                }
            }
            System.out.println("saliste");
            pilaAux.pop();
            if(sentencia.size() > 0){
                if(seeP().equals(".")){
                    pilaAux.push(".");
                    getAndDelete();
                    pilaAux.pop();
                    if(sentencia.size() > 0){
                        if(seeP().equals("where")){
                            correcto = false;
                        } else {
                            String as = getAndDelete();
                            String[] pala = as.split("");
                            for (String vs : pala) {
                                String tipo = whatIs(vs);
                                if ((tipo.equals("letra")) || (tipo.equals("digito")) || (tipo.equals("gb"))) {
                                } else {
                                    correcto = false;
                                    break;
                                }
                            }
                        }
                    } else {
                        correcto=false;
                    }
                }else if(seeP().equals(",")){
                    getAndDelete();
                    System.err.println("tracos1"+pilaAux.peek());
                    
                    pilaAux.push("muchastablas");
                    moreTables(pilaAux);
                    pilaAux.push("using");
                    usingT(pilaAux);
                }
            } 
            if(sentencia.size()>0){
                if(seeP().equals(",")){
                    getAndDelete();
                    System.err.println("tracos2"+pilaAux.peek());
                    
                    pilaAux.push("muchastablas");
                    moreTables(pilaAux);
                    pilaAux.push("using");
                    usingT(pilaAux);
                }
            }
            
            
            if((sentencia.size()>0) && correcto){
                System.out.println(pilaAux.peek()+"-----"+seeP());
                if(pilaAux.pop().equals(seeP())){
                    getAndDelete();
                    condicion(pilaAux);
                    if(correcto){
                        resto(pilaAux);
                    }
                }else{
                    resto(pilaAux);
                }
            }
        }else {
            correcto = false;
        }
    }

    private void moreTables(Stack<String> pila2) {
        System.out.println("ho,a");
        Stack<String> pilaAuxi = pila2;
        pilaAuxi.pop();
        if(sentencia.size() > 0){
            pilaAuxi.push("name");
            pilaAuxi.pop();
            palabras(pilaAuxi);
            if(sentencia.size() > 0){
                System.out.println("ni siqiera llegas");
                while (seeP().equals(",")) {
                    System.out.println(seeP());
                    getAndDelete();
                    System.out.println(seeP());
                    pilaAuxi.push("name");
                    pilaAuxi.pop();
                    if (sentencia.size() > 0 ) {
                        if(seeP().equals("\n")){
                            correcto=false;
                        }else{
                            palabras(pilaAuxi);
                            if (!(sentencia.size() > 0)) {
                                break;
                            }
                        }
                        
                    }else{
                        correcto=false;
                    }
                    
                    System.out.println(pilaAuxi.peek());
                }
            }
            System.out.println("****"+pilaAuxi.peek());
        }
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
            case ">":
                tipo = "signo";
                break;
            case "<":
                tipo = "signo";
                break;
            case "=":
                tipo = "signo";
                break;
            case "*":
                tipo="a";
                break;
            default:
                tipo = "letra";
                break;
        }
        return tipo;
    }

    private void condicion(Stack<String> pilaAux)   {
        Stack<String> pilaAuxi=pilaAux;
        if(sentencia.size() > 0){
            pilaAuxi.pop();
            pilaAuxi.push("restoCondicion");
            pilaAuxi.push("valor");
            pilaAuxi.push("signo");
            pilaAuxi.push("variable");
            variables(pilaAuxi);
            if (correcto && sentencia.size() > 0) {
                System.out.println("se valida  = > < "+ pilaAuxi.peek());
                pilaAuxi.pop();
                pilaAuxi.push("comp");
                String data = getAndDelete();
                System.out.println(data);
                if (data.equals("<") || data.equals(">") || data.equals("=")) {
                    pilaAuxi.pop();
                    System.out.println("pasex");
                    System.out.println(seeP()+"----sei");
                    if(sentencia.size() > 0){
                        if(seeP().equals("<") || seeP().equals(">")){
                            System.out.println("aqut tam");
                            getAndDelete();
                        }
                    }else {
                        correcto = false;
                    }
                } else {
                    correcto = false;
                }
            }
            if(correcto && sentencia.size() > 0){
                pilaAuxi.pop();
                valor(pilaAuxi);
                if(correcto && sentencia.size() > 0){
                     pilaAuxi.push("comparador");
                     System.out.println("comparador "+ seeP() );
                     String a = seeP();
                     if(a.equals("and") || a.equals("or")){
                         getAndDelete();
                         condicion(pilaAuxi);
                     }else{
                         
                     }
                     pilaAuxi.pop();
                }
            }else{
                correcto =false;
            }
            
        }else{
            correcto=false;
        }
    }

    private void resto(Stack<String> pilaAux) {
        System.out.println("llego a resto");
        Stack<String> pilaAuxi = pilaAux;
        pilaAuxi.pop();
        if(sentencia.size() > 0){
            System.out.println("valido" + seeP());
            String laba = getAndDelete();
            System.out.println("ninijnini-"+laba+"-");
            if(laba.equals("order")){
                if(sentencia.size() > 0){
                    if(getAndDelete().equals("by")){
                        pilaAuxi.push("name");
                        System.out.println("entro a la palabra");
                        palabras(pilaAuxi);
                    }else{
                        correcto= false;
                    }
                }else{
                    correcto=false;
                }
            }else if(laba.equals("limit")){
                
            }else if(laba.equals("")){
            }else{
                if(laba.equals("\n") || laba.equals(" ")){
                    correcto=true;
                }else{
                    correcto=false;
                }
            }
        }else{
            System.out.println("hola");
        }
    }

    private void variables(Stack<String> pilaAuxi) {
        Stack<String> pilaAu=pilaAuxi;
        if(sentencia.size() > 0){
            pilaAu.pop();
            pilaAu.push("name");
            pilaAu.pop();
            pilaAu.push("contenido");
            String auxil= getAndDelete();
            String[] auxila = auxil.split("");
            for (String auxila1 : auxila) {
                pilaAu.pop();
                pilaAu.push("letra");
                String tipo = whatIs(auxila1);
                if(tipo.equals("digito") || tipo.equals("letra") || tipo.equals("gb")){
                    
                }else{
                    correcto=false;
                    break;
                }
            }
            pilaAu.pop();
            if (sentencia.size() > 0) {
                pilaAu.push(".");
                if (pilaAu.pop().equals(seeP())) {
                    getAndDelete();
                    pilaAu.push("name");
                    pilaAu.pop();
                    pilaAu.push("contenido");
                    String aus = getAndDelete();
                    String[] auxsq = aus.split("");
                    for (String aa : auxsq) {
                        pilaAu.pop();
                        pilaAu.push("letra");
                        String tipo = whatIs(aa);
                        if (tipo.equals("digito") || tipo.equals("letra") || tipo.equals("gb")) {
                        } else {
                            correcto = false;
                            break;
                        }
                    }
                    pilaAu.pop();
                }
            }else{
                correcto=false;
            }
        } else {
            correcto = false;
        }
        
    }

    private void valor(Stack<String> pilaAuxi) {
        Stack<String> pilaAux=pilaAuxi;
        if(sentencia.size() > 0){
            if(seeP().equals("\n")){
                correcto=false;
            }else{
                pilaAux.pop();
                comillas(pilaAux);
            }
        }else{
            correcto=false;
        }
        
    }

    private void comillas(Stack<String> pilaAux) {
        Stack<String> pilaAuxi=pilaAux;
        if(sentencia.size() > 0 ) {
            pilaAuxi.push("'");
            pilaAuxi.push("name");
            pilaAuxi.push("'");
            if(pilaAuxi.pop().equals(seeP())){
                getAndDelete();
                if(sentencia.size() > 0){
                    String valor = getAndDelete();
                    String[] val = valor.split("");
                    pilaAuxi.pop();
                    pilaAuxi.push("caracteres");
                    for (int i = 0; i < val.length; i++) {
                        pilaAuxi.pop();
                        pilaAuxi.push("caracter");
                        System.out.println("..."+val[i]);
                        String type = whatIs(val[i]);
                        if(val[i].equals("\n")){
                            correcto=false;
                            i = val.length;
                        }else{
                            if(type.equals("letra") || type.equals("digito") || type.equals("gb")){
                                System.out.println(pilaAuxi.peek());
                            }else{
                                correcto = false;
                                i= val.length;
                            }
                        }
                    }
                    pilaAuxi.pop();
                    if(sentencia.size() > 0){
                        System.out.println("simon es '");
                        if(pilaAuxi.pop().equals(getAndDelete())){
                        }
                    }else{
                        correcto=false;
                    }
                }else{
                    correcto = false;
                }
            }else{
                pilaAuxi.pop();
                pilaAuxi.pop();
                pilaAuxi.push("\"");
                pilaAuxi.push("name");
                pilaAuxi.push("\"");
                dobles(pilaAuxi);
                
            }
        }else{
            correcto = false;
        }
    }

    private void dobles(Stack<String> pilav2) {
        Stack<String> pilaAuxi = pilav2;
        if(sentencia.size() > 0){
            if(pilaAuxi.pop().equals(seeP())){
                getAndDelete();
                if(sentencia.size() > 0){
                    String valor = getAndDelete();
                    String[] val = valor.split("");
                    pilaAuxi.pop();
                    pilaAuxi.push("caracteres");
                    for (int i = 0; i < val.length; i++) {
                        pilaAuxi.pop();
                        pilaAuxi.push("caracter");
                        System.out.println("..."+val[i]);
                        String type = whatIs(val[i]);
                        if(val[i].equals("\n")){
                            correcto=false;
                            i = val.length;
                        }else{
                            if(type.equals("letra") || type.equals("digito") || type.equals("gb")){
                                System.out.println(pilaAuxi.peek());
                            }else{
                                correcto = false;
                                i= val.length;
                            }
                        }
                    }
                    pilaAuxi.pop();
                    if(sentencia.size() > 0){
                        if(pilaAuxi.pop().equals(getAndDelete())){
                        }else{
                            correcto =false;
                        }
                    }else{
                        correcto = false;
                    }
                }else{
                    correcto=false;
                }
            }else{
                correcto= true;
                pilaAuxi.pop();
                pilaAuxi.pop();
                pilaAuxi.push("name");
                pilaAuxi.push(".");
                pilaAuxi.push("name");
                palabra(pilaAuxi);
                //System.out.println("--mnni----"+seeP());
            }
        }else{
            correcto=false;
        }
    }

    private void palabra(Stack<String> pilaAuxi) {
        Stack<String> auxPila = pilaAuxi;
        if(sentencia.size() > 0){
            auxPila.pop();
            String  valor = getAndDelete();
            String[] vas = valor.split("");
            for(int i = 0; i<vas.length; i++){
                String typo = whatIs(vas[i]);
                if(typo.equals("digito") || typo.equals("letra") || typo.equals("gb")){   
                }else{
                    i = vas.length;
                    correcto=false;
                }
            }
            //System.out.println("prueba" +seeP());
            if(correcto && sentencia.size() > 0){
                System.out.println("entro aqui ya que imprimi?");
                if(auxPila.peek().equals(".") && auxPila.peek().equals(seeP())){
                    getAndDelete();
                    auxPila.pop();
                    auxPila.pop();
                    if(sentencia.size() > 0){
                        String  valo = getAndDelete();
                        String[] vaa = valo.split("");
                        for(int i = 0; i<vaa.length; i++){
                            String typo = whatIs(vaa[i]);
                            if(typo.equals("digito") || typo.equals("letra") || typo.equals("gb")){   
                            }else{
                                i = vaa.length;
                                correcto=false;
                            }
                        }
                    }else{
                        correcto=false;
                    }
                }
            }else{
                auxPila.pop();
                auxPila.pop();
            }
        }else{
            correcto= false;
        }
    }

    private void palabras(Stack<String> pilaAuxi) {
        Stack<String> auxPila = pilaAuxi;
        if(sentencia.size() > 0){
            String  valor = getAndDelete();
            System.out.println(valor+" uspa");
            String[] vas = valor.split("");
            for(int i = 0; i<vas.length; i++){
                String typo = whatIs(vas[i]);
                if(typo.equals("digito") || typo.equals("letra") || typo.equals("gb")){   
                }else{
                    i = vas.length;
                    correcto=false;
                }
            }
            System.out.println("paso por qu");
            //System.out.println("prueba" +seeP());
            if(correcto && sentencia.size() > 0){
                System.out.println("entro aqui ya que imprimi?");
                if(seeP().equals(".")){
                    getAndDelete();
                    
                    if(sentencia.size() > 0){
                        String  valo = getAndDelete();
                        String[] vaa = valo.split("");
                        for(int i = 0; i<vaa.length; i++){
                            String typo = whatIs(vaa[i]);
                            if(typo.equals("digito") || typo.equals("letra") || typo.equals("gb")){   
                            }else{
                                i = vaa.length;
                                correcto=false;
                            }
                        }
                    }else{
                        correcto=false;
                    }
                }
            }
        }else{
            correcto= false;
        }
    }

    private void usingT(Stack<String> pilaAux) {
        Stack<String> auxPila = pilaAux;
        if(sentencia.size()>0){
            if(getAndDelete().equals(auxPila.pop())){
                System.out.println("entro a valida tablas");
                auxPila.push("muchasTablas");
                moreTables(auxPila);
            }else{
                correcto=false;
            }
        }else{
            correcto=false;
        }
    } 

    public void muchas(Stack<String> pila2){
        System.out.println("ho,a");
        Stack<String> pilaAuxi = pila2;
        pilaAuxi.pop();
        if(sentencia.size() > 0){
            pilaAuxi.push("name");
            pilaAuxi.pop();
            if(seeP().equals("from")){
                
            }else{
                palabras(pilaAuxi);
                if (sentencia.size() > 0) {
                    System.out.println("ni siqiera llegas");
                    System.out.println(seeP()+ "--- its the final inittial");
                    while (seeP().equals(",")) {
                        System.out.println("me rindo");
                        System.out.println(seeP());
                        getAndDelete();
                        System.out.println(seeP());
                        pilaAuxi.push("name");
                        pilaAuxi.pop();
                        if (sentencia.size() > 0) {
                            System.out.println(seeP()+ "--- its the final");
                            if (seeP().equals("\n")) {
                                correcto = false;
                            } else {
                                System.out.println("llegaste aqi bro");
                                if(seeP().equals("from")){
                                    break;
                                }else{
                                    palabras(pilaAuxi);
                                }
                                
                                if (!(sentencia.size() > 0)) {
                                    break;
                                }
                            }

                        } else {
                            correcto = false;
                        }

                        System.out.println(pilaAuxi.peek());
                    }
                }
            }

            System.out.println("****" + pilaAuxi.peek());
        }
    }
}
