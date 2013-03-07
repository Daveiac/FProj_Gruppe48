package framePackage;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DayView extends JPanel{
	
	private JFrame frame;
	private JLabel lblday;
	private JButton calendar;
	private JButton meeting;
	private ButtonGroup calendarSelect;
	
	public static void main (String[] args){
		DayView dw = new DayView();
		JFrame frame = dw.getFrame();
		frame.setBounds(100, 100, 1000, 600);
		frame.setVisible(true);
}
	
	public DayView(){
		initialize();
	}
	
	private void initialize(){
		frame = new JFrame();
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		lblday = new JLabel("Idag: "+ getDate());
		c.gridx = 0;
		c.gridy = 0;
		frame.add(lblday, c);
		
		calendar = new JButton("Kalender");
		meeting = new JButton("møte");
		
		calendarSelect = new ButtonGroup();
		calendarSelect.add(calendar);
		calendarSelect.add(meeting);
		c.gridx = 2;
		c.gridy = 0;
		frame.add(calendar, c);
		c.gridx = 3;
		c.gridy = 0;
		frame.add(meeting,c);
		
	}
	
	public String getDate(){
		Date date = new Date();
		Locale Norge = new Locale("no", "no");
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, Norge);
		return df.format(date);
	}

	public JFrame getFrame() {
		return frame;
	}
	
	
}