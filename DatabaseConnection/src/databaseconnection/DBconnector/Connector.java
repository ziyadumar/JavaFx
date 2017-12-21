package databaseconnection.DBconnector;

import java.sql.*;

public class Connector
{
	public static Connection LoginConnector()
	{
		try
		{
			//Class.forName("org.sqlite.JDBC");
                    //projectone is our schema name
                    //root -> username
                    //pass -> your password
			Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projectone?useSSL=false", "root" , "pass");
			return conn;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
}
