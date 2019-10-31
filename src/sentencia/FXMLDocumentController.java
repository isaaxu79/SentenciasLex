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
        lexico = new Analizador(area.getText());
        ArrayList<Palabra> sentencia = lexico.validateSentence();
        code.getChildren().addAll();
        for(Palabra aux : sentencia) {
            System.out.println("---"+aux.partePalabra);
            Text text = new Text(aux.partePalabra+" ");
            if(aux.color.equals("green")){
                text.setFill(Color.GREENYELLOW);
            } else if(aux.color.equals("red")){
                text.setFill(Color.RED);
            } else if(aux.color.equals("white")){
                text.setFill(Color.WHITESMOKE);
            } else if(aux.color.equals("vio")){
                text.setFill(Color.ORANGE);
            }
            text.setFont(Font.font("consolas", 18)); 
            code.getChildren().add(text);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
