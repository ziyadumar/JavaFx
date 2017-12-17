/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.util.Duration;
import net.sf.jasperreports.engine.JRException;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class PrintController implements Initializable {
    
    private Main application;
    @FXML
    private JFXButton print;
    @FXML
    private JFXButton back;
    
    public void setApp(Main application){
        this.application = application;
    }
    
    
    
    
    PreparedStatement preparedStatement=null;
	ResultSet rs=null;
        
     
    Connection conn=connector.LoginConnector();

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        print.setOnAction(e->{
            
            
        Jasper jasper = new Jasper();
            try {
                jasper.showReport();
                
            } catch (JRException ex) {
                Logger.getLogger(PrintController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PrintController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(PrintController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        });
        // TODO
    }    
    
    
    public void loadtot()
    	{
		String query="select * from bill";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                                
                                        
                                
                                
                                }
                        
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                
        }


    @FXML
    private void printer(ActionEvent event) {
    }

    @FXML
    private void goBack(ActionEvent event) {
        application.gotoBilling();
    }
    
}
