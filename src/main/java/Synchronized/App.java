package Synchronized;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
//        ThreadExample example = new ThreadExample();
//        example.execute();

//        SynchronizedMethodAndBlock log = new SynchronizedMethodAndBlock();
//        Process process1 = new Process(log);
//        Process process2 = new Process(log);
//        process1.start();
//        process2.start();

        var shareBuffer = new ShareBuffer();
        Thread threadProducer = new Thread(new Producer(shareBuffer));
        Thread threadPConsumer = new Thread(new Consumer(shareBuffer));

        threadProducer.start();
        threadPConsumer.start();

    }

}

class Process extends Thread {
    private SynchronizedMethodAndBlock log;

    public Process(SynchronizedMethodAndBlock log) {
        this.log = log;
    }

    @Override
    public void run() {
        log.log1("Hello", "World");
        log.log2("Hello", "World");
    }

}

class ShareBuffer {
    private List<Integer> buffer = new ArrayList<>();
    private int capacity = 5;

    public synchronized void produce() throws InterruptedException {
        if (buffer.size() == capacity) {
            System.out.println("Buffer full, producer waiting...");
            wait();
        }
        System.out.println("Adding items with producer...");
        for (int i = 0; i < capacity; i++) {
            buffer.add(i);
            System.out.println("Added value : " + i);
        }

        // wake up the consumer
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        if (buffer.size() < capacity) {
            System.out.println("Buffer not full yet, consumer is waiting...");
            wait();
        }

        while (!buffer.isEmpty()) {
            int item = buffer.remove(0);
            System.out.println("Consumer removes : " + item);
            Thread.sleep(300);
        }

        // wake up the producer related thread and it keeps adding items again
        notify();
    }
}

class Consumer implements Runnable {
    private ShareBuffer shareBuffer;

    public Consumer(ShareBuffer shareBuffer) {
        this.shareBuffer = shareBuffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.shareBuffer.consume();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


class Producer implements Runnable {
    private ShareBuffer shareBuffer;

    public Producer(ShareBuffer shareBuffer) {
        this.shareBuffer = shareBuffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.shareBuffer.produce();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}