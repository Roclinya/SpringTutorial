package Synchronized;

public class WaitNotify {
    public static void main(String[] args) {
        Process process = new Process();
        Thread producerThread = new Thread(() -> {
            try {
                process.produce2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                process.consume2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
    }

    /**
     * Fairness: wait() and notify() do not guarantee fairness.
     * If multiple threads are waiting on the same lock, there is no guarantee about the order
     * in which they will be notified or allowed to proceed when notify() is called.
     * This can lead to situations where some threads may experience starvation if they are consistently passed over in favor of others.
     */
    static class Process {
        private boolean isProduced = false;

        public synchronized void produce() throws InterruptedException {
            while (isProduced) {
                wait(); // 等待消費者消費
            }
            System.out.println("Producing...");
            Thread.sleep(1000); // 模擬生產過程
            isProduced = true;
            notify(); // 通知消費者可以消費了
        }

        public synchronized void consume() throws InterruptedException {
            while (!isProduced) {
                wait(); // 等待生產者生產
            }
            System.out.println("Consuming...");
            Thread.sleep(1000); // 模擬消費過程
            isProduced = false;
            notify(); // 通知生產者可以繼續生產了
        }


        public void produce2() throws InterruptedException{
            synchronized (this) {
                System.out.println("Producer is producing...");
                // The First thread releases the intrinsic lock and allows the second thread to execute the consume2() method
                wait(); // 等待生產者的通知
                System.out.println("Producer has produced.");
            }
        }
        public void consume2() throws InterruptedException{
            Thread.sleep(2000);
            synchronized (this) {
                System.out.println("Consumer is consuming...");
                // the notify() method is called to wake up the waiting producer thread(random waiting thread if there are multiple waiting threads),
                // and the producer thread will be able to reacquire the lock and continue executing the produce2() method
                notify(); // 通知等待的消費者
                System.out.println("Consumer has consumed After the notifiy() method is called.");
            }
        }
    }
}
