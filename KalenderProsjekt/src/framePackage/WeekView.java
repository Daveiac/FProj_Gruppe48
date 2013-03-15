package framePackage;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import data.*;

/**
 * This is the WeekView Panel that shows the week planner.
 */
@SuppressWarnings("serial")
public class WeekView extends JPanel implements CalendarView {

	private GregorianCalendar calendar;
	private String title;
	private DefaultTableModel tableModel;
	private JTable tableWeek;

	//test code
	ArrayList<Meeting> meetings;

	/**
	 * Constructs the WeekView Panel.
	 */
	public WeekView() {
		calendar = new GregorianCalendar();

		// test code
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

		// Creating a non-editable table
		tableWeek = new JTable() {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};

		createWeek();
		setMeetings(meetings);

		JScrollPane scrollPane = new JScrollPane(tableWeek);
		scrollPane.setPreferredSize(new Dimension(800, 407));

		add(scrollPane);
	}

	public void setMeetings(ArrayList<Meeting> meetings) {
		for (Meeting meeting : meetings) {
			GregorianCalendar mordi = new GregorianCalendar();
			long startTime = meeting.getStartTime();
			mordi.setTimeInMillis(startTime);

			int startHour = mordi.get(GregorianCalendar.HOUR_OF_DAY);
			int startMinute = mordi.get(GregorianCalendar.MINUTE);
			int startRow = startHour * 4 + startMinute / 15;
			int startColumn = mordi.get(GregorianCalendar.DAY_OF_WEEK) - 1;

			long endTime = meeting.getEndTime();
			mordi.setTimeInMillis(endTime);
			int endHour = mordi.get(GregorianCalendar.HOUR_OF_DAY);
			int endMinute = mordi.get(GregorianCalendar.MINUTE);
			int endRow = endHour * 4 + endMinute / 15;
			int endColumn = mordi.get(GregorianCalendar.DAY_OF_WEEK) - 1;

			tableModel.setValueAt(42, startRow, startColumn);
			tableModel.setValueAt("troll", endRow, endColumn);
		}

	}

	/**
	 * Creates a new week view.
	 */
	public void createWeek() {

		// Sets this week's title
		SimpleDateFormat titleFormat = new SimpleDateFormat("ww, MMMMM yyyy");
		title = "Uke " + titleFormat.format(calendar.getTime());

		// Saves this day
		int thisDayOfWeek = calendar.get(GregorianCalendar.DAY_OF_WEEK);
		// Sets the day to Monday
		int firstDayOfWeek = -calendar.get(GregorianCalendar.DAY_OF_WEEK) + 2;
		calendar.add(GregorianCalendar.DAY_OF_WEEK, firstDayOfWeek);

		// Sets table headers with corresponding days
		SimpleDateFormat weekFormat = new SimpleDateFormat("EEEE dd. MMM.");
		int columns = 8;
		String[] days = new String[columns];
		days[0] = "Tid";
		for (int i = 1; i < columns; i++) {
			days[i] = weekFormat.format(calendar.getTime());
			calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
		}

		// Sets the day today
		thisDayOfWeek -= 9;
		calendar.add(GregorianCalendar.DAY_OF_WEEK, thisDayOfWeek);

		// Creates a table model
		int quarters = 24 * 4;
		tableModel = new DefaultTableModel(days, quarters);

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

		// Sets the new week into the table
		tableWeek.setModel(tableModel);
		tableWeek.getColumnModel().getColumn(0).setPreferredWidth(0);
		tableWeek.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableWeek.setRowSelectionAllowed(false);
		tableWeek.getSelectionModel();
	}

	/**
	 * Generates the title of week panel.
	 * @return the title of week panel.
	 */
	@Override
	public String getTitle(){
		return title;
	}

	/**
	 * Shows next week.
	 */
	@Override
	public void next() {
		calendar.add(GregorianCalendar.WEEK_OF_YEAR, 1);
		createWeek();
		setMeetings(meetings);
	}

	/**
	 * Shows previous week.
	 */
	@Override
	public void prev() {
		calendar.add(GregorianCalendar.WEEK_OF_YEAR, -1);
		createWeek();
		setMeetings(meetings);
	}

	/**
	 * Gets the week panel.
	 * @return week panel.
	 */
	@Override
	public JPanel getPanel() {
		return this;
	}
}