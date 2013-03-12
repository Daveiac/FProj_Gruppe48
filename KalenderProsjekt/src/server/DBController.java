package server;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.Person;

public class DBController {

	private DBConnection dBConn;

	// Needs error handling
	public DBController() {
		dBConn = new DBConnection();
		try {
			dBConn.initialize();
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
		}
	}

	// Needs error handling
	public boolean addPerson(Person person) throws SQLException {
		String sql = "INSERT INTO Person (username, passwd, firstname, lastname, email, telephone)";
		sql += "VALUES ";
		sql += " ('" + person.getUsername() + "', '" + person.getPassword()
				+ "', '" + person.getFirstName() + "', '"
				+ person.getLastName() + "', '" + person.getEmail() + "', "
				+ Integer.toString(person.getPhone());
		sql += ");";
		dBConn.makeUpdate(sql);
		return true;
	}

	public Person getPerson(String username) throws SQLException {
		ResultSet rs = dBConn
				.makeQuery(String.format("SELECT * FROM Person WHERE username = 'stiven'"));
		while(rs.next()){
			
		}
		//This throws an exception. Look up documentation for ResultSet to find a fix
		return new Person(rs.getString("email"), rs.getInt("telephone"),
				rs.getString("firstname"), rs.getString("lastname"),
				rs.getString("username"), rs.getString("Password"));
	}

	public static void main(String[] args) throws SQLException {
		DBController dbc = new DBController();
//		Person person = new Person("stian@tull.no", 90814612, "Stian",
//				"Venstre", "stiven", "stianerbest");
//		dbc.addPerson(person);
		
		dbc.getPerson("stiven");
	}
}
