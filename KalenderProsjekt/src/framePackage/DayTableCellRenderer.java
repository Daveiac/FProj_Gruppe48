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

		Component component = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

		Meeting meeting = (Meeting) value;

		boolean isUser = false;
		boolean isCreator = false;
		if (meeting != null) {
//			if (!isUser) {
//
//				for (Person teamMember : meeting.getTeam().getMembers()) {
//					if (teamMember.getUsername().equals(calendarModel.getUser().getUsername())) {
//						component.setBackground(calendarModel.getColorOfPerson(calendarModel.getUser()));
//						isUser = true;
//					}
//
//				}
//			}
//			else if (!isCreator) {
//				for (Person teamMember : meeting.getTeam().getMembers()) {
//				
//				
//				
//				for (Person selectedPerson : calendarModel.getSelectedPersons()) {
//					if (meeting.getTeam() != null) {
//						System.out.println("Møte som skal få tildelt farge!");
//						System.out.println("valgtPers: " + calendarModel.getSelectedPersons());
//						System.out.println("deltagere: " + meeting.getTeam().getMembers().toString());
//						for (Person teamMember : meeting.getTeam().getMembers()) {
//							if (selectedPerson.getUsername().equals(teamMember.getUsername())) {
//								if (selectedPerson.getUsername().equals(calendarModel.getUser().getUsername())) {
//									component.setBackground(calendarModel.getColorOfPerson(calendarModel.getUser()));
//									isUser = true;
//
//								}
//								else if (selectedPerson.getUsername().equals(meeting.getCreator().getUsername())) {
//									component.setBackground(calendarModel.getColorOfPerson(meeting.getCreator()));
//									isCreator = true;
//								}
//								else
//									component.setBackground(calendarModel.getColorOfPerson(selectedPerson));
//							}
//							else
//								System.out.println("Du ska'kke se detta møtet du!")
//								;
//						}
//					} else {
//						component.setBackground(calendarModel.getColorOfPerson(meeting.getCreator()));
//						System.out.println(calendarModel.getColorOfPerson(selectedPerson).toString());
//					}
//				}
//				setText(meeting.getTitle());
//			} else {
//				component.setBackground(table.getBackground());
//			}
//		}
	}

	return component;
}
}