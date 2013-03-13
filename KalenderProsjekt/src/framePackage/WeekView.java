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
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @return title
 */
@SuppressWarnings("serial")
public class WeekView extends JPanel implements CalendarView {

	private GregorianCalendar calendar;
	private JLabel title;

	public WeekView() {

		SimpleDateFormat weekFormat = new SimpleDateFormat("EEEEEEE dd. MMM.");

		calendar = new GregorianCalendar();

		int dayOfWeek = calendar.get(GregorianCalendar.DAY_OF_WEEK) + 1;
		calendar.add(GregorianCalendar.DAY_OF_WEEK, dayOfWeek);


		String[] days = new String[8];
		days[0] = "Tid";
		for (int i = 1; i < 8; i++) {
			days[i] = weekFormat.format(calendar.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
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

		JScrollPane scrollPane = new JScrollPane(tblWeeks);
		scrollPane.setPreferredSize(new Dimension(814, 407));

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

	}

	@Override
	public JLabel getTitle(){
		title = new JLabel(calendar.get(GregorianCalendar.WEEK_OF_YEAR) + "," + calendar.get(GregorianCalendar.MONTH) + "," + calendar.get(GregorianCalendar.YEAR));
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
}