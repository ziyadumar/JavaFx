/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginDB;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ProfileController implements Initializable {
    
    
    private Main application;
    @FXML
    private JFXHamburger hambur;
    @FXML
    private AnchorPane sideanchor;
    @FXML
    private JFXButton button1;
    @FXML
    private JFXButton button2;
    @FXML
    private JFXButton button3;
    @FXML
    private JFXButton button4;
    @FXML
    private AnchorPane root;
    
    public void setApp(Main application){
        this.application = application;}

    int flag=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sideanchor.setMinWidth(200);
        
        hambur.setTranslateX(-200);
        
        sideanchor.setTranslateX(-200);
        
        
        button2.setOnAction(e->{
        application.gotoTablePage();
        
        });
        
        
        button4.setOnAction(e->{
        application.gotoLoginPage();     
        });
        
        hambur.setOnMouseClicked(e->{
            
        if(flag==0)
        {       
                flag=1;
                TranslateTransition tt=new TranslateTransition(Duration.millis(350), hambur);
                tt.setToX(0);
                tt.play();
                
                TranslateTransition tt0=new TranslateTransition(Duration.millis(350), sideanchor);
                tt0.setToX(0);
                tt0.play();
                
                ScaleTransition st=new ScaleTransition(Duration.millis(700), root);
                st.setFromX(1);
                st.setFromY(1);
                st.setToX(0.5);
                st.setToY(0.5);
                st.play();
        }
        else
        {
                flag=0;
                
                TranslateTransition tt=new TranslateTransition(Duration.millis(350), hambur);
                tt.setToX(-200);
                tt.play();
                
                TranslateTransition tt0=new TranslateTransition(Duration.millis(350), sideanchor);
                tt0.setToX(-200);
                tt0.play();
                
                ScaleTransition st=new ScaleTransition(Duration.millis(700), root);
                st.setFromX(.5);
                st.setFromY(.5);
                st.setToX(1);
                st.setToY(1);
                st.play();

        }
        
        });
        
        // TODO
    }    
    
}
