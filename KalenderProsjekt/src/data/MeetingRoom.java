package data;

public class MeetingRoom {
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
