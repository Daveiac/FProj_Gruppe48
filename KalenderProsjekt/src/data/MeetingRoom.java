package data;

public class MeetingRoom {
	private int roomID;
	
	
	
	public MeetingRoom(int roomID) {
		super();
		this.roomID = roomID;
	}



	public int getRoomID() {
		return roomID;
	}



	@Override
	public String toString() {
		return "MeetingRoom [roomID=" + roomID + "]";
	}
	
	
}
