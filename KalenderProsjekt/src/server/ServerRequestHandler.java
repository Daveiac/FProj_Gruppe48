package server;

import java.util.concurrent.BlockingQueue;
import networking.packages.NetworkRequest;

public class ServerRequestHandler implements Runnable{
	/**
	 * Will listen to a blockingqueue and when it is populated 
	 */
	BlockingQueue<NetworkRequest> requests;
	
	public ServerRequestHandler(BlockingQueue<NetworkRequest> requests){
		this.requests = requests;
	}
	
	public void processRequest(NetworkRequest request){
		
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
