package demo;

import java.sql.*;

public class connector
{
	public static Connection LoginConnector()
	{
		try
		{
			//Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/oman?useSSL=false", "root" , "ziyad8578");
			return conn;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
}
