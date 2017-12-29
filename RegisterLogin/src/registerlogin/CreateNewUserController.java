/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registerlogin;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import registerlogin.Database.Connector;

/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class CreateNewUserController implements Initializable {

    Connection conn=Connector.LoginConnector();
    PreparedStatement preparedStatement=null;
    ResultSet rs=null;
    
    @FXML
    private TextField name;
    @FXML
    private TextField user;
    @FXML
    private TextField pass;
    @FXML
    private TextField repass;
    
    
    private RegisterLogin application;
    @FXML
    private Label status;

    public void setApp(RegisterLogin application){
        this.application = application;}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        status.setText("");
        
        
        // TODO
    }    

    @FXML
    private void create(ActionEvent event) throws SQLException 
    {
        
        if(pass.getText().equals(repass.getText()))
        {      
            String insertquery ="INSERT INTO `tutorials`.`accounts` (`username`, `password` , `name` ) "+ "VALUES ( '"+user.getText()+"' , '"+pass.getText()+"' , '"+name.getText()+"')";
            preparedStatement = conn.prepareStatement(insertquery);
            try{
                preparedStatement.execute();
                status.setText("Succesfully Added!");
                }
            catch(Exception ex)
                {
                    status.setText("Succesfully Added!");
                    ex.printStackTrace();
                 }
        }                       
                    
    }

    @FXML
    private void back(ActionEvent event) {
        
        application.gotoFirstPage();
    }
    
}
