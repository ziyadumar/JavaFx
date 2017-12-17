/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;


import java.awt.HeadlessException;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import javax.swing.JFrame;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JasperCompileManager;

import net.sf.jasperreports.engine.JasperFillManager;

import net.sf.jasperreports.engine.JasperPrint;

import net.sf.jasperreports.engine.JasperReport;

import net.sf.jasperreports.engine.design.JRDesignQuery;

import net.sf.jasperreports.engine.design.JasperDesign;

import net.sf.jasperreports.engine.xml.JRXmlLoader;

import net.sf.jasperreports.view.JRViewer;


import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author ZIYAD
 */




public class JasperQuery  extends JFrame{   
    
    private Main application;
    
//    InvoicesController ac= new InvoicesController();

   

    Connection conn=connector.LoginConnector();

    PreparedStatement pst = null;

    ResultSet rs = null;  



    public JasperQuery() throws HeadlessException {

    }

  

    public void showReport(String mil){

         try {

             System.out.println(mil);
             

             JasperDesign jasperDesign = JRXmlLoader.load("C:/InvoiceByNumber.jrxml");
             
             //String query = "SELECT * FROM qeaf.employees where milno ="+ac.input.getText();
             
             String query = "SELECT * FROM ."+mil;
             
             System.out.println(query);
             
             JRDesignQuery jrquery = new JRDesignQuery();

             jrquery.setText(query);

             jasperDesign.setQuery(jrquery);

            

             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

             JasperPrint JasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

             JasperViewer jv = new JasperViewer( JasperPrint, false );
             jv.viewReport( JasperPrint, false );
             this.setSize(900,500);
           

         } catch (Exception e) {

             JOptionPane.showMessageDialog(rootPane, e.getMessage());

         }

  

    }
    

    public void showBarcode(){
{
        
        //showDistDet(inv);

         try {             

             JasperDesign jasperDesign = JRXmlLoader.load("C:/Barcodes.jrxml");
             
             String query = "SELECT * FROM `repeater`;";
             
             System.out.println(query);
             
             JRDesignQuery jrquery = new JRDesignQuery();

             jrquery.setText(query);

             jasperDesign.setQuery(jrquery);            

             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

             JasperPrint JasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

             JasperViewer jv = new JasperViewer( JasperPrint, false );
             jv.viewReport( JasperPrint, false );
             this.setSize(900,500);
           

         } catch (Exception e) {

             JOptionPane.showMessageDialog(rootPane, e.getMessage());
             

         }
    }
    
    }
    
    public void showDistDet(String mil){

         try {

             System.out.println(mil);
             

             JasperDesign jasperDesign = JRXmlLoader.load("C:/DisTDetails.jrxml");
             
             //String query = "SELECT * FROM qeaf.employees where milno ="+ac.input.getText();
             
             String query = " SELECT * FROM dtab where invoice ='"+mil+"'";
             
             System.out.println(query);
             
             JRDesignQuery jrquery = new JRDesignQuery();

             jrquery.setText(query);

             jasperDesign.setQuery(jrquery);

            

             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

             /*JasperPrint JasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

             JasperViewer jv = new JasperViewer( JasperPrint, false );
             jv.viewReport( JasperPrint, false );
             this.setSize(900,500);
           */

         } catch (Exception e) {

             JOptionPane.showMessageDialog(rootPane, e.getMessage());

         }

  

    }
    
    
    public void showDistReport(String inv){
        
        //showDistDet(inv);

         try {             

             JasperDesign jasperDesign = JRXmlLoader.load("C:/DistributorReport.jrxml");
             
             String query = "SELECT * FROM `distlog` where invoice = '"+inv+"';";
             
             System.out.println(query);
             
             JRDesignQuery jrquery = new JRDesignQuery();

             jrquery.setText(query);

             jasperDesign.setQuery(jrquery);

            

             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

             JasperPrint JasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

             JasperViewer jv = new JasperViewer( JasperPrint, false );
             jv.viewReport( JasperPrint, false );
             this.setSize(900,500);
           

         } catch (Exception e) {

             JOptionPane.showMessageDialog(rootPane, e.getMessage());
             

         }
    }

  
    public void showDailyReport(String date){

         try {

             System.out.println("showDailyReport date= "+date);
             

             JasperDesign jasperDesign = JRXmlLoader.load("C:/DailyReport.jrxml");
             
             String query = "SELECT * FROM billhistory where date = '"+date+"';";
             
             System.out.println(query);
             
             JRDesignQuery jrquery = new JRDesignQuery();

             jrquery.setText(query);

             jasperDesign.setQuery(jrquery);

            

             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

             JasperPrint JasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

             JasperViewer jv = new JasperViewer( JasperPrint, false );
             jv.viewReport( JasperPrint, false );
             this.setSize(900,500);
           

         } catch (Exception e) {

             JOptionPane.showMessageDialog(rootPane, e.getMessage());
             

         }

  

    }
    
    public void showCustomReport(String date,String date1){

         try {

             System.out.println("showDailyReport date= "+date);
             

             JasperDesign jasperDesign = JRXmlLoader.load("C:/CustomReport.jrxml");
             
             String query = "SELECT * FROM billhistory WHERE date BETWEEN '"+date+"' AND '"+date1+"';";
             
             System.out.println(query);
             
             JRDesignQuery jrquery = new JRDesignQuery();

             jrquery.setText(query);

             jasperDesign.setQuery(jrquery);

            

             JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

             JasperPrint JasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

             JasperViewer jv = new JasperViewer( JasperPrint, false );
             jv.viewReport( JasperPrint, false );
             this.setSize(900,500);
           

         } catch (Exception e) {

             JOptionPane.showMessageDialog(rootPane, e.getMessage());

         }

  

    }
    

}
