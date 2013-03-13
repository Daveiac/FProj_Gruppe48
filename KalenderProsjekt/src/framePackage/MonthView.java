package framePackage;

import java.awt.Dimension;
import java.awt.image.renderable.RenderContext;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class MonthView {
	
	private JTable monthTable;
	private GregorianCalendar cal;
	private JFrame frame;
	private JPanel monthPanel;
	private int realDay,realMonth,realYear,currentMonth,currentYear;
	private DefaultTableModel tableModel;
	private JLabel title;
	public static final String[] months = {"Januar","Februar", "Mars", "April", "Mai", "Juni", "Juli", 
			"August", "September", "Oktober", "November", "Desember"};
	
	public static void main(String args[]){
		MonthView mw = new MonthView(1, 2013);
		JFrame frame = new JFrame("monthView test: ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(mw.getMonthView());
		frame.setSize(700, 400);
		frame.setVisible(true);
		}
	
	public MonthView(int month, int year){
		initialize();
		refreshCalendar(month, year);
	}
	
	@SuppressWarnings("serial")
	private void initialize(){
		GregorianCalendar cal =  new GregorianCalendar();
		
		
		tableModel = new DefaultTableModel();
		String[] headers = {"Mandag","Tirsdag","Onsdag","Torsdag","Fredag","Lørdag","Søndag"};
		tableModel.setColumnIdentifiers(headers);
		tableModel.setRowCount(6);
		
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH);
		realMonth = cal.get(GregorianCalendar.MONTH);
		realYear = cal.get(GregorianCalendar.YEAR);
		currentMonth =  realMonth;
		currentYear = realYear;
		
		monthTable = new JTable(tableModel) {
			  public boolean isCellEditable(int rowIndex, int colIndex) {
				  return false; //Disallow the editing of any cell
			  }
		};
		monthTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		monthTable.setRowSelectionAllowed(false);
		monthTable.setRowHeight(42);
		
		
		monthPanel = new JPanel();
		monthPanel.setSize(600, 500);
		monthPanel.setBorder(BorderFactory.createTitledBorder("Kalender: "));
		JScrollPane jsp = new JScrollPane(monthTable);
		jsp.setPreferredSize(new Dimension(650,275));
		monthPanel.add(jsp);
	}
	private void refreshCalendar(int month, int year) {
		int nDays, monthStart;
		title = new JLabel(months[month]+", "+year);
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		monthStart = cal.get(GregorianCalendar.DAY_OF_WEEK);
		// Clear table
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				tableModel.setValueAt(null, i, j);
			}
		}
		//Draw calendar
		for (int i=1; i<=nDays; i++){
			int row = new Integer((i+monthStart-3)/7);
			int column  =  (i+monthStart-3)%7;
			tableModel.setValueAt(i, row, column);
		}
	}
	
	public JPanel getMonthView(){
		return monthPanel;
	}
	
	public JLabel getTitle() {
		return title;
	}
	
}
