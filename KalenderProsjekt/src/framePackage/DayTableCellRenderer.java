package framePackage;

import java.awt.Color;
import java.awt.Component;
import java.util.Random;

import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import data.Meeting;

@SuppressWarnings("serial")
public class DayTableCellRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		Component prevCo = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row - 1, column);
		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		Meeting meeting = (Meeting) value;

		if (meeting != null) {
			component.setBackground(Color.ORANGE);
			if (prevCo == component)
				setText(meeting.getTitle());
		}
		else {
			component.setBackground(table.getBackground());
		}

		return component;
	}
}