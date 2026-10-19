package leetCode.date;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class DateUtilsTruncate {
    public static void main(String[] args) {
        Date now = new Date();
        Date moveBeforeDate = DateUtils.truncate(now, Calendar.DATE);
        System.out.println("moveBeforeDate: " + moveBeforeDate);
    }
}
