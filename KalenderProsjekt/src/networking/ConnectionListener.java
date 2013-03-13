package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ConnectionListener implements Runnable{
	
	
	public void run(){
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(Constants.port);
		} catch (Exception e) {
			System.out.println("Could not create socket");
			System.exit(0);
		}
		while (true) {
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();
				//New client connected. Now do whatever!
			} catch (IOException e) {
				System.out.println("Client tried to connect: failed");
				break;
			}

		}
		try {
			if (serverSocket != null)
				serverSocket.close();
		} catch (IOException e) {
		}
		
	}
}
