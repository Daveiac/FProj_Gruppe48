package networking.packages;

import java.net.Socket;

public class ClientConnectedRequest {
	private Socket clientSocket;

	public ClientConnectedRequest(Socket clientSocket) {
		super();
		this.clientSocket = clientSocket;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}
	
	
}
