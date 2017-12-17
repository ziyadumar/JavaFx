/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.Settings;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import demo.Bill;
import demo.Classes.Asset;
import demo.Classes.Bank;
import demo.Classes.Expense;
import demo.Classes.Variable;
import demo.JasperQuery;
import demo.Main;
import demo.connector;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
public class SettingsController implements Initializable {

    
    private int siino;
    private Main application;
    @FXML
    private TextField nameofbank;
    @FXML
    private TextField account;
    @FXML
    private TextField balance;
    @FXML
    private TableView<Bank> table;
    @FXML
    private Label labool;
    @FXML
    private TableColumn<?, ?> namecol;
    @FXML
    private TableColumn<?, ?> acccol;
    @FXML
    private TableColumn<?, ?> balcol;
    @FXML
    private TextField assetname;
    @FXML
    private TextField assdescr;
    @FXML
    private TextField assval;
    @FXML
    private TableView<Asset> table1;
    @FXML
    private TableColumn<?, ?> assetcol;
    @FXML
    private TableColumn<?, ?> desccol;
    @FXML
    private TableColumn<?, ?> valcol;
    @FXML
    private Label explbl;
    @FXML
    private Label inclbl;
    @FXML
    private Label totlbl;
    @FXML
    private ComboBox<Integer> monthcomb;
    @FXML
    private ComboBox<Integer> yearcomb;
    @FXML
    private TextField nos;
    @FXML
    private Label asslbl;
    @FXML
    private TextField expname;
    @FXML
    private TextField expdecr;
    @FXML
    private TextField expamnt;
    @FXML
    private JFXToggleButton typeofexpense;
    @FXML
    private TableColumn<?, ?> expcol;
    @FXML
    private TableColumn<?, ?> expdesccol;
    @FXML
    private TableColumn<?, ?> exptypcol;
    @FXML
    private TableColumn<?, ?> expamntcol;
    @FXML
    private Label explabl;
    @FXML
    private DatePicker expdate;
    @FXML
    private TableView<Expense> tableExp;
    @FXML
    private TableColumn<?, ?> expdatecol;
    
    public void setApp(Main application){
        this.application = application;
    }
    
    
    Connection conn=connector.LoginConnector();
    
    PreparedStatement preparedStatement=null;
	ResultSet rs=null;
        
        
    ObservableList std=FXCollections.observableArrayList();
    ObservableList accnos=FXCollections.observableArrayList();
    ObservableList<Bank> data=FXCollections.observableArrayList();
    ObservableList<Bank> dat=FXCollections.observableArrayList();
    ObservableList<Asset> dataAss=FXCollections.observableArrayList();
    ObservableList<Expense> dataExp=FXCollections.observableArrayList();
    
    ObservableList monthsob = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
    ObservableList yearob = FXCollections.observableArrayList("2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028");
    
    
    @FXML
    private JFXButton back;
    @FXML
    private TextField nameinp;
    @FXML
    private TextField idinp;
    @FXML
    private Label label;
    @FXML
    private JFXButton print;
    
    int month,year ;
    
    Variable vr= new Variable();
    
    JasperQuery jq =new JasperQuery();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadttData();
                
        System.out.println(Variable.getStr());
        
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        month= localDate.getMonthValue();
        year= localDate.getYear();
        label.setText("");
        System.out.println("month number = "+month+" and year ="+year);
        
		namecol.setCellValueFactory(new PropertyValueFactory<>("name"));                
                acccol.setCellValueFactory(new PropertyValueFactory<>("accountno"));
                balcol.setCellValueFactory(new PropertyValueFactory<>("balance"));
        
                
		assetcol.setCellValueFactory(new PropertyValueFactory<>("name"));                
                desccol.setCellValueFactory(new PropertyValueFactory<>("description"));
                valcol.setCellValueFactory(new PropertyValueFactory<>("amount"));
                
                
		expcol.setCellValueFactory(new PropertyValueFactory<>("name"));                
                expdesccol.setCellValueFactory(new PropertyValueFactory<>("description"));
                exptypcol.setCellValueFactory(new PropertyValueFactory<>("type"));
                expamntcol.setCellValueFactory(new PropertyValueFactory<>("amount"));
                expdatecol.setCellValueFactory(new PropertyValueFactory<>("date"));
                
                typeofexpense.setSelected(true);
                typeofexpense.setOnAction(e->{
                if(typeofexpense.isSelected())
                    typeofexpense.setText("DIRECT");
                else
                    typeofexpense.setText("INDIRECT");
                
                });
                
       nameinp.textProperty().addListener((observable, oldValue, newValue) -> {
               loadId();
           });
           table.setOnMouseClicked(e->tableClicked());
           table1.setOnMouseClicked(e->tableAssClicked());
           tableExp.setOnMouseClicked(e->tableExpClicked());
           
           loadAcc();
           loadtable();
           
           loadAssettable();
           loadExptable();

           
           monthcomb.setValue(month);
           yearcomb.setValue(year);
           
