package leetCode;

import java.sql.Timestamp;
import java.text.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class DateFormat {
	/** yyyy/MM/dd */
	public static final String PATTERN7 = "yyyy/MM/dd";
	public static void main(String[] args) throws ParseException {
		// 查詢OTP發送紀錄(取得otp_records)
		var currentDate = LocalDate.now();
		var startOfDay = currentDate.atStartOfDay();
		Long optdatetime = startOfDay.toInstant(ZoneOffset.UTC).toEpochMilli();
		System.out.println("optdatetime: "+optdatetime);

		formatToDateTime(PATTERN7, 1727365094000L);


		String testDate = "2024/11/27";

		Object sqldate = stringToSqlDate(testDate);
		System.out.println("----stringToTimestamp----" );
		Long milisecond = stringToTimestamp(testDate);
		System.out.println("milisecond: " + milisecond);
		System.out.println("Util date: "+new Date(milisecond));

		System.out.println("----ZoneOffset----" );
		Long datetime = LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
		System.out.println("LocalDate.now(): " + LocalDate.now());
		System.out.println("datetime: " + datetime);
		System.out.println("Util date: "+new Date(datetime));


		//SQL date
		System.out.println("SQL date for now");
		System.out.println(new java.sql.Date(System.currentTimeMillis()));
		System.out.println("Util date for now");
		System.out.println(new Date(System.currentTimeMillis()));
		//util date
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		Date d = new Date();
//		String result = sdf.format(d);
//		System.out.println(result);
		String testDate2 = "2023-05-24";
		System.out.println("---SQL Date default with dash-");
		System.out.println(java.sql.Date.valueOf(testDate2));

	}

	private static Date stringToSqlDate(String testDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date d = sdf.parse(testDate);
		System.out.println("util Date: "+ d);
		long time = d.getTime();
		System.out.println(time);
		Date sqlDate = new java.sql.Date(time);
		System.out.println("SQL Date : " + sqlDate);
		return sqlDate;

	}

	/**
	 * 將 yyyy/MM/dd 日期字串轉換為 UTC 毫秒時間戳。
	 * 假設輸入的日期字串基於系統預設時區。
	 * 1. LocalDate.atStartOfDay()
	 * LocalDate.atStartOfDay() 會將 LocalDate 轉換為 LocalDateTime，其時間部分為當天的午夜（00:00）。
	 * 這個時間表示是「沒有時區資訊的本地時間」。因此，後續操作會假設這是基於系統的預設時區。
	 * 2. Timestamp.valueOf(LocalDateTime)
	 * Timestamp.valueOf(LocalDateTime) 會根據系統的預設時區，將 LocalDateTime 解釋為對應於 UTC 時間的毫秒數。
	 * 如果系統的時區與資料來源的時區不同，生成的 Timestamp 值可能不符合預期。
	 */
	private static Long stringToTimestamp(String inputDateString){

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		// 解析日期字串
		LocalDate localDate = LocalDate.parse(inputDateString, formatter);
		// 將 LocalDate 轉換為 Timestamp
		Timestamp timestamp = Timestamp.valueOf(localDate.atStartOfDay());
		// 輸出結果
		System.out.println("Timestamp: " + timestamp);

		return timestamp.getTime();
	}

	private static String formatToDateTime(String format, Long timeSpan) {
		if (timeSpan == null) {
			return "";
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		LocalDateTime resultTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeSpan),
				TimeZone.getDefault().toZoneId());
		System.out.println("formatToDateTime : "+formatter.format(resultTime));
		return formatter.format(resultTime);
	}

}
