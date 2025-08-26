package leetCode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Date_Calendar_addDays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int day = 5;
		Date date = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		System.out.println(sdf.format(calendar.getTime()));
		String aa = sdf.format(calendar.getTime());

	}

}
