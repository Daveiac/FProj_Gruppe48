package framePackage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MonthView {

	private JPanel monthView;
	private JLabel lbluke;
	private JLabel lblmandag;
	private JLabel lbltirdag;
	private JLabel lblonsdag;
	private JLabel lbltorsdag;
	private JLabel lblfredag;
	private JLabel lbllørdag;
	private JLabel lblsøndag;
	private Date date;
	private DefaultView df;
	

	public static void main(String[] args) {
		MonthView mw = new MonthView(new DefaultView());
		JFrame frame = new JFrame("Month view test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(mw.getMonthView());
		frame.setSize(600, 500);
		frame.setVisible(true);
	}

	public MonthView(DefaultView defV) {
		df = defV;
		date = new Date();
		initialize();
		test();
	}

	private void initialize() {
		monthView = new JPanel(new GridBagLayout());
		GridBagConstraints monthViewConstraints = new GridBagConstraints();
		
		
	}

	public JPanel getMonthView() {
		return monthView;
	}
	
	
	private void test(){
	}

}
