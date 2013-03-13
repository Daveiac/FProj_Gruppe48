package framePackage;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class WeekView extends JPanel {
	
	private JPanel weekView;
	private JLabel lbltid;
	private JLabel lblmandag;
	private JLabel lbltirsdag;
	private JLabel lblonsdag;
	private JLabel lbltorsdag;
	private JLabel lblfredag;
	private JLabel lbllørdag;
	private JLabel lblsøndag;
	private Date date;
	private JLabel lbltime;
	private JLabel title;
	private Dato dato;

	public WeekView() {

		// Default week view
		String[] columnNames = {"Tid", "Mandag", "Tirsdag", "Onsdag", "Torsdag", "Fredag", "Lørdag", "Søndag"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 24);
		for (int i = 0; i < 24; i++) {
			String time = i + ":00";
			tableModel.setValueAt(time, i, 0);
		}
		
		JTable tblWeeks = new JTable(tableModel) {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		JScrollPane scrollPane = new JScrollPane(tblWeeks);
		scrollPane.setPreferredSize(scrollPane.getPreferredSize());
		
		add(scrollPane);
	}

//	public WeekView(DefaultView defV) {
//		date = new Date();
//		initialize();
//		dato = new Dato();
//	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Week View");
		
		WeekView weekPanel = new WeekView();
		
		
		frame.setContentPane(weekPanel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * 
	 * @return title
	 */
	public JLabel getTitle(){
		title = new JLabel(dato.getWeek() + "," + dato.getYear());
		return title;
	}
	
	public String getFirstDayOfWeek(){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(dato.getWeek()));
		SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");  
		String month = monthFormat.format(date);
		return (calendar.getTime()+ "."+ month);
	}
	
}
