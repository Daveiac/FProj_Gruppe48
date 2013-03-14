package framePackage;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class DayView extends JPanel implements CalendarView {

	private GregorianCalendar calendar;
	private String title;
	private DefaultTableModel tableModel;
	private JTable tableDay;

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

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void next() {
		calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
		createDay();
	}

	@Override
	public void prev() {
		calendar.add(GregorianCalendar.DAY_OF_MONTH, -1);
		createDay();
	}

	@Override
	public JPanel getPanel() {
		return this;
	}
}