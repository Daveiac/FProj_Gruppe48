package networking.packages;

import data.Meeting;

public class QueryRequest extends NetworkRequest{
	private static final long serialVersionUID = -1922826697363368302L;
	String username;
	Meeting meeting;
	private QueryType queryType;
	
	public enum QueryType{
		GET_ALL_PERSONS, GET_EVERY_MEETING_BY_PERSON, GET_ALARMS_BY_PERSON, GET_NOTIFICATIONS_BY_MEETING,
		GET_NOTIFICATIONS_BY_PERSON, GET_TEAMS_BY_MEETING, 
	}

	public QueryRequest(String username, Meeting meeting, QueryType queryType) {
		super();
		this.username = username;
		this.meeting = meeting;
		this.queryType = queryType;
	}

	public String getUsername() {
		return username;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public QueryType getQueryType() {
		return queryType;
	}

	
}
