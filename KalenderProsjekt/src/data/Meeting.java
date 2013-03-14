package data;

public class Meeting {
	private int meetingID;
	private String title;
	private long startTime;
	private long endTime;
	private String description;
	private Team team;
	private MeetingRoom room;
	private Person creator;
	
	
	public Meeting(int meetingID, String title, long startTime, long endTime,
			String description, Team team, MeetingRoom room, Person creator) {
		super();
		this.meetingID = meetingID;
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		this.team = team;
		this.room = room;
		this.creator = creator;
	}


	public int getMeetingID() {
		return meetingID;
	}

	public String getTitle() {
		return title;
	}

	public long getStartTime() {
		return startTime;
	}


	public long getEndTime() {
		return endTime;
	}


	public String getDescription() {
		return description;
	}


	public Team getTeam() {
		return team;
	}


	public MeetingRoom getRoom() {
		return room;
	}
	
	public Person getCreator() {
		return creator;
	}


	@Override
	public String toString() {
		return "Meeting [meetingID=" + meetingID + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", description=" + description
				+ ", team=" + team + ", room=" + room + ", creator=" + creator
				+ "]";
	}





	
}
