package leetCode;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class MillisSecondToyyyyMMdd {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        long now = System.currentTimeMillis(); // 毫秒為單位
        LocalDateTime createTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(now),
                TimeZone.getDefault().toZoneId());// 修正為 ofEpochMilli 而不是ofEpochSecond
        System.out.println(formatter.format(createTime));

        LocalDateTime updateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(1724051793L),TimeZone.getDefault().toZoneId());
                System.out.println(formatter.format(updateTime));
        System.out.println("---------------");
    }
}
