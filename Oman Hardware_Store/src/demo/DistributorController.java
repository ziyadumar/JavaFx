/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import demo.Classes.BankTransVar;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import static java.util.UUID.fromString;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.util.StringConverter;
import static org.codehaus.groovy.ast.tools.GeneralUtils.stmt;
import org.controlsfx.control.textfield.TextFields;
import java.util.*;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class DistributorController implements Initializable {

    
    @FXML
    private TextField mfr;
    @FXML
    private TextField name;
    @FXML
    private TextField content;
    @FXML
    private TextField id;
    @FXML
    private TextField batch;
    private DatePicker expiry;
    @FXML
    private TextField quantity;
    @FXML
    private TextField price;
    @FXML
    private JFXButton load;
    @FXML
    private TableView<Medicine> table;
    @FXML
    private TableColumn<?, ?> namecol;
    private TableColumn<?, ?> idcol;
    private TableColumn<?, ?> expcol;
    @FXML
    private TableColumn<?, ?> quancol;
    @FXML
    private TableColumn<?, ?> pricecol;
    
    private int sino,lastoneaaa;;

    /**
     * Initializes the controller class.
     */
    
    private Main application;
    
    
    public void setApp(Main application){
        this.application = application;
    }
    
    @FXML
    private ComboBox<String> modecomb;
    @FXML
    private TextField totalam;
    @FXML
    private TextField payedm;
    private TextField free;
    @FXML
    private Label totalamt;
    @FXML
    private Label totalamount;
    @FXML
    private TextField dln1;
    @FXML
    private TextField dln2;
    @FXML
    private TextField adrs;
    @FXML
    private TextField gstin;
    @FXML
    private JFXButton invoicer;
    private TableColumn<?, ?> freecol;
    @FXML
    private TextField categ;
    @FXML
    private TextField pack;
    @FXML
    private TextField expiryt;
    @FXML
    private Label lsl;
    @FXML
    private Label lslbl;
    @FXML
    private JFXButton back1;
    @FXML
    private JFXButton delete1;
    @FXML
    private JFXToggleButton packtogg;
    @FXML
    private AnchorPane mother;
    @FXML
    private TextField invoicetoimport;
    @FXML
    private Button openfile;
    @FXML
    private Label pathlabel;
    @FXML
    private JFXButton excelimp;
    @FXML
    private AnchorPane lowerpane;
    @FXML
    private JFXButton closer;
    @FXML
    private AnchorPane upperpane;
    
    
    
    
    ObservableList autoname=FXCollections.observableArrayList();
    ObservableList autonameofcontent=FXCollections.observableArrayList();
    ObservableList automanufact=FXCollections.observableArrayList();
    ObservableList autobatch=FXCollections.observableArrayList();
    ObservableList autodist=FXCollections.observableArrayList();
    ObservableList autoinvo=FXCollections.observableArrayList();
    
    ObservableList autoid=FXCollections.observableArrayList();
    
    ObservableList gstcombo = FXCollections.observableArrayList("0","5","12","18","28");
    
    ObservableList modecombo = FXCollections.observableArrayList("Credit","Cash","Cheque");
    
    ObservableList catergories =FXCollections.observableArrayList();
    
    ObservableList<Medicine> data=FXCollections.observableArrayList();
    
    Connection conn=connector.LoginConnector();
    
    PreparedStatement preparedStatement=null;
    PreparedStatement preparedStatement1=null;
    ResultSet rs=null;
    PreparedStatement preparedStatement3=null;
    ResultSet rs3=null;
    
    
    Statement stmt = null;
        
    @FXML
    private TextField invoiceno;
    @FXML
    private DatePicker date;
    @FXML
    private Label distlbl;
    @FXML
    private Label invoicelbl;
    @FXML
    private Label datelbl;
    @FXML
    private TextField distrib;
    @FXML
    private ComboBox<String> gst;
    @FXML
    private TextField rate;
    @FXML
    private TableColumn<?, ?> ratecol;
    private TableColumn<?, ?> gstcol;
    @FXML
    private TableColumn<?, ?> totcol;
    
    private int  packq;
    
    String pathanem;
    
    private StackPane sp ;
        String pattern = "dd-MM-yyyy";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        content.setVisible(false);
        id.setVisible(false);
        batch.setVisible(false);
        categ.setVisible(false);
        
        lowerpane.setTranslateY(200);
        upperpane.setTranslateY(-1000);
        
        excelimp.setOnAction(e->{
        lowerpane.setTranslateY(0);
        upperpane.setTranslateY(0);
        });
        
        closer.setOnAction(e->{
        
        lowerpane.setTranslateY(200);
        upperpane.setTranslateY(-1000);
        
        });
        
        
        
        mother.getChildren().remove(sp);
        
        final FileChooser fileChooser = new FileChooser();
        
        sp = new StackPane();
        gstin.setVisible(false);
        gst.setVisible(false);
        lsl.setText("");
        lslbl.setText("");        
        distlbl.setText("");
        invoicelbl.setText("");
        datelbl.setText("");
        totalamount.setText("");
        
        pack.setText("1");
        pack.setEditable(false);
        dln1.setVisible(false);
        dln2.setVisible(false);
        
        packq=1;
        packtogg.setOnAction(e->{
        if(packtogg.isSelected())
        {pack.setEditable(true);}
        else
        {packq=1;
        pack.setText("1");
        pack.setEditable(false);}
        
        });
        
        pack.textProperty().addListener((observable, oldValue, newValue) -> {
          packq=Integer.valueOf(pack.getText());  
          if(packq==0)
              packq=1;
        });
        
        invoiceno.textProperty().addListener((observable, oldValue, newValue) -> {
            
            invoicelbl.setText(invoiceno.getText());
            loadInvHist();
        loadTable();
        });
        
        
                //categ.setItems(catergories);
                gst.setItems(gstcombo);
                modecomb.setItems(modecombo);
                
                //idcol.setCellValueFactory(new PropertyValueFactory<>("id")); 
                namecol.setCellValueFactory(new PropertyValueFactory<>("name"));                
                pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
                quancol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                
                //con.setCellValueFactory(new PropertyValueFactory<>("batch"));                
                //gstcol.setCellValueFactory(new PropertyValueFactory<>("gst"));
                ratecol.setCellValueFactory(new PropertyValueFactory<>("rate"));
                
                totcol.setCellValueFactory(new PropertyValueFactory<>("total"));
                
                table.setOnMouseClicked(e->tableClicked());
                
                
        
        
        AutoComplete();
        totalamt.setText("");
        
        loadCat();
        
        modecomb.setOnAction(e->{
        if(modecomb.getValue().equals("Cheque"))
        {
            /*GaussianBlur blurEffect = new GaussianBlur(5);
            mother.setEffect(blurEffect);*/
            java.util.Date datea = java.sql.Date.valueOf(date.getValue());
            BankTransVar.setDate(datea);
            BankTransVar.setInvoice(invoiceno.getText());
            
            application.gotoChooseBank();
        }
            
        
        });
        
        
        final Stage stage = new Stage();
        
        openfile.setOnAction(
            new EventHandler<ActionEvent>() {
                
            
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        System.out.println(file.getAbsoluteFile());
                        pathlabel.setText("FILE PATH : "+String.valueOf(file.getAbsoluteFile()));
                        pathanem=String.valueOf(file.getAbsoluteFile());
                        //openFile(file);
                    }
                }
            });
        
        
        expiryt.setVisible(false);
        date.setPromptText(pattern.toLowerCase());

        date.setConverter(new StringConverter<LocalDate>() {
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
        
      
        
        // TODO
        
        distrib.setOnAction(e->{
        loaddistdetails();
        
        });
        
        
        
        
    }   
    
    
    private void importe(String path){
        
        
        try {
            String query ="INSERT INTO `med` (`name`, `mfr` ,`quantity`, `rate` , `price`, `pack`) VALUES (?,?,?,?,?,?);";
            preparedStatement = conn.prepareStatement(query);
            
            String query1 ="INSERT INTO `distlog` (`sino`, `name`, `mfr`, `quantity`,  `rate` ,`price`, `invoice` ,`total`) VALUES (?,?,?,?,?,?,?,?);";
            preparedStatement1 = conn.prepareStatement(query1);
            
            FileInputStream filein = new FileInputStream(new File(path));
            
            XSSFWorkbook wb = new XSSFWorkbook(filein);
            XSSFSheet sheet = wb.getSheetAt(0);
            Row row;
            System.out.println("Last column Number = "+sheet.getLastRowNum());
            
            for(int i=1;i<=sheet.getLastRowNum();i++){
                
                row = sheet.getRow(i);
                
                preparedStatement.setString(1,  row.getCell(1).getStringCellValue()    );//roll
                preparedStatement.setString(2,  row.getCell(2).getStringCellValue()    );
                preparedStatement.setInt(3, (int)row.getCell(3).getNumericCellValue()  );//name
                preparedStatement.setFloat(4,  (float)row.getCell(4).getNumericCellValue()   );//series1
                preparedStatement.setFloat(5,  (float)row.getCell(5).getNumericCellValue() );//ser 2
                preparedStatement.setInt(6,  1 );//ser 2
                
                
                
                if(!invoicetoimport.getText().isEmpty()){
                
                preparedStatement.execute();
                
                preparedStatement1.setInt(1, lastOne()   );//roll
                preparedStatement1.setString(2,  row.getCell(1).getStringCellValue()   );//name
                preparedStatement1.setString(3,  row.getCell(2).getStringCellValue()   );
                preparedStatement1.setInt(4,  (int)row.getCell(3).getNumericCellValue()   );//series1
                preparedStatement1.setFloat(5,  (float)row.getCell(4).getNumericCellValue() );//ser 2
                preparedStatement1.setFloat(6,  (float)row.getCell(5).getNumericCellValue() );//ser 2
                preparedStatement1.setString(7,  invoicetoimport.getText()   );
                preparedStatement1.setFloat(8,  (float)row.getCell(6).getNumericCellValue() );//ser 2
                preparedStatement1.execute();
                }
                
                else{
                pathlabel.setText("Select a Batch to Import!");
                
                }

                
            }
            
            loadTable();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Succesfully Imported");
            alert.showAndWait();
            //loadttData();
            wb.close();
            filein.close();
            preparedStatement.close();
            rs.close();
            
        } catch (SQLException ex) {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Failed");
            alert.showAndWait();
            
            loadTable();
            Logger.getLogger(DistributorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DistributorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    
    
    
    }
    
    public void loadInvHist()
	{
                //data.clear();
		String query="select * from `dtab` where `invoice` ='"+invoiceno.getText()+"'";
		          System.out.println("hihi");
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			if(rs.next())
			{
                                                distrib.setText(rs.getString("name"));
                                                adrs.setText(rs.getString("address"));                                        
                                                dln1.setText(rs.getString("dl1")); 
                                                dln2.setText(rs.getString("dl2")); 
                                                gstin.setText(rs.getString("gstin")); 
                                                totalam.setText(String.valueOf(rs.getFloat("total")));
                                                payedm.setText(String.valueOf(rs.getFloat("given")));
                                                modecomb.setValue((rs.getString("modeofpay")));
                                                date.setValue((rs.getDate("date").toLocalDate()));
			}
                        else{
                        
                        distrib.setText("");
                                                adrs.setText("");                            
                                                dln1.setText("");
                                                dln2.setText("");
                                                gstin.setText("");
                                                totalam.setText("");
                                                payedm.setText("");
                                                modecomb.setValue("");
                                                date.setValue(LocalDate.now());
                        
                        
                        
                        }
                        
			preparedStatement.close();
			rs.close();
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
    
    
    
    
    
    
    public void loadCat()
	{
                
		String query ="SELECT distinct categ FROM category;";
                
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                                catergories.add(rs.getString("categ"));
                                        
                                        }
                        
			preparedStatement.close();
			rs.close();
                        TextFields.bindAutoCompletion(categ, catergories);
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
    
    
    public void loaddistdetails()
	{
                //data.clear();
		String query="select * from `dtab` where `name` ='"+distrib.getText()+"'";
		          System.out.println("hihi");
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                                                adrs.setText(rs.getString("address"));                                        
                                                dln1.setText(rs.getString("dl1")); 
                                                dln2.setText(rs.getString("dl2")); 
                                                gstin.setText(rs.getString("gstin")); 
			}
			preparedStatement.close();
			rs.close();
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
    
    
    
    
    public void AutoComplete()
    	{       
                
            
                autoname.clear();
                autobatch.clear();
                automanufact.clear();
                autonameofcontent.clear();
                autodist.clear();
                
                
                
		String namec="SELECT DISTINCT name FROM med;";
                String batchc="SELECT DISTINCT batch FROM med;";
                String manuf="SELECT DISTINCT mfr FROM med;";
                String nameco="SELECT DISTINCT name FROM med;";
                String dist="SELECT DISTINCT name FROM dtab;";                
                String invo="SELECT DISTINCT invoice FROM dtab;";
                                
                String ide="SELECT DISTINCT id FROM med;";
                
		try
		{
			preparedStatement=conn.prepareStatement(ide);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				autoid.add(rs.getInt("id")  );
                                
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
		}
                try
		{
			preparedStatement=conn.prepareStatement(invo);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				autoinvo.add(rs.getString("invoice")  );
                                
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
                try
		{
			preparedStatement=conn.prepareStatement(dist);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				autodist.add(rs.getString("name")  );
                                
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
                TextFields.bindAutoCompletion(distrib, autodist);
                TextFields.bindAutoCompletion(invoiceno, autoinvo);
                
                TextFields.bindAutoCompletion(invoicetoimport, autoinvo);
                TextFields.bindAutoCompletion(id, autoid);
                
                
        }
    
    
    public void loadContname()
	{
                data.clear();
		String query="select * from `med` where id='"+id.getText()+"'";
		          System.out.println("hihi");
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                                                name.setText(rs.getString("name"));                                        
                                                content.setText(rs.getString("contentname"));   
			}
			preparedStatement.close();
			rs.close();
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
    
    
    public void loadTable()
	{
            float amnt=0;
                data.clear();
                
                NumberFormat nf = NumberFormat.getInstance();

                
                
                
		String query="select * from `distlog` where invoice='"+invoiceno.getText()+"'";
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
                                                rs.getFloat("rate")*rs.getInt("quantity"),
                                                //Float.valueOf(((rs.getFloat("rate")*rs.getInt("quantity")*rs.getFloat("gst")/100)+(rs.getFloat("rate")*rs.getInt("quantity")))),
                                                rs.getInt("sino"),
                                                rs.getInt("free"),
                                                rs.getInt("pack"),
                                                rs.getString("cat")
                                                        ));
                                                amnt+=((rs.getFloat("rate")*rs.getInt("quantity")*rs.getFloat("gst")/100)+(rs.getFloat("rate")*rs.getInt("quantity")));
                                                
                                System.out.println(rs.getFloat("rate")*rs.getInt("quantity"));
				table.setItems(data);
			}
                        
                        
                        totalamount.setText(String.valueOf(amnt));
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
        sino=data.get(i).getSino();
        System.out.println("sino = "+sino);
        mfr.setText(String.valueOf(data.get(i).getMfr()));
        name.setText(data.get(i).getName());
        content.setText(data.get(i).getContentname());
        id.setText(String.valueOf(data.get(i).getId()));
        rate.setText(String.valueOf(data.get(i).getRate()));
        price.setText(String.valueOf(data.get(i).getPrice()));
        quantity.setText(String.valueOf(data.get(i).getQuantity()));
        gst.setValue(String.valueOf(data.get(i).getGst()));
        batch.setText(data.get(i).getBatch());
        pack.setText(String.valueOf(data.get(i).getPack()));
        categ.setText(data.get(i).getCateg());
        
        
        
    }
    
    @FXML
    public void deleteButtonClicked(){
        ObservableList<Medicine> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();        
        productSelected.forEach(allProducts::remove);
        String query = "DELETE FROM `distlog` WHERE `sino`='"+sino+"'";
        String query1 = "DELETE FROM `med` WHERE `name`='"+name.getText()+"' and `batch`= '"+batch.getText()+"' and `sino`='"+sino+"' ";
        System.out.println(query);
        
        query2(query, "DELETED");
        query2(query1, "DELETED");
        loadTable();
        
        
    }
    
    
      public void query2(String query, String message)
    {
       Statement st;
       try{
           st = conn.createStatement();
           if((st.executeUpdate(query)) == 1)
           {
               
               data.clear();
               loadTable();
           }else{
               
           }
       }catch(Exception ex){
           ex.printStackTrace();
           //fader("FAILED");
       }
   }
    
      

    @FXML
    private void loader(ActionEvent event) throws ParseException {
         System.out.println("  1   ");
        float num=Integer.valueOf(quantity.getText());
             System.out.println("   2 ");
        //if(categ.getText().equals("pack")||categ.getText().equals("package"))
        if(packtogg.isSelected())
        {System.out.println("   3 ");
            num=num*Integer.valueOf(pack.getText());
        }
        
        /*
        System.out.println(" 4   ");
        if(id.getText().equals(""))
        {System.out.println("  5  ");
        id.setText("0");
        }*/
        /*
        if(batch.getText().equals(""))
        {System.out.println("  5  ");
        batch.setText("0");
        }*/
        /*
        if(content.getText().equals(""))
        {System.out.println("  5  ");
        content.setText("0");
        }
        */
        
        System.out.println("  6  ");
        System.out.println(!(invoiceno.getText().trim().isEmpty()&&date.getValue() == null));
       System.out.println("  7  ");
            float tot=(Float.valueOf(quantity.getText())*Float.valueOf(rate.getText()));
        System.out.println("  8  ");
        if(!(invoiceno.getText().trim().isEmpty())&&!(date.getValue() == null))
        {
            System.out.println("  9  ");
        /*String str="INSERT INTO `med` (`id`, `name`, `contentname`, `batch`, `quantity`, `price`,  `mfr` , `rate` ,`cat` , `pack` ) VALUES"
                + " ('"+Integer.parseInt(id.getText())+"', '"+name.getText().toUpperCase()+"', '"+content.getText().toUpperCase()+"', "
                + "'"+batch.getText().toUpperCase()+"', '"+num+"', '"+price.getText()+"', "
                + " '"+mfr.getText().toUpperCase()+"' ,'"+rate.getText()+"'   , '"+categ.getText()+"' , '"+packq+"'  );";
        */
        String str="INSERT INTO `med` ( `name`,`quantity`, `price`,  `mfr` , `rate` , `pack` ) VALUES"
                + " ( '"+name.getText().toUpperCase()+"', "
                + " '"+num+"', '"+price.getText()+"', "
                + " '"+mfr.getText().toUpperCase()+"' ,'"+rate.getText()+"'   , '"+packq+"'  );";
        System.out.println("str1 =" +str);
        executeSQlQuery1(str, "Inserted");
        
        /*
        String str2="INSERT INTO `distlog` (`sino`, `id`, `name`, `contentname`, `batch`,`quantity`, `price`,  `mfr`, `rate`, `invoice` , `total` ,`date` ,`cat`, `pack` ) VALUES "
                         + "('"+lastOne()+"', '"+Integer.parseInt(id.getText())+"', '"+name.getText().toUpperCase()+"',"
                + " '"+content.getText().toUpperCase()+"', '"+batch.getText().toUpperCase()+"', "
                + " '"+quantity.getText()+"',  '"+price.getText()+"',  "
                + "'"+mfr.getText().toUpperCase()+"', '"+rate.getText()+"', '"+invoiceno.getText()+"', '"+tot+"' ,"
                + " '"+date.getValue()+"' , '"+categ.getText()+"'   , '"+packq+"'   );";*/
        String str2="INSERT INTO `distlog` (`sino`,  `name`,`quantity`, `price`,  `mfr`, `rate`, `invoice` , `total` ,`date` , `pack` ) VALUES "
                         + "('"+lastOne()+"',  '"+name.getText().toUpperCase()+"',"
                + "  "
                + " '"+quantity.getText()+"',  '"+price.getText()+"',  "
                + "'"+mfr.getText().toUpperCase()+"', '"+rate.getText()+"', '"+invoiceno.getText()+"', '"+tot+"' ,"
                + " '"+date.getValue()+"' , '"+packq+"'   );";
        System.out.println("str2 =" +str2);
        
        
        executeSQlQuery1(str2, "Inserted");
        
        lsl.setText("Last Added :");
        lslbl.setText(name.getText().toUpperCase());
        
        /*
        String asd="INSERT INTO `category` (`categ`) VALUES ('"+categ.getText().toUpperCase()+"');";
        executeSQlQuery1(asd, "Inserted");*/
        
        loadCat();
        int q;
        System.out.println("whaaaaaaat");
        
        
        loadTable();}
        
        
    
    }
    
    
    private int lastOne(){
    
    String adf= "SELECT * FROM `med` ORDER BY sino DESC LIMIT 1";
		try
		{
			preparedStatement3=conn.prepareStatement(adf);
			rs3=preparedStatement3.executeQuery();
			
			while(rs3.next())
			{
                                                lastoneaaa=rs3.getInt("sino");
                                                System.out.println("lastone"+lastoneaaa);
			}
			preparedStatement3.close();
			rs3.close();
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                return lastoneaaa;
    
    
    }

    @FXML
    private void goBack(ActionEvent event) {
        application.gotoProfile();
        
    }

    @FXML
    private void createInvoice(ActionEvent event) throws SQLException {
        
        
        float bal=Float.valueOf(totalam.getText())-Float.valueOf(payedm.getText());

        String str="INSERT INTO `dtab` (`name`, `date`, `invoice` ,`modeofpay`, `total`, `given`, `balance` , `address`, `dl1` , `dl2` , `gstin` ) VALUES ('"+distrib.getText()+"', '"+date.getValue()+"', '"+invoiceno.getText()+"' ,'"+modecomb.getValue()+"', '"+totalam.getText()+"', '"+payedm.getText()+"', '"+bal+"' , '"+adrs.getText()+"' , '"+dln1.getText()+"' , '"+dln2.getText()+"' , '"+gstin.getText()+"'   );";
        
        
        executeSQlQuery(str, "Inserted");
        AutoComplete();
        invoicer.setText("Succesfully Created");
        stmt = conn.createStatement();
        
    
    
    
    
    }
    
     public void executeSQlQuery(String query, String message)
   {
       Connection con =connector.LoginConnector();
       Statement st;
       try{
           st = con.createStatement();
           
           if((st.executeUpdate(query)) == 1)
           {
               distlbl.setText(distrib.getText());
               invoicelbl.setText(invoiceno.getText());
               datelbl.setText(date.getValue().toString());
               
               
               System.out.println("Data "+message+" Succefully");
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
               loadTable();
               
               System.out.println("Data "+message+" Succefully");
           }else{
               System.out.println("Data Not "+message);
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }

    @FXML
    private void otheronvoice(ActionEvent event) {
        application.gotoSeeInvoice();
    }

    @FXML
    private void importer(ActionEvent event) {
        importe(pathanem);
    }
    
    
    
}
