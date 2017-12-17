/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class AdminLoginController implements Initializable {
    
    
    
    
    @FXML
    private JFXButton back;
    
    
    private Main application;
    
    
    public void setApp(Main application){
        this.application = application;
    }


    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    public void backFromAdmin(ActionEvent event) {
        
        application.gotoLogin();
        
    }
}
