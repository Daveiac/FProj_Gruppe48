package data;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import client.Program;

import framePackage.DefaultView;


public class CalendarModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1762790448612918057L;
	private List<Person> persons;
	private ArrayList<Meeting> meetings;
	private ArrayList<Boolean> selected;
//	private FakeWhale data;
	private PropertyChangeSupport pcs;
	private ArrayList<Notification> notificationsOfUser;
	private ArrayList<Notification> notifications;
	private ArrayList<Alarm> alarms;
	private String username;
	private int responseCount;
	private Person user;
	private ArrayList<MeetingRoom> meetingRooms;
	private static final Color[] colors = {Color.red,Color.blue,Color.darkGray,Color.orange,Color.magenta,Color.gray,Color.pink};
	public static final String SELECTED_Property = "SELECTED", MEETINGS_CHANGED_Property = "MEETINGS",
			NOTIFICATIONS_CHANGED_Property = "NNOTI", CALENDAR_LOADED_Property = "LOADED", PERSONS_ADDED_Property ="PERSONS";



	public CalendarModel() {
		pcs = new PropertyChangeSupport(this);
	}
	public void init(String username) {
		this.username = username;
		persons = new ArrayList<Person>();
		meetings = new ArrayList<Meeting>();
		selected = new ArrayList<Boolean>();
		notifications = new ArrayList<Notification>();
		notificationsOfUser = new ArrayList<Notification>();
		alarms = new ArrayList<Alarm>();
		meetingRooms = new ArrayList<MeetingRoom>();
		requestAllPersons();
	}
	
	public ArrayList<Meeting> getAllMeetingsOfPerson(Person person, boolean attending) {
		ArrayList<Meeting> allMeetings = new ArrayList<Meeting>();
		for (Notification n : notifications) {
			if(n.getPerson().getUsername().equals(person.getUsername()) ) {
				allMeetings.add(n.getMeeting());
			}
		}
		for (Meeting meeting : meetings) {
			if(meeting.getTeam() == null && meeting.getCreator().getUsername().equals(user.getUsername())) {
				allMeetings.add(meeting);
			}
		}
		return allMeetings;
	}
	/**
	 * Gets ALL of the meetings of a person in the given time interval
	 * @param person the person whose meetings to get
	 * @param start the minimum start time of the meeting
	 * @param end the maximum end time of the meeting
	 * @return all the meetings of the given person within the given time interval.
	 */
//	TODO
//	public ArrayList<Meeting> getMeetings(Person person, long start, long end) {
//		ArrayList<Meeting> meetings = meetings.get(person);
//		ArrayList<Meeting> newMeetings = new ArrayList<Meeting>();
//		for (Meeting meeting : meetings) {
//			if (meeting.getStartTime() >= start && meeting.getEndTime() < end) {
//				newMeetings.add(meeting);
//			}
//		}
//		return newMeetings;
//	}
	
	public List<Person> getPersons() {
		return persons;
	}
	//TODO
//	public HashMap<Person, ArrayList<Meeting>> getHasjmap() {
//		return meetings;
//	}

	public ArrayList<Boolean> getSelected() {
		return selected;
	}

	public void setAllSelected(ArrayList<Boolean> selected) {
		this.selected = selected;
	}
	
	public void setSelected(Person person, boolean sel) {
		selected.set(persons.indexOf(person), sel);
		pcs.firePropertyChange(SELECTED_Property, person, person);
	}
	private void requestEverything() {
		requestAllMeetings();
		requestAllNotifications();
		requestAlarmsOfUser();
		requestAllRooms();
		
	}
	private void requestAllRooms() {
		// TODO Auto-generated method stub
		
	}
	private void requestAlarmsOfUser() {
		// TODO Auto-generated method stub
		
	}
	private void requestAllNotifications() {
		// TODO Auto-generated method stub
		
	}
	private void requestAllPersons() {
		try {
			if(Program.reqHandler != null){
				Program.reqHandler.sendGetAllPersonsRequest();
				responseCount = 0;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void requestAllMeetings() {
		for (Person p : persons) {
			try {
				Program.reqHandler.sendGetEvryMeetingByPersonRequest(p);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Sets all the persons of the model.
	 * This method will only be called once by the server at startup
	 * @param persons
	 */
	public void setAllPersons(List<Person> persons) {
		this.persons = persons;
		for (Person person : persons) {
			if(person.getUsername().equals(username)) {
				user = person;
				selected.add(true);
			} else {
				selected.add(false);
				
			}
		}
		pcs.firePropertyChange(PERSONS_ADDED_Property, null, persons);
		requestEverything();
	}
	
	public void setAllMeetings(List<Meeting> meetings) {
		this.meetings = (ArrayList<Meeting>) meetings;
		pcs.firePropertyChange(CALENDAR_LOADED_Property, null, meetings);
	}
	public void setAllRooms(List<MeetingRoom> rooms) {
		meetingRooms = (ArrayList<MeetingRoom>) rooms;
	}
	public void setAlarmsOfUser(List<Alarm> alarms) {
		this.alarms = (ArrayList<Alarm>) alarms;
	}
	public void setAllNotifications(List<Notification> notifications) {
		this.notifications = (ArrayList<Notification>) notifications;
		for (Notification notification : notifications) {
			if (notification.getPerson().getUsername().equals(user.getEmail())) {
				notificationsOfUser.add(notification);
			}
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
	public void pushMeeting(Meeting meeting) {
		try {
			Program.reqHandler.sendCreateMeetingRequest(meeting);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void changeMeeting(Meeting meeting) {
		//TODO
	}
	public void removeMeeting(String meetingID) {
		//TODO
	}
	public ArrayList<MeetingRoom> getRooms(){
		return meetingRooms;
	}
//	TODO
//	public ArrayList<MeetingRoom> getAvailableRooms(long startTime, long endTime){
//		ArrayList<MeetingRoom> rooms = new ArrayList<MeetingRoom>();
//		rooms.addAll(meetingRooms);
//		for(ArrayList<Meeting> meetings: meetings.values()){
//			for (int i = 0; i < meetings.size(); i++) {
//				long meetStart = meetings.get(i).getStartTime();
//				long meetEnd = meetings.get(i).getEndTime();
//				if ((meetStart >= startTime && meetStart < endTime) || (meetEnd > startTime && meetEnd < endTime)) {
//					rooms.remove(meetings.get(i).getRoom());
//				}
//			}
//		}
//		return rooms;
//	}
	public ArrayList<Notification> getNotifications(Person user) {
		return notificationsOfUser;
	}
	public Person getUser() {
		return user;
	}
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
}
