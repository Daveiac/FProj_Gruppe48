package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import networking.packages.AuthenticationRequest;
import networking.packages.AuthenticationResponse;
import networking.packages.AuthenticationResponse.AuthenticationResponseType;
import networking.packages.QueryRequest;
import networking.packages.QueryResponse;
import networking.packages.QueryResponse.QueryResponseType;
import networking.packages.Response;
import data.Meeting;
import data.Notification;
import data.Person;

public class ServerRequestHandler implements Runnable {
	/**
	 * Will listen to a blockingqueue and when it is populated handle the
	 * requests inside
	 */
	BlockingQueue<ReceivedRequest> requests;
	private DBController dbController;

	public ServerRequestHandler(BlockingQueue<ReceivedRequest> requests) {
		dbController = new DBController();
		this.requests = requests;
	}

	private AuthenticationResponse handleAuthenticationRequest(
			AuthenticationRequest aRequest) {
		try {
			if (dbController.personExists(aRequest.getUsername())) {
				if (dbController.authenticateUser(aRequest.getUsername(),
						aRequest.getPassword())) {
					return new AuthenticationResponse(
							AuthenticationResponseType.APPROVED);
				}
				return new AuthenticationResponse(
						AuthenticationResponseType.WRONG_PASS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new AuthenticationResponse(
				AuthenticationResponseType.USER_NOEXIST);

	}

	private void sendResponse(Response response, Socket clientSocket) {
		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(clientSocket.getOutputStream());
			oos.writeObject(response);
			OutputController.output("Responded to "
					+ clientSocket.getInetAddress() + " with " + response);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private QueryResponse getEveryPerson(){
		List<Person> data = null;
		try {
			data = dbController.getEveryPerson();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		QueryResponse response = new QueryResponse(data, QueryResponseType.NOTIFICATION_RESPONSE);
		return response;
	}
	
	private QueryResponse getNotificationsByMeeting(Meeting meeting) {
		List<Notification> notifications = null;
		try {
			notifications = dbController.getNotifications(meeting);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		QueryResponse response = new QueryResponse(notifications, QueryResponseType.NOTIFICATION_RESPONSE);
		return response;
	}
	
	private QueryResponse handleQueryRequest(QueryRequest request) {
		QueryResponse response = null;

		switch (request.getQueryType()) {
		case GET_NOTIFICATIONS_BY_PERSON:
			response = getNotificationsByPerson(request.getUsername());
			break;
		case GET_ALL_PERSONS:
			response = getEveryPerson();
			break;
		case GET_NOTIFICATIONS_BY_MEETING:
			response = getNotificationsByMeeting(request.getMeeting());
		default:
			break;
		}

		return response;

	}

	private QueryResponse getNotificationsByPerson(String username) {
		List<Notification> notifications = null;
		try {
			notifications = dbController.getNotifications(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		QueryResponse response = new QueryResponse(notifications, QueryResponseType.NOTIFICATION_RESPONSE);
		return response;
	}

	private void processRequest(ReceivedRequest request) {
		Response response = null;
		switch (request.networkRequest.getEventType()) {
		case AUTHENTICATION:
			response = handleAuthenticationRequest((AuthenticationRequest) request.networkRequest);
			break;
		case LOGOUT:
			break;
		case QUERY:
			response = handleQueryRequest((QueryRequest) request.networkRequest);
			break;
		default:
			OutputController
					.output("Received a request, but unable to determine type: "
							+ request);
			break;
		}
		sendResponse(response, request.clientSocket);

	}

	public void run() {
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
