package data;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import server.DBController;

public class CalendarModel {
	private List<Person> persons;
	private HashMap<Person, ArrayList<Meeting>> personMeetingRelation;
	private ArrayList<Boolean> selected;
	private FakeWhale data;
	private PropertyChangeSupport pcs;
	private static final Color[] colors = {Color.red,Color.blue,Color.green,Color.orange,Color.magenta,Color.gray,Color.pink};
	public static final String SELECTED_Property = "SELECTED", MEETING_ADDED_Property = "NEW_M", 
			MEETING_CHANGED_Property = "CHANGE", MEETING_REMOVED_Property = "REMOVE",
			NOTIFICATION_ADDED_Property = "NEW_N", CALENDAR_LOADED_Property = "LOADED";



	public CalendarModel() {
		pcs = new PropertyChangeSupport(this);
		persons = new ArrayList<Person>();
		personMeetingRelation = new HashMap<Person,ArrayList<Meeting>>();
		selected = new ArrayList<Boolean>();
		data = new FakeWhale(this);

	}
	/**
	 * Gets ALL of the meetings of a person
	 * @param person the person whose meetings to get
	 * @return all the meetings of the given person
	 */
	public ArrayList<Meeting> getMeetings(Person person) {
		return personMeetingRelation.get(person);
	}
	/**
	 * Gets ALL of the meetings of a person in the given time interval
	 * @param person the person whose meetings to get
	 * @param start the minimum start time of the meeting
	 * @param end the maximum end time of the meeting
	 * @return all the meetings of the given person within the given time interval.
	 */
	public ArrayList<Meeting> getMeetings(Person person, long start, long end) {
		ArrayList<Meeting> meetings = personMeetingRelation.get(person);
		ArrayList<Meeting> newMeetings = new ArrayList<Meeting>();
		for (Meeting meeting : meetings) {
			if (meeting.getStartTime() >= start && meeting.getEndTime() < end) {
				newMeetings.add(meeting);
			}
		}
		return newMeetings;
	}
	
	public List<Person> getPersons() {
		return persons;
	}

	public HashMap<Person, ArrayList<Meeting>> getHasjmap() {
		return personMeetingRelation;
	}

	public ArrayList<Boolean> getSelected() {
		return selected;
	}

	public void setSelected(ArrayList<Boolean> selected) {
		this.selected = selected;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	/**
	 * Sets all the persons of the model.
	 * This method will only be called once by the server at startup
	 * @param persons
	 */
	public void setAllPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	/**
	 * This method will add a meeting to the model.
	 * If the meeting already exists this method will fire a MEETING_CHANGED_Property,
	 * else this method will fire a MEETING_ADDED_Property.
	 * @param meeting the meeting to be added.
	 */
	public void addMeeting(Meeting meeting) {
		boolean meetingChanged = false;
		int meetingID = meeting.getMeetingID();
		Meeting oldMeeting = null;
		for(ArrayList<Meeting> meetings: personMeetingRelation.values()){
			for (int i = 0; i < meetings.size(); i++) {
				if (meetings.get(i).getMeetingID() == meetingID) {
					meetingChanged = true;
					oldMeeting = meetings.get(i);
					meetings.set(i, meeting);
				}
			}
		}
		if (meetingChanged) {
			pcs.firePropertyChange(MEETING_CHANGED_Property, oldMeeting, meeting);
		} else {
			pcs.firePropertyChange(MEETING_ADDED_Property, null, meeting);
		}
	}
	
	public void addAllMeetingsOfPerson(ArrayList<Meeting> meetings, Person person) {
		personMeetingRelation.put(person, meetings);
		if(personMeetingRelation.size() == persons.size()) {
			pcs.firePropertyChange(CALENDAR_LOADED_Property, null, personMeetingRelation);
		}
	}
	
	public List<Person> getSelectedPersons() {
		List<Person> selectedPersons = new ArrayList<Person>();
		for (int i = 0; i < selected.size(); i++) {
			if(selected.get(i)) {
				selectedPersons.add(persons.get(i));
			}
		}
		return selectedPersons;
	}
	
	public Color getColorOfPerson(Person person) {
		return colors[persons.indexOf(person)];
	}
}
