package cn.bugu.algorithm.shoe;

public class ThreadPacker extends Thread {
    LeftAndRightQueue leftAndRightQueue;
    ThreadPacker(LeftAndRightQueue leftAndRightQueue) {
        this.leftAndRightQueue = leftAndRightQueue;
    }

    @Override
    public void run() {
        while (true) {
            while (!leftAndRightQueue.lQueue.isEmpty() && !leftAndRightQueue.rQueue.isEmpty()) {
                LeftAndRightQueue.Shoe lShoe = leftAndRightQueue.lQueue.remove(0);
                LeftAndRightQueue.Shoe rShoe = leftAndRightQueue.rQueue.remove(0);
                System.out.println(Thread.currentThread().getName() + "left queue size:" + leftAndRightQueue.lQueue.size() +
                        ", right queue size:" + leftAndRightQueue.rQueue.size());
                pack(lShoe, rShoe);
            }
        }
    }

    void pack(LeftAndRightQueue.Shoe lShoe, LeftAndRightQueue.Shoe rShoe) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
