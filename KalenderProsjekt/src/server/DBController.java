package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	private Person personFromResultSet(ResultSet rs) throws SQLException {
		// Remember to call resultset.next() before this method
		return new Person(rs.getString("email"), rs.getInt("telephone"),
				rs.getString("firstname"), rs.getString("lastname"),
				rs.getString("username"), rs.getString("passwd"));
	}

	public Person getPerson(String username) throws SQLException {
		ResultSet rs = dBConn.makeQuery(String.format(
				"SELECT * FROM Person WHERE username = '%s'", username));
		rs.next();
		return personFromResultSet(rs);
	}

	public List<Person> getEveryPerson() throws SQLException {
		List<Person> persons = new ArrayList<Person>();
		ResultSet rs = dBConn.makeQuery("SELECT * FROM Person");
		while (rs.next()) {
			persons.add(personFromResultSet(rs));
		}
		return persons;
	}

	public static void main(String[] args) throws SQLException {
		DBController dbc = new DBController();
//		Person person = new Person("stian@tull.no", 90814612, "Stian",
//				"Venstre", "stiven", "stianerbest");
//		dbc.addPerson(person);

		System.out.println(dbc.getEveryPerson());

	}
}
