package Synchronized;

public class SynchronizedMethods {
    public static int staticSum;
    public void calculate() {
        syncStaticCalculate();
    }
    public static synchronized void syncStaticCalculate() {
        staticSum = staticSum + 1;
    }
}
