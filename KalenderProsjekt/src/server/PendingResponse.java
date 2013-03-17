package server;

import java.net.Socket;

import networking.packages.Response;

public class PendingResponse {
	private Response response;
	private Socket clientSoket;
	public final boolean respondToAll;
	
	

	public PendingResponse(Response response, Socket clientSoket,
			boolean respondToAll) {
		super();
		this.response = response;
		this.clientSoket = clientSoket;
		this.respondToAll = respondToAll;
	}
	public Response getResponse() {
		return response;
	}
	public Socket getClientSoket() {
		return clientSoket;
	}
	
	
}
