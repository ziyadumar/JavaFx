/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import demo.ConfirmController;
import demo.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class CashController implements Initializable {
    
    
    
    @FXML
    private JFXButton back;

    @FXML
    private JFXButton pay;

    @FXML
    private Label total;

    @FXML
    private Label change;

    @FXML
    private JFXTextField amnt;
    
    private Main application;
    
    
    public void setApp(Main application){
        this.application = application;}


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        total.setText("5000");
        amnt.setAlignment(Pos.CENTER);
        
        change.setOnMouseClicked(e -> calc());
        // TODO
    }    
    
    public void calc() {
        
        int amount=Integer.parseInt(amnt.getText());
        int bal=5000-amount;
        change.setText(String.valueOf(bal));
        
        
    }
    
    
    
    public void back(ActionEvent event) {
        
        application.gotoBilling();
        
    }
    
    
    @FXML
    public void clickShow(ActionEvent event) throws IOException {
    Stage stage = new Stage();
    Parent root;
        root = FXMLLoader.load(ConfirmController.class.getResource("Confirm.fxml"));
    stage.setScene(new Scene(root));
    stage.setTitle("My modal window");
    stage.initModality(Modality.WINDOW_MODAL);
    stage.initOwner(
        ((Node)event.getSource()).getScene().getWindow() );
    stage.show();
}
}
