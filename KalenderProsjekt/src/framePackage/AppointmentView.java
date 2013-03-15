package framePackage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import data.Meeting;
import data.MeetingRoom;
import data.Person;
import data.Team;

public class AppointmentView {
	
	private ArrayList<Meeting> meetings;
	private JTable meetingTable;
	private JPanel mainPanel;
	private JPanel legendPanel;
	private TableModel tableModel;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MMM.yyyy");;
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");;
	
	public AppointmentView(ArrayList<Meeting> meetings) {
		this.meetings = meetings;
		mainPanel = new JPanel();
		legendPanel = new JPanel();
		meetingTable = new JTable();
		String[] headers = {"status","dato", "tid","avtale", "sted" , "endre", "mer info"};
		tableModel = new DefaultTableModel(headers ,meetings.size());;
		
		meetingTable = new JTable(tableModel) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return colIndex == 5 || colIndex == 6; //Disable the editing of any cell
			}
		};
		meetingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		meetingTable.setRowSelectionAllowed(false);
//		meetingTable.setRowHeight(64);
//		meetingTable.getColumnModel().getColumn(0).setPreferredWidth(10);
//		for (int i = 1; i < 8; i++) {
//		}
		
		
		JScrollPane jsp = new JScrollPane(meetingTable);
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(jsp,BorderLayout.NORTH);
		mainPanel.add(legendPanel, BorderLayout.SOUTH);
		mainPanel.setPreferredSize(new Dimension(800, 500));
		
		
		refreshMeetings();
		
	}

	private void refreshMeetings() {
		GridBagConstraints mc = new GridBagConstraints();
		mc.anchor = GridBagConstraints.LINE_START;
		
		
		for (int i = 0; i < meetings.size(); i++) {
			Meeting meeting = meetings.get(i);
			JLabel icon = new JLabel();
			JButton changeBtn = new JButton("Endre");
			JButton moreBtn = new JButton("Mer info...");
			

			GregorianCalendar startDate = new GregorianCalendar();
			startDate.setTimeInMillis(meeting.getStartTime());
			String date =dateFormat.format(startDate.getTime());
			String time = timeFormat.format(startDate.getTime());
			String title = meeting.getTitle();
			String location = meeting.getLocation();
			
			tableModel.setValueAt(icon, i, 0);
			tableModel.setValueAt(date, i, 1);
			tableModel.setValueAt(time, i, 2);
			tableModel.setValueAt(title, i, 3);
			tableModel.setValueAt(location, i, 4);
			tableModel.setValueAt(changeBtn, i, 5);
			tableModel.setValueAt(moreBtn, i, 6);
			
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Meeting> meetings = new ArrayList<Meeting>();
		ArrayList<Person> members = new ArrayList<Person>();
		Team team = new Team(0, null, members);
		MeetingRoom room = new MeetingRoom("Soverommet");
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
