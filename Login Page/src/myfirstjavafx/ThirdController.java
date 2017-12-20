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



/**
 * FXML Controller class
 *
 * @author admin
 */
public class ThirdController implements Initializable {
    
    
    private Main application;
    
    public void setApp(Main application){
        this.application = application;}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void second(ActionEvent event) {
        application.gotoSecondPage();
    }
    
}
