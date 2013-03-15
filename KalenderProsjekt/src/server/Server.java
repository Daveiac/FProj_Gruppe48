package server;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import networking.ConnectionListener;
import networking.packages.NetworkRequest;


public class Server extends Thread{
	/*
	 * This is where the main method of the serverside program lives. This class
	 * will spawn new listener-threads
	 */
	
	private BlockingQueue<Socket> newClientQueue;
	private BlockingQueue<ReceivedRequest> requestQueue;
	
	public Server(){
		newClientQueue = new LinkedBlockingQueue<Socket>();
		requestQueue = new LinkedBlockingQueue<ReceivedRequest>();
		initializeServer();
	}
	
	private void initializeServer(){
		Thread connectionListener = new Thread(new ConnectionListener(newClientQueue));
		connectionListener.start();
		
		
		OutputController.output("Server initialized");
	}
	
	public void run(){
		while(true){
			try {
				System.out.println(newClientQueue);
				Socket nClient = newClientQueue.take();
				OutputController.output("spawning new RequestListener thread");
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

