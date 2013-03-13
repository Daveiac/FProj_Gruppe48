package server;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import networking.requests.ClientConnectedRequest;

public class Server {
	/*
	 * This is where the main method of the serverside program lives. This class
	 * will spawn new listener-threads
	 */
	
	private DBController dbController;
	private BlockingQueue<ClientConnectedRequest> newClientQueue;
	
	public Server(){
		dbController = new DBController();
		newClientQueue = new LinkedBlockingQueue<ClientConnectedRequest>();
	}
	
	
	
	public static void main(String args[]){
		
	}

}
