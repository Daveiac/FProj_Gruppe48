package data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class FiktivDBController {

	/*
	 * A class that deals with datatransfer with the database.
	 * Some methods require testing
	 * Not final
	 */

	private List<Person> persons;
	private ArrayList<Meeting> meetings;

	// Needs error handling
	public FiktivDBController() {
		
		persons = new ArrayList<Person>();
		Person kari = new Person("karitr@ggk.no", 81549300, "Kari", "Traa", "karitr", "123456");
		Person jon = new Person("jonbl@ggk.no", 81549301, "Jon", "blund", "jonbl", "123456");
		Person chris = new Person("chrispr@ggk.no", 81549302, "Christoffer", "Pman", "chrispr", "123456");
		Person david = new Person("davhov@ggk.no", 81549303, "David", "Hovind", "davhov", "123456");
		Person batman = new Person("batman@ggk.no", 81549304, "Bat", "Man", "batman", "123456");
		persons.add(kari);
		persons.add(jon);
		persons.add(chris);
		persons.add(david);
		persons.add(batman);
		
		meetings = new ArrayList<Meeting>();
		ArrayList<Person> members = new ArrayList<Person>();
		Team team = new Team(0, null, members);
		MeetingRoom room = new MeetingRoom("Roomsa");
		Person creator = new Person(null, 00000000, "Dav", "Hov", "dave", "1234");
		members.add(creator);
		
		long startTime = new GregorianCalendar(2013, 2, 14, 16, 30).getTimeInMillis();
		long endTime = new GregorianCalendar(2013, 2, 14, 17, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøtewwwwwwwwwwwwwwwwwwwwwwwww", "kontoret", startTime, endTime, "This is a desc", team, room, creator));
		startTime = new GregorianCalendar(2013, 2, 15, 10, 30).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 15, 11, 00).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøte2", "kontoret", startTime, endTime, "This is a desc", team, room, creator));
		startTime = new GregorianCalendar(2013, 2, 14, 16, 30).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 14, 17, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøte3", "kontoret", startTime, endTime, "This is a desc", team, room, creator));
		
		
		
	}
	
//	public boolean authenticateUser(String username, String password) throws SQLException{
//		
//	}
//	
//	public boolean personExists(String username) throws SQLException{
//		
//	}
//	
//	public void addMemberOf(String username, int teamID) throws SQLException{
//		
//	}
//	
//	public void addTeam(Team team) throws SQLException{
//		
//	}
//	
//	public void addAlarm(Alarm alarm) throws SQLException{
//		
//	}
//	
//	public MeetingRoom getMeetingRoom(String roomName) throws SQLException{
//		
//	}
//	
//	private Reservation getReservationFromResultSet(ResultSet rs) throws SQLException{
//		
//	}
//	
//	public Reservation getReservationForMeeting(Meeting meeting) throws SQLException{
//		
//	}
//	
//	/*
//	 * Returns every meeting owned by this person
//	 */
	public List<Meeting> getEveryMeetingForPerson(Person person){ 
		return null;
	}
//	
//	
//	public List<Reservation> getReservationsForMeetingRoom(MeetingRoom room) throws SQLException{
//		
//	}
//
//	public List<Meeting> getEveryMeeting() throws SQLException {
//		
//	}
//	
//	
//	public void addNotification(Notification notification) throws SQLException{
//		
//	}
//
//	public void addMeeting(Meeting meeting) throws SQLException {
//		
//	}
//
//	private Alarm getAlarmFromResultSet(ResultSet alarmResultSet)
//		
//	}
//
//	public List<Alarm> getAlarmsOfPerson(String username) throws SQLException {
//		
//	}
//
//	public Meeting getMeeting(int meetingID) throws SQLException {
//		
//	}
//
//	private Meeting getMeetingFromResultSet(ResultSet rs) throws SQLException {
//		
//	}
//
//	private Team getTeamFromResultSet(ResultSet rs) throws SQLException {
//		
//	}
//
//	public Team getTeam(int teamID) throws SQLException {
//		
//
//	}
//
//	// Needs error handling
//	public boolean addPerson(Person person) throws SQLException {
//		
//	}
//
//	private Person getPersonFromResultSet(ResultSet rs) throws SQLException {
//		
//	}
//
//	public Person getPerson(String username) throws SQLException {
//		
//	}
//
//	public void deletePerson(String username) throws SQLException {
//		
//	}

	public List<Person> getEveryPerson() throws SQLException {
		return persons;
	}
	
//	private Notification getNotificationFromResultSet(ResultSet rs) throws SQLException{
//		
//	}
//	
//	
//	/*
//	 *Returns every notficiation corresponding to the Person. 
//	 */
//	public List<Notification> getNotifications(Person person) throws SQLException{
//		
//	}
//	
//	/*
//	 *Returns every notficiation corresponding to the meeting. 
//	 */
//	public List<Notification> getNotifications(Meeting meeting) throws SQLException{
//	
//	}
//
//	public static void main(String[] args) throws SQLException {
//	
//	}
}
