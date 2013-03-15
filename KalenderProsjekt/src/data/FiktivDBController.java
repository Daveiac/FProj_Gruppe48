package data;

import java.sql.SQLException;
import java.util.List;

public class FiktivDBController {

	/*
	 * A class that deals with datatransfer with the database.
	 * Some methods require testing
	 * Not final
	 */

	// Needs error handling
	public FiktivDBController() {
		
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
		return null;
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
