/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 */
package demo;

//import demo.payment.PaymentController;
import demo.Bank.BankChooseController;
import demo.Distributor.DistributorLogController;
import demo.Reports.ReportManagerController;
import demo.Settings.SettingsController;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import demo.model.User;
import demo.salesperson.SalespersonController;
import demo.security.Authenticator;
import demo.stock.ExpiringMedsController;
import demo.stock.FinishingMedicineController;
import demo.stock.StockController;
import demo.transactions.TransactionsController;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

/**
 * Main Application. This class handles navigation and user session.
 */
public class Main extends Application {
    
    
    
    Connection conn=connector.LoginConnector();
    PreparedStatement preparedStatement=null;
    ResultSet rs=null;


    private Stage stage;
    private User loggedUser;
    private final double MINIMUM_WINDOW_WIDTH = 390.0;
    private final double MINIMUM_WINDOW_HEIGHT = 500.0;
    
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     double width = screenSize.getWidth();
     double height = screenSize.getHeight();
     
     
     
    Calendar now = Calendar.getInstance();

   
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
   
        Application.launch(Main.class, (java.lang.String[])null);
    }
    
    private String pattern = "dd-MM-yyyy";

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            stage.setTitle("Store");
            stage.sizeToScene();
            stage.setMaximized(true);
            
            String blodate= "25-12-2017";
            
            
            String dateInString =new SimpleDateFormat(pattern).format(new Date());
            System.out.println("nowdate ="+dateInString+" and blockdate = "+blodate);
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            System.out.println(sdf.parse(blodate).before(sdf.parse(dateInString)));
            checkStatus();
            
            
            
            if(!sdf.parse(blodate).before(sdf.parse(dateInString))&&aaa==0)
                
            { System.out.println("aaa ="+aaa);
                gotoLogin();
            primaryStage.show();
            }
            else
            {
                 locker();
                gotoExpired();
            primaryStage.show();} 
            


