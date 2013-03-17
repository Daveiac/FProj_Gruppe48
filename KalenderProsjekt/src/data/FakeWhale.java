package data;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FakeWhale {

	/**
	 * A fake class to simulate connection to server
	 * All the request methods in this class is needed for the real class.
	 * This is the "whale", link between model and requests
	 */

	private ArrayList<Meeting> meetings;
	private CalendarModel cModel;
	private ArrayList<Person> persons;

	public FakeWhale(CalendarModel cModel) {
		this.cModel = cModel;
		persons = new ArrayList<Person>();
		
	}
	/**
	 * Just test code
	 * @param person
	 */
	private void reccieveMeetingsFromPerson2(Person person) {
		meetings = new ArrayList<Meeting>();
		ArrayList<Person> members = new ArrayList<Person>();
		Team team = new Team(0, null, members);
		MeetingRoom room = new MeetingRoom("Roomsa");
		members.add(person);
		
		long startTime = new GregorianCalendar(2013, 2, 14, 16, 30).getTimeInMillis();
		long endTime = new GregorianCalendar(2013, 2, 14, 17, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemy", "inHell", startTime, endTime, "This is a desc", team, room, person));
		startTime = new GregorianCalendar(2013, 2, 18, 10, 00).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 18, 11, 00).getTimeInMillis();
		meetings.add(new Meeting(0, "suppelol2", "stillInHell", startTime, endTime, "This is a desc", team, room, person));
		startTime = new GregorianCalendar(2013, 2, 19, 12, 30).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 19, 18, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøte3", "wtfWhyInHell", startTime, endTime, "This is a desc", team, room, person));
		startTime = new GregorianCalendar(2013, 2, 14, 12, 00).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 14, 15, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøte4", "fuInHell", startTime, endTime, "This is a desc", team, room, person));
		startTime = new GregorianCalendar(2013, 2, 16, 12, 00).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 16, 15, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøte5", "careInHell", startTime, endTime, "This is a desc", team, room, person));
		startTime = new GregorianCalendar(2013, 2, 17, 03, 00).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 17, 04, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøte6", "w00t?", startTime, endTime, "This is a desc", team, room, person));
		// TODO Auto-generated method stub
		
	}
	/**
	 * Just test code
	 * @param person
	 */
	private void reccieveMeetingsFromPerson1(Person person) {
		meetings = new ArrayList<Meeting>();
		ArrayList<Person> members = new ArrayList<Person>();
		Team team = new Team(0, null, members);
		MeetingRoom room = new MeetingRoom("Roomsa");
		members.add(person);
		
		long startTime = new GregorianCalendar(2013, 2, 14, 16, 30).getTimeInMillis();
		long endTime = new GregorianCalendar(2013, 2, 14, 17, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøte", "inHell", startTime, endTime, "This is a desc", team, room, person));
		startTime = new GregorianCalendar(2013, 2, 15, 10, 30).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 15, 11, 00).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøte2", "stillInHell", startTime, endTime, "This is a desc", team, room, person));
		startTime = new GregorianCalendar(2013, 2, 14, 16, 30).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 14, 17, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøte3", "wtfWhyInHell", startTime, endTime, "This is a desc", team, room, person));
		startTime = new GregorianCalendar(2013, 2, 14, 12, 00).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 14, 15, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøte4", "fuInHell", startTime, endTime, "This is a desc", team, room, person));
		startTime = new GregorianCalendar(2013, 2, 16, 12, 00).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 16, 15, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøte5", "careInHell", startTime, endTime, "This is a desc", team, room, person));
		startTime = new GregorianCalendar(2013, 2, 17, 03, 00).getTimeInMillis();
		endTime = new GregorianCalendar(2013, 2, 17, 04, 30).getTimeInMillis();
		meetings.add(new Meeting(0, "suppemøte6", "w00t?", startTime, endTime, "This is a desc", team, room, person));
		
		cModel.addAllMeetingsOfPerson(meetings, person);
		
	}
	/**
	 * Just test code
	 */
	private void receiveAllPersons() {
		Person kari = new Person("karitr@ggk.no", 81549300, "Kari", "Traa", "karitr", "123456");
		Person jon = new Person("jonbl@ggk.no", 81549301, "Jon", "blund", "jonbl", "123456");
		Person chris = new Person("chrispr@ggk.no", 81549302, "Christoffer", "Pman", "chrispr", "123456");
		Person david = new Person("davhov@ggk.no", 81549303, "David", "Hovind", "davhov", "123456");
		Person batman = new Person("batman@ggk.no", 81549304, "Bat", "Man", "batman", "123456");
		persons.add(kari);
		persons.add(jon);
		persons.add(chris);
		persons.add(david);
		persons.add(batman);
		cModel.setAllPersons(persons);
	}
	/**
	 * Requests every meeting for a person
	 * @param person the persons whose meetings to request.
	 */
	public void requestEveryMeetingForPerson(Person person){ 
		if(persons.indexOf(person) %2 == 0) {
			reccieveMeetingsFromPerson1(person);
		} else {
			reccieveMeetingsFromPerson2(person);
		}
	}
	/**
	 * This method will request every person from the database.
	 */
	public void requestEveryPerson(){
		receiveAllPersons();
	}
	
	public void pushMeeting(Meeting meeting) {
		reccieveMeeting(meeting);
	}
	
	
	private void reccieveMeeting(Meeting meeting) {
		cModel.addMeeting(meeting);
	}
}
