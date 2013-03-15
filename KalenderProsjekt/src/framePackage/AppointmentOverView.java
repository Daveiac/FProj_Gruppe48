package framePackage;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import data.Meeting;
import data.MeetingRoom;
import data.Person;
import data.Team;

public class AppointmentOverView {
	
	private JFrame frame;
	private JLabel headLine, lblMoreInfo, lblParticipant,lblInfo;
	private JList participantList;
	private DefaultListModel<String> model;
	private JTextArea moreInfo;
	private JComboBox participat;
	private JPanel overViewPanel;
	private Meeting meetings;
	private Person person;
	
	
	public AppointmentOverView(Meeting meetings) {
		this.meetings = meetings;
		initialize();
	}


	private void initialize() {
		overViewPanel = new JPanel(new GridBagLayout());
		overViewPanel.setPreferredSize(new Dimension(700, 450));
		overViewPanel.setVisible(true);
		GridBagConstraints c = new GridBagConstraints();
		
		headLine = new JLabel(meetings.getTitle());
		c.gridx = 0;
		c.gridy = 0;
		overViewPanel.add(headLine, c);
		
		lblInfo = new JLabel(getTime()+ " p� rom: " + getLoc());
		c.gridx = 0;
		c.gridy = 1;
		overViewPanel.add(lblInfo,c);
		
		lblMoreInfo = new JLabel("Beskrivelse:");
		c.gridx = 0;
		c.gridy = 2;
		overViewPanel.add(lblMoreInfo, c);
		
		lblParticipant = new JLabel("Deltakere:");
		c.gridx = 0;
		c.gridy = 3;
		overViewPanel.add(lblParticipant, c);
		
		moreInfo = new JTextArea(meetings.getDescription());
		moreInfo.setEditable(false);
		moreInfo.setFocusable(false);
		moreInfo.setPreferredSize(new Dimension(300,100));
		c.gridx = 1;
		c.gridy = 2;
		overViewPanel.add(moreInfo,c);
	}


	public static void main(String args[]){
		ArrayList<Person> members = new ArrayList<Person>();
		Team team = new Team(0, null, members);
		MeetingRoom room = new MeetingRoom("Soverommet");
		Person creator = new Person(null, 00000000, "Dav", "Hov", "dave", "1234");
		members.add(creator);
		long startTime = new GregorianCalendar(2013, 2, 14, 16, 30).getTimeInMillis();
		long endTime = new GregorianCalendar(2013, 2, 14, 17, 30).getTimeInMillis();
		Meeting meetings = new Meeting(0, "suppem�tewwwwwwwwwwwwwwwwwwwwwwwww", "kontoret", startTime, endTime, "This is a desc", team, room, creator);
		JFrame frame = new JFrame("APPointmenOverViewTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new AppointmentOverView(meetings).getPanel());
		frame.pack();
		frame.setVisible(true);
	}

	private String getTime(){
		GregorianCalendar cal = new GregorianCalendar();
		GregorianCalendar cal2 = new GregorianCalendar();
		cal.setTimeInMillis(meetings.getStartTime());
		cal2.setTimeInMillis(meetings.getEndTime());
		SimpleDateFormat spl1 = new SimpleDateFormat("dd.MMMM");
		SimpleDateFormat spl2 = new SimpleDateFormat("HH:mm");
		String time = spl1.format(cal.getTime()) + ". Fra kl " + spl2.format(cal.getTime()) + " til "+ spl2.format(cal2.getTime());
		return time;
	}
	
	private String getLoc(){
		return meetings.getLocation();
	}
	
	private JPanel getPanel() {
		return overViewPanel;
	}

}