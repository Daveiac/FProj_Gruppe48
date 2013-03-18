package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import networking.Constants;
import networking.packages.AuthenticationRequest;
import networking.packages.NetworkRequest;
import networking.packages.QueryRequest;
import networking.packages.QueryRequest.QueryType;

public class Client{
	InetAddress serverAddress;
	Socket server;
	
	public Client(InetAddress serverAddress) throws IOException{
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
