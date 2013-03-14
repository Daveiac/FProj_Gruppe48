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

/**
 * 
 * @return title
 */
@SuppressWarnings("serial")
public class WeekView extends JPanel implements CalendarView {

	private GregorianCalendar calendar;
	private String title;
	private String[] days;

	public WeekView() {
		calendar = new GregorianCalendar();

		createWeek();

		DefaultTableModel tableModel = new DefaultTableModel(days, 24);
		for (int i = 0; i < 24; i++) {
			String time = String.format("%02d", i) + ":00";
			tableModel.setValueAt(time, i, 0);
		}

		// Creating a non-editable table
		JTable tblWeeks = new JTable(tableModel) {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		tblWeeks.getColumnModel().getColumn(0).setPreferredWidth(0);
		tblWeeks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblWeeks.setRowSelectionAllowed(false);

		JScrollPane scrollPane = new JScrollPane(tblWeeks);
		scrollPane.setPreferredSize(new Dimension(800, 407));

		add(scrollPane);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Week View");

		WeekView weekPanel = new WeekView();

		frame.setContentPane(weekPanel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void createWeek() {

		int startOfWeek = -calendar.get(GregorianCalendar.DAY_OF_WEEK) + 2;
		calendar.add(GregorianCalendar.DAY_OF_WEEK, startOfWeek);

		SimpleDateFormat titlekFormat = new SimpleDateFormat("ww, MMMMM yyyy");
		title = "Uke " + titlekFormat.format(calendar.getTime());

		SimpleDateFormat weekFormat = new SimpleDateFormat("EEEEEEE dd. MMM.");
		days = new String[8];
		days[0] = "Tid";
		for (int i = 1; i < 8; i++) {
			days[i] = weekFormat.format(calendar.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
	}

	@Override
	public String getTitle(){
		return title;
	}

	@Override
	public void next() {
		calendar.get(GregorianCalendar.WEEK_OF_YEAR + 1);
	}

	@Override
	public void prev() {
		calendar.get(GregorianCalendar.WEEK_OF_YEAR - 1);
	}

	@Override
	public JPanel getPanel() {
		return this;
	}
}