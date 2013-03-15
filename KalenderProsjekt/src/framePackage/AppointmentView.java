package framePackage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.Meeting;
import data.MeetingRoom;
import data.Person;
import data.Team;

public class AppointmentView {
	
	private ArrayList<Meeting> meetings;
	private JPanel meetingPanel;
	private JPanel mainPanel;
	private JPanel legendPanel;
	private GridBagConstraints mc;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MMM.yyyy");
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	
	public AppointmentView(ArrayList<Meeting> meetings) {
		this.meetings = meetings;
		mainPanel = new JPanel();
		meetingPanel = new JPanel();
		legendPanel = new JPanel();
		meetingPanel.setLayout(new GridBagLayout());
		mainPanel.add(meetingPanel,BorderLayout.CENTER);
		JScrollPane jsp = new JScrollPane(meetingPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(jsp, BorderLayout.NORTH);
		mainPanel.add(legendPanel, BorderLayout.SOUTH);
		mainPanel.setPreferredSize(new Dimension(800, 500));
		createHeaders();
		createLegend();
		refreshMeetings();
		
	}

	private void createLegend() {
		legendPanel = new JPanel();
		//TODO
	}

	private void createHeaders() {
		mc = new GridBagConstraints();
		mc.gridy = 0;
		mc.gridx = 0;
		mc.anchor = GridBagConstraints.LINE_END;
		meetingPanel.add(new JLabel("status"));
		mc.gridx = 1;
		meetingPanel.add(new JLabel("dato"));
		mc.gridx = 2;
		meetingPanel.add(new JLabel("tid"));
		mc.gridx = 3;
		meetingPanel.add(new JLabel("avtale"));
		mc.gridx = 4;
		meetingPanel.add(new JLabel("sted"));
		mc.gridx = 5;
		meetingPanel.add(new JLabel("endre"));
		mc.gridx = 6;
		meetingPanel.add(new JLabel("mer info"));
	}

	private void refreshMeetings() {
		
		for (int i = 0; i < meetings.size(); i++) {
			Meeting meeting = meetings.get(i);
			JLabel icon = new JLabel();
			JLabel date = new JLabel();
			JLabel time = new JLabel();
			JLabel title = new JLabel(meeting.getTitle());
			JLabel location = new JLabel(meeting.getLocation());
			JButton changeBtn = new JButton("endre");
			JButton moreBtn = new JButton("mer info...");
			

			GregorianCalendar startDate = new GregorianCalendar();
			startDate.setTimeInMillis(meeting.getStartTime());
			date.setText(dateFormat.format(startDate.getTime()));
			time.setText(timeFormat.format(startDate.getTime()));
			
			mc.gridy = i+1;
			mc.gridx = 0;
			mc.insets = new Insets(0, 10, 0, 0);
			meetingPanel.add(icon,mc);
			mc.gridx = 1;
			meetingPanel.add(date,mc);
			mc.gridx = 2;
			meetingPanel.add(time,mc);
			mc.gridx = 3;
			meetingPanel.add(title,mc);
			mc.gridx = 4;
			meetingPanel.add(location,mc);
			mc.gridx = 5;
			meetingPanel.add(changeBtn,mc);
			mc.insets = new Insets(0, 0, 0, 0);
			mc.gridx = 6;
			meetingPanel.add(moreBtn,mc);
			
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Meeting> meetings = new ArrayList<Meeting>();
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
		
		JFrame frame = new JFrame("APPointmensTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new AppointmentView(meetings).getPanel());
		frame.pack();
		frame.setVisible(true);
	}
	
	public JPanel getPanel() {
		return mainPanel;
	}
	
	

}
