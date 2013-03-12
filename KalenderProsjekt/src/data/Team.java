package data;

import java.util.Iterator;
import java.util.List;

public class Team {
	private int teamID;
	private String email;
	private List<Person> members;
	
	public Team(int teamID, String email, List<Person> members) {
		super();
		this.teamID = teamID;
		this.email = email;
		this.members = members;
	}
	public int getTeamID() {
		return teamID;
	}
	public String getEmail() {
		return email;
	}
	public List<Person> getMembers() {
		return members;
	}
	
	public String toString(){
		String result = "|| ";
		for (Iterator iterator = members.iterator(); iterator.hasNext();) {
			Person person = (Person) iterator.next();
			result += person.toString() + " || ";
		}
		return result;
	}
}
