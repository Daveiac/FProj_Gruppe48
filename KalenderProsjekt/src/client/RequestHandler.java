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
	
//	public void Run() throws IOException, InterruptedException{
//		Client client = new Client( InetAddress.getByName(Constants.serverIP) );
//		sendGetAllPersonsRequest(client);
//	}
	public void sendAuthenticationRequest(String username, String password) throws IOException {
		AuthenticationRequest aReq = new AuthenticationRequest(username, password);
		Program.client.sendRequest(aReq);
	}

	public void sendGetAllPersonsRequest() throws IOException {
		QueryRequest qReqGetAllPersons = new QueryRequest(null, null, QueryType.GET_ALL_PERSONS);
		Program.client.sendRequest(qReqGetAllPersons);
	}
	
	public void sendGetEvryMeetingByPersonRequest(Person person) throws IOException{
		QueryRequest qReqGetEveryMeetingByPerson = new QueryRequest(person, null, QueryType.GET_EVERY_MEETING_BY_PERSON);
		Program.client.sendRequest(qReqGetEveryMeetingByPerson);
	}
	
	public void sendGetAlarmsByPersonRequest(Person person) throws IOException{
		QueryRequest qReqGetAlarmsByPerson = new QueryRequest(person, null, QueryType.GET_ALARMS_BY_PERSON);
		Program.client.sendRequest(qReqGetAlarmsByPerson);
	}
	
	public void sendGetNotificationsByMeetingRequest(Meeting meeting) throws IOException{
		QueryRequest qReqGetNotificationsByMeeting = new QueryRequest(null, meeting, QueryType.GET_NOTIFICATIONS_BY_MEETING);
		Program.client.sendRequest(qReqGetNotificationsByMeeting);
	}
	
	public void sendGetNotificationsByPersonRequest(Person person) throws IOException{
		QueryRequest qReqGetNotificationsByPerson = new QueryRequest(person, null, QueryType.GET_NOTIFICATIONS_BY_PERSON);
		Program.client.sendRequest(qReqGetNotificationsByPerson);
	}
	
	public void sendGetTeamsByMeetingRequest(Meeting meeting) throws IOException{
		QueryRequest qReqGetTeamsByMeeting = new QueryRequest(null, meeting, QueryType.GET_TEAMS_BY_MEETING);
		Program.client.sendRequest(qReqGetTeamsByMeeting);
	}
	
	public void sendGetCreateMeetingRequest(Person person, Alarm alarm, Notification notification, Meeting meeting) throws IOException{
		UpdateRequest uReqCreateMeeting = new UpdateRequest(meeting, alarm, notification, UpdateType.CREATE_MEETING, person);
		Program.client.sendRequest(uReqCreateMeeting);
	}
	
	public void sendGetCreateAlarmRequest(Person person, Alarm alarm, Notification notification, Meeting meeting) throws IOException{
		UpdateRequest uReqCreateAlarm = new UpdateRequest(meeting, alarm, notification, UpdateType.CREATE_ALARM, person);
		Program.client.sendRequest(uReqCreateAlarm);
	}
	
	public void sendGetUpdateMeetingRequest(Person person, Alarm alarm, Notification notification, Meeting meeting) throws IOException{
		UpdateRequest uReqUpdateMeeting = new UpdateRequest(meeting, alarm, notification, UpdateType.UPDATE_METING, person);
		Program.client.sendRequest(uReqUpdateMeeting);
	}
	
	public void sendGetCreateUpdateNotification(Person person, Alarm alarm, Notification notification, Meeting meeting) throws IOException{
		UpdateRequest uReqUpdateNotification = new UpdateRequest(meeting, alarm, notification, UpdateType.UPDATE_NOTIFICATION, person);
		Program.client.sendRequest(uReqUpdateNotification);
	}
	
}

