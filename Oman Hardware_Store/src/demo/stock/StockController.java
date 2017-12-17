/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.stock;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import demo.ConfirmController;
import demo.Items;
import demo.Main;
import demo.Medicine;
import demo.connector;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.TextFields;



/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class StockController implements Initializable {
    
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     double width = screenSize.getWidth();
     double height = screenSize.getHeight();
     
     int naami;
     
     ObservableList gstcombo = FXCollections.observableArrayList("0","5","12","18","28");
    
    
    Connection conn=connector.LoginConnector();
    PreparedStatement preparedStatement=null;
	ResultSet rs=null;
    private Main application;
    
    private int ide;
    
    ObservableList<Medicine> data=FXCollections.observableArrayList();
    
    ObservableList autoname=FXCollections.observableArrayList();
    ObservableList autonameofcontent=FXCollections.observableArrayList();
    ObservableList automanufact=FXCollections.observableArrayList();
    ObservableList autobatch=FXCollections.observableArrayList();
    

    @FXML
    private Label lbl;

    @FXML
    private JFXButton back;

    @FXML
    private TableView<Medicine> table;
    
    
    


    @FXML
    private JFXToggleButton toggle;

    @FXML
    private JFXButton cancel;

    @FXML
    private JFXButton save;

    @FXML
    private JFXButton add;

    @FXML
    private JFXButton edit;
    
    private int b;
    
    @FXML
    private JFXButton delete;
    
    @FXML
    private JFXButton load;
    
    @FXML
    private JFXButton exit;

    private StackPane daddy;
    @FXML
    private TextField name;
    @FXML
    private TextField id;
    @FXML
    private TextField price;
    @FXML
    private TextField quantity;
    @FXML
    private TextField batch;
    @FXML
    private DatePicker expiry;
    @FXML
    private TableColumn<?, ?> namecol;
    @FXML
    private TableColumn<?, ?> pricecol;
    @FXML
    private TableColumn<?, ?> quancol;
    @FXML
    private TableColumn<?, ?> idcol;
    private TableColumn<?, ?> batchcol;
    @FXML
    private TextField mfr;
    @FXML
    private AnchorPane mainpane;
    @FXML
    private TextField content;
    private TableColumn<?, ?> mfrcol;
    @FXML
    private TextField rate;
    @FXML
    private ComboBox<Float> gst;
    
    private TableColumn<?, ?> margin;
    @FXML
    private TableColumn<?, ?> ratecol;
    @FXML
    private TableColumn<Float, Float> margincol;

    

    
    public void setApp(Main application){
        this.application = application;
    }
    @FXML
    public void goBack(ActionEvent event) {
        
        application.gotoProfile();
        
    }
 

    
    
    @FXML
    public void save(ActionEvent event) throws IOException {
    Stage stage = new Stage();
    Parent root;
        root = FXMLLoader.load(ConfirmController.class.getResource("stock/StockConfirm.fxml"));
    stage.setScene(new Scene(root));
    stage.setTitle("My modal window");
    stage.initModality(Modality.WINDOW_MODAL);
    stage.initOwner(
        ((Node)event.getSource()).getScene().getWindow() );
    stage.show();
}
    
    public void toggle(ActionEvent event) {
        
        add.setDisable(false);
           edit.setDisable(false);
                delete.setDisable(false);
        
    }
    
    @FXML
    public void exitDialog(ActionEvent event) {
         
        
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Hello Boys..!"));
        content.setBody(new Text ("BABES AND BOYS..!"));
        content.setActions();
        
        
        JFXDialog dialogue = new JFXDialog( daddy , new Label("hihihii"), JFXDialog.DialogTransition.CENTER);
        
        dialogue.show();
    }
    
    
    
    
    String pattern = "dd-MM-yyyy";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        content.setVisible(false);
        id.setVisible(false);
        batch.setVisible(false);
        
        
        gst.setItems(gstcombo);
        gst.setVisible(false);
        expiry.setVisible(false);
        mainpane.setMaxSize(width, height);
        System.out.println("mainpane.setMaxSize(width, height); = "+width+" ,"+height);
        
        AutoComplete();
        
        
                idcol.setCellValueFactory(new PropertyValueFactory<>("mfr"));
		namecol.setCellValueFactory(new PropertyValueFactory<>("name"));                               
                margincol.setCellValueFactory(new PropertyValueFactory<>("margin"));
                pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
                quancol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                ratecol.setCellValueFactory(new PropertyValueFactory<>("rate"));
                //gstcol.setCellValueFactory(new PropertyValueFactory<>("gst"));
                
                
                loadDatabaseData();
   
        name.setEditable(false);
         id.setEditable(false);
          price.setEditable(false);
           quantity.setEditable(false);
           batch.setEditable(false);
           expiry.setEditable(false);
           mfr.setEditable(false);
           content.setEditable(false);
           rate.setEditable(false);
           gst.setEditable(false);
           
           load.setVisible(false);
           
           
           add.setDisable(true);
           edit.setDisable(true);
           delete.setDisable(true);
           
           toggle.setOnAction( e -> toggler());
           
           expiry.setConverter(new StringConverter<LocalDate>() {
             DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

             @Override 
             public String toString(LocalDate date) {
                 if (date != null) {
                     return dateFormatter.format(date);
                 } else {
                     return "";
                 }
             }

     @Override 
     public LocalDate fromString(String string) {
         if (string != null && !string.isEmpty()) {
             return LocalDate.parse(string, dateFormatter);
         } else {
             return null;
         }
     }
 });
           
           
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                
                        
                        int i= (int)table.getFocusModel().getFocusedIndex();
                        b=data.get(i).getId();
                        System.out.println(b);
            
                        }});
        
        delete.setOnAction(e->{
            System.out.println("id = "+b);
        deleteButton1Clicked(ide);
        
        
        });
        table.setOnMouseClicked(e->tableClicked());
        
        // TODO
    } 
    
    public void AutoComplete()
    	{
                autoname.clear();
                autobatch.clear();
                automanufact.clear();
                autonameofcontent.clear();
                
		String namec="SELECT DISTINCT name FROM med;";
                String batchc="SELECT DISTINCT batch FROM med;";
                String manuf="SELECT DISTINCT mfr FROM med;";
                String nameco="SELECT DISTINCT name FROM med;";
                
		try
		{
			preparedStatement=conn.prepareStatement(namec);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				autoname.add(rs.getString("name")  );
                                
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                try
		{
			preparedStatement=conn.prepareStatement(batchc);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				autobatch.add(rs.getString("batch")  );
                                
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}try
		{
			preparedStatement=conn.prepareStatement(manuf);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				automanufact.add(rs.getString("mfr")  );
                                
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}try
		{
			preparedStatement=conn.prepareStatement(nameco);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				autonameofcontent.add(rs.getString("name")  );
                                
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                TextFields.bindAutoCompletion(name, autoname);
                TextFields.bindAutoCompletion(content, autonameofcontent);
                TextFields.bindAutoCompletion(mfr, automanufact);
                TextFields.bindAutoCompletion(batch, autobatch);
                
        }
    
    
    
    public void tableClicked(){
        
        
        
        int i= table.getFocusModel().getFocusedIndex();
        System.out.println(i);
        
        name.setText(data.get(i).getName());
        naami=data.get(i).getSino();
        id.setText(valueOf(data.get(i).getId()));
        ide=data.get(i).getId();
        quantity.setText(valueOf(data.get(i).getQuantity()));
        price.setText(valueOf(data.get(i).getPrice()));        
        batch.setText(valueOf(data.get(i).getBatch()));
        rate.setText(valueOf(data.get(i).getRate()));
        System.out.println("data.get(i).getRate() "+data.get(i).getRate());
        content.setText(data.get(i).getContentname());
        System.out.println("content.setText(data.get(i).getContentname()); = "+data.get(i).getContentname());
                
                mfr.setText(data.get(i).getMfr());
                gst.setValue(data.get(i).getGst());
                System.out.println("gst.setValue(data.get(i).getGst()); "+data.get(i).getGst());
        
        
    }
    
    public void toggler(){
        
        if(toggle.isSelected()==true)
        {
            add.setDisable(false);
           edit.setDisable(false);
                delete.setDisable(false);
            }
        else 
        {
            add.setDisable(true);
           edit.setDisable(true);
                delete.setDisable(true);
                
               name.setEditable(false);
         id.setEditable(false);
          price.setEditable(false);
           quantity.setEditable(false);
           batch.setEditable(false);
           expiry.setEditable(false);
           mfr.setEditable(false);
           content.setEditable(false);
           rate.setEditable(false);
           gst.setEditable(false);
           
           load.setVisible(false);
           
            }
        
        
    }
    
    
    
     // Button Insert
    public void QueryIN(ActionEvent evt) {  
    }                                              


 // Button Update
    public void QueryUP(ActionEvent evt) { 
        
        
       String query = "UPDATE `med`  SET `name`='"+name.getText()+"',`price`='"+price.getText()+"',`quantity`="+quantity.getText()+"  ,`price`="+price.getText()+"  ,`rate`="+rate.getText()+"  ,`gst`="+gst.getValue()+"   WHERE `id` = "+id.getText();
       //UPDATE `albaha`.`items` SET `name`='asd', `price`='23', `quantity`='234' WHERE `id_ITEMS`='54';


       executeSQlQuery(query, "Updated");
       name.clear();
        price.clear();
        quantity.clear();
        id.clear();
    }            
    DateFormat dateFormat = new SimpleDateFormat("MMM-yy");
    DecimalFormat df = new DecimalFormat("#.00"); 

    
	public void loadDatabaseData()
	{
                data.clear();
                NumberFormat nf = NumberFormat.getInstance();

                nf.setMinimumFractionDigits(2);
                nf.setMaximumFractionDigits(2);
		String query="select * from `med`";
		          System.out.println("hihi");
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                            //String s=dateFormat.format(rs.getDate("expiry"));
                            float perc;
                            float diff=rs.getFloat("price")-rs.getFloat("rate");
                            perc=diff/rs.getFloat("price");
                            System.out.println("prece = "+perc*100);
				data.add(new Medicine(
						rs.getInt("id"),
						rs.getString("name"),
                                                rs.getInt("quantity"),                                        
                                                rs.getFloat("price"),                                        
                                                rs.getString("batch"),
                                                rs.getString("mfr"),
						rs.getString("contentname"),
                                                rs.getInt("sino"),
                                        rs.getFloat("rate"),
                                        rs.getFloat("gst"),
                                        Float.valueOf(nf.format(perc*100))
                                        
                                                
						));
				table.setItems(data);
			}
			preparedStatement.close();
			rs.close();
		}
		catch(Exception e)
		{
                    System.out.println("are we here?");
                    System.err.println(e);
		}
	}



 // Button Delete
   /* private void QueryDEL(ActionEvent evt) {                                               
        String query = "DELETE FROM `users` WHERE id = "+jTextField_Id.getText();
         executeSQlQuery(query, "Deleted");
    }         
     */                                                               
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
               //table.refresh();
               loadDatabaseData();
               
               System.out.println("Data "+message+" Succefully");
               clearall();
               
           }else{
               System.out.println("Data Not "+message);
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }
    
    
     public void deleteButton1Clicked(int s){
        ObservableList<Medicine> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();
        
        productSelected.forEach(allProducts::remove);
        
        String query = "DELETE FROM `med` WHERE `id`='"+s+"'";
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
               
               data.clear();
               loadDatabaseData();
               
               //status.setText(message);
               //fader(status);
              
           }else{
               
           }
       }catch(Exception ex){
           ex.printStackTrace();
           //fader("FAILED");
       }
   }
    
    
    
    
    /////////////////////////////////
    
    
    private void clearall(){
    name.clear();
        price.clear();
        quantity.clear();
        id.clear();
        batch.clear();
        //expiry.clear();
        mfr.clear();
        content.clear();
        rate.clear();
    
    
    }
    
    
    
    
    
    
    
    ////////////////////////

    @FXML
    private void adder(ActionEvent event) {
        clearall();
        
        
      name.setEditable(true);
         id.setEditable(true);
          price.setEditable(true);
           quantity.setEditable(true);
           batch.setEditable(true);
           expiry.setEditable(true);
           mfr.setEditable(true);
           content.setEditable(true);
           rate.setEditable(true);
           
           load.setVisible(true);
           
           load.setText("Add");
                                             
    }

    @FXML
    private void loader(ActionEvent event) {
                
        if(load.getText().equals("Add"))
        {String query = "INSERT INTO `med` (`id`, `name`, `price`, `quantity`,`batch`, `mfr` ,`gst` ,`rate`) VALUES  ('"+id.getText()+"','"+name.getText()+"','"+price.getText()+"','"+quantity.getText()+"','"+batch.getText()+"','"+mfr.getText()+"','"+gst.getValue()+"' , '"+rate.getText()+"')";
        
        executeSQlQuery(query, "Inserted");
        loadDatabaseData();
        }
        else
        { String qry="UPDATE `med` SET `id`='"+id.getText()+"', `name`='"+name.getText()+"', `batch`='"+batch.getText()+"', `quantity`='"+quantity.getText()+"', `price`='"+price.getText()+"', `mfr`='"+mfr.getText()+"' , `gst`='"+gst.getValue()+"', `rate`='"+rate.getText()+"', `contentname`='"+content.getText()+"' WHERE `sino`='"+naami+"';";
          executeSQlQuery(qry, "Updated");
        
        loadDatabaseData();
        
        }
        }

    @FXML
    private void editor(ActionEvent event) {
        
       name.setEditable(true);
         id.setEditable(true);
          price.setEditable(true);
           quantity.setEditable(true);
           batch.setEditable(true);
           expiry.setEditable(true);
           mfr.setEditable(true);
           content.setEditable(true);
           rate.setEditable(true);
           
           load.setVisible(true);
           
           load.setText("Edit");
    }

    private void canceller(ActionEvent event) {
        System.out.println("Prefh = "+content.getScene().getHeight()+" width = "+content.getScene().getWidth());
        
    }
    
    
}
