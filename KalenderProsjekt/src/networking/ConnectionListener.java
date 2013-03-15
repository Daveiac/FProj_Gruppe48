package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import server.OutputController;

public class ConnectionListener implements Runnable {

	BlockingQueue<Socket> clients;

	public ConnectionListener(BlockingQueue<Socket> clients) {
		super();
		this.clients = clients;
	}

	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(Constants.port);
		} catch (Exception e) {
			OutputController.output("Could not create socket");
			System.exit(0);
		}
		while (true) {
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();
				while (!clients.offer(clientSocket, 200, TimeUnit.MILLISECONDS))
					;
			} catch (IOException | InterruptedException e) {
				OutputController.output("Client tried to connect: failed");
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
