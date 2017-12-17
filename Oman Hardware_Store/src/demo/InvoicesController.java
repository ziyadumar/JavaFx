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
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.TextFields;
import demo.JasperQuery;

/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class InvoicesController implements Initializable {
    
    
    
    Connection conn=connector.LoginConnector();
    
    PreparedStatement preparedStatement=null;
    ResultSet rs=null;
    Statement stmt = null;
    
    ObservableList<Medicine> data=FXCollections.observableArrayList();

    @FXML
    private TextField invoiceno;
    @FXML
    private Label distlbl;
    @FXML
    private Label datelbl;
    @FXML
    private TableView<Medicine> table;
    @FXML
    private TableColumn<?, ?> namecol;
    @FXML
    private TableColumn<?, ?> idcol;
    @FXML
    private TableColumn<?, ?> quancol;
    @FXML
    private TableColumn<?, ?> pricecol;
    @FXML
    private TableColumn<?, ?> ratecol;
    @FXML
    private TableColumn<?, ?> gstcol;
    @FXML
    private TableColumn<?, ?> totcol;
    @FXML
    private JFXButton back;
    
    JasperQuery js= new JasperQuery();
    
    
    private Main application;
    @FXML
    private Label labool;
    
    public void setApp(Main application){
        this.application = application;
    }
    
    
    
    ObservableList autoinvoice=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
             labool.setText("");
        
        
        loadInvoicePredi();
        
            invoiceno.textProperty().addListener((observable, oldValue, newValue) -> {
                
            loadInvoiceData();
            loadTable();
                System.out.println("textfield changed from " + oldValue + " to " + newValue);
            });
        
        
                idcol.setCellValueFactory(new PropertyValueFactory<>("id")); 
                namecol.setCellValueFactory(new PropertyValueFactory<>("name"));                
                pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
                quancol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                
                //con.setCellValueFactory(new PropertyValueFactory<>("batch"));                
                gstcol.setCellValueFactory(new PropertyValueFactory<>("gst"));
                ratecol.setCellValueFactory(new PropertyValueFactory<>("rate"));
                
                
                totcol.setCellValueFactory(new PropertyValueFactory<>("total"));
        
        
        // TODO
    }    
    
    
    public void loadInvoicePredi()
    	{
                autoinvoice.clear();
		String query="select * from dtab";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				autoinvoice.add(rs.getString("invoice")  );
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                TextFields.bindAutoCompletion(invoiceno, autoinvoice);
        }
    
    public void loadInvoiceData()
    	{
		String query="select * from dtab where invoice="+invoiceno.getText()+"";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                                distlbl.setText("Distributor : "+rs.getString("name"));
                                datelbl.setText("Date : "+rs.getDate("date").toString());
                                
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
        }
    float fla;
    public void loadTable()
	{       
                fla=0;
                data.clear();
		String query="select * from `distlog` where invoice ='"+invoiceno.getText()+"'";
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
                                                rs.getString("contentname"),
                                                rs.getFloat("gst"),
                                                rs.getFloat("rate"),                                        
                                                rs.getString("mfr"),
                                                (rs.getFloat("rate")*rs.getInt("quantity"))
						));
                                System.out.println(rs.getFloat("rate")*rs.getInt("quantity"));
				table.setItems(data);
                                
                                fla+=rs.getFloat("total");
                                labool.setText("Total Amount = "+String.valueOf(fla));
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
        application.gotoDistr();
    }
    
    @FXML
    private void report(ActionEvent event) {
        System.out.println(invoiceno.getText());
        
        //js.showReport(invoiceno.getText());
        
       
        
    }
    
    
    
}
