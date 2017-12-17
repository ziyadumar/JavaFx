/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.billing;


import demo.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class BillingController implements Initializable  {
    
    Connection conn=connector.LoginConnector();
    ObservableList<Items> data=FXCollections.observableArrayList();
    
    
    public Main application;
    private JDBC jdbc;
/////////////////////////
    /*
    @FXML
    private JFXButton back;
 @FXML
    private Label name;
 @FXML
    private Label quantity;
 @FXML
    private Label ide;
 @FXML
    private JFXButton connect;
@FXML
    private AnchorPane main;
 
    @FXML
    private JFXButton hello;
     @FXML
    private JFXTreeTableView<User> treeView;*/
     /////////////////////
  
    //private FadeIn fade;

////////////////////////////////////////////

        
    
        

        //Connection conn;
	
	
	PreparedStatement preparedStatement=null;
	ResultSet rs=null;
	
        
        //////////////////
        @FXML
    private AnchorPane main;

    @FXML
    public TableView<Items> table;

    @FXML
    private TableColumn<?, ?> idcol;

    @FXML
    private TableColumn<?, ?> namecol;

    @FXML
    private TableColumn<?, ?> pricecol;

    @FXML
    private TableColumn<?, ?> quancol;

    @FXML
    private JFXButton next;

    @FXML
    private JFXButton fader;

    @FXML
    private JFXButton connect;

    @FXML
    private JFXButton back;

    @FXML
    private Label ide;

    @FXML
    private Label name;

    @FXML
    private Label quantity;

    @FXML
    private JFXButton hello;
        ////////////////////
    /*
    
	@FXML
	private Button signOut;
	
	@FXML
	TableView<User> table;
	
	@FXML
	private TableColumn<?, ?> nameCol;

	@FXML
	private TableColumn<?, ?> usernameCol;

	@FXML
	private TableColumn<?, ?> passwordCol;
	
	@FXML
	Button addnewBtn;
	
	@FXML Button updateBtn;
	
	@FXML Button deleteBtn;
	
	@FXML TextField searchBox;
	
	@FXML TextField usernameBox;
	
	@FXML TextField nameBox;
	
	@FXML TextField passwordBox;*/

    /*public BillingController() throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/albaha?useSSL=false", "root" , "ziyad8578");
    }*/
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{       idcol.setCellValueFactory(new PropertyValueFactory<>("id_ITEMS"));
		namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
                
                pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
                quancol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		loadDatabaseData();
	}
	
	public void loadDatabaseData()
	{
		String query="select * from albaha.items";
		
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				data.add(new Items(
						rs.getInt("id_ITEMS"),
						rs.getString("name"),
                                                rs.getInt("price"),
                                                rs.getInt("quantity")
						
                                                
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



























































////////////////////////////////////////////////////////////////////////////////
 /* @FXML
    void  connect() throws SQLException{Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/albaha?useSSL=false", "root" , "ziyad8578");
			
			// 2. Create a statement
			myStmt = myConn.createStatement();
			
			// 3. Execute SQL query
			myRs = myStmt.executeQuery("select * from items");
			
			// 4. Process the result set
			while (myRs.next()) {
                            //bc.id.setText(myRs.getString("id_ITEMS"));        
               //             sql = "SELECT id, name FROM users";

                        //pst = con.prepareStatement(sql);

                        // result = pst.executeQuery();

 //the below code will populate the table with database values

                             //   table.setModel(DbUtils.resultSetToTableModel(myRs));
                          
                            ide.setText(myRs.getString("id_ITEMS"));
                            name.setText(myRs.getString("name"));
                              //quantity.setText(myRs.getString("quantity"));
                            
				//System.out.println(myRs.getString("id_ITEMS")+" , "+ myRs.getString("name") + ", " + myRs.getString("quantity"));
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}
		}
}*/

    public void setApp(Main application){
        this.application = application;
    }
    
    public void goBack(ActionEvent event) {
        
        application.gotoProfile();
        
    }
    
    /*public void fade(ActionEvent event) {
        
        fade.
    }*/

    /**
     * Initializes the controller class.
     */
    /*@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }*/
/*@Override
    public void initialize(URL url, ResourceBundle rb) {
        JFXTreeTableColumn<User, String> deptName = new JFXTreeTableColumn<>("Department");
        deptName.setPrefWidth(150);
        deptName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().department;
            }
        });

        JFXTreeTableColumn<User, String> ageCol = new JFXTreeTableColumn<>("Age");
        ageCol.setPrefWidth(150);
        ageCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().age;
            }
        });

        JFXTreeTableColumn<User, String> nameCol = new JFXTreeTableColumn<>("Name");
        nameCol.setPrefWidth(150);
        nameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().userName;
            }
        });

        ObservableList<User> users = FXCollections.observableArrayList();
        users.add(new User("Computer Department", "23", "CD 1"));
        
        users.add(new User("Sales Department", "22", "Employee 1"));
        users.add(new User("Sales Department", "22", "Employee 2"));
        users.add(new User("Sales Department", "25", "Employee 4"));
        users.add(new User("Sales Department", "25", "Employee 5"));
        users.add(new User("IT Department", "42", "ID 2"));
        users.add(new User("HR Department", "22", "HR 1"));
        users.add(new User("HR Department", "22", "HR 2"));

        final TreeItem<User> root = new RecursiveTreeItem<User>(users, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(deptName, ageCol, nameCol);
        treeView.setRoot(root);
        treeView.setShowRoot(false);

    }    
    */
    public void Pay(ActionEvent event) {
        
        application.gotoPayment();
        
    }
    
    @FXML
    private void filter(ActionEvent event) {
    }
    
    
     class User extends RecursiveTreeObject<User> {

        StringProperty userName;
        StringProperty age;
        StringProperty department;

        public User(String department, String age, String userName) {
            this.department = new SimpleStringProperty(department);
            this.userName = new SimpleStringProperty(userName);
            this.age = new SimpleStringProperty(age);
        }

    }
}
