package framePackage;

import java.awt.Color;
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

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		Meeting meeting = (Meeting) value;

		if (meeting != null) {
			if (meeting.getTeam() != null) {

				boolean user = false;
				boolean creator = false;
				for (Person selectedPerson : calendarModel.getSelectedPersons()) {
					if (selectedPerson.getUsername().equals(calendarModel.getUser().getUsername())) {
						user = true;
					}
					if (selectedPerson.getUsername().equals(meeting.getCreator().getUsername())) {
						creator = true;
					}
				}

				for (Person selectedPerson : calendarModel.getSelectedPersons()) {
					for (Person teamMember : meeting.getTeam().getMembers()) {
						if (selectedPerson.getUsername().equals(teamMember.getUsername())) {

							if (user) {
								component.setBackground(calendarModel.getColorOfPerson(calendarModel.getUser()));

							} else if (creator) {
								component.setBackground(calendarModel.getColorOfPerson(meeting.getCreator()));

							} else {
								component.setBackground(calendarModel.getColorOfPerson(selectedPerson));
							}

						}
					}
				}
			}
			setText(meeting.getTitle());
		} else {
			component.setBackground(table.getBackground());
		}
		return component;
	}

}