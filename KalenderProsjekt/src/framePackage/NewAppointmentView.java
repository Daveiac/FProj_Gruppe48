package framePackage;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.*;

import data.CalendarModel;
import data.Meeting;
import data.MeetingRoom;
import data.Person;
import data.Team;

public class NewAppointmentView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private CalendarModel calendarModel;

	// Alle labels
	private JLabel headline = new JLabel("Avtale for:                  ");
	private JLabel titel = new JLabel(
			"Tittel:                                    ");
	private JLabel startTime = new JLabel("Starttid: ");
	private JLabel endTime = new JLabel("Sluttid: ");
	private JLabel location = new JLabel("Sted: ");
	private JLabel meetingRom = new JLabel("M�terom: ");
	private JLabel participant = new JLabel("Deltaker: ");
	private JLabel info = new JLabel("Beskrivelse: ");
	private JLabel alarm = new JLabel("Alarm: ");
	private JLabel alarmTime = new JLabel("Tidspunkt for alarm: ");

	// Lagar alternativ for TidsComboBoxane
	String[] hour = new String[] { "00", "01", "02", "03", "04", "05", "06",
			"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17",
			"18", "19", "20", "21", "22", "23" };
	String[] min = new String[] { "00", "15", "30", "45"};

	// Alle tekstfelt og ComboBoxar
	private JTextField tittelComponent = new JTextField();
	private JComboBox<String> startHourComponent = new JComboBox<String>(hour);
	private JComboBox<String> startMinComponent = new JComboBox<String>(min);
	private JComboBox<String> endHourComponent = new JComboBox<String>(hour);
	private JComboBox<String> endMinComponent = new JComboBox<String>(min);
	private JTextField locComponent = new JTextField();
	private JComboBox<String> romComponent = new JComboBox<String>();
	private JComboBox<String> participantComponent = new JComboBox<String>();
	private JTextArea infoComponent = new JTextArea();
	private JComboBox<String> alarmHourComponent = new JComboBox<String>(hour);
	private JComboBox<String> alarmMinComponent = new JComboBox<String>(min);
	private JList<data.Person> participantList = new JList<data.Person>();
	final DefaultListModel<data.Person> listModel = new DefaultListModel<data.Person>();
	private JCheckBox alarmComponent = new JCheckBox();
	private JComboBox dayComponent = new JComboBox();
	private JComboBox monthComponent = new JComboBox();
	private JComboBox yearComponent = new JComboBox();
	private GregorianCalendar cal = new GregorianCalendar();
	private int nDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

	// Buttons
	private JButton leggTilDeltakerKnapp = new JButton("Legg til");
	private JButton slettDeltakerKnapp = new JButton("Slett");
	private JButton opprettKnapp = new JButton("Opprett avtale");
	private JButton endreKnapp = new JButton("Endre avtale");
	private JButton slettKnapp = new JButton("Slett avtale");

	public NewAppointmentView(Meeting meeting, CalendarModel calendarModel) {
		
		
		this.calendarModel = calendarModel;
		GregorianCalendar greCalendar = new GregorianCalendar();
		this.setPreferredSize(new Dimension(1000, 1000));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		infoComponent.setLineWrap(true);
		infoComponent.setWrapStyleWord(true);
		infoComponent.setColumns(8);
		infoComponent.setRows(8);
		scrollPane.setViewportView(infoComponent);
		participantList.setPreferredSize(new Dimension(200, 200));
		tittelComponent.setPreferredSize(new Dimension(300, 20));
		startHourComponent.setPreferredSize(new Dimension(100, 20));
		startMinComponent.setPreferredSize(new Dimension(100, 20));
		endHourComponent.setPreferredSize(new Dimension(100, 20));
		endMinComponent.setPreferredSize(new Dimension(100, 20));
		locComponent.setPreferredSize(new Dimension(100, 20));
		infoComponent.setPreferredSize(new Dimension(200, 200));
		alarmHourComponent.setPreferredSize(new Dimension(100, 20));
		alarmMinComponent.setPreferredSize(new Dimension(100, 20));
		opprettKnapp.setPreferredSize(new Dimension(150, 30));
		endreKnapp.setPreferredSize(new Dimension(150, 30));
		slettKnapp.setPreferredSize(new Dimension(150, 30));
		leggTilDeltakerKnapp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.addElement(new data.Person("Per", 5, "sd", "sd",
						"sd", "sd"));
			}
		});
		participantList = new JList<data.Person>(listModel);
		monthComponent = new JComboBox();
		yearComponent = new JComboBox();
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		GridBagConstraints d = new GridBagConstraints();
		d.anchor = GridBagConstraints.PAGE_END;
		
		//Alle labels
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		this.add(headline, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		this.add(titel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		this.add(startTime, c);
	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 3;
		c.gridwidth = 1;
		this.add(endTime, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		this.add(location, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		this.add(meetingRom, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 6;
		this.add(participant, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 10;
		this.add(info, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 16;
		this.add(alarm, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 17;
		this.add(alarmTime, c);
		
		//her i fra er det bare jeg(Shimin) som greier � lese....
		c.gridx = 1;
		c.gridy = 0;
		addDay(nDays);
		this.add(dayComponent, c);
		
		c.gridx = 2;
		c.gridy = 0;
		this.add(addMonth(), c);
		monthComponent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GregorianCalendar cal = new GregorianCalendar();
				cal.set(GregorianCalendar.MONTH, monthComponent.getSelectedIndex());
				int nDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
				addDay(nDays);
			}
		});
		
		c.gridx = 3;
		c.gridy = 0;
		this.add(addYear(), c);

		c.insets = new Insets(10, 0, 0, 0);


		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 5;
		this.add(tittelComponent, c);


		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		this.add(startHourComponent, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		c.gridwidth = 1;
		this.add(startMinComponent, c);


		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 3;
		c.gridwidth = 1;
		this.add(endHourComponent, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 3;
		c.gridwidth = 1;
		this.add(endMinComponent, c);


		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 5;
		this.add(locComponent, c);
		locComponent.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent arg0) {
			}
			
			public void keyReleased(KeyEvent arg0) {
			}
			
			public void keyPressed(KeyEvent arg0) {
				if(locComponent.getText().length() > 0){
					romComponent.setEnabled(false);
				}
				else{
					romComponent.setEnabled(true);
				}
			}
		});


		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 5;
		c.gridwidth = 5;
		romComponent.addItem(" ");
		romComponent.addItem("test");
		romComponent.setSelectedIndex(-1);
		this.add(romComponent, c);
		romComponent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(romComponent.getSelectedItem() != " "){
					locComponent.setEnabled(false);
				}
				else{
					locComponent.setEnabled(true);
				}
			}
		});
		

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 6;
		c.gridwidth = 5;
		this.add(participantComponent, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 7;
		c.gridy = 6;
		c.gridwidth = 5;
		this.add(leggTilDeltakerKnapp, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 6;
		this.add(participant, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 7;
		c.gridwidth = 5;
		this.add(new JScrollPane(participantList), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 7;
		c.gridy = 7;
		this.add(slettDeltakerKnapp, c);
		slettDeltakerKnapp.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if(participantList.getSelectedValue()!=null){
					listModel.remove(participantList.getSelectedIndex());
			}
		}});


		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 10;
		c.gridwidth = 5;
		this.add(scrollPane, c);


		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 16;
		this.add(alarmComponent, c);


		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 17;
		c.gridwidth = 1;
		this.add(alarmHourComponent, c);
		alarmMinComponent.setEnabled(false);
		alarmHourComponent.setEnabled(false);
		alarmComponent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshAlarm();
			}
		});

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 17;
		c.gridwidth = 1;
		this.add(alarmMinComponent, c);

		d.fill = GridBagConstraints.VERTICAL;
		d.gridx = 0;
		d.gridy = 18;
		d.gridwidth = 1;
		this.add(opprettKnapp, d);
		opprettKnapp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(validTime()==false){
					System.out.println("u failed");
				}
			}
		});

		d.fill = GridBagConstraints.VERTICAL;
		d.gridx = 2;
		d.gridy = 18;
		d.gridwidth = 2;
		this.add(endreKnapp, d);
		endreKnapp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validTime()==false){
					System.out.println("u failed");
				}
			}
		});

		d.fill = GridBagConstraints.VERTICAL;
		d.gridx = 5;
		d.gridy = 18;
		d.gridwidth = 9;
		this.add(slettKnapp, d);
		
		if(meeting != null){
			tittelComponent.setText(meeting.getTitle());
			greCalendar.setTimeInMillis(meeting.getStartTime());
			startHourComponent.setSelectedIndex(greCalendar.get(GregorianCalendar.HOUR_OF_DAY));
			startMinComponent.setSelectedIndex(greCalendar.get(GregorianCalendar.MINUTE)/15);
			greCalendar.setTimeInMillis(meeting.getEndTime());
			endHourComponent.setSelectedIndex(greCalendar.get(GregorianCalendar.HOUR_OF_DAY));
			endMinComponent.setSelectedIndex(greCalendar.get(GregorianCalendar.MINUTE)/15);
			if(meeting.getLocation() != null){
				locComponent.setText(meeting.getLocation());
				romComponent.setEnabled(false);
			}
			if(meeting.getLocation() == null){
				romComponent.setEnabled(true);
				locComponent.setEnabled(false);
				romComponent.addItem(meeting.getRoom().getRoomName());
				romComponent.setSelectedItem(meeting.getRoom().getRoomName());
			}
			//SER P� DEN HER
			for(int i = 0; i<calendarModel.getPersons().size();i++ ){
				for(int j = 0; j < participantList.getModel().getSize(); j++){
					participantList.setSelectedIndex(j);
					if(calendarModel.getPersons().get(i) != participantList.getSelectedValue()){
						participantComponent.addItem(calendarModel.getPersons().get(i).getFirstName() +calendarModel.getPersons().get(i).getLastName());
					}
					if(calendarModel.getPersons().get(i) != participantList.getSelectedValue()){
						listModel.addElement(calendarModel.getPersons().get(i));
					}
				}
			}
			infoComponent.setText(meeting.getDescription());
			meeting.getTeam();
		}
	}

	private void addDay(int nDays) {
		GregorianCalendar cal = new GregorianCalendar();
		dayComponent.removeAllItems();
		for(int i=0; i<nDays;i++){
			dayComponent.addItem(i+1);
		}
		dayComponent.setSelectedIndex(cal.get(GregorianCalendar.DAY_OF_MONTH));
	}
	
	private JComboBox addMonth(){
		SimpleDateFormat sdf = new SimpleDateFormat("MMMMM");
		
		monthComponent = new JComboBox();
		GregorianCalendar cal = new GregorianCalendar();
		for(int i=0; i<12;i++){
			cal.set(GregorianCalendar.MONTH, i);
			monthComponent.addItem(sdf.format(cal.getTime()));
		}
		GregorianCalendar cal2 = new GregorianCalendar();
		monthComponent.setSelectedIndex(cal2.get(GregorianCalendar.MONTH));
		return monthComponent;
	}
	
	private JComboBox addYear(){
		yearComponent = new JComboBox();
		GregorianCalendar cal = new GregorianCalendar();
		for(int i=0; i<50;i++){
			yearComponent.addItem(cal.get(GregorianCalendar.YEAR)+i);
		}
		return yearComponent;
	}
	
	

	public void refreshAlarm() {
		if (alarmComponent.isSelected() == false) {
			alarmHourComponent.setEnabled(false);
			alarmMinComponent.setEnabled(false);
		}
		if (alarmComponent.isSelected() == true) {
			alarmHourComponent.setEnabled(true);
			alarmMinComponent.setEnabled(true);
			if(startHourComponent.getSelectedIndex() > 0){
				alarmHourComponent.setSelectedIndex(startHourComponent.getSelectedIndex()-1);
				alarmMinComponent.setSelectedIndex(startMinComponent.getSelectedIndex());
			}
			else{
				alarmHourComponent.setSelectedIndex(23);
				alarmMinComponent.setSelectedIndex(startMinComponent.getSelectedIndex());
			}
		}
	}
	
	private boolean validTime(){		
		if(startHourComponent.getSelectedIndex() > endHourComponent.getSelectedIndex()){
			return true;
		}
		if(startHourComponent.getSelectedIndex() == endHourComponent.getSelectedIndex()){
			if(startMinComponent.getSelectedIndex() >= endHourComponent.getSelectedIndex()){
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Person kari = new Person("karitr@ggk.no", 81549300, "Kari", "Traa", "karitr", "123456");
		ArrayList<Person> members = new ArrayList<Person>();
		Team team = new Team(0, null, members);
		MeetingRoom room = new MeetingRoom("Roomsa");
		members.add(kari);
		long startTime = new GregorianCalendar(2013, 2, 14, 16, 30).getTimeInMillis();
		long endTime = new GregorianCalendar(2013, 2, 14, 17, 30).getTimeInMillis();
		Meeting meeting = new Meeting(0, "suppe1", null, startTime, endTime, "This is a desc", team, room, kari);
		JFrame frame = new JFrame("Avtale");
		frame.setPreferredSize(new Dimension(850, 700));
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CalendarModel calendarModel2 = new CalendarModel();
		calendarModel2.init();
		frame.getContentPane().add(new NewAppointmentView(meeting,calendarModel2));
		frame.pack();
		frame.setVisible(true);
	}
}