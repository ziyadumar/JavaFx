/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.stock;

import com.jfoenix.controls.JFXButton;
import demo.Main;
import demo.Medicine;
import demo.connector;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class FinishingMedicineController implements Initializable {

    @FXML
    private JFXButton back;
    @FXML
    private TextField quan;
    @FXML
    private TableView<Medicine> table;
    private TableColumn<?, ?> idcol;
    @FXML
    private TableColumn<?, ?> mfrcol;
    @FXML
    private TableColumn<?, ?> namecol;
    @FXML
    private TableColumn<?, ?> pricecol;
    @FXML
    private TableColumn<?, ?> quancol;
    private TableColumn<?, ?> batchcol;
    private TableColumn<?, ?> expcol;
    
    
    Connection conn=connector.LoginConnector();
    PreparedStatement preparedStatement=null;
	ResultSet rs=null;
    
    
    ObservableList<Medicine> data=FXCollections.observableArrayList();
    
    
    private Main application;
    public void setApp(Main application){
        this.application = application;
    }
    
    DateFormat dateFormat = new SimpleDateFormat("MMM-yy");
        Date date = new Date();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        System.out.println(dateFormat.format(date));
        
        
                //idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
		namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
                
                pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
                quancol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                //batchcol.setCellValueFactory(new PropertyValueFactory<>("batch"));
                //expcol.setCellValueFactory(new PropertyValueFactory<>("exp"));
                mfrcol.setCellValueFactory(new PropertyValueFactory<>("mfr"));
                
                loadDatabaseData();
                
                quan.setOnKeyPressed(e->loadDatabaseData(quan.getText()));
                
        
        
        // TODO
    }    
    
    
	public void loadDatabaseData()
	{
                data.clear();
		String query="select * from `med` where quantity <=10";
		          System.out.println("hihi");
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                            String s=dateFormat.format(rs.getDate("expiry"));
                            
				data.add(new Medicine(
						rs.getInt("id"),
						rs.getString("name"),
                                                rs.getInt("quantity"),                                        
                                                rs.getFloat("price"),                                        
                                                rs.getString("batch"),
                                                s,
                                                rs.getString("mfr")
						
                                                
						));
				table.setItems(data);
			}
			preparedStatement.close();
			rs.close();
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
        
        public void loadDatabaseData(String n)
	{
                data.clear();
		String query="select * from `med` where quantity <= "+n+" ";
		          System.out.println("hihi");
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				data.add(new Medicine(
						rs.getInt("id"),
						rs.getString("name"),
                                                rs.getInt("quantity"),                                        
                                                rs.getFloat("price"),                                        
                                                rs.getString("batch"),
                                                rs.getString("expiry"),
                                                rs.getString("mfr")
						
                                                
						));
				table.setItems(data);
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
        application.gotoProfile();
    }
    
}