           monthcomb.setOnMousePressed(e->{
                      
           monthcomb.setItems(monthsob);
           
           });
           
           monthcomb.setOnAction(e->{
                      
           loadProf();
           loadBill();
           
           });
           
           yearcomb.setOnMousePressed(e->{
           
           yearcomb.setItems(yearob);
                    
           });
           
           
           yearcomb.setOnAction(e->{
           
           loadProf();
           loadBill();
                    
           });
           
           
           
           loadProf();
           loadBill();
           
           
           
                
    }    
    
    float fla=0;
    
    public void loadtable()
    	{
            fla=0;
                dat.clear();
		String query="select * from bank";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				dat.add(new Bank(
                                        
                                        rs.getInt("sino"),
                                        rs.getString("name"),
                                        rs.getString("accountno"),
                                        rs.getFloat("balance") 
                                        
                                )
                                        
                                        
                                
                                );
                                fla+=rs.getFloat("balance");
                                table.setItems(dat);
                                
                                }
                        labool.setText("Total Amount = "+String.valueOf(fla));
                        
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                
        }
    
    float fla1;
    public void loadAssettable()
    	{
            fla1=0;
                dataAss.clear();
		String query="select * from asset";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				dataAss.add(new Asset(
                                        
                                        rs.getInt("sino"),
                                        rs.getString("name"),
                                        rs.getString("description"),
                                        rs.getFloat("amount") 
                                        
                                )
                                        
                                        
                                
                                );
                                fla1+=rs.getFloat("amount");
                                table1.setItems(dataAss);
                                
                                }
                        asslbl.setText("Total Asset Value = "+String.valueOf(fla1));
                        
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                
        }
    
    
    float fla10;
    public void loadExptable()
    	{
            fla10=0;
                dataExp.clear();
		String query="select * from expense";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				dataExp.add(new Expense(
                                        
                                        rs.getInt("sino"),
                                        rs.getString("name"),
                                        rs.getString("description"),
                                        rs.getString("type"),
                                        rs.getFloat("amount") ,
                                        rs.getDate("date")
                                        
                                )
                                        
                                        
                                
                                );
                                fla10+=rs.getFloat("amount");
                                tableExp.setItems(dataExp);
                                
                                }
                        explabl.setText("Total Expense = "+String.valueOf(fla10));
                        
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                
        }
    
    
    
    
    public void loadttData()
    	{
                std.clear();
		String query="select DISTINCT name from med where quantity >= 1 ";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				std.add(rs.getString("name")  );
                                
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                TextFields.bindAutoCompletion(nameinp, std);
        }
    
    float roundd;
    public void loadId()
    	{
            roundd=0;
		String query="select * from med where `name` = '"+nameinp.getText()+"' ";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				idinp.setText(String.valueOf(rs.getInt("sino")));
                                nos.setText(String.valueOf(rs.getFloat("quantity")));
                                
                                
                                }
                        
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
        }
    
    
    public void tableClicked(){
        
        
        int i= table.getFocusModel().getFocusedIndex();
        System.out.println(i);
        siino=dat.get(i).getSino();
        nameofbank.setText(dat.get(i).getName());
        account.setText(dat.get(i).getAccountno());
        balance.setText(String.valueOf(dat.get(i).getBalance()));
        
        
    }
    
    
    public void tableAssClicked(){
        
        
        int i= table1.getFocusModel().getFocusedIndex();
        System.out.println(i);
        siino=dataAss.get(i).getSino();
        assetname.setText(dataAss.get(i).getName());
        assdescr.setText(dataAss.get(i).getDescription());
        assval.setText(String.valueOf(dataAss.get(i).getAmount()));
        
        
    }
    
    
    
    public void tableExpClicked(){
        
        
        int i= tableExp.getFocusModel().getFocusedIndex();
        System.out.println(i);
        siino=dataExp.get(i).getSino();
        expname.setText(dataExp.get(i).getName());
        expdecr.setText(dataExp.get(i).getDescription());
        expamnt.setText(String.valueOf(dataExp.get(i).getAmount()));
        expdate.setValue(dataExp.get(i).getDate());
        
        
    }
    
    
    public void loadAcc()
	{
                
		String query ="SELECT distinct accountno FROM bank;";
                
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                                accnos.add(rs.getString("accountno"));
                                        
                                        }
                        
			preparedStatement.close();
			rs.close();
                        TextFields.bindAutoCompletion(account, accnos);
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
    
    
    

    @FXML
    private void Back(ActionEvent event) {
        
        application.gotoProfile();

        }

    @FXML
    private void print(ActionEvent event) {
        
        roundd=Math.round(Float.parseFloat(nos.getText()));
        int aa=Math.round(roundd);
        
        
        String ab1="truncate repeater;";
        executeSQlQuery(ab1, "truncated");
        
        for(int a=aa;a>0;a--)
        {
            
        String ab="INSERT INTO repeater (`name`, `sino_of_pdt`) VALUES ('"+nameinp.getText()+"', '"+idinp.getText()+"');";
        executeSQlQuery(ab, "Inserted");
        
        
        }
        jq.showBarcode();
        
        
    }

    
    @FXML
    private void addbank(ActionEvent event) {
        
        siino=0;
    
        String a="INSERT INTO `oman`.`bank` (`name`, `accountno`, `balance`) VALUES ('"+nameofbank.getText()+"', '"+account.getText()+"', '"+balance.getText()+"');";
        executeSQlQuery(a, "Inserted");
    
    
    
    }
    
    @FXML
    private void updbank()
    {
    String ab="UPDATE `oman`.`bank` SET `name`='"+nameofbank.getText()+"', `accountno`= '"+account.getText()+"', `balance`='"+balance.getText()+"' WHERE `sino`='"+siino+"';" ;
    executeSQlQuery1(ab, "Updated!");
        
    }
    
    
    
    float expen;
    public void loadProf()
	{
                expen=0;
		String query ="SELECT * FROM dtab WHERE MONTH(date) = '"+monthcomb.getValue()+"' AND YEAR(date) = '"+yearcomb.getValue()+"' ;";
                
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                                expen+=rs.getFloat("given");
                                        
                                        }
                        explbl.setText(String.valueOf(new DecimalFormat("#.##").format(expen)));
                        
                        
			preparedStatement.close();
			rs.close();
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
    
    
    
    float inc;
    public void loadBill()
	{
                inc=0;
		String query ="SELECT * FROM billhistory WHERE MONTH(date) = '"+monthcomb.getValue()+"' AND YEAR(date) = '"+yearcomb.getValue()+"' ;";
                
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                                inc+=rs.getFloat("amount");
                                        
                                        }
                        inclbl.setText(String.valueOf(new DecimalFormat("#.##").format(inc)));
                        float tota=0;
                        tota=inc-expen;
                        totlbl.setText(String.valueOf(new DecimalFormat("#.##").format(tota)));
                        
                        
			preparedStatement.close();
			rs.close();
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
    
    
    
     public void executeSQlQuery(String query, String message)
   {
       Connection con =connector.LoginConnector();
       Statement st;
       try{
           st = con.createStatement();
           
           if((st.executeUpdate(query)) == 1)
           {
                      loadtable();        System.out.println("Data "+message+" Succefully");
           }else{
               System.out.println("Data Not "+message);
           }
       }catch(Exception ex){
           
           ex.printStackTrace();
       }
   }
     
     
     public void executeSQlQuery1(String query, String message)
   {
       Connection con =connector.LoginConnector();
       Statement st;
       try{
           st = con.createStatement();
           
           if((st.executeUpdate(query)) == 1)
           {
                            loadtable();     System.out.println("Data "+message+" Succefully");
           }else{
               System.out.println("Data Not "+message);
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }
     
     
    
    
    @FXML
    public void delete(){
        
        ObservableList<Bank> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();        
        productSelected.forEach(allProducts::remove);
        String query = "DELETE FROM `bank` WHERE `sino`='"+siino+"'";
        System.out.println(query);
        query2(query, "DELETED");
        
        
    }
    
    
      public void query2(String query, String message)
    {
       Statement st;
       try{
           st = conn.createStatement();
           if((st.executeUpdate(query)) == 1)
           {
               
               dat.clear();
               loadtable();
           }else{
               
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }

    @FXML
    private void addAsset(ActionEvent event) {
        
        
        siino=0;
    
        String a="INSERT INTO `asset` (`name`, `description`, `amount`) VALUES ('"+assetname.getText()+"', '"+assdescr.getText()+"', '"+assval.getText()+"');";
        executeSQlQuery(a, "Inserted");
        loadAssettable();
        
        
    }

    @FXML
    private void updAsset(ActionEvent event) {
        
        
    String ab="UPDATE `asset` SET `name`='"+assetname.getText()+"', `description`= '"+assdescr.getText()+"', `amount`='"+assval.getText()+"' WHERE `sino`='"+siino+"';" ;
    executeSQlQuery1(ab, "Updated!");
        loadAssettable();
    }

    @FXML
    private void deleteAsset(ActionEvent event) {
        
        ObservableList<Asset> productSelected, allProducts;
        allProducts = table1.getItems();
        productSelected = table1.getSelectionModel().getSelectedItems();        
        productSelected.forEach(allProducts::remove);
        String query = "DELETE FROM `asset` WHERE `sino`='"+siino+"'";
        System.out.println(query);
        query2(query, "DELETED");
        loadAssettable();
    }

    @FXML
    private void addExp(ActionEvent event) {
        String asd="INSERT INTO `expense` (`name`, `description`, `type`, `amount`, `date`) VALUES ('"+expname.getText()+"', '"+expdecr.getText()+"', '"+typeofexpense.getText()+"', '"+expamnt.getText()+"', '"+expdate.getValue()+"');";
        executeSQlQuery(asd, "Inserted");
        loadExptable();
        
    }

    @FXML
    private void updExp(ActionEvent event) {
    }

    @FXML
    private void deleteExp(ActionEvent event) {
    }
    
}
