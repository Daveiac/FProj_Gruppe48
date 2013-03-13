package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.Alarm;
import data.Meeting;
import data.MeetingRoom;
import data.Notification;
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
	
	public void addMemberOf(String username, int teamID) throws SQLException{
		String sql = "INSERT INTO memberOF (teamID, username) ";
		sql += "VALUES (" ;
		sql += Integer.toString(teamID) + ", '" + username + "');";
		dBConn.makeUpdate(sql);
	}
	
	public void addTeam(Team team) throws SQLException{
		String sql = "INSERT INTO Team (email) ";
		sql += "VALUES ('" + team.getEmail() + "');";
		int teamID = dBConn.makeUpdateReturnID(sql);
		for (Person person : team.getMembers()) {
			addMemberOf(person.getUsername(), teamID);
		}
	}
	
	public void addAlarm(Alarm alarm) throws SQLException{
		String kind = "'" + Character.toString(alarm.getKind()) + "'";
		String time = Long.toString(alarm.getTime());
		String meetingID = Integer.toString(alarm.getMeeting().getMeetingID());
		String username = "'" + alarm.getPerson().getUsername() + "'";
		String sql = "INSERT INTO Alarm (kind, time, meetingID, username) ";
		sql += "VALUES (" ;
		sql += kind + ", " + time + ", " + meetingID + ", " + username + ");";
		
		dBConn.makeUpdate(sql);
		
		
	}
	
	/*
	 * Returns every meeting owned by this person
	 */
	public List<Meeting> getEveryMeetingForPerson(Person person)
			throws SQLException {
		List<Meeting> meetings = new ArrayList<Meeting>();

		String sql = "SELECT * FROM Meeting, Person "
				+ "WHERE Meeting.username = Person.username;";
		ResultSet rs = dBConn.makeQuery(sql);

		while (rs.next()) {
			getMeetingFromResultSet(rs);
		}
		return meetings;
	}

	public List<Meeting> getEveryMeeting() throws SQLException {
		List<Meeting> meetings = new ArrayList<Meeting>();

		String sql = "SELECT * FROM Meeting;";
		ResultSet rs = dBConn.makeQuery(sql);

		while (rs.next()) {
			getMeetingFromResultSet(rs);
		}
		return meetings;
	}
	
	
	public void addNotification(Notification notification) throws SQLException{
		String time = Long.toString(notification.getTime());
		String approved = Character.toString(notification.getApproved());
		String kind = Character.toString(notification.getKind());
		String meetingID = Integer.toString(notification.getMeeting().getMeetingID());
		String username = notification.getPerson().getUsername();
		String sql = "INSERT INTO Notification (time, approved, kind, meetingID, username) ";
		sql += "VALUES (" ;
		sql += time + ", '" + approved + "', '" + kind + "', " + meetingID + ", '" + username + "')";
		
		dBConn.makeUpdate(sql);
	}

	public void addMeeting(Meeting meeting) throws SQLException {
		String teamID = "null";
		// Checks if the team has been set or not. This is the main difference
		// between appointments and meetings.
		if (meeting.getTeam() != null) {
			teamID = Integer.toString(meeting.getTeam().getTeamID());
		}
		
		String sql = "INSERT INTO Meeting (startTime, endTime, description, username, teamID) ";
		sql += "VALUES ";
		sql += "( " + meeting.getStartTime() + ", "
				+ meeting.getEndTime() + ", '" + meeting.getDescription()
				+ "', '";
		sql += meeting.getCreator().getUsername() + "', ";
		sql += teamID;
		sql += ");";
		System.out.println(sql);
		dBConn.makeUpdate(sql);
	}

	private Alarm getAlarmFromResultSet(ResultSet alarmResultSet)
			throws SQLException {
		return new Alarm(alarmResultSet.getInt("alarmID"), alarmResultSet
				.getString("type").charAt(0), alarmResultSet.getLong("time"),
				getMeeting(alarmResultSet.getInt("meetingID")));
	}

	public List<Alarm> getAlarmsOfPerson(String username) throws SQLException {
		List<Alarm> alarms = new ArrayList<Alarm>();
		ResultSet alarmsOfPersonRS = dBConn
				.makeQuery("SELECT * FROM Alarm, Person "
						+ "WHERE Alarm.username = Person.username;");
		while (alarmsOfPersonRS.next()) {
			alarms.add(getAlarmFromResultSet(alarmsOfPersonRS));
		}
		return alarms;

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
				new MeetingRoom(100), getPerson(rs.getString("username")));
	}

	private Team getTeamFromResultSet(ResultSet rs) throws SQLException {
		List<Person> members = new ArrayList<Person>();
		ResultSet membersOf = dBConn
				.makeQuery("SELECT * FROM Person, memberOF, Team "
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
		List<Person> personList = new ArrayList<Person>();
		Person hakon = dbc.getPerson("haakondi");
		Person david = dbc.getPerson("davidhov");
		personList.add(david);
		personList.add(hakon);
//		Team team = new Team(-1, "anyone", personList);
//		dbc.addTeam(team);
		Person stian = dbc.getPerson("stiven");
		Meeting meeting = dbc.getMeeting(3);
		
		Notification notification = new Notification(10000, 'n', 'n', meeting, stian);
		dbc.addNotification(notification);
		
	}
}
