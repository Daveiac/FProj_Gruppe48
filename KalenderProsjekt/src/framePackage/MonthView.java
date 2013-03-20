package framePackage;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
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

	// private JPanel monthPanel;
	// private int realDay, realMonth, realYear, currentMonth, currentYear;
	// public static final String[] months = { "Januar", "Februar", "Mars",
	// "April", "Mai", "Juni", "Juli", "August", "September", "Oktober",
	// "November", "Desember" };

	// public static void main(String args[]) {
	// CalendarModel cm = new CalendarModel();
	// MonthView mw = new MonthView(cm);
	// cm.init();
	// JFrame frame = new JFrame("monthView test: ");
	// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// frame.setContentPane(mw.getPanel());
	// frame.setSize(700, 600);
	// frame.setVisible(true);
	// }

	/**
	 * Constructs the MonthView Panel.
	 */
	public MonthView(CalendarModel calendarModel) {
		initialize(calendarModel);
		// refreshCalendar();
	}

	private void initialize(CalendarModel calendarModel) {
		calendar = new GregorianCalendar();
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
		monthTable.setRowHeight(300);
		monthTable.getColumnModel().getColumn(0).setPreferredWidth(0);

		monthTable.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = monthTable.rowAtPoint(evt.getPoint());
		        int col = monthTable.columnAtPoint(evt.getPoint());
		        if (row >= 0 && col >= 0) {
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
		//
		// realDay = cal.get(GregorianCalendar.DAY_OF_MONTH);
		// realMonth = cal.get(GregorianCalendar.MONTH);
		// realYear = cal.get(GregorianCalendar.YEAR);
		// currentMonth = realMonth;
		// currentYear = realYear;
		//
		// monthTable = new JTable(tableModel) {
		// public boolean isCellEditable(int rowIndex, int colIndex) {
		// return false; // Disable the editing of any cell
		// }
		// };
		// monthTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// monthTable.setRowSelectionAllowed(false);
		// monthTable.setRowHeight(64);
		// monthTable.getColumnModel().getColumn(0).setPreferredWidth(10);
		// for (int i = 1; i < 8; i++) {
		// monthTable.getColumnModel().getColumn(i)
		// .setCellRenderer(new MonthTableCellRenderer());
		// }
		//
		// monthPanel = new JPanel();
		// monthPanel.setSize(600, 500);
		// JScrollPane jsp = new JScrollPane(monthTable);
		// jsp.setPreferredSize(new Dimension(800, 407));
		// monthPanel.add(jsp);
		//
		// title = months[currentMonth] + ", " + currentYear;
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
			// System.out.println("dashitMONTH");
			// System.out.println(shit[0].toString());
			// System.out.println(shit[1].toString());
			tableModel.setValueAt(shit, week, dayOfWeek);
		}

		// List<Person> persons = calendarModel.getSelectedPersons();
		// for (Person person : persons) {
		// ArrayList<Meeting> meetings = calendarModel.getMeetings(person);
		// setMeetings(calendar, tableModel, dayOfWeek, meetings);
		// }
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

	// private void refreshCalendar() {
	// int nDays, monthStart, weekStart;
	// title = months[currentMonth] + ", " + currentYear;
	// GregorianCalendar cal = new GregorianCalendar(currentYear,
	// currentMonth, 1);
	// nDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
	// monthStart = cal.get(GregorianCalendar.DAY_OF_WEEK);
	// monthStart = (monthStart == 1) ? 6 : monthStart - 2;
	// weekStart = cal.get(GregorianCalendar.WEEK_OF_YEAR);
	// // Clear table
	// for (int i = 0; i < 6; i++) {
	// for (int j = 0; j < 8; j++) {
	// tableModel.setValueAt(null, i, j);
	// }
	// }
	// // Draw calendar
	// for (int i = 0; i < 6; i++) {
	// tableModel.setValueAt(weekStart + i, i, 0);
	// }
	// for (int i = 0; i < nDays; i++) {
	// int row = new Integer((i + monthStart) / 7);
	// int column = (i + monthStart) % 7 + 1;
	// tableModel
	// .setValueAt(new JList<String>(new String[] {
	// i + 1 + ". " + "Møte 1", "AvtaleYO", "zomg" }),
	// row, column);
	// }
	// }

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
		createMonthTable();
	}

	/**
	 * Shows previous month.
	 */
	@Override
	public void prev() {
		calendar.add(GregorianCalendar.MONTH, -1);
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
		default:
			break;
		}
	}
}
