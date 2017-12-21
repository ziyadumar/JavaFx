/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnection;

import databaseconnection.DBconnector.Connector;
import databaseconnection.JavaClass.SampleTable;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class TableviewController implements Initializable {
    
    
    private Main application;
    
    public void setApp(Main application){
        this.application = application;}
    
    
    
    Connection conn=Connector.LoginConnector();
    
    PreparedStatement preparedStatement=null;
    ResultSet rs=null;
    
    
    ObservableList<SampleTable> dat=FXCollections.observableArrayList();
    
    
    @FXML
    private TableView<SampleTable> table;
    @FXML
    private TableColumn<?, ?> sinocol;
    @FXML
    private TableColumn<?, ?> namecol;
        
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                sinocol.setCellValueFactory(new PropertyValueFactory<>("sino"));
                namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
                
                loadtable();
                
                
                ScaleTransition st=new ScaleTransition(Duration.millis(700), table);
                st.setFromX(0);
                st.setFromY(0);
                st.setToX(1);
                st.setToY(1);
                st.play();
                
        // TODO
    }

    
    public void loadtable()
    	{
                dat.clear();
		String query="SELECT * FROM projectone.sampletable;";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				dat.add(new SampleTable(
                                        rs.getInt("sino"),
                                        rs.getString("name") 
                                        
                                )
                                        
                                        
                                
                                );
                                table.setItems(dat);
                                
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
    private void goBack(ActionEvent event) {
        application.gotoProfilePage();
    }
    
}
