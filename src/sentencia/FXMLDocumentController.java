/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentencia;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Jose Pablo Sandoval
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Label label;
    @FXML private TextArea area;
    @FXML private TextFlow code;
    @FXML private Label estado;
    Analizador lexico; 
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
    }
    
    @FXML
    private void limpiar(ActionEvent event) {
        area.setText("");
        
    }
    
    @FXML
    private void descomponer(ActionEvent event) {
        code.getChildren().clear();
        boolean pass= true;
        lexico = new Analizador(area.getText().toLowerCase());
        ArrayList<Palabra> sentencia = null;
        try {
            sentencia = lexico.validateSentence();
            code.getChildren().addAll();
            for(Palabra aux : sentencia) {
                System.out.println("---"+aux.partePalabra);
                Text text = new Text(aux.partePalabra+" ");
                if(aux.color.equals("green")){
                    text.setFill(Color.GREENYELLOW);
                } else if(aux.color.equals("red")){
                    text.setFill(Color.RED);
                    pass=false;
                } else if(aux.color.equals("white")){
                    text.setFill(Color.WHITESMOKE);
                } else if(aux.color.equals("orange")){
                    text.setFill(Color.ORANGE);
                }else if(aux.color.equals("vio")){
                    text.setFill(Color.DARKORCHID);
                }else if(aux.color.equals("blue")){
                    text.setFill(Color.AQUA);
                }
                text.setFont(Font.font("consolas", 18)); 
                code.getChildren().add(text);
            }
        } catch (Exception e) {
            System.err.println("error");
        }
        if(pass){
           AutomataPila ap;
           boolean t=true;
            try {
                ap = new AutomataPila(lexico.getArrayListMe());
                t = ap.validacion();
            } catch (Exception e) {
                estado.setText("exception :c");
            }
            
            if(t){
                estado.setText("  Correcta");
            }else{
                estado.setText("   Incorrecta");
            } 
        }else{
            estado.setText("   Incorrecta");
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
