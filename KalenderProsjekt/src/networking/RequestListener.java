package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import networking.requests.NetworkRequest;

public class RequestListener implements Runnable{
	Socket clientSocket;
	BlockingQueue<NetworkRequest> queue;

	
	@Override
	public void run() {
		try {
			ObjectInputStream oIS = new ObjectInputStream(clientSocket.getInputStream());
			while(true){
				NetworkRequest event = (NetworkRequest) oIS.readObject();
				while(!queue.offer(event, 200, TimeUnit.MILLISECONDS));				
			}
			
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
