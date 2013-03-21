package data;

import java.io.Serializable;

public class Notification implements Serializable{
	private static final long serialVersionUID = 1L;
	private long time;
	private char approved;
	private char kind;
	private Meeting meeting;
	private Person person;
	
	public Notification(long time, char approved, char kind, Meeting meeting,
			Person person) {
		super();
		this.time = time;
		this.approved = approved;
		this.kind = kind;
		this.meeting = meeting;
		this.person = person;
	}

	public long getTime() {
		return time;
	}

	public char getApproved() {
		return approved;
	}

	public char getKind() {
		return kind;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public Person getPerson() {
		return person;
	}

	@Override
	public String toString() {
		return "Notification ["+ ", approved=" + approved
				 + ", meeting=" + meeting + ", person="
				+ person + "]";
	}
	
	
}
