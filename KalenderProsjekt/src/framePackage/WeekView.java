package framePackage;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import data.CalendarModel;

/**
 * This is the WeekView Panel that shows the week planner.
 */
@SuppressWarnings("serial")
public class WeekView extends JPanel implements CalendarView,
		PropertyChangeListener {

	private JTable weekTable;
	private DefaultTableModel tableModel;
	private CalendarModel calendarModel;
	private GregorianCalendar calendar;
	private String title;
	private String[] columnHeaders;
	private DayView dayView;
	private GregorianCalendar weekCalendar;
	private static final SimpleDateFormat weekDayFormat = new SimpleDateFormat("EEEE dd. MMM.");

	/**
	 * Constructs the WeekView Panel.
	 */
	public WeekView(CalendarModel calendarModel) {
		calendar = new GregorianCalendar();
		this.calendarModel = calendarModel;
		this.calendarModel.addPropertyChangeListener(this);
		dayView = new DayView(calendarModel);
		dayView.getDayView();

		// Creating a non-editable week table
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

		// Sets the clock every 15 minutes in the 1st column
		GregorianCalendar timeCalendar = new GregorianCalendar();
		timeCalendar.setTimeInMillis(calendar.getTimeInMillis());
		int thisHourOfDay = timeCalendar.get(GregorianCalendar.HOUR_OF_DAY);
		int thisMinuteOfDay = timeCalendar.get(GregorianCalendar.MINUTE);
		timeCalendar.add(GregorianCalendar.HOUR_OF_DAY, -thisHourOfDay);
		timeCalendar.add(GregorianCalendar.MINUTE, -thisMinuteOfDay);

		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		for (int i = 0; i < quarters; i++) {
			String timeText = timeFormat.format(timeCalendar.getTime());
			tableModel.setValueAt(timeText, i, 0);
			timeCalendar.add(GregorianCalendar.MINUTE, 15);
		}
		
		JScrollPane scrollPane = new JScrollPane(weekTable);
		scrollPane.setPreferredSize(new Dimension(800, 407));
		
		add(scrollPane);

		// Sets this week's title
		setWeekTitle();

		weekTable.setModel(tableModel);
		weekTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		weekTable.setRowSelectionAllowed(false);

		weekCalendar = new GregorianCalendar();
		setWeekTitle();
		setHeaders();
	}

	/**
	 * Creates a new week view.
	 */
	public void createWeekTable() {

		// Sets this week's title
		setWeekTitle();

		// Sets table headers with corresponding days and creates the week data
		setHeaders();

		int daysInWeek = 7;
		for (int dayOfWeek = 1; dayOfWeek <= daysInWeek; dayOfWeek++) {
			dayView.createDay(weekCalendar, tableModel, dayOfWeek);
			weekCalendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
		}

	}

	private void setHeaders() {
		weekCalendar.setTimeInMillis(calendar.getTimeInMillis());
		int firstDayOfWeek = -weekCalendar.get(GregorianCalendar.DAY_OF_WEEK) + 2;
		weekCalendar.add(GregorianCalendar.DAY_OF_WEEK, firstDayOfWeek);
		// Sets the new week into the table
		for (int i = 1; i < 8; i++) {
			weekTable.getColumnModel().getColumn(i)
			.setCellRenderer(new DayTableCellRenderer(calendarModel));
		}
		
		int daysInWeek = 7;
		for (int dayOfWeek = 1; dayOfWeek <= daysInWeek; dayOfWeek++) {
			columnHeaders[dayOfWeek] = weekDayFormat.format(weekCalendar.getTime());
			weekCalendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
		}
		tableModel.setColumnIdentifiers(columnHeaders);
		weekTable.getColumnModel().getColumn(0).setPreferredWidth(0);
	}

	/**
	 * Sets this week's title.
	 */
	private void setWeekTitle() {
		SimpleDateFormat titleFormat = new SimpleDateFormat("ww, MMMMM yyyy");
		title = "Uke " + titleFormat.format(calendar.getTime());
	}

	/**
	 * Generates the title of week panel.
	 * 
	 * @return the title of week panel.
	 */
	@Override
	public String getTitle() {
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
	 * 
	 * @return week panel.
	 */
	@Override
	public JPanel getPanel() {
		return this;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case CalendarModel.CALENDAR_LOADED_Property:
			createWeekTable();
			break;
		case CalendarModel.MEETING_ADDED_Property:
			createWeekTable();
			break;
		case CalendarModel.MEETING_CHANGED_Property:
			createWeekTable();
			break;
		case CalendarModel.MEETING_REMOVED_Property:
			createWeekTable();
			break;
		default:
			break;
		}
	}
}