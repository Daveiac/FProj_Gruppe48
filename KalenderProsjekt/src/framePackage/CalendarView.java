package framePackage;

import javax.swing.JPanel;

public interface CalendarView {
	
	public String getTitle();
	public JPanel getPanel();
	public void next();
	public void prev();

}
