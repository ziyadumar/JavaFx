/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listofobjects;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author ZIYAD
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField name;
    @FXML
    private TextField contact;
    @FXML
    private TextArea address;
    
    private List<Person> listofperson;
    
    private Person person1 ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        person1 = new Person();
//        
//        person1.setName("a name");
//        person1.setContact("2554");
//        person1.setAddress("Address of street");
//        
//        
        listofperson = new ArrayList<>();
        
        listofperson.add(0,person1);
        
        // TODO
    }  
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        name.setText(listofperson.get(0).name);
        contact.setText(listofperson.get(0).contact);
        address.setText(listofperson.get(0).address);
    }
    
}
