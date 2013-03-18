package framePackage;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import data.CalendarModel;
import data.Meeting;
import data.Person;

@SuppressWarnings("serial")
public class DayTableCellRenderer extends DefaultTableCellRenderer {

	private CalendarModel calendarModel;
	private List<Person> persons;

	public DayTableCellRenderer(CalendarModel calendarModel, List<Person> persons) {
		this.calendarModel = calendarModel;
		this.persons = persons;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		Meeting meeting = (Meeting) value;

		if (meeting != null) {
			for (Person person : persons) {
				component.setBackground(calendarModel.getColorOfPerson(person));
			}
			setText(meeting.getTitle());
		}
		else {
			component.setBackground(table.getBackground());
		}

		return component;
	}
}