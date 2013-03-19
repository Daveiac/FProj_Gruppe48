package networking.packages;

import data.Meeting;
import data.Person;

public class QueryRequest extends NetworkRequest {
	private static final long serialVersionUID = -1922826697363368302L;
	Person person;
	Meeting meeting;
	private QueryType queryType;

	public enum QueryType {
		GET_ALL_PERSONS, GET_EVERY_MEETING_BY_PERSON, GET_ALARMS_BY_PERSON,
		GET_NOTIFICATIONS_BY_MEETING, GET_NOTIFICATIONS_BY_PERSON,
		GET_TEAMS_BY_MEETING, GET_ALL_MEETINGROOMS, GET_ALL_NOTIFICATIONS
		
	}

	public QueryRequest(Person person, Meeting meeting, QueryType queryType) {
		super(EventType.QUERY);
		this.person = person;
		this.meeting = meeting;
		this.queryType = queryType;
	}

	public String getUsername() {
		return person.getUsername();
	}

	public Person getPerson() {
		return person;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public QueryType getQueryType() {
		return queryType;
	}

}
