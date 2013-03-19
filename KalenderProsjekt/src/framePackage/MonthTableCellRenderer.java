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

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		//
		// JList<String> meetingList = (JList<String>) value;
		// DefaultListModel<String> listModel = (DefaultListModel<String>)
		// meetingList.getModel();
		// DefaultListModel<String> listModel = new DefaultListModel<String>();
		// listModel.addElement(String.valueOf(dayOfMonth));
		// listModel.addElement(meeting.getTitle());
		//
		// JList<String> meetingList = new JList<String>();
		// meetingList.setModel(listModel);
		//

		Object[] shit = (Object[]) value;

		if (shit == null)
			return null;

//		System.out.println("dashitCELLRENDUS");
//		System.out.println(shit[0].toString());
//		System.out.println(shit[1].toString());

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

		// @SuppressWarnings("unchecked")
		// JList<String> list = (JList<String>) value;
		// if (list != null) {
		// if (hasFocus) {
		// list.setBorder(lineBorder);
		// } else {
		// if (list != null)
		// list.setBorder(null);
		// }
		// }

		// DefaultListModel<Meeting> listModel = (DefaultListModel<Meeting>)
		// list.getModel();
		//
		// for (int i = 0; i < listModel.size(); i++) {
		// Meeting meeting = listModel.elementAt(i);
		// if (meeting != null) {
		// for (Person selectedPerson : calendarModel.getSelectedPersons()) {
		// for (Person teamMember : meeting.getTeam().getMembers()) {
		// if (selectedPerson.equals(teamMember)) {
		// component.setBackground(calendarModel.getColorOfPerson(selectedPerson));
		// }
		// }
		// }
		// setText(meeting.getTitle());
		// } else {
		// component.setBackground(table.getBackground());
		// }
		// }
		return meetingList;
	}
}
// listModel.addElement(String.valueOf(dayOfMonth));