package framePackage;

import javax.swing.JLabel;
import javax.swing.JPanel;

public interface CalendarView {
	
	public JLabel getTitle();
	public JPanel getPanel();
	public void next();
	public void prev();

}
