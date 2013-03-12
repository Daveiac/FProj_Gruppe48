package framePackage;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WeekView {
	
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
	private DefaultView df;
	private JLabel lbltime;
	private JLabel tittle;
	private Dato dato;
	
	public static void main(String[] args) {
		WeekView wv = new WeekView(new DefaultView());
		JFrame frame = new JFrame("week view test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(wv.getWeekView());
		frame.setSize(600, 500);
		frame.setVisible(true);
	}

	private JPanel getWeekView() {
		return weekView;
	}

	public WeekView(DefaultView defV) {
		df = defV;
		date = new Date();
		initialize();
		dato = new Dato();
	}
	
	private void initialize(){
		weekView = new JPanel(new GridBagLayout());
		GridBagConstraints weekViewConstraints = new GridBagConstraints();
		
		for (int i=0;i<24;i++){
			lbltime = new JLabel(i+ ":00");
			weekViewConstraints.gridx = 0;
			weekViewConstraints.gridy = i+1;
			weekView.add(lbltime, weekViewConstraints);
		}
		weekViewConstraints.insets = new Insets(0, 10, 0, 15);
		lbltid = new JLabel("Uke");
		weekViewConstraints.gridx = 0;
		weekViewConstraints.gridy = 0;
		weekView.add(lbltid, weekViewConstraints);
		
		weekViewConstraints.insets = new Insets(0, 0, 0, 35);
		lblmandag = new JLabel("Mandag");
		weekViewConstraints.gridx = 1;
		weekViewConstraints.gridy = 0;
		weekView.add(lblmandag, weekViewConstraints);
		
		lbltirsdag = new JLabel("Tirsdag");
		weekViewConstraints.gridx = 2;
		weekViewConstraints.gridy = 0;
		weekView.add(lbltirsdag, weekViewConstraints);
		
		lblonsdag = new JLabel("Onsdag");
		weekViewConstraints.gridx = 3;
		weekViewConstraints.gridy = 0;
		weekView.add(lblonsdag, weekViewConstraints);
		
		lbltorsdag = new JLabel("Torsdag");
		weekViewConstraints.gridx = 4;
		weekViewConstraints.gridy = 0;
		weekView.add(lbltorsdag, weekViewConstraints);
		
		lblfredag = new JLabel("Fredag");
		weekViewConstraints.gridx = 5;
		weekViewConstraints.gridy = 0;
		weekView.add(lblfredag, weekViewConstraints);
		
		lbllørdag = new JLabel("Lørdag");
		weekViewConstraints.gridx = 6;
		weekViewConstraints.gridy = 0;
		weekView.add(lbllørdag, weekViewConstraints);
		
		lblsøndag = new JLabel("Søndag");
		weekViewConstraints.gridx = 7;
		weekViewConstraints.gridy = 0;
		weekView.add(lblsøndag, weekViewConstraints);
		
		
	}
	
	public JLabel getTittle(){
		tittle = new JLabel(dato.getWeek() + "," + dato.getYear());
		return tittle;
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
