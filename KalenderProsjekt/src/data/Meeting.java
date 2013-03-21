package data;

import java.io.Serializable;

public class Meeting implements Serializable{
	private static final long serialVersionUID = 1L;
	private int meetingID;
	private String title;
	private String location;
	private long startTime;
	private long endTime;
	private String description;
	private Team team;
	private MeetingRoom room;
	private Person creator;
	
	
	public Meeting(int meetingID, String title, String location, long startTime, long endTime,
			String description, Team team, MeetingRoom room, Person creator) {
		super();
		this.meetingID = meetingID;
		this.title = title;
		this.location = location;
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
	
	public String getLocation() {
		return location;
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
		return "Meeting [meetingID=" + meetingID + ", title=" + title
				+ ", team=" + team + ", creator=" + creator + "]";
	}





	
}
