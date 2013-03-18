package framePackage;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import data.CalendarModel;
import data.Meeting;
import data.Person;

/**
 * This is the MonthView Panel that shows the month planner.
 */
@SuppressWarnings("serial")
public class MonthView extends JPanel implements CalendarView,
PropertyChangeListener {

	private JTable monthTable;
	private DefaultTableModel tableModel;
	private CalendarModel calendarModel;
	private GregorianCalendar calendar;
	private String title;
	private String[] columnHeaders;

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
//		refreshCalendar();
	}

	@SuppressWarnings("serial")
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
		columnHeaders[0] = "Tid";
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

		// Sets the new month into the table
		monthTable.setModel(tableModel);
		monthTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		monthTable.setRowSelectionAllowed(false);

		JScrollPane scrollPane = new JScrollPane(monthTable);
		scrollPane.setPreferredSize(new Dimension(800, 407));

		add(scrollPane);

		//
		// tableModel = new DefaultTableModel();
		// String[] headers = { "Uke", "Mandag", "Tirsdag", "Onsdag", "Torsdag",
		// "Fredag", "Lørdag", "Søndag" };
		// tableModel.setColumnIdentifiers(headers);
		// tableModel.setRowCount(6);
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
		GregorianCalendar monthCalendar = new GregorianCalendar();
		monthCalendar.setTimeInMillis(calendar.getTimeInMillis());
		int thisDayOfMonth = monthCalendar.get(GregorianCalendar.DAY_OF_MONTH);
		monthCalendar.add(GregorianCalendar.DAY_OF_MONTH, -thisDayOfMonth);

		SimpleDateFormat weekDay = new SimpleDateFormat("EEEE");
		int daysInWeek = 7;
		for (int dayOfWeek = 1; dayOfWeek <= daysInWeek; dayOfWeek++) {
			columnHeaders[dayOfWeek] = weekDay.format(monthCalendar.getTime());
			// dayView.createDay(monthCalendar, tableModel, dayOfWeek);
			createMonth(monthCalendar);
			monthCalendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
		}
		tableModel.setColumnIdentifiers(columnHeaders);

		// Sets the new week into the table
		monthTable.setModel(tableModel);
		monthTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		monthTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		monthTable.setRowSelectionAllowed(false);
		monthTable.getSelectionModel();
		for (int i = 1; i < 8; i++) {
			monthTable
			.getColumnModel()
			.getColumn(i)
			.setCellRenderer(
					new MonthTableCellRenderer(calendarModel));
		}
	}

	/**
	 * Sets this month's title.
	 */
	private void setMonthTitle() {
		SimpleDateFormat titleFormat = new SimpleDateFormat("MMMMM yyyy");
		title = titleFormat.format(calendar.getTime());
	}

	private void createMonth(GregorianCalendar monthCalendar) {

		// Clear table
		for (int row = 0; row < tableModel.getRowCount(); row++) {
			for (int column = 0; column < tableModel.getColumnCount(); column++) {
				tableModel.setValueAt(null, row, column);
			}
		}

		// Sets this month's meetings
		for (int dayOfMonth = 0; dayOfMonth < calendar.get(GregorianCalendar.MONTH); dayOfMonth++) {
			int week = calendar.get(GregorianCalendar.WEEK_OF_MONTH);
			int dayOfWeek = calendar.get(GregorianCalendar.DAY_OF_WEEK);
			
			JList<Meeting> meetingList = new JList<Meeting>();
			DefaultListModel<Meeting> listModel = new DefaultListModel<Meeting>();

			List<Person> persons = calendarModel.getSelectedPersons();
			for (Person person : persons) {
				ArrayList<Meeting> meetings = calendarModel.getMeetings(person);

				for (Meeting meeting : meetings) {
					// Sets the meetings at the given times
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
					if (sdf.format(calendar.getTime()).equals(
							sdf.format(monthCalendar.getTime()))) {

						listModel.addElement(meeting);
					}
				}
			}
			tableModel.setValueAt(meetingList, week, dayOfWeek);
		}

		// for (int row = 0; row < tableModel.getRowCount(); row++) {
		// for (int column = 0; column < tableModel.getColumnCount(); column++)
		// {
		//
		// }
		// }

		// List<Person> persons = calendarModel.getSelectedPersons();
		// for (Person person : persons) {
		// ArrayList<Meeting> meetings = calendarModel.getMeetings(person);
		// setMeetings(calendar, tableModel, dayOfWeek, meetings);
		// }
	}

//	private void refreshCalendar() {
//		int nDays, monthStart, weekStart;
//		title = months[currentMonth] + ", " + currentYear;
//		GregorianCalendar cal = new GregorianCalendar(currentYear,
//				currentMonth, 1);
//		nDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
//		monthStart = cal.get(GregorianCalendar.DAY_OF_WEEK);
//		monthStart = (monthStart == 1) ? 6 : monthStart - 2;
//		weekStart = cal.get(GregorianCalendar.WEEK_OF_YEAR);
//		// Clear table
//		for (int i = 0; i < 6; i++) {
//			for (int j = 0; j < 8; j++) {
//				tableModel.setValueAt(null, i, j);
//			}
//		}
//		// Draw calendar
//		for (int i = 0; i < 6; i++) {
//			tableModel.setValueAt(weekStart + i, i, 0);
//		}
//		for (int i = 0; i < nDays; i++) {
//			int row = new Integer((i + monthStart) / 7);
//			int column = (i + monthStart) % 7 + 1;
//			tableModel
//			.setValueAt(new JList<String>(new String[] {
//					i + 1 + ". " + "Møte 1", "AvtaleYO", "zomg" }),
//					row, column);
//		}
//	}

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
	public JPanel getPanel() {
		return this;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case CalendarModel.CALENDAR_LOADED_Property:
			createMonthTable();
			break;
		case CalendarModel.MEETING_ADDED_Property:
			createMonthTable();
			break;
		case CalendarModel.MEETING_CHANGED_Property:
			createMonthTable();
			break;
		case CalendarModel.MEETING_REMOVED_Property:
			createMonthTable();
			break;
		default:
			break;
		}
	}
}
