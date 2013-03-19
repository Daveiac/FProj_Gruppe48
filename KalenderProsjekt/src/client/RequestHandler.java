package client;

import java.io.IOException;

import networking.packages.QueryRequest;
import networking.packages.QueryRequest.QueryType;
import networking.*;
import java.net.*;

import data.Person;

import framePackage.DefaultView;

public class RequestHandler {
	
//	public void Run() throws IOException, InterruptedException{
//		Client client = new Client( InetAddress.getByName(Constants.serverIP) );
//		sendGetAllPersonsRequest(client);
//	}

	public void sendGetAllPersonsRequest() throws IOException {
		QueryRequest qReqGetAllPersons = new QueryRequest(null, null, QueryType.GET_ALL_PERSONS);
		Program.client.sendRequest(qReqGetAllPersons);
	}
	
	public void sendGetEvryMeetingByPersonRequest(Person p) throws IOException{
		QueryRequest qReqGetEveryMeetingByPerson = new QueryRequest(p, null, QueryType.GET_EVERY_MEETING_BY_PERSON);
		Program.client.sendRequest(qReqGetEveryMeetingByPerson);
	}
	
	/*
	public void sendGetAlarmsByPersonRequest() throws IOExeption{
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

