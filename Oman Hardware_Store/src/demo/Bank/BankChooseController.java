/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.Bank;

import com.jfoenix.controls.JFXButton;
import demo.Classes.BankTransVar;
import demo.Main;
import demo.connector;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class BankChooseController implements Initializable {

    @FXML
    private AnchorPane anchorpa;
    @FXML
    private Label ballbl;
    @FXML
    private JFXButton payer;
    
    
    public void setApp(Main application){
        this.application = application;
    }
    
    
    Connection conn=connector.LoginConnector();
    
    PreparedStatement preparedStatement=null;
	ResultSet rs=null;
        
        
    private Main application;
    @FXML
    private TextField cheqfield;
    
        
    
    ObservableList std=FXCollections.observableArrayList();
    ObservableList stddoc=FXCollections.observableArrayList();
    ObservableList banknam=FXCollections.observableArrayList();
    ObservableList banknum=FXCollections.observableArrayList();

    @FXML
    private JFXButton back;
    @FXML
    private ComboBox<?> namecomb;
    @FXML
    private TextField amountfield;
    @FXML
    private ComboBox<?> acccomb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Autoname();
        namecomb.setOnAction(e->LoadNum());
        // TODO
        acccomb.setOnAction(e->{
        LoadBal();        
        });
    }    

    @FXML
    private void Back(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
        //stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    
        //stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        
    }
    
    
    public void Autoname()
    	{
                banknam.clear();
		String query="select DISTINCT name from bank ";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				banknam.add(rs.getString("name")  );
                                
                                }
                        namecomb.setItems(banknam);
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
        }
    
    public void LoadNum()
    	{
                banknum.clear();
		String query="select accountno from bank where name = '"+namecomb.getValue().toString()+"'";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				banknum.add(rs.getString("accountno")  );
                                
                                }
                        acccomb.setItems(banknum);
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
        }
    
    
    public void LoadBal()
    	{
		String query="select * from bank where accountno = '"+acccomb.getValue().toString()+"'";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				ballbl.setText("Available Balance = "+String.valueOf(rs.getFloat("balance")));
                                
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
    private void pay(ActionEvent event) {
        
        String a="INSERT INTO `oman`.`banktrans` (`name`, `accountno`, `cheq`, `amount` , `date` , `invoice` ) "
                + "VALUES ('"+namecomb.getValue().toString()+"', '"+acccomb.getValue().toString()+"' ,'"+cheqfield.getText()+"'"
                + ",'"+amountfield.getText()+"' , '"+BankTransVar.getDate()+"' , '"+BankTransVar.getInvoice()+"' );";
        executeSQlQuery(a, "Inserted");
        payer.setText("Succesful!");
    
    
    }
    
    
     public void executeSQlQuery(String query, String message)
   {
       Connection con =connector.LoginConnector();
       Statement st;
       try{
           st = con.createStatement();
           
           if((st.executeUpdate(query)) == 1)
           {
                             System.out.println("Data "+message+" Succefully");
           }else{
               System.out.println("Data Not "+message);
           }
       }catch(Exception ex){
           
           ex.printStackTrace();
       }
   }
     
    
}
