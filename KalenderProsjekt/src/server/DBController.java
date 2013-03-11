package server;

import java.sql.SQLException;

import data.Person;

public class DBController {

	private DBConnection dBConn;
	
	//Needs error handling
	public DBController(){
		dBConn = new DBConnection();
		try {
			dBConn.initialize();
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
		}
	}
	
	public boolean addPerson(Person person){
		String sql = "INSERT INTO Person (username, passwd, firstname, lastname, email, telephone)";
		sql += "VALUES";
		
		return true;
	}
	
	
}
