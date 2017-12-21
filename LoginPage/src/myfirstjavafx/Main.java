/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstjavafx;

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
 * @author ZIYAD
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
            
            gotoFourthPage();
            
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        /*Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    public void gotoFirstPage() {
        try {
            FirstPageController login = (FirstPageController) replaceSceneContent("FirstPage.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void gotoSecondPage() {
        try {
            SecondPageController login = (SecondPageController) replaceSceneContent("SecondPage.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void gotoThirdPage() {
        try {
            ThirdController login = (ThirdController) replaceSceneContent("Third.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void gotoFourthPage() {
        try {
            FourthController login = (FourthController) replaceSceneContent("Fourth.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    public void gotoGeneralPage() {
        try {
            GeneralWindowController login = (GeneralWindowController) replaceSceneContent("GeneralWindow.fxml");
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
