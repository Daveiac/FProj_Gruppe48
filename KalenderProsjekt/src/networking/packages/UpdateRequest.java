package networking.packages;

import data.Alarm;
import data.Meeting;
import data.Notification;
import data.Person;

public class UpdateRequest extends NetworkRequest{
	private Meeting meeting;
	private Alarm alarm;
	private Notification notification;
	private UpdateType updateType;
	private Person sender;
	
	public enum UpdateType{
		CREATE_MEETING, UPDATE_MEETING, CREATE_ALARM, UPDATE_NOTIFICATION, DELETE_MEETING
	}
	
	public UpdateRequest(Meeting meeting, Alarm a, Notification n, UpdateType updateType) {
		super(EventType.UPDATE);
		this.meeting = meeting;
		this.updateType = updateType;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public Alarm getAlarm() {
		return alarm;
	}

	public Notification getNotification() {
		return notification;
	}

	public UpdateType getUpdateType() {
		return updateType;
	}
	
	public Person getSender(){
		return sender;
	}
	
	
	
	
}
