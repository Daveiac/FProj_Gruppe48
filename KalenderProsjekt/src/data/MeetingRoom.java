package data;

import java.io.Serializable;

public class MeetingRoom implements Serializable{
	private String roomName;
	
	
	
	public MeetingRoom(String roomName) {
		super();
		this.roomName = roomName;
	}



	public String getRoomName() {
		return roomName;
	}



	@Override
	public String toString() {
		return "MeetingRoom [roomName=" + roomName + "]";
	}
	
	
}
