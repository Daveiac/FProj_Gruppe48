package data;

public class Alarm {
	/*
	 * A class that represents an entry in the alarm table in the database.
	 */
	private int alarmID;
	private char kind;
	private long time;
	private int meetingID;
	private int appointmentID;
	public Alarm(int alarmID, char kind, long time, int meetingID,
			int appointmentID) {
		super();
		this.alarmID = alarmID;
		this.kind = kind;
		this.time = time;
		this.meetingID = meetingID;
		this.appointmentID = appointmentID;
	}
	public int getAlarmID() {
		return alarmID;
	}
	public char getKind() {
		return kind;
	}
	public long getTime() {
		return time;
	}
	public int getMeetingID() {
		return meetingID;
	}
	public int getAppointmentID() {
		return appointmentID;
	}
	
	
}
