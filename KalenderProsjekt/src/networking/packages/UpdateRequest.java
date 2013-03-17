package networking.packages;

import data.Alarm;
import data.Meeting;
import data.Notification;

public class UpdateRequest extends NetworkRequest{
	private Meeting meeting;
	private Alarm alarm;
	private Notification notification;
	private UpdateType updateType;
	
	public enum UpdateType{
		CREATE_MEETING, UPDATE_METING, CREATE_ALARM, UPDATE_NOTIFICATION
	}
	
	public UpdateRequest(Meeting meeting, Alarm alarm,
			Notification notification, UpdateType updateType) {
		super(EventType.UPDATE);
		this.meeting = meeting;
		this.alarm = alarm;
		this.notification = notification;
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
	
	
	
	
	
	
}
