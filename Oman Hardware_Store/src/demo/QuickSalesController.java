/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import com.jfoenix.controls.JFXButton;
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
public class QuickSalesController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField quantity;
    @FXML
    private TextField price;
    @FXML
    private TextField id;
    @FXML
    private TextField tot;
    @FXML
    private JFXButton add;
    @FXML
    private TableView<Bill> table;
    @FXML
    private TableColumn<?, ?> namecol;
    @FXML
    private TableColumn<?, ?> batchcol;
    @FXML
    private TableColumn<?, ?> mfrcol;
    @FXML
    private TableColumn<?, ?> expcol;
    @FXML
    private TableColumn<?, ?> pricecol;
    @FXML
    private TableColumn<?, ?> quancol;
    @FXML
    private TableColumn<?, ?> totcol;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton back;
    
    Connection conn=connector.LoginConnector();
    PreparedStatement preparedStatement=null;
    ResultSet rs=null;
    
    
    ObservableList std=FXCollections.observableArrayList();
    
    ObservableList batchlist=FXCollections.observableArrayList();
    
    ObservableList<Bill> dat=FXCollections.observableArrayList();
    
    private String exp,mfr,batch,s;
    private int idd,newquan,z;
    private int olquan,flag;
    private float pricegbl;
    
    
    
    
    private Main application;
    @FXML
    private ComboBox<?> batcht;
    @FXML
    private Label remain;
    @FXML
    private Label blbl;
    
    public void setApp(Main application){
        this.application = application;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        id.setVisible(false);

                
		namecol.setCellValueFactory(new PropertyValueFactory<>("name"));                
                pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
                quancol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                
		batchcol.setCellValueFactory(new PropertyValueFactory<>("batch"));                
                expcol.setCellValueFactory(new PropertyValueFactory<>("expiry"));
                mfrcol.setCellValueFactory(new PropertyValueFactory<>("mfr"));
                
                totcol.setCellValueFactory(new PropertyValueFactory<>("total"));        
                
                
                loadtable();
             loadttData();
             remain.setText("");
             blbl.setText("");
             
             
        table.setOnMouseClicked(e -> tableClicked());
        
        name.textProperty().addListener((observable, oldValue, newValue) -> {
        
        //loadFields(name.getText());
        loadBatch(name.getText());
        batcht.setItems(batchlist);
        blbl.setText("*SELECT A BATCH");
            System.out.println("Clicked");
            
        
        
        });
        
        batcht.setOnAction(e->{
            blbl.setText("");
        loadFields((String) batcht.getValue());
        float a;
           a=Integer.parseInt(quantity.getText())*pricegbl;
       tot.setText(String.valueOf(a));
        
        });
        
        quantity.setOnAction((ActionEvent e)->{
       float a;
           a=Integer.parseInt(quantity.getText())*pricegbl;
       tot.setText(String.valueOf(a));
              
       });
        // TODO
    }   
    
    
    
    public void loadFields(String a)
    	{
                std.clear();
                //lbl1.setText("Lo moyanth");
		//String query="select * from students";
		String query="select * from med where `name` = '"+name.getText()+"' AND `batch` = '"+a+"' ;";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				price.setText(String.valueOf(rs.getFloat("price")));
                                pricegbl=rs.getFloat("price");
                                remain.setText("Remaining Quantity : "+String.valueOf(rs.getInt("quantity")));
                                quantity.setText("1");
                                id.setText(String.valueOf(rs.getInt("id")));
                                
                                
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
    
    
    
    public void loadMEd()
    	{
            
		String query1="select * from `med` where `id` = "+id.getText()+"";
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
    
    
    public void loadtable()
    	{
                dat.clear();
                //lbl1.setText("Lo moyanth");
		//String query="select * from students";
		String query="select * from quickbill";
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
    private int siino;
    
    public void tableClicked(){
        
        
        
        int i= table.getFocusModel().getFocusedIndex();
        z=i;
        siino=dat.get(i).getSino();
        System.out.println(i);
        idd=dat.get(i).getId();
        System.out.println("id = "+idd);
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
    
    
    
    


    @FXML
    public void QueryIN(ActionEvent evt) {  
        
        flag=0;
        loadMEd();
        loadtable();
        float f=Float.valueOf(quantity.getText());
        float g=Float.valueOf(price.getText());
        float t=f*g;
        
        String query = "INSERT INTO `quickbill` (`name`, `rate`, `amount`, `batch`, `mfr`, `exp` ,`total`,`id` ) VALUES  ('"+name.getText()+"','"+price.getText()+"','"+quantity.getText()+"','"+batch+"','"+mfr+"','"+exp+"','"+t+"','"+id.getText()+"')";
        
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
    
    
    private void clearall(){
    name.clear();
        price.clear();
        quantity.clear();
        id.clear();
        tot.clear();
        remain.setText("");
        batchlist.clear();
    
    
    }

    
    
    public void loadcount()
    	{
            
		String query1="select * from `med` where `id` = "+id.getText()+"";
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
    

    
    @FXML
    public void deleteButtonClicked(){
        ObservableList<Bill> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();        
        productSelected.forEach(allProducts::remove);
        String query = "DELETE FROM `quickbill` WHERE `name`='"+s+"'";
        System.out.println(query);
        query2(query, "DELETED");
        
        int pp=olquan+newquan;
        System.out.println("pp = "+pp);
        try{
            
       String query1 = "UPDATE `med` SET `quantity`='"+pp+"' WHERE `id`='"+idd+"';";
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
      
    @FXML
    private void goBack(ActionEvent event) {
        application.gotoProfile();
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
    
}
