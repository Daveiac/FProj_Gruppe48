package networking.packages;

import java.util.List;

public class DataResponse extends Response {
	private static final long serialVersionUID = -1664992392938826860L;
	private List data;
	private DataResponseType dataResponseType;

	public enum DataResponseType {
		NOTIFICATION_RESPONSE, PERSON_RESPONSE, MEETING_RESPONSE, ALARM_RESPONSE, TEAM_RESPONSE
	}

	public DataResponse(List data, DataResponseType dataResponseType) {
		super(ResponseType.QUERY_RESPONSE);
		this.data = data;
		this.dataResponseType = dataResponseType;
	}

	public List getData() {
		return data;
	}

	public DataResponseType getQueryResponseType() {
		return dataResponseType;
	}

}
