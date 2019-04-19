package cn.bugu.algorithm.shoe;

public class ThreadMaster extends Thread {
    LeftAndRightQueue leftAndRightQueue;

    ThreadMaster(LeftAndRightQueue leftAndRightQueue) {
        this.leftAndRightQueue = leftAndRightQueue;
    }

    @Override
    public void run() {
        while (true) {
            if (leftAndRightQueue.lQueue.size() < leftAndRightQueue.rQueue.size()) {
                leftAndRightQueue.lFlag = true;
                leftAndRightQueue.rFlag = false;
            } else {
                leftAndRightQueue.lFlag = false;
                leftAndRightQueue.rFlag = true;
            }
        }
    }
}
