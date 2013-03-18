package framePackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.Meeting;
import data.MeetingRoom;
import data.Notification;
import data.Person;
import data.Team;

public class NotiPanelView {
	
	private JFrame frame;
	private JPanel varselPanel;
	private JLabel lblWarning;
	private JList warningList;
	private DefaultListModel listModel;
	private List<Notification> notifications;
	
	public NotiPanelView(Notification noti){
		notifications = new ArrayList<Notification>();
		notifications.add(noti);
		initialize();
	}
	
	private void initialize(){
		varselPanel = new JPanel(new GridBagLayout());
		varselPanel.setPreferredSize(new Dimension(250, 300));
		varselPanel.setVisible(true);
		GridBagConstraints c = new GridBagConstraints();
		
		lblWarning = new JLabel("Varsel");
		lblWarning.setFont(new Font(lblWarning.getFont().getName(),lblWarning.getFont().getStyle(), 20 ));
		c.gridx = 0;
		c.gridy = 0;
		varselPanel.add(lblWarning, c);
		
		listModel = new DefaultListModel();
		listModel.addElement(notifications.get(0));
		warningList = new JList<Notification>(listModel);
		warningList.setFixedCellWidth(15);
		warningList.setCellRenderer(new NotiViewRender());
		JScrollPane scrollPane = new JScrollPane(warningList);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(200, 250));
		c.gridx = 0;
		c.gridy = 1;
		varselPanel.add(scrollPane, c);
		
	}
	
	public static void main(String args[]){
		ArrayList<Person> members = new ArrayList<Person>();
		Team team = new Team(0, null, members);
		MeetingRoom room = new MeetingRoom("Soverommet");
		Person creator = new Person(null, 00000000, "Dav", "Hov", "dave",
				"1234");
		members.add(creator);
		long startTime = new GregorianCalendar(2013, 2, 14, 16, 30)
		.getTimeInMillis();
		long endTime = new GregorianCalendar(2013, 2, 14, 17, 30)
		.getTimeInMillis();
		Meeting meetings = new Meeting(0, "suppemøtewwwwwwwwwwwwwwwwwwwwwwwww",
				"kontoret", startTime, endTime, "This is a desc", team, room,
				creator);
		Notification notification = new Notification(0, 'y', 'c', meetings,
				creator);
		JFrame frame = new JFrame("APPointmenOverViewTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new NotiPanelView(notification).getPanel());
		frame.pack();
		frame.setVisible(true);
	}
	
	private JPanel getPanel(){
		return varselPanel;
	}
	
}
