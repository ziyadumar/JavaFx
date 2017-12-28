package registerlogin.Database;

import java.sql.*;

public class Connector
{
	public static Connection LoginConnector()
	{
		try
		{
			Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tutorials?useSSL=false", "root" , "ziyad8578");
			return conn;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
}
