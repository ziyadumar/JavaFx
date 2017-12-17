/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.Reports;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import demo.JasperQuery;
import demo.Main;
import demo.Returntable;
import demo.connector;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class ReportManagerController implements Initializable {

    @FXML
    private RadioButton daily;
    @FXML
    private ToggleGroup group;
    @FXML
    private RadioButton custom;
    @FXML
    private JFXDatePicker from;
    @FXML
    private JFXDatePicker to;
    @FXML
    private JFXButton back;
    
    private int flag;
    
    String name,modeofpay,invoices,address,gst;
    Float total,balance,given;
    Date date;
    
    
    private Main application;
    @FXML
    private TextField invoice;
    public void setApp(Main application){
        this.application = application;
    }
    
    
    PreparedStatement preparedStatement=null;
	ResultSet rs=null;
        
    Connection conn=connector.LoginConnector();
    ObservableList std=FXCollections.observableArrayList();

    JasperQuery jq= new JasperQuery();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        daily.setOnAction(e->{
        
            from.setPromptText("DATE");
            to.setDisable(true);
            flag=0;
        
        
        });
        
        loadinvoices();
        
        custom.setOnAction(e->{
        
            from.setPromptText("FROM");
            to.setDisable(false);
            to.setPromptText("TO");
            flag=1;
        
        
        });
        
        
        
        // TODO
    }    
    
    public void loadinvoices()
    	{
                std.clear();
                //lbl1.setText("Lo moyanth");
		//String query="select * from students";
		String query="select distinct invoice from `distlog` ";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                                
                            std.addAll(rs.getString("invoice"));
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                TextFields.bindAutoCompletion(invoice, std);
        }
    
    public void loadData()
    	{
                
                //lbl1.setText("Lo moyanth");
		//String query="select * from students";
		String query="select * from dtab where invoice ='"+invoice.getText()+"'";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				
                                
                                
                                name=rs.getString("name")  ;
				invoices=rs.getString("invoice")  ;
                                balance=rs.getFloat("balance")  ;
                                given=rs.getFloat("given")  ;
                                total=rs.getFloat("total")  ;
                                modeofpay=rs.getString("modeofpay")  ;
                                date=rs.getDate("date")  ;
                                address=rs.getString("address");
                                gst=rs.getString("gstin")  ;
                                
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
    private void show(ActionEvent event) {
        if(flag==1){
            
            jq.showCustomReport(from.getValue().toString(),to.getValue().toString());
        
        
        }
        else{
        
        jq.showDailyReport(from.getValue().toString());
        
        }
        
    }

    @FXML
    private void goBack(ActionEvent event) {
        application.gotoProfile();
    }

    @FXML
    private void showinv(ActionEvent event) {
        
        loadData();
        
        String str="UPDATE `distdet` SET `name`='"+name+"', `date`='"+date+"', `invoice`='"+invoices+"', `modeofpay`='"+modeofpay+"', `total`='"+total+"', `given`='"+given+"', `balance`='"+balance+"' WHERE `id`='1';";
        executeSQlQuery(str, "Updated");
        
        String st1= "UPDATE `dtabtemp` SET `invoice`='"+invoices+"', `name`='"+name+"', `date`='"+date+"', `total`='"+total+"', "
                + "`given`='"+given+"', `balance`='"+balance+"', `address`='"+address+"',  `gstin`='"+gst+"' WHERE `sino`='1';";
        executeSQlQuery(st1, "Updated");
        
        
        //jq.showDistDet(invoice.getText());
        jq.showDistReport(invoice.getText());
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
