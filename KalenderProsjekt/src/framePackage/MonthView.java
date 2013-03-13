package framePackage;

import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MonthView {
	
	private JTable monthTable;
	private GregorianCalendar cal;
	private JFrame frame;
	private JPanel monthPanel;
	private int realDay,realMonth,realYear,currentMonth,currentYear;
	private DefaultTableModel tableModel;
	
	private static void main(String args[]){
		MonthView mw = new MonthView();
		JFrame frame = new JFrame("monthView test: ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(mw.getMonthView());
		frame.setSize(600, 500);
		frame.setVisible(true);
		}
	
	public MonthView(){
		initialize();
	}
	
	private void initialize(){
		monthPanel = new JPanel();
		GregorianCalendar cal =  new GregorianCalendar();
		tableModel = new DefaultTableModel();
		monthTable = new JTable(tableModel);
		monthPanel.setSize(600, 500);
		monthPanel.setBorder(BorderFactory.createTitledBorder("Kalender: "));
		monthPanel.add(monthTable);
		tableModel.setColumnCount(7);
		tableModel.setRowCount(6);
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH);
		realMonth = cal.get(GregorianCalendar.MONTH);
		realYear = cal.get(GregorianCalendar.YEAR);
		currentMonth =  realMonth;
		currentYear = realYear;
		String[] headers = {"Mandag","Tirsdag","Onsdag","Torsdag","Fredag","Lørdag","Søndag"};
		for (int i = 0; i<7 ;i++){
			tableModel.addColumn(headers[i])
		}
	}
	
	public JPanel getMonthView(){
		return monthPanel;
	}
	
}
