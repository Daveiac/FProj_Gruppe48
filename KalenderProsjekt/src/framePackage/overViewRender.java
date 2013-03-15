package framePackage;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import data.Notification;

public class overViewRender extends DefaultListCellRenderer {

	private final ImageIcon check = new ImageIcon("res/icons/icon_check.png");
	private final ImageIcon cross = new ImageIcon("res/icons/icon_cross.png");
	private final ImageIcon question = new ImageIcon("res/icons/icon_question.png");
	private final ImageIcon star = new ImageIcon("res/icons/icon_star.png");
	
	
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		JLabel label = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		Notification noti = (Notification) value;
		
		if() {
			label.setIcon(check);
		}
		label.setText(noti.getPerson().getFirstName() + noti.getPerson().getLastName());
		return label;
		
	}

}
