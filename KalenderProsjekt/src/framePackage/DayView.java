package framePackage;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import data.Meeting;
import data.MeetingRoom;
import data.Person;
import data.Team;

/**
 * This is the DayView Panel that shows the day planner.
 */
@SuppressWarnings("serial")
public class DayView extends JPanel implements CalendarView {

	private GregorianCalendar calendar;
	private String title;
	private DefaultTableModel tableModel;
	private JTable dayTable;
	String[] columnHeaders;

	// ----- test code -----
	ArrayList<Meeting> meetings;
	// ----- test code end -----

	/**
	 * Constructs the DayView Panel.
	 */
	public DayView() {
		calendar = new GregorianCalendar();



		// ----- test code -----
		meetings = new ArrayList<Meeting>();
		ArrayList<Person> members = new ArrayList<Person>();
		Team team = new Team(0, null, members);
		MeetingRoom room = new MeetingRoom("0");
		Person creator = new Person(null, 00000000, "Dav", "Hov", "dave", "1234");
		members.add(creator);
		long startTime = new GregorianCalendar(2013, 2, 14, 16, 30).getTimeInMillis();
		long endTime = new GregorianCalendar(2013, 2, 14, 17, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøte", "inHell", startTime, endTime, "This is a desc", team, room, creator));
		startTime = new GregorianCalendar(2013, 2, 15, 10, 30).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 15, 11, 00).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøte2", "stillInHell", startTime, endTime, "This is a desc", team, room, creator));
		startTime = new GregorianCalendar(2013, 2, 14, 16, 30).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 14, 17, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøte3", "wtfWhyInHell", startTime, endTime, "This is a desc", team, room, creator));
		startTime = new GregorianCalendar(2013, 2, 14, 12, 00).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 14, 15, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøte4", "fuInHell", startTime, endTime, "This is a desc", team, room, creator));
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

		// Sets the clock every 15 minutes
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
		createDay();

		// Sets the new week into the table
		dayTable.setModel(tableModel);
		dayTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		dayTable.getColumnModel().getColumn(1).setPreferredWidth(718);
		dayTable.getColumnModel().getColumn(1).setCellRenderer(new DayTableCellRenderer());
	}

	/**
	 * Creates a new day view.
	 */
	public void createDay() {

		// Sets this day's title
		setDayTitle();

		// Sets table headers
		SimpleDateFormat weekFormat = new SimpleDateFormat("EEEE");

		columnHeaders[1] = weekFormat.format(calendar.getTime());

		tableModel.setColumnIdentifiers(columnHeaders);

		for (int i = 0; i < tableModel.getRowCount(); i++) {
			tableModel.setValueAt(null, i, 1);
		}

		// Sets today's meetings
		setMeetings(meetings);
	}

	/**
	 * Sets this day's title.
	 */
	public void setDayTitle() {
		SimpleDateFormat titleFormat = new SimpleDateFormat("dd. MMMMM");
		title = titleFormat.format(calendar.getTime());
	}

	/**
	 * Attaches the meetings to the table.
	 * @param meetings	a list of meeting objects
	 */
	public void setMeetings(ArrayList<Meeting> meetings) {

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

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			if (sdf.format(calendar.getTime()).equals(sdf.format(meetingCalendar.getTime()))) {
				for (int time = startTableTime; time < endTableTime; time++) {
					tableModel.setValueAt(meeting, time, 1);
				}
			}
		}

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