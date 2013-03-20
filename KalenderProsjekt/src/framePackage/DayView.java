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

import data.CalendarModel;
import data.Meeting;
import data.Person;

/**
 * This is the DayView Panel that shows the day planner.
 */
@SuppressWarnings("serial")
public class DayView implements CalendarView, PropertyChangeListener {

	private JTable dayTable;
	private DefaultTableModel tableModel;

	private CalendarModel calendarModel;
	private GregorianCalendar calendar;
	private String title;
	private String[] columnHeaders;
	private int dayOfWeek;
	private JScrollPane scrollPane;

	/**
	 * Constructs the DayView Panel.
	 */
	public DayView(CalendarModel calendarModel) {
		calendar = calendarModel.getCalendar();
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

		dayOfWeek = 1;

		// Sets this day's title
		setDayTitle();

		// Sets table headers
		setHeaders();

		// Sets the new day into the table
		dayTable.setModel(tableModel);
		dayTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dayTable.setRowSelectionAllowed(false);
		dayTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		dayTable.getColumnModel().getColumn(1).setPreferredWidth(718);

		dayTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = dayTable.rowAtPoint(evt.getPoint());
				int col = dayTable.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 1) {
					Meeting meeting = (Meeting) DayView.this.tableModel
							.getValueAt(row, col);
					new NewAppointmentView(meeting);
				}
			}
		});

		dayTable.getColumnModel().getColumn(1)
				.setCellRenderer(new DayTableCellRenderer(this.calendarModel));

		scrollPane = new JScrollPane(dayTable);
	}

	/**
	 * Creates a new day view.
	 */
	public void createDayTable() {

		// Sets this day's title
		setDayTitle();

		// Sets table headers with corresponding days
		setHeaders();

		// Creates the day data
		createDay(calendar, tableModel, dayOfWeek);
	}

	/**
	 * Sets this day's title.
	 */
	public void setDayTitle() {
		SimpleDateFormat titleFormat = new SimpleDateFormat("dd. MMMMM");
		title = titleFormat.format(calendar.getTime());
	}

	/**
	 * Sets the table headers with corresponding days.
	 */
	private void setHeaders() {
		setDayTitle();
		SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
		columnHeaders[dayOfWeek] = dayFormat.format(calendar.getTime());
		tableModel.setColumnIdentifiers(columnHeaders);
	}

	/**
	 * Creates the day data.
	 */
	public void createDay(GregorianCalendar calendar,
			DefaultTableModel tableModel, int dayOfWeek) {

		// Clear table
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			tableModel.setValueAt(null, i, dayOfWeek);
		}

		// Sets today's meetings
		List<Person> persons = calendarModel.getSelectedPersons();
		for (Person person : persons) {
			ArrayList<Meeting> meetings = calendarModel.getAllMeetingsOfPerson(
					person, true);
			setMeetings(calendar, tableModel, dayOfWeek, meetings);
		}
		dayTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		dayTable.getColumnModel().getColumn(1).setPreferredWidth(718);

		dayTable.getColumnModel().getColumn(1)
				.setCellRenderer(new DayTableCellRenderer(this.calendarModel));
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
			if (sdf.format(calendar.getTime()).equals(
					sdf.format(meetingCalendar.getTime()))) {
				for (int time = startTableTime; time < endTableTime; time++) {
					tableModel.setValueAt(meeting, time, dayOfWeek);
				}
			}
		}
	}
	
	/**
	 * Returns the table model.
	 * @return tableModel
	 */
	public DefaultTableModel getTableModel() {
		return tableModel;
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
		calendarModel.changeDate();
		createDayTable();
	}

	/**
	 * Shows previous day.
	 */
	@Override
	public void prev() {
		calendar.add(GregorianCalendar.DAY_OF_MONTH, -1);
		calendarModel.changeDate();
		createDayTable();
	}

	/**
	 * Gets the day panel.
	 * 
	 * @return day panel.
	 */
	@Override
	public JComponent getPanel() {
		return scrollPane;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case CalendarModel.CALENDAR_LOADED_Property:
			createDayTable();
			break;
		case CalendarModel.MEETINGS_CHANGED_Property:
			createDayTable();
			break;
		case CalendarModel.SELECTED_Property:
			System.out.println("someone just got lucky! (SELECTED)");
			createDayTable();
			break;
		case CalendarModel.DATE_CHANGED_Property:
			createDayTable();
			break;
		default:
			break;
		}
	}
}