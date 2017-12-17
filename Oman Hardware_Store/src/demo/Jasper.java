/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.awt.HeadlessException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Ram
 */
public class Jasper  extends JFrame{    
    
    Connection conn = connector.LoginConnector();// Database Connection
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;   

    public Jasper() throws HeadlessException {
    }
   
    public void showReport() throws JRException, ClassNotFoundException, SQLException{
         try {
             
             
             loadBillno();
             String pdf=  "C:/Bills/"+bill+".pdf";
             
             //String reportSrcFile = "demo.Reports/Bill.jrxml";
 
        // First, compile jrxml file.
             //JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
             JasperReport jasperReport = JasperCompileManager.compileReport("C:/Bill.jrxml");
             JasperPrint jp =  (JasperPrint) JasperFillManager.fillReport(jasperReport, null, conn);
             JasperExportManager.exportReportToPdfFile(jp, pdf);
             
             JasperViewer jv = new JasperViewer( jp, false );
             jv.viewReport( jp, false );
             this.setSize(900,500);
             
         } catch (JRException e) {
             
             System.out.println(e.getMessage());
         }
   
    }
    private int bill;
    
    private void loadBillno()
    	{
		String query="select * from billhistory ORDER BY billno DESC LIMIT 1;";
		try
		{
			preparedStatement=conn.prepareStatement(query);
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
                                bill=rs.getInt("billno");
                                
                                }
			preparedStatement.close();
			rs.close();
                        
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
        }
}
