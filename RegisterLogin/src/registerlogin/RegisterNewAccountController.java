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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import registerlogin.Database.Connector;

/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class RegisterNewAccountController implements Initializable {
    
    
    Connection conn=Connector.LoginConnector();
    PreparedStatement preparedStatement=null;
    ResultSet rs=null;
    
    
    @FXML
    private TextField user;
    @FXML
    private TextField pass;
    
    private RegisterLogin application;

    public void setApp(RegisterLogin application){
        this.application = application;}
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) {   
        checkuser();        
    }

    @FXML
    private void newuser(ActionEvent event) {
        
        application.gotoCreateNewPage();
        
    }
    
    
    public void checkuser()
    	{
            
		String query="SELECT * FROM `tutorials`.`accounts` where `username` = '"+user.getText()+"'  and `password` = '"+pass.getText()+"' ;";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                             
                         application.gotoUserPage();
                                        
                         }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                
        }
    
    
}
