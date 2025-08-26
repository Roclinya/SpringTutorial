package synchronizedTest;

import Synchronized.SynchronizedMethods;

public class TwoThreadsCallSameInstance {

    public static void main(String[] args) throws InterruptedException {
        //case 1 same instance
        Counter counter = new Counter();
        // case 2 different instance
//        Counter counterA = new Counter();
//        Counter counterB = new Counter();
        Thread  threadA = new CounterThread(counter);
        Thread  threadB = new CounterThread(counter);

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }
}
