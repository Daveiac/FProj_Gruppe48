package client;

import java.io.IOException;

import networking.packages.AuthenticationRequest;
import networking.packages.QueryRequest;
import networking.packages.QueryRequest.QueryType;
import networking.packages.UpdateRequest;
import networking.packages.UpdateRequest.UpdateType;
import networking.*;
import java.net.*;

import data.Alarm;
import data.Notification;
import data.Person;
import data.Meeting;

import framePackage.DefaultView;

public class RequestHandler {
	
	
	//Authentication requests:
	public void sendAuthenticationRequest(String username, String password) throws IOException {
		AuthenticationRequest aReq = new AuthenticationRequest(username, password);
		Program.client.sendRequest(aReq);
	}
	
	//data requests:
	public void sendGetAllPersonsRequest() throws IOException {
		QueryRequest qReqGetAllPersons = new QueryRequest(null, null, QueryType.GET_ALL_PERSONS);
		Program.client.sendRequest(qReqGetAllPersons);
	}
	
	public void sendGetAllMeetingroomsRequest() throws IOException {
		QueryRequest qReqGetAllMeetingrooms = new QueryRequest(null, null, QueryType.GET_ALL_MEETINGROOMS);
		Program.client.sendRequest(qReqGetAllMeetingrooms);
	}
	
	public void sendGetAllNotificationsRequest() throws IOException {
		QueryRequest qReqGetAllNotifications = new QueryRequest(null, null, QueryType.GET_ALL_NOTIFICATIONS);
		Program.client.sendRequest(qReqGetAllNotifications);
	}
	
	public void sendGetEvryMeetingRequest() throws IOException{
		QueryRequest qReqGetEveryMeeting =
				new QueryRequest(null, null, QueryType.GET_EVERY_MEETING);
		Program.client.sendRequest(qReqGetEveryMeeting);
	}
	
	public void sendGetAlarmsByPersonRequest(Person person) throws IOException{
		QueryRequest qReqGetAlarmsByPerson =
				new QueryRequest(person, null, QueryType.GET_ALARMS_BY_PERSON);
		Program.client.sendRequest(qReqGetAlarmsByPerson);
	}
	
	public void sendGetNotificationsByMeetingRequest(Meeting meeting) throws IOException{
		QueryRequest qReqGetNotificationsByMeeting =
				new QueryRequest(null, meeting, QueryType.GET_NOTIFICATIONS_BY_MEETING);
		Program.client.sendRequest(qReqGetNotificationsByMeeting);
	}
	
	public void sendGetNotificationsByPersonRequest(Person person) throws IOException{
		QueryRequest qReqGetNotificationsByPerson =
				new QueryRequest(person, null, QueryType.GET_NOTIFICATIONS_BY_PERSON);
		Program.client.sendRequest(qReqGetNotificationsByPerson);
	}
	
	public void sendGetTeamsByMeetingRequest(Meeting meeting) throws IOException{
		QueryRequest qReqGetTeamsByMeeting =
				new QueryRequest(null, meeting, QueryType.GET_TEAMS_BY_MEETING);
		Program.client.sendRequest(qReqGetTeamsByMeeting);
	}
	
	//Update requests:
	public void sendCreateMeetingRequest(Meeting m) throws IOException{
		UpdateRequest uReqCreateMeeting =
				new UpdateRequest(m, UpdateType.CREATE_MEETING);
		Program.client.sendRequest(uReqCreateMeeting);
	}
//	TODO
//	public void sendCreateAlarmRequest(Person p, Alarm a,Notification n, Meeting m) throws IOException{
//		UpdateRequest uReqCreateAlarm =
//				new UpdateRequest(m, a, n, UpdateType.CREATE_ALARM, p);
//		Program.client.sendRequest(uReqCreateAlarm);
//	}
	
	public void sendUpdateMeetingRequest(Person p, Alarm a, Notification n, Meeting m) throws IOException{
		UpdateRequest uReqUpdateMeeting =
				new UpdateRequest(m, UpdateType.UPDATE_METING);
		Program.client.sendRequest(uReqUpdateMeeting);
	}
//	TODO
//	public void sendUpdateNotificationRequest(Person p,Alarm a,Notification n,Meeting m)throws IOException{
//		UpdateRequest uReqUpdateNotification =
//				new UpdateRequest(m, a, n, UpdateType.UPDATE_NOTIFICATION, p);
//		Program.client.sendRequest(uReqUpdateNotification);
//	}
	
}

