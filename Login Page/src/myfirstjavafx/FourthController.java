/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstjavafx;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class FourthController implements Initializable {
    
    
    private Main application;
    @FXML
    private Label status;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private JFXButton loginButton;
    @FXML
    private ImageView img;
    
    public void setApp(Main application){
        this.application = application;}
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        animation();
        
        
        
        
        // TODO
    }    

    @FXML
    private void goLogin(ActionEvent event) {
        application.gotoGeneralPage();
        
    }

    private void animation() {
        
        
        TranslateTransition tr= new TranslateTransition(Duration.millis(1000), username);
        tr.setFromX(-100);
        tr.setToX(0);        
        tr.play();
        
        
        TranslateTransition tr1= new TranslateTransition(Duration.millis(1000), password);
        tr1.setFromX(100);
        tr1.setToX(0);        
        tr1.play();
        
        
        TranslateTransition tr2= new TranslateTransition(Duration.millis(1000), loginButton);
        tr2.setFromY(100);
        tr2.setToY(0);        
        tr2.play();
        
        
        FadeTransition fd2= new FadeTransition(Duration.millis(1500), loginButton);
        fd2.setFromValue(0);
        fd2.setToValue(1);
        fd2.play();
        
        FadeTransition fd= new FadeTransition(Duration.millis(1500), status);
        fd.setFromValue(0);
        fd.setToValue(1);
        fd.play();
        
        ScaleTransition sl= new ScaleTransition(Duration.millis(1000), img);
        sl.setToY(1);
        sl.setFromY(0);
        sl.setToX(1);
        sl.setFromX(0);
        sl.play();
    }
    
}
