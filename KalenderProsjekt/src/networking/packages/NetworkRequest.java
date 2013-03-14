package networking.packages;

import java.io.Serializable;

public abstract class NetworkRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6014500107648524509L;
	private EventType eventType;
	
	public enum EventType{
		AUTHENTICATION, QUERY, LOGOUT,
	}

	public EventType getEventType() {
		return eventType;
	}
	
	
}
