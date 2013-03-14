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
public class DayView2 extends JPanel implements CalendarView {

	private GregorianCalendar calendar;
	private String title;
	public DayView2() {

		SimpleDateFormat weekFormat = new SimpleDateFormat("EEEEEEE dd. MMM.");
		calendar = new GregorianCalendar();

		title = weekFormat.format(calendar.getTime());
		String[] headers = {"Tid", title};
		DefaultTableModel tableModel = new DefaultTableModel(headers, 24);
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
		tblWeeks.getColumnModel().getColumn(1).setPreferredWidth(719);
		tblWeeks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblWeeks.setRowSelectionAllowed(false);

		JScrollPane scrollPane = new JScrollPane(tblWeeks);
		scrollPane.setPreferredSize(new Dimension(800, 500));

		add(scrollPane);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Day View");

		DayView2 weekPanel = new DayView2();

		frame.setContentPane(weekPanel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void createWeek() {

	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void next() {
		//TODO
	}

	@Override
	public void prev() {
		//TODO
	}

	@Override
	public JPanel getPanel() {
		return this;
	}
}