
package registerlogin;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author ZIYAD
 */
public class RegisterLogin extends Application {
    
    private Stage stage;
     
    public static void main(String[] args) throws SQLException {
   
        Application.launch(RegisterLogin.class, (java.lang.String[])null);
    }
    

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            stage.setTitle("New Account");
            stage.sizeToScene();
            stage.setMaximized(true);
            gotoFirstPage();
            primaryStage.show();
                
        } catch (Exception ex) {
            Logger.getLogger(RegisterLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    private Initializable replaceSceneContent(String fxml) throws Exception {
        
        
        FXMLLoader loader = new FXMLLoader();
        InputStream in = RegisterLogin.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(RegisterLogin.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        Scene scene = new Scene(page);      
        stage.setScene(scene);
        return (Initializable) loader.getController();
    }

    public void gotoFirstPage() {
        try {
            RegisterNewAccountController login = (RegisterNewAccountController) replaceSceneContent("RegisterNewAccount.fxml");
            login.setApp(this);
            } catch (Exception ex) {
                Logger.getLogger(RegisterLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    

    public void gotoUserPage() {
        try {
            AccountPageController login = (AccountPageController) replaceSceneContent("AccountPage.fxml");
            login.setApp(this);
            } catch (Exception ex) {
                Logger.getLogger(RegisterLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    
    
    public void gotoCreateNewPage() {
        try {
            CreateNewUserController login = (CreateNewUserController) replaceSceneContent("CreateNewUser.fxml");
            login.setApp(this);
            } catch (Exception ex) {
                Logger.getLogger(RegisterLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    

}
