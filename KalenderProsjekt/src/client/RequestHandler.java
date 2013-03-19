package client;

import java.io.IOException;

import networking.packages.AuthenticationRequest;
import networking.packages.QueryRequest;
import networking.packages.QueryRequest.QueryType;
import networking.*;
import java.net.*;

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
	
	/*
	 * 
	 */

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
	
}

