package data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import server.DBController;

public class CalendarModel {
	List<Person> persons;
	HashMap<Person, ArrayList<Meeting>> hasjmap;
	ArrayList<Boolean> selected;
	DBController data;



	public CalendarModel() {
		persons = new ArrayList<Person>();
		hasjmap = new HashMap<Person,ArrayList<Meeting>>();
		selected = new ArrayList<Boolean>();
		data = new DBController();

		try {
			persons = data.getEveryPerson();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	public ArrayList<Meeting> getMeetings(Person person) {
		return hasjmap.get(person);
	}

	public ArrayList<Meeting> getMeetings(Person person, long start, long end) {
		ArrayList<Meeting> meetings = hasjmap.get(person);
		ArrayList<Meeting> newMeetings = new ArrayList<Meeting>();
		for (Meeting meeting : meetings) {
			if (meeting.getStartTime() >= start && meeting.getEndTime() < end) {
				newMeetings.add(meeting);
			}
		}
		return newMeetings;
	}

	public ArrayList<Person> getPersons() {
		return persons;
	}

	public void setPersons(ArrayList<Person> persons) {
		this.persons = persons;
	}

	public HashMap<Person, ArrayList<Meeting>> getHasjmap() {
		return hasjmap;
	}

	public void setHasjmap(HashMap<Person, ArrayList<Meeting>> hasjmap) {
		this.hasjmap = hasjmap;
	}

	public ArrayList<Boolean> getSelected() {
		return selected;
	}

	public void setSelected(ArrayList<Boolean> selected) {
		this.selected = selected;
	}



}
