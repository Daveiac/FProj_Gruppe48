package framePackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DayView {
	
	private JPanel view;
	private JLabel lbltid;
	private Date date;
	private JLabel lbldayInWeek;
	private JLabel lbltime;
	private JButton hourButton;
	private ArrayList<JButton> buttonList = new ArrayList<JButton>();
	private JLabel tittle;
	private DefaultView df;
	
	
	public static void main(String[] args) {
		DayView dw = new DayView(new DefaultView());
		JFrame frame = new JFrame("Day view test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane( dw.getDayView());
		frame.setSize(600, 500);
		frame.setVisible(true);
		
	}

	public DayView(DefaultView defV) {
		df = defV;
		date = new Date();
		initialize();
	}

	private void initialize(){
		view =  new JPanel(new GridBagLayout());
		GridBagConstraints dayViewContraints = new GridBagConstraints();
		
		lbltid = new JLabel("Tid: ");
		dayViewContraints.gridx = 0;
		dayViewContraints.gridy = 0;
		view.add(lbltid, dayViewContraints);
		
		lbldayInWeek = new JLabel(getDayInWeek());
		dayViewContraints.gridx = 1;
		dayViewContraints.gridy = 0;
		view.add(lbldayInWeek,dayViewContraints);
		for (int i=0;i<24;i++){
			lbltime = new JLabel(i+ ":00");
			dayViewContraints.gridx = 0;
			dayViewContraints.gridy = i+1;
			view.add(lbltime, dayViewContraints);
		}
		for(int i=0;i<24;i++){
			hourButton = new JButton("                                              ");
			dayViewContraints.gridx = 2;
			dayViewContraints.gridy = i+1;
			buttonList.add(hourButton);
			view.add(hourButton, dayViewContraints);
		}
		
		
	}
	
	public JPanel getDayView(){
		return view;
	}
	
	public JLabel getTittle(){
		tittle = new JLabel(df.getDay() +"."+df.getMonth());
		return tittle;
	}
	
	public String getDayInWeek(){
		SimpleDateFormat dayInWeek = new SimpleDateFormat("EEEE");  
		String week = dayInWeek.format(date);
		return week;
	}
	
}
