package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	Connection connection;
	String url;

	public DBConnection(){
		url = "46.9.153.76";
	}
	
	public void initialize() throws SQLException{
		Properties connectionProperties = new Properties();
		
		connectionProperties.setProperty("user", "caluser");
		connectionProperties.setProperty("password", "heyo!");
		connection = DriverManager.getConnection(url, connectionProperties);
	}

	
	public static void main(String args[]) throws SQLException{
		(new DBConnection()).initialize();
	}
}
