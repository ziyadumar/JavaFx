/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstjavafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author ZIYAD
 */
public class FirstPageController implements Initializable {
    
    @FXML
    private Label label;
    
    private Main application;
    
    public void setApp(Main application){
        this.application = application;}
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        application.gotoSecondPage();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
