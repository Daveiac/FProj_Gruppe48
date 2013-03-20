package framePackage;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import data.Alarm;
import data.CalendarModel;
import data.Meeting;
import data.MeetingRoom;
import data.Notification;
import data.Person;
import data.Team;

public class AppointmentOverView {

	private JFrame frame;
	private JLabel headLine, lblMoreInfo, lblParticipant, lblInfo,
			lblYourStatus, lblStatus;
	private JList participantList;
	private DefaultListModel listModel;
	private JTextArea moreInfo;
	private JComboBox<ImageIcon> yourStatus;
	private JPanel overViewPanel;
	private Meeting meeting;
	private Person creator;
	private JButton change, delete;
	private ImageIcon check, cross, question;
	private List<Notification> notifications;
	private NewAppointmentView newAppointment;
	private CalendarModel calendarModel;
	private Alarm alarm;

	public AppointmentOverView(Meeting meeting, Person creator,
			Notification notification) {
		this.meeting = meeting;
		notifications = new ArrayList<Notification>();
		notifications.add(notification); 
		this.creator = creator;
		initialize();
	}

	private void initialize() {
		check = new ImageIcon("res/icons/icon_check.png");
		cross = new ImageIcon("res/icons/icon_cross.png");
		question = new ImageIcon("res/icons/icon_question.png");

		overViewPanel = new JPanel(new GridBagLayout());
		overViewPanel.setPreferredSize(new Dimension(700, 450));
		overViewPanel.setVisible(true);
		GridBagConstraints c = new GridBagConstraints();		
		
		headLine = new JLabel(meeting.getTitle());
		c.gridx = 0;
		c.gridy = 0;
		headLine.setPreferredSize(new Dimension(300, 25));
		headLine.setFont(new Font(headLine.getFont().getName(),headLine.getFont().getStyle(), 20 ));
		overViewPanel.add(headLine, c);

		lblInfo = new JLabel(getTime() + " på rom: " + getLoc());
		c.gridx = 0;
		c.gridy = 1;
		lblInfo.setPreferredSize(new Dimension(300,25));
		overViewPanel.add(lblInfo, c);
		
		c.insets = new Insets(10,0,10,0);
		
		lblMoreInfo = new JLabel("Beskrivelse:");
		c.gridx = 0;
		c.gridy = 2;
		overViewPanel.add(lblMoreInfo, c);

		lblParticipant = new JLabel("Deltakere:");
		c.gridx = 0;
		c.gridy = 3;
		overViewPanel.add(lblParticipant, c);

		lblYourStatus = new JLabel("Din status:");
		c.gridx = 0;
		c.gridy = 4;
		overViewPanel.add(lblYourStatus, c);

		change = new JButton("Endre avtale");
		c.gridx = 0;
		c.gridy = 5;
		overViewPanel.add(change, c);
		change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newAppointment = new NewAppointmentView(meeting, calendarModel, alarm);
				frame.setVisible(false);
				overViewPanel.setVisible(true);
			}
		});

		delete = new JButton("Slett avtale");
		c.gridx = 1;
		c.gridy = 5;
		overViewPanel.add(delete, c);
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//sett inn hva du skal sende her
			}
		});

		yourStatus = new JComboBox<ImageIcon>();
		yourStatus.addItem(check);
		yourStatus.addItem(cross);
		yourStatus.addItem(question);
		c.gridx = 1;
		c.gridy = 4;
		overViewPanel.add(yourStatus, c);
		c.gridx = 2;
		c.gridy = 4;
		lblStatus = new JLabel();
		lblStatus.setPreferredSize(new Dimension(70, 25));
		overViewPanel.add(lblStatus, c);
		if (notifications.get(0).getApproved() == 'y') {
			yourStatus.setSelectedItem(check);
			lblStatus.setText("Deltar");
		}
		if (notifications.get(0).getApproved() == 'n') {
			yourStatus.setSelectedItem(cross);
			lblStatus.setText("Deltar Ikke");
		}
		if (notifications.get(0).getApproved() == 'w') {
			yourStatus.setSelectedItem(question);
			lblStatus.setText("Vet Ikke");
		}
		yourStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (yourStatus.getSelectedItem() == check) {
					lblStatus.setText("Deltar");
					//skrive inn hvor du skal sende det til
				}
				if (yourStatus.getSelectedItem() == cross) {
					lblStatus.setText("Deltar Ikke");
				}
				if (yourStatus.getSelectedItem() == question) {
					lblStatus.setText("Ikke svart");
				}
			}
		});

		moreInfo = new JTextArea(meeting.getDescription());
		moreInfo.setEditable(false);
		moreInfo.setFocusable(false);
		moreInfo.setPreferredSize(new Dimension(320, 100));
		c.gridx = 1;
		c.gridy = 2;
		overViewPanel.add(moreInfo, c);

		listModel = new DefaultListModel();
		listModel.addElement(notifications.get(0));
		participantList = new JList<Notification>();
		participantList.setModel(listModel);
		participantList.setFixedCellWidth(300);
		participantList.setCellRenderer(new overViewRender());
		JScrollPane myJScrollPane = new JScrollPane(participantList);
		myJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		c.gridx = 1;
		c.gridy = 3;
		overViewPanel.add(myJScrollPane, c);
		
		frame =  new JFrame();
		frame.setContentPane(overViewPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}

	public static void main(String args[]) {
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
		CalendarModel calendarModel2 = new CalendarModel();
		new AppointmentOverView(meetings, creator,
				notification);
	}

	private String getTime() {
		GregorianCalendar cal = new GregorianCalendar();
		GregorianCalendar cal2 = new GregorianCalendar();
		cal.setTimeInMillis(meeting.getStartTime());
		cal2.setTimeInMillis(meeting.getEndTime());
		SimpleDateFormat spl1 = new SimpleDateFormat("dd.MMMM");
		SimpleDateFormat spl2 = new SimpleDateFormat("HH:mm");
		String time = spl1.format(cal.getTime()) + ". Fra kl "
				+ spl2.format(cal.getTime()) + " til "
				+ spl2.format(cal2.getTime());
		return time;
	}

	private String getLoc() {
		return meeting.getLocation();
	}

	public JPanel getPanel() {
		return overViewPanel;
	}
	
	public void showFrame(){
		frame.setVisible(true);
	}

}
