package leetCode;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class simpleDateFormat {

	public static void main(String[] args) throws IOException, ParseException {

		SimpleDateFormat formatterSt = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
		String dateInString = "20230101";
		Date date = formatterSt.parse(dateInString);
		System.out.println(date);
		System.out.println("-------");
//		String message[] = new String[]{"RFILEList","insR6BackTime","content","insR6Insuno","backupFile"};
//		List<String> messageList = Arrays.asList(message);
//		messageList.forEach(e->{
//			System.out.println(e);
//			
//		});
//		
//		File file = new File("");
//		System.out.println(file.list());
//		
//		Map<String,String>[] records = new Map[6];

		// 日期測試 localDate轉客製化字串YYYYMMDD
		LocalDate localDateOf = LocalDate.of(2023, 04, 01);
		LocalDate localDate = LocalDate.now();// For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		String formattedLocalDateOf = localDateOf.format(formatter);
		System.out.println(formattedLocalDateOf);
		
		String formattedString = localDate.format(formatter);
		System.out.println(formattedString);
		
		

		System.out.println("-------");

		// sql date
		HashMap map = new HashMap();
		date = java.sql.Date.valueOf("1970-01-01");
		map.put("formattedString", "1970-01-01");
		map.computeIfPresent("formattedString", (k, v) -> java.sql.Date.valueOf(v.toString()));
		System.out.println("Date " + date);
		System.out.println(map.get("formattedString"));

		// java.util.Date(今天)(現在)(如:Wed Oct 05 22:46:06 CST 2022)
		java.util.Date now = new java.util.Date();
		System.out.println("java.util.Date() = " + now);
		// java.sql.Date(今天)(如:2022-10-05)
		long long1 = now.getTime(); // 也可以用long long1 = System.currentTimeMillis();
		java.sql.Date date1 = new java.sql.Date(long1);
		System.out.println("java.sql.Date()  = " + date1);

	}

}
