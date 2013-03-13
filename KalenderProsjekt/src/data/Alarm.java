package data;

public class Alarm {
	/*
	 * A class that represents an entry in the alarm table in the database.
	 */
	private int alarmID;
	private char kind;
	private long time;
	private Meeting meeting;
	private Person person;
	
	public Alarm(int alarmID, char kind, long time, Meeting meeting,
			Person person) {
		super();
		this.alarmID = alarmID;
		this.kind = kind;
		this.time = time;
		this.meeting = meeting;
		this.person = person;
	}
	public Alarm(int alarmID, char kind, long time, Meeting meeting) {
		super();
		this.alarmID = alarmID;
		this.kind = kind;
		this.time = time;
		this.meeting = meeting;
	}
	public int getAlarmID() {
		return alarmID;
	}
	public char getKind() {
		return kind;
	}
	public long getTime() {
		return time;
	}
	public Meeting getMeeting() {
		return meeting;
	}
	public Person getPerson() {
		return person;
	}

	
	
}
