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

		if (meeting != null) {
			if (meeting.getTeam() != null) {

//				for (Person selectedPerson : calendarModel.getSelectedPersons()) {
//					for (Person teamMember : meeting.getTeam().getMembers()) {
//						if (selectedPerson.getUsername().equals(teamMember.getUsername())) {
//
//							System.out.println("selectedPerson = teamMember");
//
//							Color background = component.getBackground();
//
//							boolean isUser = calendarModel.getColorOfPerson(calendarModel.getUser()).equals(background);
//							boolean isCreator = calendarModel.getColorOfPerson(meeting.getCreator()).equals(background);
//
//							if (!isUser	&& !isCreator) {
//								component.setBackground(calendarModel.getColorOfPerson(selectedPerson));
//								System.out.println("FARGE else: valgt person satt");
//							}
//
//							else if (!isCreator) {
//								component.setBackground(calendarModel.getColorOfPerson(meeting.getCreator()));
//
//								System.out.println("FARGE elif: creator satt");
//							}
//
//							else {
//								component.setBackground(calendarModel.getColorOfPerson(calendarModel.getUser()));
//
//								System.out.println("FARGE if  : user satt");
//							}
//
//
//							//							if (teamMember.getUsername().equals(calendarModel.getUser().getUsername())) {
//							//								component.setBackground(calendarModel.getColorOfPerson(calendarModel.getUser()));
//							//
//							//								System.out.println("FARGE if  : user satt");
//							//							}
//							//
//							//							else if (teamMember.equals(meeting.getCreator().getUsername())) {
//							//								component.setBackground(calendarModel.getColorOfPerson(meeting.getCreator()));
//							//
//							//								System.out.println("FARGE elif: creator satt");
//							//							}
//							//
//							//							else if (calendarModel.getColorOfPerson(calendarModel.getUser()).equals(component.getBackground())){
//							//								component.setBackground(calendarModel.getColorOfPerson(selectedPerson));
//							//								System.out.println("FARGE else: valgt person satt");
//							//							}
//						}
//						//						else
//						//							System.out.println("Du ska'kke se detta møtet du!")
//						//							;
//					}
//				}
				setText(meeting.getTitle());
			}
		}
		else {
			component.setBackground(table.getBackground());
		}



		//				for (Person selectedPerson : calendarModel.getSelectedPersons()) {
		//					System.out.println("Møte som skal få tildelt farge!");
		//					System.out.println("valgtPers: " + calendarModel.getSelectedPersons());
		//					System.out.println("deltagere: " + meeting.getTeam().getMembers().toString());
		//					for (Person teamMember : meeting.getTeam().getMembers()) {
		//						if (selectedPerson.getUsername().equals(teamMember.getUsername())) {
		//							if (selectedPerson.getUsername().equals(calendarModel.getUser().getUsername())) {
		//								component.setBackground(calendarModel.getColorOfPerson(calendarModel.getUser()));
		//								isUser = true;
		//
		//							}
		//							else if (selectedPerson.getUsername().equals(meeting.getCreator().getUsername())) {
		//								component.setBackground(calendarModel.getColorOfPerson(meeting.getCreator()));
		//								isCreator = true;
		//							}
		//							else
		//								component.setBackground(calendarModel.getColorOfPerson(selectedPerson));
		//						}
		//						else
		//							System.out.println("Du ska'kke se detta møtet du!")
		//							;
		//					}
		//				} else {
		//					component.setBackground(calendarModel.getColorOfPerson(meeting.getCreator()));
		//					System.out.println(calendarModel.getColorOfPerson(selectedPerson).toString());
		//				}
		//			}
		//			setText(meeting.getTitle());
		//		}


		return component;
	}

}