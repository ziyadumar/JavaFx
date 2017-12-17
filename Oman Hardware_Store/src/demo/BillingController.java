/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import net.sf.jasperreports.engine.JRException;
import org.controlsfx.control.textfield.TextFields;



/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class BillingController implements Initializable  {
    
    Connection conn=connector.LoginConnector();
    
    PreparedStatement preparedStatement=null;
	ResultSet rs=null;
        
    
    ObservableList std=FXCollections.observableArrayList();
    ObservableList stddoc=FXCollections.observableArrayList();
    ObservableList custo=FXCollections.observableArrayList();
    
    
    ObservableList<Bill> dat=FXCollections.observableArrayList();
    
    
        private String exp,mfr,batch,s;
        private int idd,olquan,newquan,z,idee;
	
    private float pricegbl;
    private JDBC jdbc;
    
    private float totalamt;
    @FXML
    private Label remain;
    
    private int flag;
    
    
    private Main application;
    @FXML
    private TextField age;
    @FXML
    private ComboBox<String> batcht;
    private Label blbl;
    @FXML
    private Label labool;
    @FXML
    private TextField address;
    @FXML
    private TextField ideetext;
    public void setApp(Main application){
        this.application = application;
    }
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

    @FXML
    private AnchorPane main;

    @FXML
    public TableView<Bill> table;

    private TableColumn<?, ?> idcol;

    @FXML
    private TableColumn<?, ?> namecol;

    @FXML
    private TableColumn<?, ?> pricecol;

    @FXML
    private TableColumn<?, ?> quancol;

    @FXML
    private JFXButton next;


    private JFXButton connect;

    @FXML
    private JFXButton back;

    

    
    
    @FXML
    private JFXButton edit;
    
    
   /* @FXML
    public JFXButton add;

    @FXML
    public static JFXButton delete;*/

    @FXML
    public static Label lbl1;
    
    @FXML
    private TextField name;

    @FXML
    private TextField quantity;

    @FXML
    private TextField price;

    @FXML
    private TextField id;   
    
     ObservableList batchlist=FXCollections.observableArrayList();
    
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton delete;
    @FXML
    private TextField tot;
    @FXML
    private TableColumn<?, ?> totcol;
    @FXML
    private TextField doctor;
    @FXML
    private TextField customer;
    @FXML
    private TextField contact;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{       
            
            ideetext.setFocusTraversable(true);
             age.setVisible(false);
             doctor.setVisible(false);
             loadttData();
             loaddOC();
             remain.setText("");
             labool.setText("");
             
             price.setEditable(false);
             id.setEditable(false);
             tot.setEditable(false);

                
		namecol.setCellValueFactory(new PropertyValueFactory<>("name"));                
                pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
                quancol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                
                
                totcol.setCellValueFactory(new PropertyValueFactory<>("total"));
                
           loadtable();
           loadCustomers();
           
           
           customer.textProperty().addListener((observable, oldValue, newValue) -> {
               loadContact();
           });
           
        JFXButton add = new JFXButton();
        //add.setOnAction(e -> addButtonClicked());
        JFXButton delete = new JFXButton();
        
        table.setOnMouseClicked(e -> tableClicked());
        
        
        
        batcht.setVisible(false);
            name.textProperty().addListener((observable, oldValue, newValue) -> {
                
        //loadFields(name.getText());
        loadFields();
        //loadBatch(name.getText());
        batcht.setItems(batchlist);
            System.out.println("Clicked");
                System.out.println("textfield changed from " + oldValue + " to " + newValue);
            });
        
        
            
        
        
        id.setVisible(false);
        batcht.setOnAction(e->{
        //loadFields((String) batcht.getValue());
        float a;
           a=Integer.parseInt(quantity.getText())*pricegbl;
       tot.setText(String.valueOf(a));
        
        });
        
        
        
        /*
       name.setOnMousePressed(e->{
           
           //loadRemCount();
        
        loadFields(name.getText());
            System.out.println("pressed");
            float a;
           a=Integer.parseInt(quantity.getText())*pricegbl;
       tot.setText(String.valueOf(a));
       ///////////
        
        
        
        });    */
       
       quantity.setOnAction((ActionEvent e)->{
       float a;
           a=Integer.parseInt(quantity.getText())*pricegbl;
       tot.setText(String.valueOf(a));
              
       });
	}
	

       
    
    
    private void clearall(){
    name.clear();
        price.clear();
        quantity.clear();
        id.clear();
        tot.clear();
        remain.setText("");
    
    
    }
    
    
    public void loadBatch(String a)
    	{
                batchlist.clear();
		String query="select * from med where `name` = '"+a+"';";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				batchlist.add(rs.getString("batch"));
                                
                                
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
    public void deleteButtonClicked(){
        ObservableList<Bill> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();        
        productSelected.forEach(allProducts::remove);
        String query = "DELETE FROM `bill` WHERE `sino`='"+siino+"'";
        System.out.println(query);
        query2(query, "DELETED");
        
        int pp=olquan+newquan;
        System.out.println("pp = "+pp);
        try{
            
       String query1 = "UPDATE `med` SET `quantity`='"+pp+"' WHERE `sino`='"+siino+"';";
            System.out.println(query1);
        executeSQlQuery(query1, "Updated");
        }catch(Exception e){
            System.err.println(e);
        }
       clearall();
            
        
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
           //fader("FAILED");
       }
   }
    
    
    public void tableClicked(){
        
        
        
        int i= table.getFocusModel().getFocusedIndex();
        siino=dat.get(i).getSino();
        z=i;
        System.out.println(i);
        idd=dat.get(i).getId();
        System.out.println("siino = "+siino);
        s=dat.get(i).getName();
        name.setText(dat.get(i).getName());
        id.setText(valueOf(dat.get(i).getId()));
        quantity.setText(valueOf(dat.get(i).getQuantity()));
        newquan=dat.get(i).getQuantity();
        System.out.println("new Count  "+newquan);
        price.setText(valueOf(dat.get(i).getPrice()));
        float a =dat.get(i).getQuantity()*dat.get(i).getPrice();
        tot.setText(valueOf(a));
        loadcount(siino);
        
        
    }
    
    
    public void loadttData()
    	{
                std.clear();
                //lbl1.setText("Lo moyanth");
		//String query="select * from students";
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
                TextFields.bindAutoCompletion(name, std);
        }
    
    public void loadContact()
    	{
		String query="select * from customers where name='"+customer.getText()+"'";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				contact.setText(String.valueOf(rs.getString("contact")));
                                age.setText(String.valueOf(rs.getInt("age")));
                                address.setText(String.valueOf(rs.getString("address")));
                                
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
        }
    
    public void loaddOC()
    	{
                stddoc.clear();
		String query="select * from doctors";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				stddoc.add(rs.getString("name")  );
                                
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                TextFields.bindAutoCompletion(doctor, stddoc);
        }
    
    private float fla;
    
    public void loadtable()
    	{
            fla=0;
                dat.clear();
                //lbl1.setText("Lo moyanth");
		//String query="select * from students";
		String query="select * from bill";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				dat.add(new Bill(
                                        rs.getString("name"),
                                        rs.getInt("amount") ,
                                        rs.getFloat("rate") ,
                                        rs.getString("exp"),
                                        rs.getString("batch"),
                                        rs.getString("mfr"), 
                                        rs.getFloat("total"),
                                        rs.getInt("id"),
                                        rs.getInt("sino")
                                        
                                )
                                        
                                        
                                
                                );
                                fla+=rs.getFloat("total");
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
    
    
    float gaast;
    int siino;
    
    public void loadFields()
    	{
                std.clear();
                float n;
                //lbl1.setText("Lo moyanth");
		//String query="select * from students";
		//String query="select * from med where `name` = '"+name.getText()+"' AND `batch` = '"+a+"' ;";
                String query="select * from med where `name` = '"+name.getText()+"' ;";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				//price.setText(String.valueOf(rs.getFloat("price")));
                                pricegbl=rs.getFloat("price");
                                siino=rs.getInt("sino");
                                remain.setText(": "+String.valueOf(rs.getInt("quantity")));
                                quantity.setText("1");
                                id.setText(String.valueOf(rs.getInt("id")));
                                gaast=rs.getFloat("gst");
                                n=rs.getFloat("price")/rs.getInt("pack");
                                pricegbl=n;
                                price.setText(String.valueOf(n));
                                tot.setText(String.valueOf(n));
                                //System.out.println("rs.getString(\"cat\").equals(\"TAB\")||rs.getString(\"cat\").equals(\"CAPSULE\") = "+(rs.getString("cat").equals("TAB")||rs.getString("cat").equals("CAPSULE")));
                               /* if(rs.getString("cat").equals("PACK")||rs.getString("cat").equals("PACKET")||rs.getString("cat").equals("packet")||rs.getString("cat").equals("pack"))
                                {n=rs.getFloat("price")/rs.getInt("pack");
                                pricegbl=n;
                                price.setText(String.valueOf(n));
                                tot.setText(String.valueOf(n));
                                }
                                else 
                                {price.setText(String.valueOf(rs.getFloat("price")));
                                pricegbl=rs.getFloat("price");}*/
                                
                                }
                        
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                TextFields.bindAutoCompletion(name, std);
        }
    
    
    
    
    
    
    
    @FXML
    public void goBack(ActionEvent event) {
        
        application.gotoProfile();
        
    }
   
    @FXML
    public void Pay(ActionEvent event) {
        
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        loadTotal();
        
        
        String str="INSERT INTO `billhistory` (`date`, `customer`, `amount` ,`contact` , `doc` , `address`) VALUES ('"+dateFormat.format(date)+"', '"+customer.getText().toUpperCase()+"', '"+totalamt+"' , '"+contact.getText()+"'  , '"+doctor.getText().toUpperCase()+"' ,'"+address.getText().toUpperCase()+"');";
        executeSQlQuery(str, "Inserted");
        
        String str1="INSERT INTO `doctors` (`name`) VALUES ('"+doctor.getText().toUpperCase()+"');";
        executeSQlQuery(str1, "Inserted");
        
        String str2="INSERT INTO `customers` (`contact` , `name` ,`address` ) VALUES ('"+contact.getText()+"','"+customer.getText().toUpperCase()+"' ,'"+address.getText().toUpperCase()+"');";
        executeSQlQuery(str2, "Inserted");
        
        
        
        Jasper jasper = new Jasper();
            try {
                jasper.showReport();
                
            } catch (JRException ex) {
                Logger.getLogger(BillingController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BillingController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(BillingController.class.getName()).log(Level.SEVERE, null, ex);
            }
        //application.gotoPayment();
        
        
    }
    
    public void loadTotal()
    	{
            totalamt=0;
		String query="select * from bill;";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                                totalamt+=rs.getFloat("total");
                                
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
        }
    
     
     
     // Button Insert
    @FXML
    public void QueryIN(ActionEvent evt) {  
        
        flag=0;
        loadMEd();
        loadtable();
        float f=Float.valueOf(quantity.getText());
        float g=Float.valueOf(price.getText());
        float t=f*g;
        
        String query = "INSERT INTO `bill` (`sino` , `name`, `rate`, `amount`, `batch`, `mfr`, `exp` ,`total`,`id` ,`gst`) VALUES  (  '"+siino+"' ,    '"+name.getText()+"','"+price.getText()+"','"+quantity.getText()+"','"+batch+"','"+mfr+"','"+exp+"','"+t+"','"+id.getText()+"' , '"+gaast+"')";
        System.out.println("query = "+query);
        
        executeSQlQuery(query, "Inserted");
        loadcount();
        int ss=Integer.valueOf(quantity.getText());
        int pp=olquan-ss;
        if(flag==1){
        String query1 = "UPDATE `med` SET `quantity`='"+pp+"' WHERE `sino`='"+siino+"';";
        executeSQlQuery(query1, "Updated");
        }
     
       clearall();
    }  
    
    public void loadcount()
    	{
            
		String query1="select * from `med` where `sino` = "+siino+"";
		try
		{
			preparedStatement=conn.prepareStatement(query1);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                            olquan=rs.getInt("quantity");
                            System.out.println("Old count "+olquan);
                                
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                TextFields.bindAutoCompletion(name, std);
        }
    
    public void loadRemCount()
    	{
            
		String query1="select * from `med` where `sino` = "+siino+"";
		try
		{
			preparedStatement=conn.prepareStatement(query1);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                            remain.setText(String.valueOf(rs.getInt("quantity")));
                                
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
        }
    public void loadCustomers()
    	{
            
		String query1="select * from `customers`";
		try
		{
			preparedStatement=conn.prepareStatement(query1);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                            custo.add(rs.getString("name") );
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                TextFields.bindAutoCompletion(customer, custo);
        }
    
public void loadcount(int a)
    	{
            
		String query1="select * from `med` where `sino` = "+a+"";
		try
		{
			preparedStatement=conn.prepareStatement(query1);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                            olquan=rs.getInt("quantity");
                            System.out.println("Old count "+olquan);
                                
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                TextFields.bindAutoCompletion(name, std);
        }
    
    
    public void loadMEd()
    	{
            
		String query1="select * from `med` where `sino` = "+siino+"";
		try
		{
			preparedStatement=conn.prepareStatement(query1);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				batch=rs.getString("batch");
                                mfr=rs.getString("mfr");
                                exp=rs.getString("expiry");
                                
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                TextFields.bindAutoCompletion(name, std);
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
               dat.clear();
               loadtable();
               //table.refresh();
//               loadDatabaseData();
               flag=1;
               System.out.println("Data "+message+" Succefully");
           }else{
               flag=0;
               System.out.println("Data Not "+message);
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }
    

    @FXML
    private void drop(ActionEvent event) {
        
        String qry="truncate bill;";
        executeSQlQuery(qry, "Deleted");
        loadtable();
        
        
        
        
    }
}
