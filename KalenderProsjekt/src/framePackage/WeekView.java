package framePackage;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @return title
 */
@SuppressWarnings("serial")
public class WeekView extends JPanel implements CalendarView {

	private GregorianCalendar calendar;
	private String title;
	private DefaultTableModel tableModel;
	private JTable tableWeek;

	public WeekView() {
		calendar = new GregorianCalendar();

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

	public void createWeek() {

		SimpleDateFormat titleFormat = new SimpleDateFormat("ww, MMMMM yyyy");
		title = "Uke " + titleFormat.format(calendar.getTime());

		int startOfWeek = -calendar.get(GregorianCalendar.DAY_OF_WEEK) + 2;
		calendar.add(GregorianCalendar.DAY_OF_WEEK, startOfWeek);

		SimpleDateFormat weekFormat = new SimpleDateFormat("EEEE dd. MMM.");
		String[] days = new String[8];
		days[0] = "Tid";
		for (int i = 1; i < 8; i++) {
			days[i] = weekFormat.format(calendar.getTime());
			calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
		}
		calendar.add(GregorianCalendar.WEEK_OF_YEAR, -1);
		tableModel = new DefaultTableModel(days, 24);
		tableWeek.setModel(tableModel);

		for (int i = 0; i < 24; i++) {
			String time = String.format("%02d", i) + ":00";
			tableModel.setValueAt(time, i, 0);
		}

		tableWeek.getColumnModel().getColumn(0).setPreferredWidth(0);
		tableWeek.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableWeek.setRowSelectionAllowed(false);
	}

	@Override
	public String getTitle(){
		return title;
	}

	@Override
	public void next() {
		calendar.add(GregorianCalendar.WEEK_OF_YEAR, 1);
		createWeek();
	}

	@Override
	public void prev() {
		calendar.add(GregorianCalendar.WEEK_OF_YEAR, -1);
		createWeek();
	}

	@Override
	public JPanel getPanel() {
		return this;
	}
}