package leetCode;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.*;
import java.time.format.*;
import java.util.Calendar;
import java.util.Locale;

public class DateChange {

	public static void main(String[] args) {
		

		//民國年 yyyMMdd 轉 西元年 yyyyMMdd
		System.out.println(transferMinguoDateToADDate("1010101"));


		Calendar calendar = Calendar.getInstance();
		// Convert Calendar object to Timestamp object
		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());

		System.out.println(modifyDate(timestamp,1));


		LocalDateTime today = LocalDateTime.now();
		LocalDateTime tomorrow = today.plusDays(1);


		System.out.println("today :"+today);
		System.out.println("tomorrow :"+tomorrow);

		long milliseconds = tomorrow.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		System.out.println(milliseconds);



	}
	
	
	/**
	 * Transfer AD date to minguo date.
	 * 西元年 yyyyMMdd 轉 民國年 yyyMMdd
	 *
	 * @param dateString the String dateString
	 * @return the string
	 */
	public static String transferADDateToMinguoDate(String dateString) {
		LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
		return MinguoDate.from(localDate).format(DateTimeFormatter.ofPattern("yyyMMdd"));
	}

	/**
	 * Transfer minguo date to AD date.
	 * 民國年 yyyMMdd 轉 西元年 yyyyMMdd
	 *
	 * @param dateString the String dateString
	 * @return the string
	 */
	public static String transferMinguoDateToADDate(String dateString) {
		Chronology chrono = MinguoChronology.INSTANCE;
		DateTimeFormatter df = new DateTimeFormatterBuilder().parseLenient()
		        .appendPattern("yyyMMdd")
		        .toFormatter()
		        .withChronology(chrono)
		        .withDecimalStyle(DecimalStyle.of(Locale.getDefault()));

		ChronoLocalDate chDate = chrono.date(df.parse(dateString));
		return LocalDate.from(chDate).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}

	/**
	 * 修改日期(移動至下一天)
	 *
	 * @param timestamp
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp modifyDate(Timestamp timestamp, int days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timestamp);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return new Timestamp(calendar.getTime().getTime());
	}

}
