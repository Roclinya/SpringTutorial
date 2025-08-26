package Synchronized;

public class SynchronizedMethodAndBlock {

    public  synchronized void log1(String msg1, String msg2){
        // 打印當前執行緒名稱
        System.out.println("Thread: " + Thread.currentThread().getName());
        System.out.println(msg1);
        System.out.println(msg2);
        for (int i = 0; i < 1000; i++) {
            System.out.println("log1: "+i);
        }
    }


    public  void log2(String msg1, String msg2){
        Object obj = new Object();
        synchronized(obj){
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
