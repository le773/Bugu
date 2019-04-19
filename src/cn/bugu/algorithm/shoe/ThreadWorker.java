package cn.bugu.algorithm.shoe;

public class ThreadWorker extends Thread {
    LeftAndRightQueue leftAndRightQueue;
    ThreadWorker(LeftAndRightQueue leftAndRightQueue) {
        this.leftAndRightQueue = leftAndRightQueue;
    }

    @Override
    public void run() {
        while (true) {
            if (leftAndRightQueue.lFlag) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (leftAndRightQueue.lQueue) {
                    leftAndRightQueue.lQueue.add(new LeftAndRightQueue.Shoe(LeftAndRightQueue.Shoe.LEFT));
                }
                System.out.println(Thread.currentThread().getName() + " product shoe left");
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (leftAndRightQueue.rQueue) {
                    leftAndRightQueue.rQueue.add(new LeftAndRightQueue.Shoe(LeftAndRightQueue.Shoe.RIGHT));
                }
                System.out.println(Thread.currentThread().getName() + " product shoe right");
            }
        }
    }
}
