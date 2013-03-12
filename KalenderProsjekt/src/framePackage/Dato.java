package framePackage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Dato {
	
	
	private Date date;
	private int year;
	private int day;
	private int monthNumber;
	private String monthName;
	
	public Dato(){
		date = new Date();
	}
	
	
	public String getDate(){
		Locale Norge = new Locale("no", "no");
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, Norge);
		return df.format(date);
	}
	public String getDay() {
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");  
		String day = dayFormat.format(date);
		return day;
	}
	
	public String getMonth() {
		SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");  
		String month = monthFormat.format(date);
		return month;
	}
	
	public String getWeek(){
		SimpleDateFormat weekFormat = new SimpleDateFormat("ww");  
		String week = weekFormat.format(date);
		return week;
	}
	
	public String getYear(){
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");  
		String year = yearFormat.format(date);
		return year;
	}
	
}
