package framePackage;

import java.awt.List;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JList;

import data.Meeting;
import data.MeetingRoom;
import data.Person;
import data.Team;

public class AppointmentView {
	
	private ArrayList<Meeting> meetings;
	
	public AppointmentView(ArrayList<Meeting> meetings) {
		this.meetings = meetings;
		
	}
	
	public static void main(String[] args) {
		ArrayList<Meeting> meetings = new ArrayList<Meeting>();
		ArrayList<Person> members = new ArrayList<Person>();
		Team team = new Team(0, null, members);
		MeetingRoom room = new MeetingRoom(0);
		Person creator = new Person(null, 00000000, "Dave", "Hov", "dave", "1234");
		members.add(creator);
		long startTime = new GregorianCalendar(2013, 2, 14, 16, 30).getTimeInMillis();
		long endTime = new GregorianCalendar(2013, 2, 14, 17, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppem�te", startTime, endTime, "This is a desc", team, room, creator));
		startTime = new GregorianCalendar(2013, 2, 15, 10, 30).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 15, 11, 00).getTimeInMillis();
		meetings.add(new Meeting(0, "suppem�te2", startTime, endTime, "This is a desc", team, room, creator));
		startTime = new GregorianCalendar(2013, 2, 14, 16, 30).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 14, 17, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppem�te3", startTime, endTime, "This is a desc", team, room, creator));
	}
	
	

}
