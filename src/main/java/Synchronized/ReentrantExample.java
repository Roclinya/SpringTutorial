package Synchronized;

/**
 * A thread cannot acquire a lock that is owned by another thread.
 * However, a thread can acquire a lock that it already owns.
 * This is known as re-entrant synchronization.
 */
public class ReentrantExample {
    public synchronized void outerMethod() {
        System.out.println("Entered outerMethod");
        innerMethod(); // Calling another synchronized method
        System.out.println("Exiting outerMethod");
    }

    public synchronized void innerMethod() {
        System.out.println("Entered innerMethod");
        // Do something
        System.out.println("Exiting innerMethod");
    }

    public static void main(String[] args) {
        ReentrantExample example = new ReentrantExample();

        Thread thread = new Thread(() -> {
            example.outerMethod();
        });

        thread.start();
    }
}
