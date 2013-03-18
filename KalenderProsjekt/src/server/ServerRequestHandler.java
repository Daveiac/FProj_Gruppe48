package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import networking.packages.AuthenticationRequest;
import networking.packages.AuthenticationResponse;
import networking.packages.AuthenticationResponse.AuthenticationResponseType;
import networking.packages.QueryRequest;
import networking.packages.DataResponse;
import networking.packages.DataResponse.DataResponseType;
import networking.packages.Response;
import networking.packages.UpdateRequest;
import data.Alarm;
import data.Meeting;
import data.Notification;
import data.Person;
import data.Team;

public class ServerRequestHandler implements Runnable {
	/**
	 * Will listen to a blockingqueue and when it is populated handle the
	 * requests inside
	 */
	BlockingQueue<PendingResponse> responses;
	BlockingQueue<ReceivedRequest> requests;
	private DBController dbController;


	
	

	public ServerRequestHandler(BlockingQueue<PendingResponse> responses,
			BlockingQueue<ReceivedRequest> requests, DBController dbController) {
		super();
		this.responses = responses;
		this.requests = requests;
		this.dbController = dbController;
	}

	private void handleAuthenticationRequest(
			AuthenticationRequest aRequest, Socket client) {
		AuthenticationResponse aResponse;
		try {
			if (dbController.personExists(aRequest.getUsername())) {
				if (dbController.authenticateUser(aRequest.getUsername(),
						aRequest.getPassword())) {
					aResponse =  new AuthenticationResponse(
							AuthenticationResponseType.APPROVED);
				}
				aResponse = new AuthenticationResponse(
						AuthenticationResponseType.WRONG_PASS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		aResponse = new AuthenticationResponse(
				AuthenticationResponseType.USER_NOEXIST);
		
		try {
			while(!responses.offer(new PendingResponse(aResponse, client, false), 200, TimeUnit.MILLISECONDS));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	private DataResponse getEveryPerson() {
		List<Person> data = null;
		try {
			data = dbController.getEveryPerson();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DataResponse response = new DataResponse(data,
				DataResponseType.PERSON_RESPONSE);
		return response;
	}

	private DataResponse getNotificationsByMeeting(Meeting meeting) {
		List<Notification> notifications = null;
		try {
			notifications = dbController.getNotifications(meeting);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DataResponse response = new DataResponse(notifications,
				DataResponseType.NOTIFICATION_RESPONSE);
		return response;
	}

	private DataResponse getAlarms(String username) {
		List<Alarm> data = null;
		try {
			data = dbController.getAlarmsOfPerson(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DataResponse response = new DataResponse(data,
				DataResponseType.ALARM_RESPONSE);
		return response;
	}

	private DataResponse getMeetingsByPerson(String username) {
		List<Meeting> data = null;
		try {
			data = dbController.getEveryMeetingOwnedByPerson(new Person(null,
					0, null, null, username, null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DataResponse response = new DataResponse(data,
				DataResponseType.MEETING_RESPONSE);
		return response;
	}
	
	private DataResponse getTeamsByMeeting(Meeting meeting){
		List<Team> teams = null;
		
		try {
			teams = dbController.getTeamsByMeeting(meeting.getMeetingID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new DataResponse(teams, DataResponseType.MEETING_RESPONSE);
	}
	
	private void handleQueryRequest(QueryRequest request, Socket client) {
		DataResponse response = null;

		switch (request.getQueryType()) {
		case GET_NOTIFICATIONS_BY_PERSON:
			response = getNotificationsByPerson(request.getUsername());
			break;
		case GET_ALL_PERSONS:
			response = getEveryPerson();
			break;
		case GET_NOTIFICATIONS_BY_MEETING:
			response = getNotificationsByMeeting(request.getMeeting());
			break;
		case GET_ALARMS_BY_PERSON:
			response = getAlarms(request.getUsername());
		case GET_EVERY_MEETING_BY_PERSON:
			response = getMeetingsByPerson(request.getUsername());
			break;
		case GET_TEAMS_BY_MEETING:
			response = getTeamsByMeeting(request.getMeeting());
			break;
		default:
			break;
		}

		try {
			while(!responses.offer(new PendingResponse(response, client, false), 200, TimeUnit.MILLISECONDS));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

	private DataResponse getNotificationsByPerson(String username) {
		List<Notification> notifications = null;
		try {
			notifications = dbController.getNotifications(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DataResponse response = new DataResponse(notifications,
				DataResponseType.NOTIFICATION_RESPONSE);
		return response;
	}

	private void processRequest(ReceivedRequest request) {
		
		switch (request.networkRequest.getEventType()) {
		case AUTHENTICATION:
			handleAuthenticationRequest((AuthenticationRequest) request.networkRequest, request.clientSocket);
			break;
		case LOGOUT:
			break;
		case QUERY:
			handleQueryRequest((QueryRequest) request.networkRequest, request.clientSocket);
			break;
		case UPDATE:
			handleUpdateRequest((UpdateRequest) request.networkRequest, request.clientSocket);
			break;
		default:
			OutputController
					.output("Received a request, but unable to determine type: "
							+ request);
			break;
		}
		

	}
	
	private Response addAlarm(UpdateRequest request){
		Response response = null;
		List<Alarm> alarms = null;
		try {
			int alarmID = dbController.addAlarm(request.getAlarm());
			alarms = dbController.getAlarmsOfPerson(request.getSender().getUsername());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new DataResponse(alarms, DataResponseType.ALARM_RESPONSE);
		
	}

	private void addMeeting(Meeting meeting){
		
	}
	
	private void handleUpdateRequest(UpdateRequest request, Socket client) {
		Response response =  null;
		boolean respondToAllClients = false;
		switch(request.getUpdateType()){
		case CREATE_ALARM:
			response = addAlarm(request);
			break;
		case CREATE_MEETING:
			break;
		}
		
		try {
			while(!responses.offer(new PendingResponse(response, client, respondToAllClients), 200, TimeUnit.MILLISECONDS));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	public void run() {
		OutputController.output("RequestHandler initialized");
		while (true) {
			try {
				processRequest(requests.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}