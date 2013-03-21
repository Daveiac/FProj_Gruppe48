package data;

import java.io.*;

public class Reservation implements Serializable{
	private static final long serialVersionUID = 1L;
	private TimeInterval timeInterval;
	private MeetingRoom meetingRoom;
	private Meeting meeting;

	public Reservation(TimeInterval timeInterval, MeetingRoom meetingRoom,
			Meeting meeting) {
		super();
		this.timeInterval = timeInterval;
		this.meetingRoom = meetingRoom;
		this.meeting = meeting;
	}

	public TimeInterval getTimeInterval() {
		return timeInterval;
	}

	public MeetingRoom getMeetingRoom() {
		return meetingRoom;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	@Override
	public String toString() {
		return "Reservation [timeInterval=" + timeInterval + ", meetingRoom="
				+ meetingRoom + ", meeting=" + meeting + "]";
	}

}
