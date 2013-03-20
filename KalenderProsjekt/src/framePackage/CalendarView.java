package framePackage;

import javax.swing.JComponent;

public interface CalendarView {
	
	public String getTitle();
	public JComponent getPanel();
	public void next();
	public void prev();

}
