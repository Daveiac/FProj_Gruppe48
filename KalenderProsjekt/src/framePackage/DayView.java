package framePackage;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import data.CalendarModel;
import data.Meeting;
import data.Person;

/**
 * This is the DayView Panel that shows the day planner.
 */
@SuppressWarnings("serial")
public class DayView extends JPanel implements CalendarView, PropertyChangeListener {

	private GregorianCalendar calendar;
	private String title;
	private DefaultTableModel tableModel;
	private JTable dayTable;
	private String[] columnHeaders;

	private CalendarModel calendarModel;
	private List<Person> persons;

	/**
	 * Constructs the DayView Panel.
	 */
	public DayView(CalendarModel calendarModel) {
		calendar = new GregorianCalendar();
		this.calendarModel = calendarModel;
		this.calendarModel.addPropertyChangeListener(this);

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
		// createDayTable();

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
		dayTable.getColumnModel()
		.getColumn(1)
		.setCellRenderer(
				new DayTableCellRenderer(calendarModel, persons));
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
	public void createDay(GregorianCalendar calendar,
			DefaultTableModel tableModel, int dayOfWeek) {

		for (int i = 0; i < tableModel.getRowCount(); i++) {
			tableModel.setValueAt(null, i, dayOfWeek);
		}

		// Sets today's meetings
		persons = calendarModel.getSelectedPersons();
		for (Person person : persons) {
			ArrayList<Meeting> meetings = calendarModel.getMeetings(person);
			setMeetings(calendar, tableModel, dayOfWeek, meetings);
		}
	}

	/**
	 * Attaches the meetings to the table.
	 * 
	 * @param meetings
	 *            a list of meeting objects
	 */
	public void setMeetings(GregorianCalendar calendar,
			DefaultTableModel tableModel, int dayOfWeek,
			ArrayList<Meeting> meetings) {

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
	 * 
	 * @return this The day view
	 */
	public DayView getDayView() {
		return this;
	}

	/**
	 * Generates the title of day panel.
	 * 
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
	 * 
	 * @return day panel.
	 */
	@Override
	public JPanel getPanel() {
		return this;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case CalendarModel.CALENDAR_LOADED_Property:
			createDayTable();
			break;
		case CalendarModel.MEETING_ADDED_Property:
			createDayTable();
			break;
		case CalendarModel.MEETING_CHANGED_Property:
			createDayTable();
			break;
		case CalendarModel.MEETING_REMOVED_Property:
			createDayTable();
			break;
		default:
			break;
		}
	}
}