package Synchronized;

public class ObjectLocking {

    public synchronized void instanceMethod(){
        System.out.println(Thread.currentThread().getName()+ " entered instanceMethod...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+ " finished instanceMethod...");
}

    public static void main(String[] args) {
//        ThreadExample example = new ThreadExample();
//        example.execute();

        //independent lock,therefore two thread can execute simultaneously
       var object1 = new ObjectLocking();
       var object2 = new ObjectLocking();
       Runnable task1 = object1::instanceMethod;
       Runnable task2 = object2::instanceMethod;

        new Thread(task1,"First Thread").start();
        new Thread(task2,"Second Thread").start();
    }
}
