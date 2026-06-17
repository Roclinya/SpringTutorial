package Synchronized;

public class SynchronizedMethodAndBlock {

    public synchronized void log1(String msg1, String msg2) {
        // 打印當前執行緒名稱
        System.out.println("Thread: " + Thread.currentThread().getName());
        System.out.println(msg1);
        System.out.println(msg2);
        for (int i = 0; i < 1000; i++) {
            System.out.println("log1: " + i);
        }
    }

    Object obj = new Object();

    public void log2(String msg1, String msg2) {
        // 這邊新增一個lock: 使用另外的intrinsic lock避免因為只有一個lock而需要等待釋放才能繼續執行其他方法
        synchronized (obj) {
            // 打印當前執行緒名稱
            System.out.println("Thread: " + Thread.currentThread().getName());
            System.out.println(msg1);
            System.out.println(msg2);
            for (int i = 0; i < 1000; i++) {
                System.out.println("log2: " + i);
            }
        }
    }
}
