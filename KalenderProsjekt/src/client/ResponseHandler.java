package client;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import networking.packages.DataResponse;
import networking.packages.Response;
import data.Person;

public class ResponseHandler implements Runnable{
	private BlockingQueue<Response> responseQueue;
	
	
	
	public ResponseHandler(BlockingQueue<Response> responseQueue) {
		super();
		this.responseQueue = responseQueue;
	}
	
	private void receivedPeople(List data){
		List<Person> people = new ArrayList<Person>();
		for (Object object : data) {
			people.add((Person) object);
		}
		//TODO where should this list go?
	}

	private void handleResponse(Response response){
		switch(response.getResponseType()){
		case AUTHENTICATION_RESPONSE:
			break;
		case QUERY_RESPONSE:
			DataResponse dataResponse = (DataResponse) response;
			switch (dataResponse.getQueryResponseType()){
			case ALARM_RESPONSE:
				dataResponse.getData();
				break;
			case NOTIFICATION_RESPONSE:
				break;
			case MEETING_RESPONSE:
				break;
			case PERSON_RESPONSE:
				receivedPeople(dataResponse.getData());
				
				break;
			case TEAM_RESPONSE:
				break;
			}
			break;
		}
	}
	
	@Override
	public void run() {
		while(true){
			try {
				handleResponse(responseQueue.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
