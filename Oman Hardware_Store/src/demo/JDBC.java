/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ZIYAD
 */
public class JDBC {
    
     public static BillingController bc;
    

	public static void main(String[] args) throws SQLException {
            
            
           

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/oman?useSSL=false", "root" , "ziyad8578");
			
			// 2. Create a statement
			myStmt = myConn.createStatement();
			
			// 3. Execute SQL query
			myRs = myStmt.executeQuery("select * from items");
			
			// 4. Process the result set
			while (myRs.next()) {
                            //bc.id.setText(myRs.getString("id_ITEMS"));
                            
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
	}

}
