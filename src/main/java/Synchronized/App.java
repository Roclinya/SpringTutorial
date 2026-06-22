package Synchronized;

public class App {
    public static void main(String[] args) {
//        ThreadExample example = new ThreadExample();
//        example.execute();

            SynchronizedMethodAndBlock log = new SynchronizedMethodAndBlock();
            Process process1 = new Process(log);
            Process process2 = new Process(log);
            process1.start();
            process2.start();
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