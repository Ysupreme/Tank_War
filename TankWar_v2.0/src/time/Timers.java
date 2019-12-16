package time;

import java.util.Calendar;

public class Timers {
	public int hour;
	public int minute;
	public int second;
	public static int m,s,mm,ss;
	public static int minutestop;
	public static int secondstop;
	public Timers(){
	   Calendar cal = Calendar.getInstance();
       minute = cal.get(Calendar.MINUTE);
       second = cal.get(Calendar.SECOND);
	}
}