//System.out.println("Failed");
                
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getLoggedUser() {
        return loggedUser;
    }
        
    public boolean userLogging(String userId, String password){
        if (Authenticator.validate(userId, password)) {
            loggedUser = User.of(userId);
            gotoProfile();
            return true;
        } else {
            return false;
        }
    }
    
    void userLogout(){
        loggedUser = null;
        gotoLogin();
    }
    
    
    
    int aaa;
    
    void locker(){
    String a="UPDATE `chart` SET `status`='1' WHERE `id`='1';";
        query1(a);
    
    }
    
    
   private void query1(String query)
           
            {
               Statement st;

               try{
                   st = conn.createStatement();
                   if((st.executeUpdate(query)) == 1)
                   {
                       System.out.println("locked succesfully");

                   }else{

                   }
               }catch(Exception ex){
                   ex.printStackTrace();

               }
           } 
    
    public void checkStatus()
    	{       
            System.out.println("status is poga");
		String caaa="SELECT * FROM chart where `id`='1' ;";                                
                
		try
		{
			preparedStatement=conn.prepareStatement(caaa);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				aaa=rs.getInt("status") ;
                                
                                }
                                              
                        
                        
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
                if(aaa!=1)
                {
                    System.out.println("trueee");
                    
                }
                            else
                {
                    System.out.println("faaaslse"); }
                
        }
    
    
    public void gotoProfile() {
        try {
            GeneralWindowController profile = (GeneralWindowController) replaceSceneContent("GeneralWindow.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void gotoExpired() {
        try {
            ExpiredController profile = (ExpiredController) replaceSceneContent("Expired.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void gotoAdminLogin() {
        try {
            AdminLoginController profile = (AdminLoginController) replaceSceneContent("AdminLogin.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoDistr() {
        try {
            DistributorController profile = (DistributorController) replaceSceneContent("Distributor.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoDistLog() {
        try {
            //LoginController login = (LoginController) replaceSceneContent("Demos.fxml");
            DistributorLogController login = (DistributorLogController) replaceSceneContent("Distributor/DistributorLog.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void gotoLogin() {
        try {
            //LoginController login = (LoginController) replaceSceneContent("Demos.fxml");
            DemosController login = (DemosController) replaceSceneContent("Demos.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void gotoSettings() {
        try {
            //LoginController login = (LoginController) replaceSceneContent("Demos.fxml");
            SettingsController login = (SettingsController) replaceSceneContent("Settings/Settings.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void gotoReport() {
        try {
            //LoginController login = (LoginController) replaceSceneContent("Demos.fxml");
            ReportManagerController login = (ReportManagerController) replaceSceneContent("Reports/ReportManager.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoStock() {
        try {
            //LoginController login = (LoginController) replaceSceneContent("Demos.fxml");
            StockController login = (StockController) replaceSceneContent("stock/Stock.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoSeeInvoice() {
        try {
            InvoicesController profile = (InvoicesController) replaceSceneContent("Invoices.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotoTrans() {
        try {
            TransactionsController profile = (TransactionsController) replaceSceneContent("transactions/Transactions.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoBilling() {
        try {
            //LoginController login = (LoginController) replaceSceneContent("Demos.fxml");
            BillingController login = (BillingController) replaceSceneContent("Billing.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            System.out.println("HEELLLLLLLLOOOOOOOOOOO");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotoPayment() {
        try {
            //LoginController login = (LoginController) replaceSceneContent("Demos.fxml");
            PrintController login = (PrintController) replaceSceneContent("Print.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoCash() {
        try {
            //LoginController login = (LoginController) replaceSceneContent("Demos.fxml");
            CashController login = (CashController) replaceSceneContent("Cash.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoRefund() {
        try {
            //LoginController login = (LoginController) replaceSceneContent("Demos.fxml");
            ReturnSalesController login = (ReturnSalesController) replaceSceneContent("ReturnSales.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoFinish() {
        try {
            //LoginController login = (LoginController) replaceSceneContent("Demos.fxml");
            FinishingMedicineController login = (FinishingMedicineController) replaceSceneContent("stock/Finishing Medicine.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoExpire() {
        try {
            //LoginController login = (LoginController) replaceSceneContent("Demos.fxml");
            ExpiringMedsController login = (ExpiringMedsController) replaceSceneContent("stock/ExpiringMeds.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void gotoQuotation() {
        try {
            //LoginController login = (LoginController) replaceSceneContent("Demos.fxml");
            QuotationController login = (QuotationController) replaceSceneContent("Quotation.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void gotoQuickBill() {
        try {
            //LoginController login = (LoginController) replaceSceneContent("Demos.fxml");
            QuickSalesController login = (QuickSalesController) replaceSceneContent("QuickSales.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    public void gotoChooseBank() {
        try {
            //LoginController login = (LoginController) replaceSceneContent("Demos.fxml");
            BankChooseController login = (BankChooseController) replaceSceneContent1("Bank/BankChoose.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
      
      
    
    
    
    


    private Initializable replaceSceneContent(String fxml) throws Exception {
        
        stage.widthProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                    setCurrentWidthToStage(); 
                }
            });

            stage.heightProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                    setCurrentHeightToStage();
                }
            });
            
        
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        //Scene scene = new Scene(page, 600, 400);
        Scene scene = new Scene(page);
        /*
        stage.setMaxHeight(height);
        stage.setMaxWidth(width);
        stage.setMinHeight(height);
        stage.setMinWidth(width);
        */
        
        
            stage.setScene(scene);
        //stage.setMaximized(true);
        System.out.println("height = "+height+ " and width = "+width);
        //stage.sizeToScene();
        return (Initializable) loader.getController();
    }
    
    
    private Initializable replaceSceneContent1(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        //Scene scene = new Scene(page, 600, 400);
        
        
        
        Stage addDialogue = new Stage();
            addDialogue.setTitle("Sparrow | Add New Student");
            addDialogue.initModality(Modality.WINDOW_MODAL);
            addDialogue.initStyle(StageStyle.UNDECORATED);
            addDialogue.initOwner(stage);
            Scene scene = new Scene(page);
            addDialogue.setScene(scene);
            addDialogue.showAndWait();
        //stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    
    private void setCurrentWidthToStage() {
        System.out.println("--");
            stage.setWidth(width);
        }

        private void setCurrentHeightToStage() {
            System.out.println("--");
            stage.setHeight(height);
        }
    
    
}
