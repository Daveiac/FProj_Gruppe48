package client;

import java.io.IOException;

import networking.packages.QueryRequest;
import networking.packages.QueryRequest.QueryType;
import networking.*;
import java.net.*;

public class RequestHandler {
	
	public void Run() throws IOException, InterruptedException{
		Client client = new Client( InetAddress.getByName(Constants.serverIP) );
		QueryRequest qReqGetAllPersons = new QueryRequest(null, null, QueryType.GET_ALL_PERSONS);
		client.sendRequest(qReqGetAllPersons);
	}
	
	/*
	//TODO
	//Add PersonObject and MeetingObject
	public void sendGetEvryMeetingByPersonRequest(){
		Client client = new Client( InetAddress.getByName(Constants.serverIP) );
		QueryRequest qReqGetEveryMeetingByPerson = new QueryRequest(PersonObject, null, QueryType.GET_EVERY_MEETING_BY_PERSON);
		client.sendRequest(qReqGetEveryMeetingByPerson);
	}
	
	public void sendGetAlarmsByPersonRequest(){
		Client client = new Client( InetAddress.getByName(Constants.serverIP) );
		QueryRequest qReqGetAlarmsByPerson = new QueryRequest(PersonObject, null, QueryType.GET_ALARMS_BY_PERSON);
		client.sendRequest(qReqGetAlarmsByPerson);
	}
	
	public void sendGetNotificationsByMeetingRequest(){
		Client client = new Client( InetAddress.getByName(Constants.serverIP) );
		QueryRequest qReqGetNotificationsByMeeting = new QueryRequest(null, MeetingObject, QueryType.GET_NOTIFICATIONS_BY_MEETING);
		client.sendRequest(qReqGetNotificationsByMeeting);
	}
	
	public void sendGetNotificationsByPersonRequest(){
		Client client = new Client( InetAddress.getByName(Constants.serverIP) );
		QueryRequest qReqGetNotificationsByPerson = new QueryRequest(PersonObject, null, QueryType.GET_NOTIFICATIONS_BY_PERSON);
		client.sendRequest(qReqGetNotificationsByPerson);
	}
	
	public void sendGetTeamsByMeetingRequest(){
		Client client = new Client( InetAddress.getByName(Constants.serverIP) );
		QueryRequest qReqGetTeamsByMeeting = new QueryRequest(null, MeetingObject, QueryType.GET_TEAMS_BY_MEETING);
		client.sendRequest(qReqGetTeamsByMeeting);
	}
	*/
		
}

