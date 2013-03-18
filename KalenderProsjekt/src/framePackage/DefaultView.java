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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import data.CalendarModel;

public class DefaultView extends JPanel {

	// her er main frame work, som kan brukes p�year og month views ogs�
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
	private JButton warning;
	private JLabel lblvarsel;
	private int warningCounter = 1;

	private JPanel timePanel;
	private JLabel calendarTitle;
	private JButton prevBtn;
	private JButton nextBtn;
	private ButtonGroup dayWeekMonthSelect;
	private JToggleButton dayBtn;
	private JToggleButton weekBtn;
	private JToggleButton monthBtn;
	private Date date;
	private CalendarView mainView;
	private DayView dayView;
	private WeekView weekView;
	private MonthView monthView;
	private NotiPanelView warningPanel;
	private Dato dato;
	private JPanel calendarPanel;
	private GridBagConstraints timePanelContraints;

	private CalendarModel calendarModel;

	public static void main(String[] args) {
		DefaultView dw = new DefaultView();
		JFrame frame = dw.getFrame();
		frame.setBounds(0, 0, 1260, 768);
		frame.setVisible(true);
	}

	public DefaultView() {
		calendarModel = new CalendarModel();
		dato = new Dato();
		dayView = new DayView(calendarModel);
		weekView = new WeekView(calendarModel);
		monthView = new MonthView();
		warningPanel = new NotiPanelView();
		date = new Date();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagConstraints backGroundConstraints = new GridBagConstraints();
		backGroundConstraints.anchor = GridBagConstraints.LINE_END;
		lblday = new JLabel("Idag: " + dato.getDate());
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

		backGroundConstraints.gridx = 0;
		backGroundConstraints.gridy = 2;
		frame.add(warningPanel, backGroundConstraints);
		GridBagConstraints varselPanelContraints = new GridBagConstraints();
		lblvarsel = new JLabel("varsel");
		varselPanelContraints.gridx = 0;
		varselPanelContraints.gridy = 0;
		warningPanel.add(lblvarsel, varselPanelContraints);
		varselPanelContraints.gridy = warningCounter;

		// her i fra kommer selve dayView delen, tar vekk lbltid s�kan dette
		// brukes p�andre frames

		timePanel = new JPanel(new GridBagLayout());

		timePanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		backGroundConstraints.gridx = 1;
		backGroundConstraints.gridy = 1;
		backGroundConstraints.gridheight = 2;
		backGroundConstraints.gridwidth = 2;
		backGroundConstraints.fill = GridBagConstraints.HORIZONTAL;
		frame.add(timePanel, backGroundConstraints);

		timePanelContraints = new GridBagConstraints();

		mainView = dayView;
		JPanel prevNextPanel = new JPanel();
		calendarTitle = new JLabel(mainView.getTitle());
		calendarTitle.setPreferredSize(new Dimension(150,20));
		prevBtn = new JButton("<");
		nextBtn = new JButton(">");
		PrevNextListener prevNextListener = new PrevNextListener();
		prevBtn.addActionListener(prevNextListener);
		nextBtn.addActionListener(prevNextListener);
		prevNextPanel.add(prevBtn);
		prevNextPanel.add(calendarTitle);
		prevNextPanel.add(nextBtn);
		timePanelContraints.gridx = 0;
		timePanelContraints.gridy = 0;
		timePanelContraints.weightx = 1;
		timePanel.add(prevNextPanel, timePanelContraints);

		dayWeekMonthSelect = new ButtonGroup();
		DayWeekMonthListener dwmListener = new DayWeekMonthListener(); 
		dayBtn = new JToggleButton("Dag");
		weekBtn = new JToggleButton("Uke");
		monthBtn = new JToggleButton("Måned");
		dayBtn.addActionListener(dwmListener);
		weekBtn.addActionListener(dwmListener);
		monthBtn.addActionListener(dwmListener);
		dayWeekMonthSelect.add(dayBtn);
		dayWeekMonthSelect.add(weekBtn);
		dayWeekMonthSelect.add(monthBtn);
		//setter inn if her for hva som skal v鎟e selected
		dayBtn.setSelected(true);
		timePanelContraints.weightx = 0;
		timePanelContraints.gridx = 1;
		timePanelContraints.gridy = 0;
		timePanel.add(dayBtn, timePanelContraints);
		timePanelContraints.gridx = 2;
		timePanelContraints.gridy = 0;
		timePanel.add(weekBtn, timePanelContraints);
		timePanelContraints.gridx = 3;
		timePanelContraints.gridy = 0;
		timePanel.add(monthBtn, timePanelContraints);
		//		
		timePanelContraints.gridwidth = 5;
		timePanelContraints.fill = GridBagConstraints.HORIZONTAL;
		timePanelContraints.weightx = 1;
		timePanelContraints.gridx = 0;
		timePanelContraints.gridy = 1;
		calendarPanel = mainView.getPanel();
		timePanel.add(calendarPanel, timePanelContraints);
		//		
	}

	public JFrame getFrame() {
		return frame;
	}

	private class PrevNextListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == prevBtn) {
				mainView.prev();
			} else {
				mainView.next();
			}
			calendarTitle.setText(mainView.getTitle());
		}
	}
	private class DayWeekMonthListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == dayBtn) {
				mainView = dayView;
			} else if(e.getSource() == weekBtn) {
				mainView = weekView;
			} else {
				mainView = monthView;
			}
			calendarTitle.setText(mainView.getTitle());
			timePanel.remove(calendarPanel);
			calendarPanel = mainView.getPanel();
			timePanel.add(calendarPanel, timePanelContraints);
			timePanel.validate();
		}
	}
}
