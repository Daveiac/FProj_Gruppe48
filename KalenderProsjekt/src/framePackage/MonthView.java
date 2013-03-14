package framePackage;

import java.awt.Dimension;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class MonthView implements CalendarView{
	
	private JTable monthTable;
	private GregorianCalendar cal;
	private JFrame frame;
	private JPanel monthPanel;
	private int realDay,realMonth,realYear,currentMonth,currentYear;
	private DefaultTableModel tableModel;
	private String title;
	public static final String[] months = {"Januar","Februar", "Mars", "April", "Mai", "Juni", "Juli", 
			"August", "September", "Oktober", "November", "Desember"};
	
	public static void main(String args[]){
		MonthView mw = new MonthView();
		JFrame frame = new JFrame("monthView test: ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(mw.getPanel());
		frame.setSize(700, 400);
		frame.setVisible(true);
		}
	
	public MonthView(){
		initialize();
		refreshCalendar();
	}
	
	@SuppressWarnings("serial")
	private void initialize(){
		GregorianCalendar cal =  new GregorianCalendar();
		
		tableModel = new DefaultTableModel();
		String[] headers = {"Uke", "Mandag","Tirsdag","Onsdag","Torsdag","Fredag","Lørdag","Søndag"};
		tableModel.setColumnIdentifiers(headers);
		tableModel.setRowCount(6);
		
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH);
		realMonth = cal.get(GregorianCalendar.MONTH);
		realYear = cal.get(GregorianCalendar.YEAR);
		currentMonth =  realMonth;
		currentYear = realYear;
		
		monthTable = new JTable(tableModel) {
			  public boolean isCellEditable(int rowIndex, int colIndex) {
				  return false; //Disable the editing of any cell
			  }
		};
		monthTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		monthTable.setRowSelectionAllowed(false);
		monthTable.setRowHeight(42);
		monthTable.getColumnModel().getColumn(0).setPreferredWidth(10);
		for (int i = 1; i < 8; i++) {
			monthTable.getColumnModel().getColumn(i).setCellRenderer(new MonthTableCellRenderer());
		}
		
		monthPanel = new JPanel();
		monthPanel.setSize(600, 500);
		JScrollPane jsp = new JScrollPane(monthTable);
		jsp.setPreferredSize(new Dimension(650,275));
		monthPanel.add(jsp);
		
		title = months[currentMonth]+", "+currentYear;
	}
	private void refreshCalendar() {
		int nDays, monthStart, weekStart;
		title = months[currentMonth]+", "+currentYear;
		GregorianCalendar cal = new GregorianCalendar(currentYear, currentMonth, 1);
		nDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		monthStart = cal.get(GregorianCalendar.DAY_OF_WEEK);
		monthStart = (monthStart == 1) ? 6 : monthStart-2;
		weekStart = cal.get(GregorianCalendar.WEEK_OF_YEAR);
		// Clear table
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				tableModel.setValueAt(null, i, j);
			}
		}
		//Draw calendar
		for (int i = 0; i < 6; i++) {
			tableModel.setValueAt(weekStart+i, i, 0);			
		}
		for (int i=0; i<=nDays; i++){
			int row = new Integer((i+monthStart)/7);
			int column  =  (i+monthStart)%7 +1 ;
			tableModel.setValueAt(
					new JList<String>(new String[]{i+1+". "+"Møte 1","AvtaleYO","zomg"})
					, row, column);
		}
	}
	
	public JPanel getPanel(){
		return monthPanel;
	}
	
	public String getTitle() {
		return title;
	}

	@Override
	public void next() {
		if(currentMonth == 11) {
			currentMonth = 0;
			currentYear++;
		} else {
			currentMonth++;
		}
		refreshCalendar();
	}

	@Override
	public void prev() {
		if(currentMonth == 0) {
			currentMonth = 11;
			currentYear--;
		} else {
			currentMonth--;
		}
		refreshCalendar();
	}
}
