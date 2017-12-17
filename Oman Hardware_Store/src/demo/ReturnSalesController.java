/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import static java.lang.String.valueOf;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class ReturnSalesController implements Initializable {
    
    private Main application;
    @FXML
    private ComboBox<?> batchcomb;
    public void setApp(Main application){
        this.application = application;
    }
    @FXML
    private TableView<Returntable> table;
    @FXML
    private TableColumn<?, ?> namecol;
    @FXML
    private TableColumn<?, ?> pricecol;
    @FXML
    private TableColumn<?, ?> quancol;
    @FXML
    private TableColumn<?, ?> totcol;
    @FXML
    private Label label;
    
    private String str;
    
    private int id,olcount,newcount,hsn;
    
    private float rettot=0;
    
    

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField price;
    @FXML
    private JFXTextField quantity;
    @FXML
    private JFXTextField tot;
    @FXML
    private JFXButton back;
    
    private float pr,gtot=0;

    
    PreparedStatement preparedStatement=null;
	ResultSet rs=null;
        
     ObservableList<Returntable> data=FXCollections.observableArrayList();
     ObservableList bat=FXCollections.observableArrayList();
     
    Connection conn=connector.LoginConnector();
    ObservableList std=FXCollections.observableArrayList();
    ObservableList std1=FXCollections.observableArrayList();
    
    @FXML
    private JFXTextField billno;
    @FXML
    private JFXTextField cust;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
		namecol.setCellValueFactory(new PropertyValueFactory<>("name"));                
                pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
                quancol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                
		totcol.setCellValueFactory(new PropertyValueFactory<>("total"));
        loadtable();
        loadMed();
        loadBill();
        batchcomb.setEditable(false);
        
        table.setOnMouseClicked(e -> tableClicked());
        
        billno.textProperty().addListener((observable, oldValue, newValue) -> loadName());
        
        name.textProperty().addListener((observable, oldValue, newValue) -> {
                            loadBatch();});
        
        quantity.setOnAction(e->{
        
        float a=Float.valueOf(quantity.getText());
        float p= pr*a;
        tot.setText(String.valueOf(p));
        
        });
        
        batchcomb.setOnAction(e->{
        loadPrice();
        });
        // TODO
    }    
    
    
    public void loadBill()
    	{
                std.clear();
                //lbl1.setText("Lo moyanth");
		//String query="select * from students";
		String query="select * from billhistory";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				std.add(rs.getInt("billno")  );
                                
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                TextFields.bindAutoCompletion(billno, std);
        }
    public void loadBatch()
    	{
                std.clear();
                //lbl1.setText("Lo moyanth");
		//String query="select * from students";
		String query="select * from med where name ='"+name.getText()+"'";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				bat.add(rs.getString("batch")  );
                                
                                }
                        batchcomb.setItems(bat);
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
        }
    
    public void loadName()
    	{
		String query="select * from billhistory where billno = "+billno.getText()+"";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				cust.setText(rs.getString("customer")  );
                                
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
        }
    
    
    public void loadMed()
    	{
                std1.clear();
		String query="SELECT DISTINCT name FROM med";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				std1.add(rs.getString("name")  );
                                
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                TextFields.bindAutoCompletion(name, std1);
        }
    
    public void loadPrice()
    	{
		String query="select * from med where name = '"+name.getText()+"' AND  `batch`= '"+batchcomb.getValue()+"' ";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                                pr=rs.getFloat("price") ;
				price.setText(String.valueOf(rs.getFloat("price")  ));
                                quantity.setText("1");
                                tot.setText(String.valueOf(rs.getFloat("price")));
                                
                                }
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
               data.clear();
               loadtable();
               
               System.out.println("Data "+message+" Succefully");
           }else{
               System.out.println("Data Not "+message);
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }
     
      
    public void loadtable()
    	{
                data.clear();
		String query="select * from return";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				data.add(new Returntable(
                                        rs.getString("name"),
                                        rs.getFloat("price") ,
                                        rs.getInt("quantity") ,
                                        rs.getFloat("total")
                                        
                                )
                                        
                                
                                );
                                table.setItems(data);
                                gtot+=rs.getFloat("total");
                                //label.setText("Grand Total = "+gtot);
                                
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
        str=data.get(i).getName();
        newcount=data.get(i).getQuantity();
        System.out.println("New count = "+newcount);
        
    }
    
    
    
    

    @FXML
    private void goBack(ActionEvent event) {
        
        application.gotoProfile();
        
        
        
    }

    @FXML
    private void add(ActionEvent event) {
        
        float f=Float.valueOf(price.getText());
        float t=Float.valueOf(quantity.getText());
        float a=f*t;
        String query = "INSERT INTO `return` (`name`, `price`, `quantity`, `total`) VALUES ('"+name.getText()+"', '"+price.getText()+"', '"+quantity.getText()+"', '"+a+"');";
        executeSQlQuery(query, "Inserted");
        loadrettot();
        loadcount();
        int neww = Integer.valueOf(quantity.getText());
        
        int pp=olcount+neww;
        System.out.println("pp = "+pp);
        
       String query1 = "UPDATE `med` SET `quantity`='"+pp+"' WHERE `name`='"+name.getText()+"' and `batch` ='"+batchcomb.getValue()+"'; ";
       System.out.println(query1);
       executeSQlQuery(query1, "Updated");
    }
    
    
    public void loadcount()
    	{
            
		String query1="select * from `med` where `name` = '"+name.getText()+"' and `batch` ='"+batchcomb.getValue()+"';";
		try
		{
			preparedStatement=conn.prepareStatement(query1);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                            olcount=rs.getInt("quantity");
                            System.out.println("Old count = "+olcount);
                                
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
    
     public void loadcount(String s)
    	{
            
		String query1="select * from `med` where `name` = '"+s+"'";
		try
		{
			preparedStatement=conn.prepareStatement(query1);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                            olcount=rs.getInt("quantity");
                            System.out.println("Old count = "+olcount);
                                
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
    private void delete(ActionEvent event) {
        
            System.out.println("focused");
        ObservableList<Returntable> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();        
        productSelected.forEach(allProducts::remove);
        String query = "DELETE FROM `return` WHERE `name`='"+str+"'";
        System.out.println(query);
        query2(query, "DELETED");
        loadrettot();
        loadcount(str);
        int pp=olcount-newcount;
        System.out.println("delete pp = "+pp);
        
            loadtable();
       String query1 = "UPDATE `med` SET `quantity`='"+pp+"' WHERE `name`='"+str+"';";
            System.out.println(query1);
        executeSQlQuery(query1, "Updated");
        
        
        
        
        
    }
    
    public void query2(String query, String message)
    {
       Statement st;
       try{
           st = conn.createStatement();
           if((st.executeUpdate(query)) == 1)
           {
               
               data.clear();
               loadtable();
           }else{
               
           }
       }catch(Exception ex){
           ex.printStackTrace();
           //fader("FAILED");
       }
   }

    @FXML
    private void print(ActionEvent event) {
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        
        loadrettot();
        try{
        String query= "INSERT INTO `returnhistory` (`billno`, `amount`, `date`, `customer`) VALUES ('"+billno.getText()+"', '"+rettot+"', '"+dateFormat.format(date)+"', '"+cust.getText()+"');";
        executeSQlQuery(query, "Inserted");
        label.setText("Succesful");
        String qry="truncate return;";
        executeSQlQuery(qry, "Deleted");
        loadtable();
        application.gotoProfile();
        
        }
        catch(Exception e){
            
        
        
        
        }
        
        
        }
        
        
        
    
    
     public void loadrettot()
    	{
            rettot=0;
		String query1="select * from `return` ";
		try
		{
			preparedStatement=conn.prepareStatement(query1);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                            rettot+=rs.getFloat("total");
                            label.setText("Total = "+String.valueOf(rettot));
                                
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
}
