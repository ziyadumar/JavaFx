/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.transactions;
import demo.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import demo.ConfirmController;
import demo.Returntable;
import demo.connector;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.HostServices;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class TransactionsController implements Initializable {
    
    int f;
    
    private Main application;
    ObservableList<TransactionHistory> data=FXCollections.observableArrayList();
    
     @FXML
    private TableView<TransactionHistory> table;

    @FXML
    private JFXButton close;

    @FXML
    private JFXButton details;

    @FXML
    private JFXButton print;

    @FXML
    private JFXButton back;
    @FXML
    private TableColumn<?, ?> billcol;
    @FXML
    private TableColumn<?, ?> datecol;
    @FXML
    private TableColumn<?, ?> custcol;
    @FXML
    private TableColumn<?, ?> totcol;
    @FXML
    private JFXToggleButton togg;
    @FXML
    private Label laabel;
    public void setApp(Main application){
        this.application = application;
    }
    
    
    
    PreparedStatement preparedStatement=null;
	ResultSet rs=null;
        
     
    Connection conn=connector.LoginConnector();

    @FXML
    void back(ActionEvent event) {
        application.gotoProfile();

    }

    @FXML
    public void print(ActionEvent event) throws IOException {
        
    Stage stage = new Stage();
    Parent root;
        root = FXMLLoader.load(ConfirmController.class.getResource("transactions/Print.fxml"));
    stage.setScene(new Scene(root));
    stage.setTitle("Print");
    stage.initModality(Modality.WINDOW_MODAL);
    stage.initOwner(
        ((Node)event.getSource()).getScene().getWindow() );
    stage.show();
}

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
		billcol.setCellValueFactory(new PropertyValueFactory<>("billno"));                
                datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
                custcol.setCellValueFactory(new PropertyValueFactory<>("customer"));
                
		totcol.setCellValueFactory(new PropertyValueFactory<>("amount"));
                loadtable();
                
                
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    
                    if(mouseEvent.getClickCount() == 1){
                        laabel.setText("Double-Click to see Details.");
                        
                    }
                    else if(mouseEvent.getClickCount() == 2){
                        int i= (int)table.getFocusModel().getFocusedIndex();
                        f=data.get(i).getBillno();
                        openfile();
                        
                    }
                }
                
            }
        });   
                togg.setOnAction(e->{
                
                
                if(!togg.isSelected())
                    loadtable();
                else
                    loadretable();
                
                });
                
                if(!togg.isSelected())
                    loadtable();
                else
                    loadretable();
        // TODO
    }    
    
    
    public void loadtable()
    	{
                data.clear();
		String query="select * from billhistory";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				data.add(new TransactionHistory(
                                        rs.getInt("billno"),
                                        rs.getString("date") ,
                                        rs.getString("customer") ,
                                        rs.getFloat("amount")
                                        
                                )
                                        
                                
                                );
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
    
    public void loadretable()
    	{
                data.clear();
		String query="select * from returnhistory";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				data.add(new TransactionHistory(
                                        rs.getInt("billno"),
                                        rs.getString("date") ,
                                        rs.getString("customer") ,
                                        rs.getFloat("amount")
                                        
                                )
                                        
                                
                                );
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
    
    private void openfile(){
            File file = new File("C:/Bills/"+f+".pdf");
            
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Logger.getLogger(TransactionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }

    private HostServices getHostServices() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void Back(ActionEvent event) {
        application.gotoProfile();
    }
    
}
