package framePackage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Meeting;
import data.MeetingRoom;
import data.Person;
import data.Team;

public class AppointmentView {
	
	private ArrayList<Meeting> meetings;
	
	public AppointmentView(ArrayList<Meeting> meetings) {
		this.meetings = meetings;
		JPanel mainPanel = new JPanel();
		JPanel meetingPanel = new JPanel();
		JPanel legend = new JPanel();
		
		meetingPanel.setLayout(new GridBagLayout());
		refreshMeetings();
		
	}

	private void refreshMeetings() {
		GridBagConstraints mc = new GridBagConstraints();
		for (int i = 0; i < meetings.size(); i++) {
			JLabel icon = new JLabel();
			JLabel date = new JLabel();
			JLabel time = new JLabel();
			JLabel title = new JLabel();
			JLabel location = new JLabel();
			JButton moreBtn = new JButton();
			
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Meeting> meetings = new ArrayList<Meeting>();
		ArrayList<Person> members = new ArrayList<Person>();
		Team team = new Team(0, null, members);
		MeetingRoom room = new MeetingRoom(0);
		Person creator = new Person(null, 00000000, "Dav", "Hov", "dave", "1234");
		members.add(creator);
		long startTime = new GregorianCalendar(2013, 2, 14, 16, 30).getTimeInMillis();
		long endTime = new GregorianCalendar(2013, 2, 14, 17, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøte", startTime, endTime, "This is a desc", team, room, creator));
		startTime = new GregorianCalendar(2013, 2, 15, 10, 30).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 15, 11, 00).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøte2", startTime, endTime, "This is a desc", team, room, creator));
		startTime = new GregorianCalendar(2013, 2, 14, 16, 30).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 14, 17, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøte3", startTime, endTime, "This is a desc", team, room, creator));
	}
	
	

}
