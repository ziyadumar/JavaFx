/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import com.jfoenix.controls.JFXButton;
import demo.Classes.Variable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ZIYAD
 */
public class GeneralWindowController implements Initializable {
    
     @FXML
    private JFXButton salesp;

    @FXML
    private JFXButton refund;

    @FXML
    private JFXButton trans;

    @FXML
    private JFXButton stock;

    @FXML
    private JFXButton reports;

    @FXML
    private JFXButton billing;

    @FXML
    private JFXButton general;

    @FXML
    private JFXButton logout;
    
    private Main application;
    @FXML
    private JFXButton quickbill;
    @FXML
    private JFXButton dlog;
    @FXML
    private JFXButton fgoods;
    @FXML
    private JFXButton settings;
    @FXML
    private JFXButton quoto;
    @FXML
    private JFXButton other;
    
    Variable vr= new Variable();
    
    public void setApp(Main application){
        this.application = application;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
       
        Variable.setStr("sadfgsdfg");
        
        TranslateTransition quib = new TranslateTransition(Duration.millis(1000), quickbill);
        quib.setFromX(1000);
        quib.setToX(0);
        quib.setCycleCount(1);
        quib.setAutoReverse(true);
        
        TranslateTransition sto = new TranslateTransition(Duration.millis(800), stock);
        sto.setFromX(1000);
        sto.setToX(0);
        sto.setCycleCount(1);
        sto.setAutoReverse(true);
        
        TranslateTransition ref = new TranslateTransition(Duration.millis(1500), refund);
        ref.setFromX(1000);
        ref.setToX(0);
        ref.setCycleCount(1);
        ref.setAutoReverse(true);
        
        TranslateTransition dlo = new TranslateTransition(Duration.millis(1200), dlog);
        dlo.setFromX(1000);
        dlo.setToX(0);
        dlo.setCycleCount(1);
        dlo.setAutoReverse(true);
        
        
        
        ///RIGHT side
        TranslateTransition billo = new TranslateTransition(Duration.millis(1100), billing);
        billo.setFromX(-1000);
        billo.setToX(0);
        billo.setCycleCount(1);
        billo.setAutoReverse(true);
        
        TranslateTransition sales = new TranslateTransition(Duration.millis(1400), salesp);
        sales.setFromX(-1000);
        sales.setToX(0);
        sales.setCycleCount(1);
        sales.setAutoReverse(true);        
        
        TranslateTransition tra = new TranslateTransition(Duration.millis(900), trans);
        tra.setFromX(-1000);
        tra.setToX(0);
        tra.setCycleCount(1);
        tra.setAutoReverse(true);
        
        
        TranslateTransition repo = new TranslateTransition(Duration.millis(1300), reports);
        repo.setFromX(-1000);
        repo.setToX(0);
        repo.setCycleCount(1);
        repo.setAutoReverse(true);
        
        TranslateTransition fgo = new TranslateTransition(Duration.millis(1600), fgoods);
        fgo.setFromX(-1000);
        fgo.setToX(0);
        fgo.setCycleCount(1);
        fgo.setAutoReverse(true);
        
        
        ScaleTransition scla = new ScaleTransition(Duration.millis(1500), general);
        scla.setFromX(0.0);
        scla.setFromY(0.0);
        scla.setToX(1);
        scla.setToY(1);
        
        
        ParallelTransition all = new ParallelTransition(quib,sto,ref,dlo,billo,sales,tra,repo,fgo);
        
        //all.play();
        //scla.play();
        
        
        
        
    }
   
        
     @FXML
        public void logout(ActionEvent event) {
        
        application.gotoLogin();
        
    }
        
     @FXML
        public void stock(ActionEvent event) {
        
        application.gotoStock();
        
    }
        
     @FXML
        public void trans(ActionEvent event) {
        
        application.gotoTrans();
        
    }
        
     @FXML
        public void billing(ActionEvent event) {
        
        application.gotoBilling();
        
    }
        
     @FXML
        public void salesperson(ActionEvent event) {
        
        application.gotoDistr();
        
    }
        // TODO

    @FXML
    private void refund(ActionEvent event) {
        application.gotoRefund();
    }

    @FXML
    private void distlog(ActionEvent event) {
        application.gotoDistLog();
    }

    @FXML
    private void report(ActionEvent event) {
        
        application.gotoReport();
    }

    @FXML
    private void finish(ActionEvent event) {
        
        application.gotoFinish();
    }

    
    @FXML
    private void quickbill(ActionEvent event) {
        application.gotoQuickBill();
    }

    @FXML
    private void setting(ActionEvent event) {
        application.gotoSettings();
    }

    @FXML
    private void quotation(ActionEvent event) {
        application.gotoQuotation();
    }
    

}
    

/*

        TranslateTransition quib = new TranslateTransition(Duration.millis(1500), quickbill);
        quib.setFromX(50);
        quib.setToX(0);
        quib.setCycleCount(1);
        quib.setAutoReverse(true);
        
        TranslateTransition sto = new TranslateTransition(Duration.millis(1500), stock);
        sto.setFromX(-100);
        sto.setToX(0);
        sto.setCycleCount(1);
        sto.setAutoReverse(true);
        
        TranslateTransition ref = new TranslateTransition(Duration.millis(1500), refund);
        ref.setFromY(-100);
        ref.setToY(0);
        ref.setCycleCount(1);
        ref.setAutoReverse(true);
        
        TranslateTransition dlo = new TranslateTransition(Duration.millis(1500), dlog);
        dlo.setFromY(-75);
        dlo.setToY(0);
        dlo.setCycleCount(1);
        dlo.setAutoReverse(true);
        
        
        
        ///RIGHT side
        TranslateTransition billo = new TranslateTransition(Duration.millis(1500), billing);
        billo.setFromX(50);
        billo.setToX(0);
        billo.setCycleCount(1);
        billo.setAutoReverse(true);
        
        TranslateTransition sales = new TranslateTransition(Duration.millis(1500), salesp);
        sales.setFromX(-100);
        sales.setToX(0);
        sales.setCycleCount(1);
        sales.setAutoReverse(true);        
        
        TranslateTransition tra = new TranslateTransition(Duration.millis(1500), trans);
        tra.setFromX(75);
        tra.setToX(0);
        tra.setCycleCount(1);
        tra.setAutoReverse(true);
        
        
        TranslateTransition repo = new TranslateTransition(Duration.millis(1500), reports);
        repo.setFromY(-100);
        repo.setToY(0);
        repo.setCycleCount(1);
        repo.setAutoReverse(true);
        
        TranslateTransition fgo = new TranslateTransition(Duration.millis(1500), fgoods);
        fgo.setFromY(-75);
        fgo.setToY(0);
        fgo.setCycleCount(1);
        fgo.setAutoReverse(true);
        
        
        
        ParallelTransition all = new ParallelTransition(quib,sto,ref,dlo,billo,sales,tra,repo,fgo);
        all.play();
        


*/