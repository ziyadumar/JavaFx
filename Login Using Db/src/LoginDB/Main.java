/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginDB;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class Main extends Application {
    
    
    public Stage stage;
    private final double MINIMUM_WINDOW_WIDTH = 390.0;
    private final double MINIMUM_WINDOW_HEIGHT = 500.0;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     double width = screenSize.getWidth();
     double height = screenSize.getHeight();
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            
            stage = primaryStage;
            stage.setTitle("SPARROW");
            stage.sizeToScene();
            stage.setWidth(width);
            stage.setHeight(height);
            stage.setMaxHeight(height);
            stage.setMaxWidth(width);
            
            gotoLoginPage();
            
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    public void gotoLoginPage() {
        try {
            LoginController login = (LoginController) replaceSceneContent("Login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    public void gotoProfilePage() {
        try {
            ProfileController login = (ProfileController) replaceSceneContent("Profile.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    public void gotoTablePage() {
        try {
            TableviewController login = (TableviewController) replaceSceneContent("Tableview.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
            page.setMinSize(width, height);
        } finally {
            in.close();
        } 
        Scene scene = new Scene(page, width, height);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setHeight(height);
        stage.setWidth(width);
        //stage.sizeToScene();
        return (Initializable) loader.getController();
    }
    
}
