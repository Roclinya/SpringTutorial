package Synchronized;

public class ClassLocking {
    public static synchronized void instanceMethod() {
        System.out.println(Thread.currentThread().getName() + " entered instanceMethod...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " finished instanceMethod...");
    }

    /**
     * we should avoid class level locking
     */
    public static void main(String[] args) {
        // the lock is tied to the class object that is shared across all instances
        // which means that both threads call a static synchronized method ,so they
        // share the same class level lock
        Runnable task1 = ClassLocking::instanceMethod;
        Runnable task2 = ClassLocking::instanceMethod;

        new Thread(task1,"First Thread").start();
        new Thread(task2,"Second Thread").start();
        // this is why we come to conclusion that we should avoid static methods and class level locking
        // when possible
    }
}
