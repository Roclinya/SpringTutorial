package Synchronized;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedBlocks {


    private static int staticCount = 0;

    public static int getStaticCount() {
        return staticCount;
    }

    public static void setStaticCount(int count) {
        staticCount = count;
    }

    public static void performStaticSyncTask() {
        synchronized (SynchronizedBlocks.class) {
            setStaticCount(getStaticCount() + 1);
        }
    }


}
