package server;

import java.net.Socket;

import networking.packages.NetworkRequest;

public class ReceivedRequest {
	public final NetworkRequest networkRequest;
	public final Socket clientSocket;

	public ReceivedRequest(NetworkRequest networkRequest, Socket clientSocket) {
		super();
		this.networkRequest = networkRequest;
		this.clientSocket = clientSocket;
	}

	@Override
	public String toString() {
		return "ReceivedRequest [networkRequest=" + networkRequest
				+ ", clientSocket=" + clientSocket + "]";
	}
}
