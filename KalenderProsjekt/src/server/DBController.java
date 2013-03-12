package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.Meeting;
import data.MeetingRoom;
import data.Person;
import data.Team;

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

	public Meeting getMeeting(int meetingID) throws SQLException {
		ResultSet rs = dBConn.makeQuery(String.format(""
				+ "SELECT * FROM Meeting " + "WHERE meetingID = %s",
				Integer.toString(meetingID)));
		rs.next();
		return getMeetingFromResultSet(rs);
	}

	private Meeting getMeetingFromResultSet(ResultSet rs) throws SQLException {
		Team team = getTeam(rs.getInt("teamID"));
		return new Meeting(rs.getInt("meetingID"), rs.getLong("startTime"),
				rs.getLong("endTime"), rs.getString("description"), team,
				new MeetingRoom(100));
	}

	private Team getTeamFromResultSet(ResultSet rs) throws SQLException {
		List<Person> members = new ArrayList<Person>();
		ResultSet membersOf = dBConn.makeQuery("" + "SELECT * FROM Person, memberOF, Team "
				+ "WHERE memberOF.username = Person.username "
				+ "AND memberOF.teamID = Team.teamID;");
		while (membersOf.next()) {
			members.add(getPersonFromResultSet(membersOf));
		}
		return new Team(rs.getInt("teamID"), rs.getString("email"), members);
	}

	public Team getTeam(int teamID) throws SQLException {
		ResultSet rs = dBConn.makeQuery(String.format(
				"SELECT * FROM Team WHERE teamID = 'teamID'",
				Integer.toString(teamID)));
		rs.next();
		return getTeamFromResultSet(rs);

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

	private Person getPersonFromResultSet(ResultSet rs) throws SQLException {
		// Remember to call resultset.next() before this method
		return new Person(rs.getString("email"), rs.getInt("telephone"),
				rs.getString("firstname"), rs.getString("lastname"),
				rs.getString("username"), rs.getString("passwd"));
	}

	public Person getPerson(String username) throws SQLException {
		ResultSet rs = dBConn.makeQuery(String.format(
				"SELECT * FROM Person WHERE username = '%s'", username));
		rs.next();
		return getPersonFromResultSet(rs);
	}

	public void deletePerson(String username) throws SQLException {
		dBConn.makeUpdate(String.format(
				"DELETE FROM Person WHERE username = '%s'", username));
	}

	public List<Person> getEveryPerson() throws SQLException {
		List<Person> persons = new ArrayList<Person>();
		ResultSet rs = dBConn.makeQuery("SELECT * FROM Person");
		while (rs.next()) {
			persons.add(getPersonFromResultSet(rs));
		}
		return persons;
	}

	public static void main(String[] args) throws SQLException {
		DBController dbc = new DBController();
		// Person person = new Person("stian@tull.no", 90814612, "Stian",
		// "Venstre", "stiven", "stianerbest");
		// dbc.addPerson(person);
		System.out.println(dbc.getMeeting(2));
	}
}
