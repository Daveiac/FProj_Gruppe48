package client;

import java.io.IOException;

import networking.packages.QueryRequest;
import networking.packages.QueryRequest.QueryType;
import networking.*;
import java.net.*;

public class RequestHandler {
	
	public void run() throws IOException{
		Client client = new Client( InetAddress.getByName(Constants.serverIP) );
		QueryRequest qReqGetAllPersons = new QueryRequest(null, null, QueryType.GET_ALL_PERSONS);
		client.sendRequest(qReqGetAllPersons);
	}
}
