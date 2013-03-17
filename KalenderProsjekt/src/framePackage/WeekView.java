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
	private JTable weekTable;
	private DayView dayView;
	String[] columnHeaders;

	// ----- test code -----
	ArrayList<Meeting> meetings;
	// ----- test code end -----

	/**
	 * Constructs the WeekView Panel.
	 */
	public WeekView() {
		calendar = new GregorianCalendar();
		dayView = new DayView();
		dayView.getDayView();





		//		// ----- test code -----
		//		meetings = new ArrayList<Meeting>();
		//		ArrayList<Person> members = new ArrayList<Person>();
		//		Team team = new Team(0, null, members);
		//		MeetingRoom room = new MeetingRoom("0");
		//		Person creator = new Person(null, 00000000, "Dav", "Hov", "dave", "1234");
		//		members.add(creator);
		//		long startTime = new GregorianCalendar(2013, 2, 14, 16, 30).getTimeInMillis();
		//		System.out.println("meeting1: " + startTime);
		//		long endTime = new GregorianCalendar(2013, 2, 14, 17, 30).getTimeInMillis();
		//		meetings.add(new Meeting(0, "suppemøte", "inHell", startTime, endTime, "This is a desc", team, room, creator));
		//		startTime = new GregorianCalendar(2013, 2, 15, 10, 30).getTimeInMillis();
		//		endTime = new GregorianCalendar(2013, 2, 15, 11, 00).getTimeInMillis();
		//		meetings.add(new Meeting(0, "suppemøte2", "stillInHell", startTime, endTime, "This is a desc", team, room, creator));
		//		startTime = new GregorianCalendar(2013, 2, 14, 16, 30).getTimeInMillis();
		//		endTime = new GregorianCalendar(2013, 2, 14, 17, 30).getTimeInMillis();
		//		meetings.add(new Meeting(0, "suppemøte3", "wtfWhyInHell", startTime, endTime, "This is a desc", team, room, creator));
		//		startTime = new GregorianCalendar(2013, 2, 14, 12, 00).getTimeInMillis();
		//		endTime = new GregorianCalendar(2013, 2, 14, 15, 30).getTimeInMillis();
		//		meetings.add(new Meeting(0, "suppemøte4", "fuInHell", startTime, endTime, "This is a desc", team, room, creator));
		//		// ----- test code end -----



		// Creating a non-editable table
		weekTable = new JTable() {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};

		// Creates a table model
		int quarters = 24 * 4;
		columnHeaders = new String[8];
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

		// Creates the new week
		createWeekTable();
		//		dayView.setMeetings(meetings);

		// Sets the new day into the table
		weekTable.setModel(tableModel);
		weekTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		weekTable.setRowSelectionAllowed(false);

		JScrollPane scrollPane = new JScrollPane(weekTable);
		scrollPane.setPreferredSize(new Dimension(800, 407));

		add(scrollPane);
	}

	/**
	 * Creates a new week view.
	 */
	public void createWeekTable() {

		// Sets this week's title
		setWeekTitle();

		// Saves this day
		int thisDayOfWeek = calendar.get(GregorianCalendar.DAY_OF_WEEK);
		// Sets the day to Monday
		int firstDayOfWeek = -calendar.get(GregorianCalendar.DAY_OF_WEEK) + 2;
		calendar.add(GregorianCalendar.DAY_OF_WEEK, firstDayOfWeek);

		// Sets table headers with corresponding days
		SimpleDateFormat weekFormat = new SimpleDateFormat("EEEE dd. MMM.");
		int daysInWeek = 8;
		for (int dayOfWeek = 1; dayOfWeek < daysInWeek; dayOfWeek++) {
			columnHeaders[dayOfWeek] = weekFormat.format(calendar.getTime());
			tableModel.setColumnIdentifiers(columnHeaders);
			dayView.createDay(tableModel, dayOfWeek);
			calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
		}

		// Sets the day today
		thisDayOfWeek -= 9;
		calendar.add(GregorianCalendar.DAY_OF_WEEK, thisDayOfWeek);


		//		// Creates the week data
		//		CreateWeek();

		// Sets the new week into the table
		weekTable.setModel(tableModel);
		weekTable.getColumnModel().getColumn(0).setPreferredWidth(0);
	}

	/**
	 * Sets this week's title.
	 */
	public void setWeekTitle() {
		SimpleDateFormat titleFormat = new SimpleDateFormat("ww, MMMMM yyyy");
		title = "Uke " + titleFormat.format(calendar.getTime());
	}

	public void CreateWeek() {


		//		// Creates a table model
		//		int quarters = 24 * 4;
		//		tableModel = new DefaultTableModel(days, quarters);
		//
		//		// Sets the clock every 15 minutes
		//		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		//		// Saves this hour and minute
		//		int thisHourOfDay = calendar.get(GregorianCalendar.HOUR_OF_DAY);
		//		calendar.add(GregorianCalendar.HOUR_OF_DAY, -thisHourOfDay);
		//		int thisMinuteOfDay = calendar.get(GregorianCalendar.MINUTE);
		//		calendar.add(GregorianCalendar.MINUTE, -thisMinuteOfDay);
		//
		//		for (int i = 0; i < quarters; i++) {
		//			String time = timeFormat.format(calendar.getTime());
		//			tableModel.setValueAt(time, i, 0);
		//			calendar.add(GregorianCalendar.MINUTE, 15);
		//		}
		//
		//		// Sets this hour and minute
		//		int hoursOfDay = 23;
		//		thisHourOfDay -= hoursOfDay;
		//		calendar.add(GregorianCalendar.HOUR_OF_DAY, thisHourOfDay);
		//		int minutesOfHour = 60;
		//		thisMinuteOfDay -= minutesOfHour;
		//		calendar.add(GregorianCalendar.MINUTE, thisMinuteOfDay);

		// Sets the new week into the table
		weekTable.setModel(tableModel);
		weekTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		weekTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		weekTable.setRowSelectionAllowed(false);
		weekTable.getSelectionModel();
		for (int i = 1; i < 8; i++) {
			weekTable.getColumnModel().getColumn(i).setCellRenderer(new DayTableCellRenderer());
		}
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
		createWeekTable();
	}

	/**
	 * Shows previous week.
	 */
	@Override
	public void prev() {
		calendar.add(GregorianCalendar.WEEK_OF_YEAR, -1);
		createWeekTable();
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