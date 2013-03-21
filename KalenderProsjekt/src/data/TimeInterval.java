package data;

import java.io.Serializable;

public class TimeInterval implements Serializable{
	private static final long serialVersionUID = 1L;
	public final long startTime;
	public final long endTime;
	
	
	public TimeInterval(long startTime, long endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	};
	
}
