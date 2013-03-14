package framePackage;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import data.Meeting;
import data.MeetingRoom;
import data.Person;
import data.Team;

/**
 * This is the WeekView Panel that shows the week planner.
 */
@SuppressWarnings("serial")
public class WeekView extends JPanel implements CalendarView {

	private GregorianCalendar calendar;
	private String title;
	private DefaultTableModel tableModel;
	private JTable tableWeek;

	/**
	 * Constructs the WeekView Panel.
	 */
	public WeekView() {
		calendar = new GregorianCalendar();

		// test code
		ArrayList<Meeting> meetings = new ArrayList<Meeting>();
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

		JScrollPane scrollPane = new JScrollPane(tableWeek);
		scrollPane.setPreferredSize(new Dimension(800, 407));

		add(scrollPane);
	}

	/**
	 * Creates a new week view.
	 */
	public void createWeek() {

		// sets this week's title
		SimpleDateFormat titleFormat = new SimpleDateFormat("ww, MMMMM yyyy");
		title = "Uke " + titleFormat.format(calendar.getTime());

		// saves this day
		int thisDayOfWeek = calendar.get(GregorianCalendar.DAY_OF_WEEK);
		// sets the day to monday
		int firstDayOfWeek = -calendar.get(GregorianCalendar.DAY_OF_WEEK) + 2;
		calendar.add(GregorianCalendar.DAY_OF_WEEK, firstDayOfWeek);

		// sets table headers with corresponding days
		SimpleDateFormat weekFormat = new SimpleDateFormat("EEEE dd. MMM.");
		String[] days = new String[8];
		days[0] = "Tid";
		for (int i = 1; i < 8; i++) {
			days[i] = weekFormat.format(calendar.getTime());
			calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
		}

		// sets the day today
		thisDayOfWeek -= 9;
		calendar.add(GregorianCalendar.DAY_OF_WEEK, thisDayOfWeek);

		// creates a table model
		tableModel = new DefaultTableModel(days, 24);

		// ---------------------------------------------
		// WEIRD
		// ---------------------------------------------
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		int firstHourOfDay = calendar.get(GregorianCalendar.HOUR_OF_DAY);
		int firstMinuteofHour = calendar.get(GregorianCalendar.MINUTE);
//		int year = calendar.get(Calendar.YEAR);
//		int month = calendar.get(Calendar.MONTH);
//		int date = calendar.get(Calendar.DATE);
//		calendar.set(year, month, date, 0, 0, 0);
		System.out.println(firstHourOfDay);
		System.out.println(GregorianCalendar.HOUR_OF_DAY);
		System.out.println(timeFormat.format(calendar.getTime()));

		calendar.add(GregorianCalendar.HOUR_OF_DAY, firstHourOfDay);

		for (int i = 0; i < 24; i++) {
			//			String time = String.format("%02d", i) + ":00";
			String time = timeFormat.format(calendar.getTime());
			tableModel.setValueAt(time, i, 0);
			calendar.add(GregorianCalendar.HOUR_OF_DAY, 1);
		}

		tableWeek.setModel(tableModel);
		tableWeek.getColumnModel().getColumn(0).setPreferredWidth(0);
		tableWeek.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableWeek.setRowSelectionAllowed(false);
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
	}

	/**
	 * Shows previous week.
	 */
	@Override
	public void prev() {
		calendar.add(GregorianCalendar.WEEK_OF_YEAR, -1);
		createWeek();
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