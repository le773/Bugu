package cn.bugu.algorithm;

import java.util.concurrent.Semaphore;

public class MyTest5 {

    public static void main(String[] args) {
        CheckState checkState = new CheckState();
        Thread t1 = new Thread(checkState, "A");
        Thread t2 = new Thread(checkState, "B");
        Thread t3 = new Thread(checkState, "C");

        t1.start();
        t2.start();
        t3.start();
    }
}

class CheckState implements Runnable {

    Semaphore t1 = new Semaphore(1);
    Semaphore t2 = new Semaphore(0);
    Semaphore t3 = new Semaphore(0);

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String name = Thread.currentThread().getName();
            if ("A".equals(name)) {
                try {
                    t1.acquire();
                    System.out.println(name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    t2.release();
                }
            } else if ("B".equals(name)) {
                try {
                    t2.acquire();
                    System.out.println(name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    t3.release();
                }
            } else if ("C".equals(name)) {
                try {
                    t3.acquire();
                    System.out.println(name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    t1.release();
                }
            }
        }
    }
}
