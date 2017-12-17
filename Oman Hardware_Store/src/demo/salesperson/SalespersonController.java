/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.salesperson;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import demo.Main;
import demo.Properties.AutoCompleteTextField;
import demo.connector;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
//import np.com.ngopal.control.AutoFillTextBox;

/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class SalespersonController implements Initializable {
    
    Connection conn=connector.LoginConnector();
    ObservableList<Sperson> data=FXCollections.observableArrayList();
    PreparedStatement preparedStatement=null;
	ResultSet rs=null;
        
        
        private Main application;
        @FXML
    public TableView<Sperson> table;
	
    
    @FXML
    private TableColumn<?, ?> idcol;

    @FXML
    private TableColumn<?, ?> namecol;

    @FXML
    private TableColumn<?, ?> q_idcol;

    @FXML
    private TableColumn<?, ?> addresscol;
    
    @FXML
    private TableColumn<?, ?> mobilecol;
    
    @FXML
    private TableColumn<?, ?> pointcol;

    
    @FXML
    private JFXButton back;

    @FXML
    private JFXButton close;

    @FXML
    private JFXButton edit;

    @FXML
    private JFXButton add;
    
    
    @FXML
    private JFXTextField zee;
    
    @FXML
    private JFXComboBox<Sperson> editableComboBox;

    
    
    
    
    
    
     @FXML
    public HBox hbox;

    @FXML
    //private AutoFillTextBox<?> box;
    
    
    
    public void setApp(Main application){
        this.application = application;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
		namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
                mobilecol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
                q_idcol.setCellValueFactory(new PropertyValueFactory<>("q_id"));
                addresscol.setCellValueFactory(new PropertyValueFactory<>("address"));
                pointcol.setCellValueFactory(new PropertyValueFactory<>("points"));
//        AutoCompleteTextField autoCompleteTextField = new AutoCompleteTextField(data);
                loadDatabaseData1();
                //hbox.getChildren().addAll(bt);
                //box.getStylesheets().add(getResource("control.css").toExternalForm());
                        
                
                
                ComboBox<Sperson> editableComboBox = new ComboBox<Sperson>();
        editableComboBox.setId("second-editable");
        editableComboBox.setPromptText("Edit or Choose...");
        editableComboBox.setItems(data);
        editableComboBox.setEditable(true);
                

                
                                                
                //box(data);
        
        // TODO
    }   
    
    public void loadDatabaseData1()
	{
                //lbl1.setText("Lo moyanth");
		String query="select * from albaha.sperson";
		
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				data.add(new Sperson(
						rs.getInt("id"),
						rs.getString("name"),
                                                rs.getInt("q_id"),
                                                rs.getInt("points"),
                                                rs.getInt("mobile"),
                                                rs.getString("address")
						
                                                
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
    
    public void goBack(ActionEvent event) {
        
        application.gotoProfile();
        
    }

    private void AutoFillTextBox(ObservableList<Sperson> data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void box(ObservableList<Sperson> data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
