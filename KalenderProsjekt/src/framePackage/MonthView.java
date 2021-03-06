package framePackage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import client.Program;
import data.CalendarModel;
import data.Meeting;
import data.Person;

/**
 * This is the MonthView Panel that shows the month planner.
 */
@SuppressWarnings("serial")
public class MonthView implements CalendarView, PropertyChangeListener {

	private JTable monthTable;
	private DefaultTableModel tableModel;
	private CalendarModel calendarModel;
	private GregorianCalendar calendar;
	private String title;
	private String[] columnHeaders;
	private JScrollPane scrollPane;

	/**
	 * Constructs the MonthView Panel.
	 */
	public MonthView(CalendarModel calendarModel) {
		calendar = calendarModel.getCalendar();
		this.calendarModel = calendarModel;
		this.calendarModel.addPropertyChangeListener(this);

		// Creating a non-editable month table
		monthTable = new JTable(tableModel) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disable the editing of any cell
			}
		};

		// Creates a table model
		int weeks = 6;
		columnHeaders = new String[8];
		columnHeaders[0] = "Uke";
		tableModel = new DefaultTableModel(columnHeaders, weeks);

		// Sets every week in month in the 1st column
		GregorianCalendar weekCalendar = new GregorianCalendar();
		weekCalendar.setTimeInMillis(calendar.getTimeInMillis());
		int thisWeekOfMonth = weekCalendar.get(GregorianCalendar.WEEK_OF_MONTH);
		weekCalendar.add(GregorianCalendar.WEEK_OF_MONTH, -thisWeekOfMonth);

		SimpleDateFormat weekFormat = new SimpleDateFormat("ww");
		for (int i = 0; i < weeks; i++) {
			String weekText = weekFormat.format(weekCalendar.getTime());
			tableModel.setValueAt(weekText, i, 0);
			weekCalendar.add(GregorianCalendar.WEEK_OF_MONTH, 1);
		}

		// Sets this month's title
		setMonthTitle();

		// Sets table headers with corresponding days
		setHeaders();

		// Sets the new month into the table
		monthTable.setModel(tableModel);
		monthTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		monthTable.setRowSelectionAllowed(false);
		monthTable.setRowHeight(80);
		monthTable.getColumnModel().getColumn(0).setPreferredWidth(0);

		monthTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = monthTable.rowAtPoint(evt.getPoint());
				int weekDay = monthTable.columnAtPoint(evt.getPoint()) - 1;
				if (row >= 0 && weekDay >= 0) {
					int firstDayOfWeek = -calendar
							.get(GregorianCalendar.DAY_OF_WEEK) + 2;
					calendar.add(GregorianCalendar.DAY_OF_WEEK, weekDay
							+ firstDayOfWeek);
					int calWeekOfMonth = calendar
							.get(GregorianCalendar.WEEK_OF_MONTH);
					calendar.add(GregorianCalendar.WEEK_OF_MONTH, row
							- calWeekOfMonth);
					MonthView.this.calendarModel.changeDate();
					Program.dw.setView(Program.dw.dayView);
				}
			}
		});

		int daysInWeek = 7;
		for (int dayOfWeek = 1; dayOfWeek <= daysInWeek; dayOfWeek++) {
			monthTable.getColumnModel().getColumn(dayOfWeek)
					.setCellRenderer(new MonthTableCellRenderer(calendarModel));
		}

		scrollPane = new JScrollPane(monthTable);
	}

	/**
	 * Creates a new month view.
	 */
	public void createMonthTable() {

		// Sets this month's title
		setMonthTitle();

		// Sets table headers with corresponding days
		setHeaders();

		// Creates the month data
		createMonth();

		monthTable.getColumnModel().getColumn(0).setPreferredWidth(0);

		int daysInWeek = 7;
		for (int dayOfWeek = 1; dayOfWeek <= daysInWeek; dayOfWeek++) {
			monthTable.getColumnModel().getColumn(dayOfWeek)
					.setCellRenderer(new MonthTableCellRenderer(calendarModel));
		}
	}

	/**
	 * Sets this month's title.
	 */
	private void setMonthTitle() {
		SimpleDateFormat titleFormat = new SimpleDateFormat("MMMMM yyyy");
		title = titleFormat.format(calendar.getTime());
	}

	/**
	 * Sets the table headers with corresponding days.
	 */
	private void setHeaders() {

		GregorianCalendar weekDayCalendar = createWeekDayCalendar();

		SimpleDateFormat weekDayFormat = new SimpleDateFormat("EEEE");
		int daysInWeek = 7;
		for (int dayOfWeek = 1; dayOfWeek <= daysInWeek; dayOfWeek++) {
			columnHeaders[dayOfWeek] = weekDayFormat.format(weekDayCalendar
					.getTime());
			weekDayCalendar.add(GregorianCalendar.DAY_OF_WEEK, 1);
		}
		tableModel.setColumnIdentifiers(columnHeaders);

		// Sets every week in month in the 1st column
		int weeks = 6;
		GregorianCalendar weekCalendar = new GregorianCalendar();
		weekCalendar.setTimeInMillis(calendar.getTimeInMillis());
		int thisWeekOfMonth = weekCalendar.get(GregorianCalendar.WEEK_OF_MONTH);
		weekCalendar.add(GregorianCalendar.WEEK_OF_MONTH, -thisWeekOfMonth);

		SimpleDateFormat weekFormat = new SimpleDateFormat("ww");
		for (int i = 0; i < weeks; i++) {
			String weekText = weekFormat.format(weekCalendar.getTime());
			tableModel.setValueAt(weekText, i, 0);
			weekCalendar.add(GregorianCalendar.WEEK_OF_MONTH, 1);
		}
	}

	/**
	 * Creates the month data.
	 */
	private void createMonth() {

		// Clear table
		for (int row = 0; row < tableModel.getRowCount(); row++) {
			for (int column = 1; column < tableModel.getColumnCount(); column++) {
				tableModel.setValueAt(null, row, column);
			}
		}

		GregorianCalendar monthCalendar = createMonthCalendar();

		// Sets this month's meetings
		int daysInMonth = monthCalendar
				.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		for (int dayOfMonth = 1; dayOfMonth <= daysInMonth; dayOfMonth++) {
			int week = monthCalendar.get(GregorianCalendar.WEEK_OF_MONTH);
			int dayOfWeek = monthCalendar.get(GregorianCalendar.DAY_OF_WEEK) - 1;

			// Sunday -.-
			if (dayOfWeek == 0)
				dayOfWeek += 7;

			// Sets the day of the month

			ArrayList<Meeting> todaysMeetings = new ArrayList<Meeting>();

			List<Person> persons = calendarModel.getSelectedPersons();
			for (Person person : persons) {

				ArrayList<Meeting> meetings = calendarModel
						.getAllMeetingsOfPerson(person, true);
				for (Meeting meeting : meetings) {

					GregorianCalendar meetingCalendar = new GregorianCalendar();

					// Starting time of meeting
					long startTime = meeting.getStartTime();
					meetingCalendar.setTimeInMillis(startTime);

					// Sets the meetings at the given times
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
					if (sdf.format(monthCalendar.getTime()).equals(
							sdf.format(meetingCalendar.getTime()))) {

						if (!todaysMeetings.contains(meeting)) {
							todaysMeetings.add(meeting);
						}
					}
				}
			}
			monthCalendar.add(GregorianCalendar.DAY_OF_MONTH, 1);

			Object[] shit = { dayOfMonth, todaysMeetings };
			tableModel.setValueAt(shit, week, dayOfWeek);
		}
	}

	/**
	 * Returns a calendar that starts on the first day of the month.
	 * 
	 * @return monthCalendar
	 */
	private GregorianCalendar createMonthCalendar() {
		GregorianCalendar monthCalendar = new GregorianCalendar();
		monthCalendar.setTimeInMillis(calendar.getTimeInMillis());
		int thisDayOfMonth = monthCalendar.get(GregorianCalendar.DAY_OF_MONTH);
		monthCalendar.add(GregorianCalendar.DAY_OF_MONTH, -thisDayOfMonth + 1);
		return monthCalendar;
	}

	/**
	 * Returns a calendar that starts on Monday.
	 * 
	 * @return weekCalendar
	 */
	private GregorianCalendar createWeekDayCalendar() {
		GregorianCalendar weekDayCalendar = new GregorianCalendar();
		weekDayCalendar.setTimeInMillis(calendar.getTimeInMillis());
		int firstDayOfWeek = -weekDayCalendar
				.get(GregorianCalendar.DAY_OF_WEEK) + 2;
		weekDayCalendar.add(GregorianCalendar.DAY_OF_WEEK, firstDayOfWeek);
		return weekDayCalendar;
	}

	/**
	 * Generates the title of month panel.
	 * 
	 * @return the title of month panel.
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/**
	 * Shows nest month.
	 */
	@Override
	public void next() {
		calendar.add(GregorianCalendar.MONTH, 1);
		calendarModel.changeDate();
		createMonthTable();
	}

	/**
	 * Shows previous month.
	 */
	@Override
	public void prev() {
		calendar.add(GregorianCalendar.MONTH, -1);
		calendarModel.changeDate();
		createMonthTable();
	}

	/**
	 * Gets the month panel.
	 * 
	 * @return month panel.
	 */
	@Override
	public JComponent getPanel() {
		return scrollPane;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case CalendarModel.CALENDAR_LOADED_Property:
			createMonthTable();
			break;
		case CalendarModel.MEETINGS_CHANGED_Property:
			createMonthTable();
			break;
		case CalendarModel.NOTIFICATIONS_CHANGED_Property:
			createMonthTable();
			break;
		case CalendarModel.SELECTED_Property:
			createMonthTable();
			break;
		case CalendarModel.DATE_CHANGED_Property:
			createMonthTable();
			break;
		case CalendarModel.PERSONS_ADDED_Property:
			createMonthTable();
			break;
		default:
			break;
		}
	}
}
