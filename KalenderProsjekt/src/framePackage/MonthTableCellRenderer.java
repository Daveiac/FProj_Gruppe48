package framePackage;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import data.CalendarModel;
import data.Meeting;
import data.Person;

@SuppressWarnings("serial")
class MonthTableCellRenderer extends DefaultTableCellRenderer {

	private static final LineBorder lineBorder = new LineBorder(Color.black);;
	private CalendarModel calendarModel;

	public MonthTableCellRenderer(CalendarModel calendarModel) {
		this.calendarModel = calendarModel;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		Component component = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
		
		@SuppressWarnings("unchecked")
		JList<Meeting> list =(JList<Meeting>) value;
		if (list != null) {
			if (hasFocus) {
				list.setBorder(lineBorder);
			} else {
				if (list != null)
					list.setBorder(null);
			}
		}
		
//		DefaultListModel<Meeting> listModel = (DefaultListModel<Meeting>) list.getModel();
//		
//		for (int i = 0; i < listModel.size(); i++) {
//			Meeting meeting = listModel.elementAt(i);
//			if (meeting != null) {
//				for (Person selectedPerson : calendarModel.getSelectedPersons()) {
//					for (Person teamMember : meeting.getTeam().getMembers()) {
//						if (selectedPerson.equals(teamMember)) {
//							component.setBackground(calendarModel.getColorOfPerson(selectedPerson));
//						}
//					}
//				}
//				setText(meeting.getTitle());
//			} else {
//				component.setBackground(table.getBackground());
//			}
//		}
		return list;
	}
}
//listModel.addElement(String.valueOf(dayOfMonth));