package cn.bugu.algorithm;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyTest4 {
    static AtomicInteger mainLoop = new AtomicInteger(2);

    public static void main(String[] args) {
        Object mainLock = new Object();
        Object childLock = new Object();
        Ceshi2 ceshi = new Ceshi2(mainLock, childLock);
        Thread t1 = new Thread(ceshi, "I am child");
        t1.start();
        synchronized (mainLock) {
            try {
                System.out.println("main thread sync mainLock pre1");
                mainLock.wait();
                System.out.println("main thread sync mainLock pre2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main thread sync mainLock pre3");
        }

        int picket = 10;
        while (true) {
            if (picket <= 0) {
                if (mainLoop.getAndDecrement() <= 0) {
                    System.out.println("mainLoop:" + mainLoop.get());
                    break;
                }
                synchronized (childLock) {
                    System.out.println("main thread sync childLock1");
                    childLock.notifyAll();
                    System.out.println("main thread sync childLock2");
                }

                synchronized (mainLock) {
                    try {
                        System.out.println("main thread sync mainLock1");
                        mainLock.wait();
                        System.out.println("main thread sync mainLock2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                picket = 10;
            }
            System.out.println(Thread.currentThread().getName() + ", picket:" + picket--);
        }

    }

    static class Ceshi2 implements Runnable {
        Object mainLock, childLock;

        public Ceshi2(Object mainLock, Object childLock) {
            this.mainLock = mainLock;
            this.childLock = childLock;
        }

        @Override
        public void run() {
            int picket = 5;
            while (true) {
                if (picket <= 0) {
                    synchronized (mainLock) {
                        System.out.println("child thread sync mainLock3");

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("child thread mainLock notifyAll1");
                        mainLock.notifyAll();
                        System.out.println("child thread mainLock notifyAll2");
                    }

                    synchronized (childLock) {
                        try {
                            System.out.println("child thread sync childLock4");
                            childLock.wait();
                            System.out.println("child thread sync childLock4");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (mainLoop.get() <= 0) {
                        break;
                    }

                    picket = 5;
                }
                System.out.println(Thread.currentThread().getName() + ", picket:" + picket--);
            }
        }
    }
}
