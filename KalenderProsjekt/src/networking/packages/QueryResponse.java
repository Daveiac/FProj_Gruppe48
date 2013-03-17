package networking.packages;

import java.util.List;

public class QueryResponse extends Response {
	private static final long serialVersionUID = -1664992392938826860L;
	private List data;
	private QueryResponseType queryResponseType;

	public enum QueryResponseType {
		NOTIFICATION_RESPONSE, PERSON_RESPONSE, MEETING_RESPONSE, ALARM_RESPONSE, TEAM_RESPONSE
	}

	public QueryResponse(List data, QueryResponseType queryResponseType) {
		super(ResponseType.QUERY_RESPONSE);
		this.data = data;
		this.queryResponseType = queryResponseType;
	}

	public List getData() {
		return data;
	}

	public QueryResponseType getQueryResponseType() {
		return queryResponseType;
	}

}
