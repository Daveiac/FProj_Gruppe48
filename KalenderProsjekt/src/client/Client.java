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
	
	
	
	
	public Client(InetAddress serverAddress) throws IOException{
		
		
	}
	
	
	public void sendRequest(NetworkRequest request) throws IOException{
		ObjectOutputStream out = new ObjectOutputStream(Program.server.getOutputStream());
		out.writeObject(request);
//		out.close();
//		server.close();
	}
	
	
}

