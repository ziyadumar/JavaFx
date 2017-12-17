/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;




/////////////////
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.Rectangle;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class DemosController extends AnchorPane implements Initializable {
    public BillingController bc;
    
    
    


    
    @FXML
    private TextField user;

    @FXML
    private PasswordField pass;

    @FXML
    private JFXButton login;
    
    
    private AnchorPane mainimg;

    
    
    private Label msg;
    
    private AnchorPane loginpane;
    

    
    
    private Main application;
    @FXML
    private StackPane stack;
    @FXML
    private AnchorPane outeranchor;
    @FXML
    private AnchorPane anchor;
    @FXML
    private StackPane innerstack;
    @FXML
    private AnchorPane inneranchor;
    @FXML
    private Label lbl;
    @FXML
    private Label label;
    @FXML
    private ImageView logo;
    
    int i=0;
    
    public void setApp(Main application){
        this.application = application;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        
        TranslateTransition passplay = new TranslateTransition(Duration.millis(1500), pass);
        passplay.setFromX(50);
        passplay.setToX(0);
        passplay.setCycleCount(1);
        passplay.setAutoReverse(true);
        
        TranslateTransition userplay = new TranslateTransition(Duration.millis(1500), user);
        userplay.setFromX(-100);
        userplay.setToX(0);
        userplay.setCycleCount(1);
        userplay.setAutoReverse(true);
        
        TranslateTransition buttonplay = new TranslateTransition(Duration.millis(1500), login);
        buttonplay.setFromY(-100);
        buttonplay.setToY(0);
        buttonplay.setCycleCount(1);
        buttonplay.setAutoReverse(true);
        
        TranslateTransition logoplay = new TranslateTransition(Duration.millis(1500), logo);
        logoplay.setFromY(-75);
        logoplay.setToY(0);
        logoplay.setCycleCount(1);
        logoplay.setAutoReverse(true);
        
        TranslateTransition labelplay = new TranslateTransition(Duration.millis(1500), label);
        labelplay.setFromX(75);
        labelplay.setToX(0);
        labelplay.setCycleCount(1);
        labelplay.setAutoReverse(true);
        
        
        
        ParallelTransition all = new ParallelTransition(userplay,passplay,buttonplay,labelplay,logoplay);
        all.play();
        
        
        TranslateTransition buttonplay1 = new TranslateTransition(Duration.millis(1500), login);
        buttonplay1.setFromY(30);
        buttonplay1.setToY(0);
        buttonplay1.setCycleCount(1);
        buttonplay1.setAutoReverse(true);
        
        user.setOnMouseClicked(e->{
            
            lbl.setText("");
            if(i==1)
                {buttonplay1.play();
                i=0;
                }
        
        });
        
        pass.setOnMouseClicked(e->{
            
            lbl.setText("");
            if(i==1)
                {buttonplay1.play();
                i=0;
                }
        
        });
        
        
        
        
        
        
        
        lbl.setText("");
        user.setPromptText("Username");
        pass.setPromptText("Password");
        

        
    }
    
    
    
    public void adminLogin(ActionEvent event) throws SQLException {
        
        application.gotoAdminLogin();
      //  
        
        
    }
    
    public void processLogin(ActionEvent event) throws SQLException {
        if (application == null){
            
            
            
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            msg.setText("Hello " + user.getText());
        } else {
            //bc.connect();
            //Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/albaha?useSSL=false", "root" , "ziyad8578");
            System.out.println("Hello");
            if (!application.userLogging(user.getText(), pass.getText())){
                msg.setText("Punnine PattiSSeyy");
            }
        }
    }

    @FXML
    private void login(ActionEvent event) {
        application.gotoProfile();
    }

    /**
     * Initializes the controller class.
     */
   
    
}
