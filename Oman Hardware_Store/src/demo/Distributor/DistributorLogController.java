/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.Distributor;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import demo.Classes.Suppliers;
import demo.Main;
import demo.Medicine;
import demo.connector;
import static java.lang.String.valueOf;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class DistributorLogController implements Initializable {
    
    private String invoice;
    private float tot,given;
    
    
    PreparedStatement preparedStatement=null;
    ResultSet rs=null;
    Statement stmt = null;
    
    ObservableList<Suppliers> data=FXCollections.observableArrayList();
    ObservableList<Suppliers> data1=FXCollections.observableArrayList();
    ObservableList<Suppliers> data3=FXCollections.observableArrayList();
    ObservableList modpay = FXCollections.observableArrayList();
    ObservableList stda = FXCollections.observableArrayList();
    
    Connection conn=connector.LoginConnector();
        
    
    
    private Main application;
    @FXML
    private TableColumn<?, ?> invoicecol;
    @FXML
    private TableColumn<?, ?> namecol;
    @FXML
    private TableColumn<?, ?> datecol;
    @FXML
    private TableColumn<?, ?> mopcol;
    @FXML
    private TableColumn<?, ?> totalcol;
    @FXML
    private TableColumn<?, ?> paycol;
    @FXML
    private TableColumn<?, ?> balcol;
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField dln1;
    @FXML
    private TextField dln2;
    @FXML
    private TextField gst;
    @FXML
    private TableView<Suppliers> table1;
    @FXML
    private TableColumn<?, ?> namecol1;
    @FXML
    private TableColumn<?, ?> addresscol1;
    private TableColumn<?, ?> gstcol1;
    private TableColumn<?, ?> dln1col1;
    private TableColumn<?, ?> dln2col1;
    @FXML
    private TextField byname;
    @FXML
    private TableView<Suppliers> table2;
    @FXML
    private TableColumn<?, ?> invoicecol1;
    @FXML
    private TableColumn<?, ?> datecol1;
    @FXML
    private TableColumn<?, ?> mopcol1;
    @FXML
    private TableColumn<?, ?> totalcol1;
    @FXML
    private TableColumn<?, ?> paycol1;
    @FXML
    private TableColumn<?, ?> balcol1;
    
    public void setApp(Main application){
        this.application = application;
    }

    @FXML
    private TableView<Suppliers> table;
    @FXML
    private JFXButton delete;
    @FXML
    private TextField payed;
    @FXML
    private JFXButton back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                invoicecol.setCellValueFactory(new PropertyValueFactory<>("invoice")); 
                namecol.setCellValueFactory(new PropertyValueFactory<>("name"));                
                datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
                mopcol.setCellValueFactory(new PropertyValueFactory<>("modeofpay"));                
		totalcol.setCellValueFactory(new PropertyValueFactory<>("total"));                
                balcol.setCellValueFactory(new PropertyValueFactory<>("balance"));
                paycol.setCellValueFactory(new PropertyValueFactory<>("given"));
                
                dln1.setVisible(false);
                dln2.setVisible(false);
                gst.setVisible(false);
                              
                namecol1.setCellValueFactory(new PropertyValueFactory<>("name"));
                //dln1col1.setCellValueFactory(new PropertyValueFactory<>("dln1"));                
		//dln2col1.setCellValueFactory(new PropertyValueFactory<>("dln2"));                
                addresscol1.setCellValueFactory(new PropertyValueFactory<>("address"));
                //gstcol1.setCellValueFactory(new PropertyValueFactory<>("gst"));
                
                invoicecol1.setCellValueFactory(new PropertyValueFactory<>("invoice")); 
                datecol1.setCellValueFactory(new PropertyValueFactory<>("date"));
                mopcol1.setCellValueFactory(new PropertyValueFactory<>("modeofpay"));                
		totalcol1.setCellValueFactory(new PropertyValueFactory<>("total"));                
                balcol1.setCellValueFactory(new PropertyValueFactory<>("balance"));
                paycol1.setCellValueFactory(new PropertyValueFactory<>("given"));
                
                loadAutodata();
                
                loadPartTable();
                
                loadTable();
                table.setOnMouseClicked(e->tableClicked());
                
                loadDetTable();
                table1.setOnMouseClicked(e->table1Clicked());
                
    }    
    
    public void tableClicked(){
        
        
        
        int i= table.getFocusModel().getFocusedIndex();
        System.out.println(i);
        invoice=data.get(i).getInvoice();
        tot=data.get(i).getTotal();
        given=data.get(i).getGiven();
        System.out.println("invoice = "+invoice);
        payed.setText(String.valueOf(data.get(i).getGiven()));
        
    }
    
    String prmname="";
    
    public void table1Clicked(){       
        
        
        int i= table1.getFocusModel().getFocusedIndex();
        System.out.println(i);
        name.setText(data1.get(i).getName());
        prmname=data1.get(i).getName();
        address.setText(data1.get(i).getAddress());
        dln1.setText(data1.get(i).getDln1());
        dln2.setText(data1.get(i).getDln2());
        gst.setText(data1.get(i).getGst());
        
    }
    
    
    public void loadTable()
	{
                data.clear();
		String query="select * from `dtab`";
		          System.out.println("hihi");
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				data.add(new Suppliers(
						rs.getString("invoice"),
						rs.getDate("date"),
                                                rs.getString("modeofpay"),                                        
                                                rs.getString("name"),                                        
                                                rs.getFloat("total"),
                                                rs.getFloat("balance"),                                   
                                                rs.getFloat("given")
                                        
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
    
    
    public void loadAutodata()
    	{
                stda.clear();
                //lbl1.setText("Lo moyanth");
		//String query="select * from students";
		String query="select DISTINCT name from dtab";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				stda.add(rs.getString("name")  );
                                
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                TextFields.bindAutoCompletion(byname, stda);
        }

    
    
    public void loadPartTable()
	{
                data3.clear();
		String query="select * from `dtab` where name ='"+byname.getText()+"'";
		          System.out.println("hihi");
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				data3.add(new Suppliers(
						rs.getString("invoice"),
						rs.getDate("date"),
                                                rs.getString("modeofpay"),                                        
                                                rs.getString("name"),                                        
                                                rs.getFloat("total"),
                                                rs.getFloat("balance"),                                   
                                                rs.getFloat("given")
                                        
						));
				table2.setItems(data3);
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
    private void deleteButtonClicked(ActionEvent event) {
    }


    @FXML
    private void goBack(ActionEvent event) {
        application.gotoProfile();
    }

    @FXML
    private void update(ActionEvent event) {
        
        float sum=tot-Float.valueOf((payed.getText()));
        String qry="UPDATE `dtab` SET `given`='"+payed.getText()+"' , `balance`='"+sum+"' WHERE `invoice`='"+invoice+"';";

        executeSQlQuery(qry, "Updated");
        
    }
    
    public void executeSQlQuery(String query, String message)
   {
       Connection con =connector.LoginConnector();
       Statement st;
       try{
           st = con.createStatement();
           
           if((st.executeUpdate(query)) == 1)
           {
               // refresh jtable data
               data.clear();
               loadTable();
               //table.refresh();
//               loadDatabaseData();
               
               System.out.println("Data "+message+" Succefully");
           }else{
               System.out.println("Data Not "+message);
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }
    
    
    
    
    
    public void loadDetTable()
	{
                data1.clear();
		String query="SELECT * FROM dtab group by name;";
		          System.out.println("hihi");
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				data1.add(new Suppliers(
						rs.getString("name"),
						rs.getString("address"),
                                                rs.getString("dl1"),                                        
                                                rs.getString("dl2"),                                        
                                                rs.getString("gstin")
                                        
						));
				table1.setItems(data1);
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
    private void updateDetails(ActionEvent event) {
        
        String a ="UPDATE `dtab` SET `name`='"+name.getText()+"', "
                + "`address`='"+address.getText()+"', `dl1`='"+dln1.getText()+"',"
                + " `dl2`='"+dln2.getText()+"', `gstin`='"+gst.getText()+"'"
                + " WHERE `name`='"+prmname+"';";
        executeSQlQuery1(a, "Updated");
    }
    
    
    public void executeSQlQuery1(String query, String message)
   {
       Connection con =connector.LoginConnector();
       Statement st;
       try{
           st = con.createStatement();
           
           if((st.executeUpdate(query)) == 1)
           {
               data1.clear();
                loadDetTable();
                loadTable();
               
               System.out.println("Data "+message+" Succefully");
           }else{
                 data1.clear();
                loadDetTable();
                loadTable();
               System.out.println("Data No00000000t "+message);
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }

    @FXML
    private void find(ActionEvent event) {
        loadPartTable();
        
    }
    
    
    
    
}
