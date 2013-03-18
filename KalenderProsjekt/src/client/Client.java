package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import networking.*;
import networking.packages.*;

public class Client extends Thread{
	InetAddress serverAddress;
	Socket server;
	private BlockingQueue<Response> queueForHandlingResponses;
	
	public void startThreads(){
		Thread t = new Thread(new ResponseListener(server, queueForHandlingResponses));
		t.start();
		Thread t2 = new Thread(new ResponseHandler(queueForHandlingResponses));
		t2.start();
	}
	
	public Client(InetAddress serverAddress) throws IOException{
		queueForHandlingResponses = new LinkedBlockingQueue<Response>();
		
		
		this.serverAddress = serverAddress;
		server = new Socket(serverAddress, Constants.port);
		
	}
	
	
	public void sendRequest(NetworkRequest request) throws IOException{
		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		out.writeObject(request);
//		out.close();
//		server.close();
	}
	
	
	public static void main (String[] args) throws InterruptedException, UnknownHostException, IOException{
		Client client = new Client( InetAddress.getByName(Constants.serverIP) );
		Thread t = new Thread(new ResponseListener(client.server, null));
		t.start();
		t.join();
		
	}

}

