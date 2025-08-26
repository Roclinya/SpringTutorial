package Synchronized;

public class SynchronizedStaticMethodAndBlock {

    public static synchronized void log1(String msg1, String msg2){
        // 打印當前執行緒名稱
        System.out.println("Thread: " + Thread.currentThread().getName());
        System.out.println(msg1);
        System.out.println(msg2);
        for (int i = 0; i < 1000; i++) {
            System.out.println("log1: "+i);
        }
    }


    public static void log2(String msg1, String msg2){
//        Object obj = new Object();
        synchronized(SynchronizedStaticMethodAndBlock.class){
            // 打印當前執行緒名稱
            System.out.println("Thread: " + Thread.currentThread().getName());
            System.out.println(msg1);
            System.out.println(msg2);
            for (int i = 0; i < 1000; i++) {
                System.out.println("log2: "+i);
            }
        }
    }
}
