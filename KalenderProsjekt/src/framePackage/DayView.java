package framePackage;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * This is the DayView Panel that shows the day planner.
 */
@SuppressWarnings("serial")
public class DayView extends JPanel implements CalendarView {

	private GregorianCalendar calendar;
	private String title;
	private DefaultTableModel tableModel;
	private JTable tableDay;

	/**
	 * Constructs the DayView Panel.
	 */
	public DayView() {
		calendar = new GregorianCalendar();

		tableDay = new JTable() {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};

		createDay();

		JScrollPane scrollPane = new JScrollPane(tableDay);
		scrollPane.setPreferredSize(new Dimension(800, 407));

		add(scrollPane);
	}

	/**
	 * Creates a new day view.
	 */
	public void createDay() {

		// Sets this week's title
		SimpleDateFormat titleFormat = new SimpleDateFormat("dd. MMMMM");
		title = titleFormat.format(calendar.getTime());

		// Sets table headers
		SimpleDateFormat weekFormat = new SimpleDateFormat("EEEE");
		int columns = 2;
		String[] days = new String[columns];
		days[0] = "Tid";
		days[1] = weekFormat.format(calendar.getTime());

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
		tableDay.setModel(tableModel);
		tableDay.getColumnModel().getColumn(0).setPreferredWidth(0);
		tableDay.getColumnModel().getColumn(1).setPreferredWidth(718);
		tableDay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDay.setRowSelectionAllowed(false);
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
		createDay();
	}

	/**
	 * Shows previous day.
	 */
	@Override
	public void prev() {
		calendar.add(GregorianCalendar.DAY_OF_MONTH, -1);
		createDay();
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