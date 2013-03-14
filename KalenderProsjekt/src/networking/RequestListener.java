package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import server.OutputController;

import networking.packages.NetworkRequest;

public class RequestListener implements Runnable{
	private Socket clientSocket;
	private BlockingQueue<NetworkRequest> queue;
	
	
	public RequestListener(Socket clientSocket,
			BlockingQueue<NetworkRequest> queue) {
		super();
		this.clientSocket = clientSocket;
		this.queue = queue;
	}




	@Override
	public void run() {
		try {
			ObjectInputStream oIS = new ObjectInputStream(clientSocket.getInputStream());
			while(true){
				NetworkRequest event = (NetworkRequest) oIS.readObject();
				OutputController.output("Received a new request [" + event.toString() + "]");
				while(!queue.offer(event, 200, TimeUnit.MILLISECONDS));				
			}
			
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
