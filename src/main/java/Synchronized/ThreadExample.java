package Synchronized;

/**
 * The conclusion of this lecture is that we can use synchronized blocks to minimize the scope of the critical section and improve performance.
 * Instead of synchronizing the entire method, we can synchronize only the part of the code(synchronized blocks) that modifies the shared resource, which can reduce contention and improve concurrency.
 * We can also use different locks for different methods to avoid contention and improve concurrency.
 */
public class ThreadExample {
    private int counter1;
    private int counter2;
    private static int counter3;

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    // object-level locking: we lock on a given instantiated object
    // synchronized (lock1) 等同於 synchronized (this): if we synchronized this, it refers to the object itself
    public void increment1() {
        synchronized (lock1) {
            counter1++;
        }
    }

    public void increment2() {
        synchronized (lock2) {
            counter2++;
        }
    }

    // class-level locking
    public void increment1Plus() {
        // execute some code before the critical section

        // the only operations that need to be synchronized are the ones that modify the shared resource,
        // so we can minimize the scope of the synchronized block to just those operations
        synchronized (ThreadExample.class) {
            // incrementing a static variable, we need to use class level locking
            // because the lock is tied to the class object that is shared across all instances
            counter3++;
        }
        // execute some code after the critical section
    }


    public void execute() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                increment1();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                increment2();
            }
        });

        t1.start();
        t2.start();
        // we have to wait for the threads to finish
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Counter1 value is " + counter1);
        System.out.println("Counter2 value is " + counter1);
    }

}
