package cn.bugu.algorithm;

public class MyTest3 {
    public static void main(String[] args) {
        Object mLock = new Object();
        Ceshi ceshi = new Ceshi(mLock);
        Thread t1 = new Thread(ceshi, "A");
        //Thread t2 = new Thread(ceshi, "B");
        //Thread t3 = new Thread(ceshi, "C");

        t1.start();
        //t2.start();
        //t3.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + ", i:" + i);
        }
        synchronized (mLock) {
            mLock.notifyAll();
        }
    }
}

class Ceshi implements Runnable {

    boolean flag = true;
    public Object mLock;

    public Ceshi(Object mLock) {
        this.mLock = mLock;
    }

    @Override
    public void run() {
        int picket = 10;
        while (flag) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (picket == 5) {
                synchronized (mLock) {
                    try {
                        mLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (picket <= 0) {
                return;
            }
            System.out.println(Thread.currentThread().getName() + ", picket:" + picket--);
        }
    }
}

