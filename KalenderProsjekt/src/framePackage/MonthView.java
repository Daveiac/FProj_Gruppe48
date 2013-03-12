package framePackage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
	private JLabel lbltirsdag;
	private JLabel lblonsdag;
	private JLabel lbltorsdag;
	private JLabel lblfredag;
	private JLabel lbllørdag;
	private JLabel lblsøndag;
	private Date date;
	private DefaultView df;
	private JLabel tittle;
	private Dato dato;
	

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
		dato = new Dato();
		date = new Date();
		initialize();
	}

	private void initialize() {
		monthView = new JPanel(new GridBagLayout());
		GridBagConstraints monthViewConstraints = new GridBagConstraints();
		
		monthViewConstraints.insets = new Insets(0, 10, 0, 15);
		lbluke = new JLabel("Uke");
		monthViewConstraints.gridx = 0;
		monthViewConstraints.gridy = 0;
		monthView.add(lbluke, monthViewConstraints);
		
		monthViewConstraints.insets = new Insets(0, 0, 0, 35);
		lblmandag = new JLabel("Mandag");
		monthViewConstraints.gridx = 1;
		monthViewConstraints.gridy = 0;
		monthView.add(lblmandag, monthViewConstraints);
		
		lbltirsdag = new JLabel("Tirsdag");
		monthViewConstraints.gridx = 2;
		monthViewConstraints.gridy = 0;
		monthView.add(lbltirsdag, monthViewConstraints);
		
		lblonsdag = new JLabel("Onsdag");
		monthViewConstraints.gridx = 3;
		monthViewConstraints.gridy = 0;
		monthView.add(lblonsdag, monthViewConstraints);
		
		lbltorsdag = new JLabel("Torsdag");
		monthViewConstraints.gridx = 4;
		monthViewConstraints.gridy = 0;
		monthView.add(lbltorsdag, monthViewConstraints);
		
		lblfredag = new JLabel("Fredag");
		monthViewConstraints.gridx = 5;
		monthViewConstraints.gridy = 0;
		monthView.add(lblfredag, monthViewConstraints);
		
		lbllørdag = new JLabel("Lørdag");
		monthViewConstraints.gridx = 6;
		monthViewConstraints.gridy = 0;
		monthView.add(lbllørdag, monthViewConstraints);
		
		lblsøndag = new JLabel("Søndag");
		monthViewConstraints.gridx = 7;
		monthViewConstraints.gridy = 0;
		monthView.add(lblsøndag, monthViewConstraints);
	}

	public JPanel getMonthView() {
		return monthView;
	}
	
	
	private JLabel getTittle(){
		tittle = new JLabel (dato.getMonth()+"."+dato.getYear());
		return tittle;
	}

}
