import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import server.DBController;
import data.Meeting;
import data.MeetingRoom;
import data.Person;
import data.Team;


public class FillDB {
	static DBController control = new DBController();
	public static void addPeople(){
		List<Person> persons = new ArrayList<Person>();
		Person kari = new Person("christoffer@ggk.no", 81549300, "Aina", "Thunestveit", "ainaelt", "123456");
		Person jon = new Person("jonbl@ggk.no", 81549301, "H�kon", "Dissen", "haakondi", "1234567");
		Person chris = new Person("chrispr@ggk.no", 81549302, "Christoffer", "Pram", "chrispr", "123456");
		Person david = new Person("davhov@ggk.no", 81549303, "David", "Hovind", "davhov", "123456");
		Person batman = new Person("batman@ggk.no", 81549304, "Bat", "Man", "batman", "123456");
		persons.add(new Person("shiming@ggk.no", 81549300, "Shiming", "Sun", "shimings", "123456"));
		persons.add(new Person("vegar@ggk.no", 81549300, "Vegar", "Lerpoll", "vegarhl", "123456"));
		persons.add(kari);
		persons.add(jon);
		persons.add(chris);
		persons.add(david);
		persons.add(batman);
		
		for (Person person : persons) {
			try {
				control.addPerson(person);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void addMeetings() throws SQLException{
		Team team = null;
		MeetingRoom room = new MeetingRoom("Roomsa");
		Person creator = control.getPerson("batman");
		List<Meeting> meetings = new ArrayList<Meeting>();
		long startTime = new GregorianCalendar(2013, 2, 14, 16, 30).getTimeInMillis();
		long endTime = new GregorianCalendar(2013, 2, 14, 17, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppem�tewwwwwwwwwwwwwwwwwwwwwwwww", "kontoret", startTime, endTime, "This is a desc", team, room, creator));
		startTime = new GregorianCalendar(2013, 2, 15, 10, 30).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 15, 11, 00).getTimeInMillis();
		meetings.add(new Meeting(0, "suppem�te2", "kontoret", startTime, endTime, "This is a desc", team, room, creator));
		startTime = new GregorianCalendar(2013, 2, 14, 16, 30).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 14, 17, 30).getTimeInMillis();
		
		List<Person> members = new ArrayList<Person>();
		members.add(control.getPerson("karitr"));
		team = new Team(-1, "team1@hotmail.com", members);
		meetings.add(new Meeting(0, "suppem�te3", "kontoret", startTime, endTime, "This is a desc", team, room, creator));
		
		for (Meeting meeting : meetings) {
			
			try {
				control.addMeeting(meeting);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void addMeetingrooms(){
		List<MeetingRoom> rooms = new ArrayList<MeetingRoom>();
		rooms.add(new MeetingRoom("s1"));
		rooms.add(new MeetingRoom("s2"));
		rooms.add(new MeetingRoom("s3"));
		rooms.add(new MeetingRoom("s4"));
		rooms.add(new MeetingRoom("r1"));
		rooms.add(new MeetingRoom("r2"));
		rooms.add(new MeetingRoom("r3"));
		
		for (MeetingRoom meetingRoom : rooms) {
			try {
				control.addMeetingRoom(meetingRoom);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
		
	/**
	 * Used to fill the db with data after a reset
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		addPeople();
//		addMeetings();
		addMeetingrooms();
	}

}
