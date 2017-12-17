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
public class PaymentController implements Initializable {
    
    
    
    @FXML
    private JFXButton cash;

    @FXML
    private JFXButton back;
    
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
    public void cashPay(ActionEvent event) {
        
        application.gotoCash();
    }
        
    
    @FXML
    public void back(ActionEvent event) {
        
        application.gotoBilling();
        
    }
    

    
}
