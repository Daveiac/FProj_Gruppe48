package framePackage;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import data.CalendarModel;
import data.Meeting;

@SuppressWarnings("serial")
class MonthTableCellRenderer extends DefaultTableCellRenderer {

	private static final LineBorder lineBorder = new LineBorder(Color.black);;

	private CalendarModel calendarModel;

	public MonthTableCellRenderer(CalendarModel calendarModel) {
		this.calendarModel = calendarModel;
	}

	@SuppressWarnings("unchecked")
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		Object[] shit = (Object[]) value;

		if (shit == null)
			return null;

		int dayOfMonth = (int) shit[0];
		ArrayList<Meeting> meetings = (ArrayList<Meeting>) shit[1];

		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.addElement(String.valueOf(dayOfMonth));

		if (meetings != null) {

			for (Meeting meeting : meetings) {
				listModel.addElement(meeting.getTitle());
			}
		}

		JList<String> meetingList = new JList<String>();
		meetingList.setModel(listModel);
		add(meetingList);

		if (meetingList != null) {
			if (hasFocus) {
				meetingList.setBorder(lineBorder);
			} else {
				if (meetingList != null)
					meetingList.setBorder(null);
			}
		}

		return meetingList;
	}
}