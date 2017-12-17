/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.payment;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.validation.*;
import com.jfoenix.controls.JFXTextField;
import demo.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class CashPaymentController implements Initializable {
    
    @FXML
    private Label totaldue;

    @FXML
    private Label balanceR;

    @FXML
    private JFXTextField amntR;

    @FXML
    private JFXButton cancel;

    @FXML
    private JFXButton pay;

    @FXML
    private JFXButton back;

   

    
    
    private Main application;
    
 //   Image imimgage =new Image("a.png") ;
 // back.setGraphics(new ImageView(image));
    
    
    
    public void setApp(Main application){
        this.application = application;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        
        
        RequiredFieldValidator validator= new RequiredFieldValidator();
        NumberValidator numValidator = new NumberValidator();
        
        amntR.getValidators().add(numValidator);
        numValidator.setMessage("Only Numbers");
        
        amntR.getValidators().add(validator);
        validator.setMessage("NO INPUT GIVEN");
        amntR.focusedProperty().addListener(new ChangeListener<Boolean>() {
        @Override 
        public void changed (ObservableValue<? extends Boolean> observable,Boolean oldValue, Boolean newValue){
            if(!newValue)
            {
            amntR.validate();
            }
        }
        });
        
        
//  back.setGraphics(new ImageView(img));

    
        // TODO
       totaldue.setText("2000");
        
        */
        
    }    
    
    /*@FXML
    public void calculate(ActionEvent event) {
       
          
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            
            String s= amntR.getText();
            int t = 500;
            Integer.parseInt(s);
            balanceR.setText("0x1f4");
    }*/
    
   
    @FXML
    public void back(ActionEvent event) {
       
          application.gotoPayment();
    }
    
    
          
        
            
            
            
       
    
 
   
 
    


}