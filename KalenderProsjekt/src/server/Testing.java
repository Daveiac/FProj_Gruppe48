package server;

public class Testing {

	/**
	 * This calss is for manual testing purposes ONLY. Never call on anything or create an instance of it!
	 */
	public static void main(String[] args){
		DBController control = new DBController();
		Meeting meeting = control.getMeeting(); 
		control.updateMeeting(meeting)
	}
}
