package server;

import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

import networking.packages.AuthenticationRequest;
import networking.packages.AuthenticationResponse;
import networking.packages.AuthenticationResponse.AuthenticationResponseType;
import networking.packages.NetworkRequest;
import networking.packages.ReceivedRequest;
import data.Person;

public class ServerRequestHandler implements Runnable{
	/**
	 * Will listen to a blockingqueue and when it is populated 
	 */
	BlockingQueue<ReceivedRequest> requests;
	private DBController dbController;
	
	public ServerRequestHandler(BlockingQueue<ReceivedRequest> requests){
		dbController = new DBController();
		this.requests = requests;
	}
	
	private AuthenticationResponse handleAuthenticationRequest(AuthenticationRequest aRequest){
		try {
			if(dbController.personExists(aRequest.getUsername())){
				if(dbController.authenticateUser(aRequest.getUsername(), aRequest.getPassword())){
					return new AuthenticationResponse(AuthenticationResponseType.APPROVED);
				}
				return new AuthenticationResponse(AuthenticationResponseType.WRONG_PASS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new AuthenticationResponse(AuthenticationResponseType.USER_NOEXIST);
		
	}
	
	private void processRequest(ReceivedRequest request) {
		switch(request.networkRequest.getEventType()){
		case AUTHENTICATION:
			handleAuthenticationRequest((AuthenticationRequest) request.networkRequest);
			break;
		case LOGOUT:
			break;
		case QUERY:
			break;
		default:
			OutputController.output("Received a request, but unable to determine type: " + request);
			break;
		}
	}
	
	public void run(){
		while(true){
			try {
				processRequest(requests.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
