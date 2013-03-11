package framePackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class DefaultView extends JPanel {

	// her er main frame work, som kan brukes på year og month views også

	private JFrame frame;
	private JLabel lblday;
	private JLabel lblcalendar;
	private JToggleButton calendar;
	private JToggleButton meeting;
	private ButtonGroup calendarSelect;
	private JButton logOut;
	private JPanel sharedCalendar;
	private JComboBox<String> calendarVisible;
	private JButton addCalendar;
	private JCheckBox otherCalendar;
	private JPanel warningPanel;
	private JButton warning;
	private JLabel lblvarsel;
	private int warningCounter = 1;

	private JPanel timePanel;
	private JLabel lbldate;
	private JButton toYesterDay;
	private JButton toTomorrow;
	private ButtonGroup dayWeekMonthSelect;
	private JToggleButton day;
	private JToggleButton week;
	private JToggleButton month;
	private Date date;
	private JPanel mainView;
	private DayView dayView;

	public static void main(String[] args) {
		DefaultView dw = new DefaultView();
		JFrame frame = dw.getFrame();
		frame.setBounds(0, 0, 1024, 768);
		frame.setVisible(true);
	}

	public DefaultView() {
		dayView = new DayView(this);
		date = new Date();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagConstraints backGroundConstraints = new GridBagConstraints();
		backGroundConstraints.anchor = GridBagConstraints.LINE_END;
		lblday = new JLabel("Idag: " + getDate());
		backGroundConstraints.gridx = 0;
		backGroundConstraints.gridy = 0;
		frame.add(lblday, backGroundConstraints);
		
		calendar = new JToggleButton("Kalender");
		calendar.setSelected(true);
		meeting = new JToggleButton("møte");
		calendarSelect = new ButtonGroup();
		calendarSelect.add(calendar);
		calendarSelect.add(meeting);
		
		backGroundConstraints.gridx = 1;
		backGroundConstraints.gridy = 0;
		backGroundConstraints.weightx = 0.5;
		backGroundConstraints.anchor = GridBagConstraints.LINE_END;
		frame.add(calendar, backGroundConstraints);
		backGroundConstraints.gridx = 2;
		backGroundConstraints.gridy = 0;
		backGroundConstraints.anchor = GridBagConstraints.LINE_START;
		frame.add(meeting, backGroundConstraints);
		backGroundConstraints.weightx = 0;

		logOut = new JButton("logout");
		backGroundConstraints.gridx = 3;
		backGroundConstraints.gridy = 0;
		frame.add(logOut, backGroundConstraints);

		sharedCalendar = new JPanel(new GridBagLayout());
		sharedCalendar.setSize(100, 100);
		sharedCalendar.setBorder(BorderFactory.createLineBorder(Color.black));
		backGroundConstraints.anchor = GridBagConstraints.NORTH;
		backGroundConstraints.gridx = 0;
		backGroundConstraints.gridy = 1;
		frame.add(sharedCalendar, backGroundConstraints);
		
		GridBagConstraints sharedCalendarContraints = new GridBagConstraints();
		lblcalendar = new JLabel("kalender");
		sharedCalendarContraints.gridx = 0;
		sharedCalendarContraints.gridy = 0;
		sharedCalendar.add(lblcalendar, sharedCalendarContraints);
		calendarVisible = new JComboBox<String>();
		calendarVisible.addItem("David Hovind");
		calendarVisible.addItem("Aina Elisabeth Thunestveit");
		calendarVisible.addItem("Christoffer Pram");
		calendarVisible.addItem("Håkon Dissen");
		calendarVisible.addItem("Vegar Lerpoll");
		calendarVisible.setSelectedItem(null);
		sharedCalendarContraints.gridx = 0;
		sharedCalendarContraints.gridy = 1;
		sharedCalendar.add(calendarVisible, sharedCalendarContraints);
		addCalendar = new JButton("Legg til");
		sharedCalendarContraints.gridx = 1;
		sharedCalendarContraints.gridy = 1;
		sharedCalendar.add(addCalendar, sharedCalendarContraints);
		otherCalendar = new JCheckBox("Shimin Sun");
		sharedCalendarContraints.gridx = 0;
		sharedCalendarContraints.gridy = 2;
		sharedCalendar.add(otherCalendar, sharedCalendarContraints);
		
		warningPanel = new JPanel(new GridBagLayout());
		warningPanel.setSize(100, 300);
		warningPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		backGroundConstraints.gridx = 0;
		backGroundConstraints.gridy = 2;
		frame.add(warningPanel, backGroundConstraints);
		GridBagConstraints varselPanelContraints = new GridBagConstraints();
		lblvarsel = new JLabel("varsel");
		varselPanelContraints.gridx = 0;
		varselPanelContraints.gridy = 0;
		warningPanel.add(lblvarsel, varselPanelContraints);
		varselPanelContraints.gridy = warningCounter;
		warningPanel.add(createWarning("shit just got real"),
				varselPanelContraints);

		// her i fra kommer selve dayView delen, tar vekk lbltid så kan dette
		// brukes på andre frames

		timePanel = new JPanel(new GridBagLayout());
		
		timePanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		backGroundConstraints.gridx = 1;
		backGroundConstraints.gridy = 1;
		backGroundConstraints.gridheight = 2;
		backGroundConstraints.gridwidth = 2;
		backGroundConstraints.fill = GridBagConstraints.HORIZONTAL;
		frame.add(timePanel, backGroundConstraints);
		
		GridBagConstraints timePanelContraints = new GridBagConstraints();
		
		JPanel prevNextPanel = new JPanel();
		lbldate = new JLabel(getDay()+"."+getMonth());
		toYesterDay = new JButton("<");
		toTomorrow = new JButton(">");
		prevNextPanel.add(toYesterDay);
		prevNextPanel.add(lbldate);
		prevNextPanel.add(toTomorrow);
		timePanelContraints.gridx = 0;
		timePanelContraints.gridy = 0;
		timePanel.add(prevNextPanel, timePanelContraints);
		
		JPanel dayWeekMonthPanel = new JPanel();
		dayWeekMonthSelect = new ButtonGroup();
		day = new JToggleButton("Dag");
		week = new JToggleButton("Uke");
		month = new JToggleButton("Månede");
		dayWeekMonthSelect.add(day);
		dayWeekMonthSelect.add(week);
		dayWeekMonthSelect.add(month);
		//setter inn if her for hva som skal være selected
		day.setSelected(true);
		dayWeekMonthPanel.add(day);
		dayWeekMonthPanel.add(week);
		dayWeekMonthPanel.add(month);
		timePanelContraints.gridx = 1;
		timePanelContraints.gridy = 0;
		timePanel.add(dayWeekMonthPanel, timePanelContraints);
		
		mainView = getFocusPanel();
		timePanelContraints.gridwidth = 5;
		timePanelContraints.fill = GridBagConstraints.HORIZONTAL;
		timePanelContraints.weightx = 1;
		timePanelContraints.gridx = 0;
		timePanelContraints.gridy = 1;
		timePanel.add(mainView, timePanelContraints);
		
	}

	private JPanel getFocusPanel() {
		return dayView.getDayView();
	}

	public String getDate() {
		Locale Norge = new Locale("no", "no");
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, Norge);
		return df.format(date);
	}

	public String getDay() {
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");  
		String day = dayFormat.format(date);
		return day;
	}
	public String getMonth() {
		SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");  
		String month = monthFormat.format(date);
		return month;
	}

	public JFrame getFrame() {
		return frame;
	}

	public Component createWarning(String w) {
		warning = new JButton(w);
		warning.setContentAreaFilled(false);
		warning.setFocusable(true);
		warning.setCursor(new Cursor(Cursor.HAND_CURSOR));
		warningCounter += 1;
		return warning;
	}
}