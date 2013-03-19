package networking.packages;

import java.io.Serializable;

public abstract class NetworkRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6014500107648524509L;
	private EventType eventType;
	
	public static enum EventType{
		AUTHENTICATION, QUERY, LOGOUT, UPDATE,
	}
	
	

	public NetworkRequest(EventType eventType) {
		super();
		this.eventType = eventType;
	}



	public EventType getEventType() {
		return eventType;
	}



	@Override
	public String toString() {
		return "NetworkRequest [eventType=" + eventType + "]";
	}
	
	
}
