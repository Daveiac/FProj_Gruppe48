package data;

import java.io.Serializable;

public class TimeInterval implements Serializable{
	public final long startTime;
	public final long endTime;
	
	
	public TimeInterval(long startTime, long endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	};
	
}
