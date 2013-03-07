package framePackage;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DayView extends JPanel{
	
	public static void main (String[] args){
		Frame dayFrame = new Frame();
		dayFrame.setVisible(true);
	}
	
	public DayView(){
		initialize();
	}
	
	private void initialize(){
		Frame dayFrame = new Frame();
		dayFrame.setSize(500, 300);
		dayFrame.setVisible(true);
		
		dayFrame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel lblday = new JLabel("Idag: "+ getDate());
		c.gridx = 0;
		c.gridy = 0;
		dayFrame.add(lblday, c);
	}
	
	public Date getDate(){
		Date date = new Date();
		Locale Norge = new Locale("no");
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, Norge);
		df.format(date);
		return date;
	}
}