package data;

public class Meeting {
	private int meetingID;
	private long startTime;
	private long endTime;
	private String description;
	private Team team;
	private MeetingRoom room;
	
	
	public Meeting(int meetingID, long startTime, long endTime,
			String description, Team team, MeetingRoom room) {
		super();
		this.meetingID = meetingID;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		this.team = team;
		this.room = room;
	}


	public int getMeetingID() {
		return meetingID;
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
	
	
}
