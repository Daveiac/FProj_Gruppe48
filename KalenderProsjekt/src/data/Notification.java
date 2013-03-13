package data;

public class Notification {
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
		return "Notification [time=" + time + ", approved=" + approved
				+ ", kind=" + kind + ", meeting=" + meeting + ", person="
				+ person + "]";
	}
	
	
}
