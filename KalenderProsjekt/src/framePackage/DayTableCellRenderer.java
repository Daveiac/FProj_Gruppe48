package framePackage;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import data.CalendarModel;
import data.Meeting;
import data.Person;

@SuppressWarnings("serial")
public class DayTableCellRenderer extends DefaultTableCellRenderer {

	private CalendarModel calendarModel;

	public DayTableCellRenderer(CalendarModel calendarModel) {
		this.calendarModel = calendarModel;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		Component component = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

		Meeting meeting = (Meeting) value;

		if (meeting != null) {
			for (Person selectedPerson : calendarModel.getSelectedPersons()) {
				if (meeting.getTeam() != null) {
					for (Person teamMember : meeting.getTeam().getMembers()) {
						if (selectedPerson.equals(teamMember)) {
							component.setBackground(calendarModel
									.getColorOfPerson(selectedPerson));
						}
					}
				} else {

					component.setBackground(calendarModel
							.getColorOfPerson(selectedPerson));
				}
			}
			setText(meeting.getTitle());
		} else {
			component.setBackground(table.getBackground());
		}

		return component;
	}
}