package framePackage;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import data.Notification;

public class VarselViewRender extends DefaultListCellRenderer{

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {		
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		Notification noti = (Notification) value;
		label.setText("Ny møte:" + noti.getMeeting().getTitle());
		
		return label;
	}
}
