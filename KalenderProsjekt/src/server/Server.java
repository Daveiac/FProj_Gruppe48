package server;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import networking.ConnectionListener;
import networking.RequestListener;
import networking.packages.NetworkRequest;


public class Server extends Thread{
	/*
	 * This is where the main method of the serverside program lives. This class
	 * will spawn new listener-threads
	 */
	
	private DBController dbController;
	private BlockingQueue<Socket> newClientQueue;
	private BlockingQueue<NetworkRequest> requestQueue;
	
	public Server(){
		dbController = new DBController();
		newClientQueue = new LinkedBlockingQueue<Socket>();
		initializeServer();
	}
	
	private void initializeServer(){
		Thread connectionListener = new Thread(new ConnectionListener());
		connectionListener.start();
		
		
		OutputController.output("Server initialized");
	}
	
	public void run(){
		while(true){
			try {
				Socket nClient = newClientQueue.take();
				(new Thread(new RequestListener(nClient, requestQueue))).start();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String args[]){
		(new Server()).start();
	}

}

