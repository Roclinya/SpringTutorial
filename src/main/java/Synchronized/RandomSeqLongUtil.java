package Synchronized;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomSeqLongUtil {

    public static void main(String[] args) {
        long randomLong = getRandomLongByYYYYMMDD();
        System.out.println("Generated Random Long: " + randomLong);
    }
    private static DateFormat yyyyMMddDf = new SimpleDateFormat("yyyyMMdd");

    public static long getRandomLongByYYYYMMDD() {

        long longDate = 0;
//     這裡的 synchronized 是這段程式碼的關鍵。它的作用是確保多執行緒同時存取 yyMMddDf 這個 SimpleDateFormat 物件時不會發生問題。
        synchronized (yyyyMMddDf) {
            longDate = Long.parseLong(yyyyMMddDf.format(new Date()));
        }
        longDate =  Long.valueOf(longDate + "00000000", 16);

        long nanoTime = System.nanoTime() >> 7;
        nanoTime = nanoTime & 0x00000000FFFFFFFFL;

        long rs = longDate + nanoTime;

        return rs;
    }

    /*這裡的 synchronized 是這段程式碼的關鍵。它的作用是確保多執行緒同時存取 yyMMddDf 這個 SimpleDateFormat 物件時不會發生問題。

    為什麼需要同步 (synchronized)?
    SimpleDateFormat 不是執行緒安全的

    SimpleDateFormat 物件在多執行緒環境下是非執行緒安全 (not thread-safe) 的。
    這是因為 format() 和 parse() 方法內部會修改 SimpleDateFormat 物件的狀態，例如緩存日期資訊、重複使用 Calendar 物件等。
    如果多個執行緒同時呼叫 format()，可能會產生錯誤的日期格式或拋出 NumberFormatException。
    避免競爭條件 (Race Condition)

    假設有多個執行緒同時呼叫 getRandomLongByYYMMDD()，而 yyMMddDf 沒有受到同步保護，那麼這些執行緒可能會相互干擾，導致錯誤的日期輸出。
    完整的執行流程
new Date() 取得當前時間。
            yyMMddDf.format(new Date()) 會將日期格式化為 yyMMdd 形式的字串。
            Long.parseLong(...) 轉換字串為 long 數值。
    synchronized (yyMMddDf) {} 確保在多執行緒環境下只有一個執行緒可以同時存取 yyMMddDf。
*/
}
