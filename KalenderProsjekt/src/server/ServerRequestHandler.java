package server;

import java.util.concurrent.BlockingQueue;

import networking.packages.AuthenticationRequest;
import networking.packages.NetworkRequest;

public class ServerRequestHandler implements Runnable{
	/**
	 * Will listen to a blockingqueue and when it is populated 
	 */
	BlockingQueue<NetworkRequest> requests;
	private DBController dbController;
	
	public ServerRequestHandler(BlockingQueue<NetworkRequest> requests){
		dbController = new DBController();
		this.requests = requests;
	}
	
	private void handleAuthenticationRequest(AuthenticationRequest aRequest){
		dbController.authenticatePerson()
	}
	
	private void processRequest(NetworkRequest request){
		switch(request.getEventType()){
		case AUTHENTICATION:
			handleAuthenticationRequest((AuthenticationRequest) request);
			break;
		case LOGOUT:
			break;
		case QUERY:
			break;
		default:
			OutputController.output("Received a requst, but unable to determine type: " + request);
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
