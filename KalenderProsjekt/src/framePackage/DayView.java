package framePackage;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
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

		SimpleDateFormat titleFormat = new SimpleDateFormat("dd. MMMMM");
		title = titleFormat.format(calendar.getTime());

		SimpleDateFormat weekFormat = new SimpleDateFormat("EEEE");
		String[] days = new String[2];
		days[0] = "Tid";
		days[1] = weekFormat.format(calendar.getTime());
		tableModel = new DefaultTableModel(days, 24);
		tableDay.setModel(tableModel);

		for (int i = 0; i < 24; i++) {
			String time = String.format("%02d", i) + ":00";
			tableModel.setValueAt(time, i, 0);
		}

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