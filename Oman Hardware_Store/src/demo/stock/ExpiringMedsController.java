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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class ExpiringMedsController implements Initializable {

    @FXML
    private TableView<Medicine> table;
    @FXML
    private TableColumn<?, ?> idcol;
    @FXML
    private TableColumn<?, ?> mfrcol;
    @FXML
    private TableColumn<?, ?> namecol;
    @FXML
    private TableColumn<?, ?> pricecol;
    @FXML
    private TableColumn<?, ?> quancol;
    @FXML
    private TableColumn<?, ?> batchcol;
    @FXML
    private TableColumn<?, ?> expcol;
    @FXML
    private JFXButton back;
    
    private int mon,count=0,yeara;

     ObservableList months = FXCollections.observableArrayList("January","February","March","April","May","June","July","August","September","October","November","December");
    
    Connection conn=connector.LoginConnector();
    PreparedStatement preparedStatement=null;
	ResultSet rs=null;
    
    
    ObservableList<Medicine> data=FXCollections.observableArrayList();
    
    int yearnu = Calendar.getInstance().get(Calendar.YEAR);
    
    
    private Main application;
    @FXML
    private ComboBox<?> combo;
    @FXML
    private TextField year;
    @FXML
    private JFXButton find;
    @FXML
    private Label countlbl;
    
    public void setApp(Main application){
        this.application = application;
    }
    
    Calendar now = Calendar.getInstance();
    
     DateFormat dateFormat = new SimpleDateFormat("MMM-yy");
        Date date = new Date();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
   
    System.out.println("Current date : " + (now.get(Calendar.MONTH) + 1)
                        + "-"
                        + now.get(Calendar.DATE)
                        + "-"
                        + now.get(Calendar.YEAR));
   
    //add months to current date using Calendar.add method
    now.add(Calendar.MONTH,3);
 
    //System.out.println("date after 2 months : " + (now.get(Calendar.MONTH) + 1)+ "-"+ now.get(Calendar.DATE)+ "-"+ now.get(Calendar.YEAR));
 
        
        countlbl.setText("");
        combo.setItems(months);
        
        year.setText(String.valueOf(yearnu));
        
                idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
		namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
                
                pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
                quancol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                batchcol.setCellValueFactory(new PropertyValueFactory<>("batch"));
                expcol.setCellValueFactory(new PropertyValueFactory<>("exp"));
                mfrcol.setCellValueFactory(new PropertyValueFactory<>("mfr"));
                
                loadData();
                
                combo.setOnAction(e->{
                    
                    monNumb();
                
                });
                
                find.setOnAction(e->{
                    
                    monNumb();
                    loadCustomData();
                
                
                });
                
    }    
    
    
    private void monNumb() {
        
        
        
        if(combo.getValue().equals("January"))
            mon=1;
        if(combo.getValue().equals("February"))
            mon=2;
        if(combo.getValue().equals("March"))
            mon=3;
        if(combo.getValue().equals("April"))
            mon=4;
        if(combo.getValue().equals("May"))
            mon=5;
        if(combo.getValue().equals("June"))
            mon=6;
        if(combo.getValue().equals("July"))
            mon=7;
        if(combo.getValue().equals("August"))
            mon=8;
        if(combo.getValue().equals("September"))
            mon=9;
        if(combo.getValue().equals("October"))
            mon=10;
        if(combo.getValue().equals("November"))
            mon=11;
        if(combo.getValue().equals("December"))
            mon=12;
        
        System.out.println(combo.getValue()+" : "+mon);
        
        
        
    }
    
    public void loadData()
	{
            count=0;
                data.clear();
		String query="SELECT * FROM med where expiry < '"+now.get(Calendar.YEAR)+"-"+now.get(Calendar.MONTH)+"-30'";
		          System.out.println(query);
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{   
                            String s=dateFormat.format(rs.getDate("expiry"));
                                count++;
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
                                countlbl.setText(String.valueOf(count));
			}
			preparedStatement.close();
			rs.close();
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
    
    
    
	public void loadCustomData()
	{
            count=0;
                data.clear();
		String query="SELECT * FROM med where expiry < '"+year.getText()+"-"+mon+"-30'";
		          System.out.println(query);
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                            String s=dateFormat.format(rs.getDate("expiry"));
                             count++;
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
                                countlbl.setText(String.valueOf(count));
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
