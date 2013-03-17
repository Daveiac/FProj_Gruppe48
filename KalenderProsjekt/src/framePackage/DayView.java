package framePackage;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import data.*;

/**
 * This is the DayView Panel that shows the day planner.
 */
@SuppressWarnings("serial")
public class DayView extends JPanel implements CalendarView {

	private GregorianCalendar calendar;
	private String title;
	private DefaultTableModel tableModel;
	private JTable dayTable;
	private String[] columnHeaders;
	
	private CalendarModel calendarModel;

	// ----- test code -----
	ArrayList<Meeting> meetingsTest;
	// ----- test code end -----

	/**
	 * Constructs the DayView Panel.
	 */
	public DayView() {
		calendar = new GregorianCalendar();
//		calendarModel.getCalendarModel();
//		meetingsTest = calendarModel.



		// ----- test code -----
		meetingsTest = new ArrayList<Meeting>();
		ArrayList<Person> members = new ArrayList<Person>();
		Team team = new Team(0, null, members);
		MeetingRoom room = new MeetingRoom("0");
		Person creator = new Person(null, 00000000, "Dav", "Hov", "dave", "1234");
		members.add(creator);
		long startTime = new GregorianCalendar(2013, 2, 14, 16, 30).getTimeInMillis();
		long endTime = new GregorianCalendar(2013, 2, 14, 17, 30).getTimeInMillis();
		meetingsTest.add(new Meeting(0, "suppem�te", "inHell", startTime, endTime, "This is a desc", team, room, creator));
		startTime = new GregorianCalendar(2013, 2, 15, 10, 30).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 15, 11, 00).getTimeInMillis();
		meetingsTest.add(new Meeting(0, "suppem�te2", "stillInHell", startTime, endTime, "This is a desc", team, room, creator));
		startTime = new GregorianCalendar(2013, 2, 14, 16, 30).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 14, 17, 30).getTimeInMillis();
		meetingsTest.add(new Meeting(0, "suppem�te3", "wtfWhyInHell", startTime, endTime, "This is a desc", team, room, creator));
		startTime = new GregorianCalendar(2013, 2, 14, 12, 00).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 14, 15, 30).getTimeInMillis();
		meetingsTest.add(new Meeting(0, "suppem�te4", "fuInHell", startTime, endTime, "This is a desc", team, room, creator));
		startTime = new GregorianCalendar(2013, 2, 16, 12, 00).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 16, 15, 30).getTimeInMillis();
		meetingsTest.add(new Meeting(0, "suppem�te5", "careInHell", startTime, endTime, "This is a desc", team, room, creator));
		startTime = new GregorianCalendar(2013, 2, 17, 03, 00).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 17, 04, 30).getTimeInMillis();
		meetingsTest.add(new Meeting(0, "suppem�te6", "w00t?", startTime, endTime, "This is a desc", team, room, creator));
		// ----- test code end -----




		// Creating a non-editable table
		dayTable = new JTable() {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};

		// Creates a table model
		int quarters = 24 * 4;
		columnHeaders = new String[2];
		columnHeaders[0] = "Tid";
		tableModel = new DefaultTableModel(columnHeaders, quarters);

		// Sets the clock every 15 minutes in the 1st column
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		// Saves this hour and minute
		int thisHourOfDay = calendar.get(GregorianCalendar.HOUR_OF_DAY);
		calendar.add(GregorianCalendar.HOUR_OF_DAY, -thisHourOfDay);
		int thisMinuteOfDay = calendar.get(GregorianCalendar.MINUTE);
		calendar.add(GregorianCalendar.MINUTE, -thisMinuteOfDay);

		for (int i = 0; i < quarters; i++) {
			String time = timeFormat.format(calendar.getTime());
			tableModel.setValueAt(time, i, 0);
			calendar.add(GregorianCalendar.MINUTE, 15);
		}

		// Sets this hour and minute
		int hoursOfDay = 23;
		thisHourOfDay -= hoursOfDay;
		calendar.add(GregorianCalendar.HOUR_OF_DAY, thisHourOfDay);
		int minutesOfHour = 60;
		thisMinuteOfDay -= minutesOfHour;
		calendar.add(GregorianCalendar.MINUTE, thisMinuteOfDay);

		// Creates the new day
		createDayTable();

		// Sets the new day into the table
		dayTable.setModel(tableModel);
		dayTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dayTable.setRowSelectionAllowed(false);

		JScrollPane scrollPane = new JScrollPane(dayTable);
		scrollPane.setPreferredSize(new Dimension(800, 407));

		add(scrollPane);
	}

	/**
	 * Creates a new day view.
	 */
	public void createDayTable() {

		// Sets this day's title
		setDayTitle();

		// Sets table headers
		int numberOfDays = 1;
		SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
		columnHeaders[numberOfDays] = dayFormat.format(calendar.getTime());
		tableModel.setColumnIdentifiers(columnHeaders);

		// Creates the day data
		createDay(calendar, tableModel, numberOfDays);

		// Sets the new week into the table
		dayTable.setModel(tableModel);
		dayTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		dayTable.getColumnModel().getColumn(1).setPreferredWidth(718);
		dayTable.getColumnModel().getColumn(1).setCellRenderer(new DayTableCellRenderer());
	}

	/**
	 * Sets this day's title.
	 */
	public void setDayTitle() {
		SimpleDateFormat titleFormat = new SimpleDateFormat("dd. MMMMM");
		title = titleFormat.format(calendar.getTime());
	}

	/**
	 * Creates the new day's data.
	 */
	public void createDay(GregorianCalendar calendar, DefaultTableModel tableModel, int dayOfWeek) {

		for (int i = 0; i < tableModel.getRowCount(); i++) {
			tableModel.setValueAt(null, i, dayOfWeek);
		}

		// Sets today's meetings
		setMeetings(calendar, tableModel, dayOfWeek, meetingsTest);
	}

	/**
	 * Attaches the meetings to the table.
	 * @param meetings	a list of meeting objects
	 */
	public void setMeetings(GregorianCalendar calendar, DefaultTableModel tableModel, int dayOfWeek, ArrayList<Meeting> meetings) {

		for (Meeting meeting : meetings) {
			GregorianCalendar meetingCalendar = new GregorianCalendar();

			// Starting time of meeting
			long startTime = meeting.getStartTime();
			meetingCalendar.setTimeInMillis(startTime);

			int startHour = meetingCalendar.get(GregorianCalendar.HOUR_OF_DAY);
			int startMinute = meetingCalendar.get(GregorianCalendar.MINUTE);
			int startTableTime = startHour * 4 + startMinute / 15;

			// Ending time of meeting
			long endTime = meeting.getEndTime();
			meetingCalendar.setTimeInMillis(endTime);

			int endHour = meetingCalendar.get(GregorianCalendar.HOUR_OF_DAY);
			int endMinute = meetingCalendar.get(GregorianCalendar.MINUTE);
			int endTableTime = endHour * 4 + endMinute / 15;

			// Sets the meetings at the given times
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			if (sdf.format(calendar.getTime()).equals(sdf.format(meetingCalendar.getTime()))) {
				for (int time = startTableTime; time < endTableTime; time++) {
					tableModel.setValueAt(meeting, time, dayOfWeek);
				}
			}
		}
	}

	/**
	 * Gets the day view.
	 * @return this	The day view
	 */
	public DayView getDayView() {
		return this;
	}

	/**
	 * Generates the title of day panel.
	 * @return the title of day panel.
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/**
	 * Shows next day.
	 */
	@Override
	public void next() {
		calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
		createDayTable();
	}

	/**
	 * Shows previous day.
	 */
	@Override
	public void prev() {
		calendar.add(GregorianCalendar.DAY_OF_MONTH, -1);
		createDayTable();
	}

	/**
	 * Gets the day panel.
	 * @return day panel.
	 */
	@Override
	public JPanel getPanel() {
		return this;
	}
}