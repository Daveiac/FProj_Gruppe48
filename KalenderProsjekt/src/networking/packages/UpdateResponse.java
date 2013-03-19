package networking.packages;

public class UpdateResponse extends Response{
	private static final long serialVersionUID = 1L;
	
	public enum DataResponseType {
		NOTIFICATION_RESPONSE, PERSON_RESPONSE, MEETING_RESPONSE, ALARM_RESPONSE, TEAM_RESPONSE
	}
	
	public UpdateResponse(ResponseType responseType) {
		super(responseType);
	}

}
