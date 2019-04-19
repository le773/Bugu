package cn.bugu.algorithm.shoe;

import java.util.Vector;

public class Launch {
    public static void main(String[] args) {
        final Vector<LeftAndRightQueue.Shoe> lQueue = new Vector<>();
        final Vector<LeftAndRightQueue.Shoe> rQueue = new Vector<>();
        boolean lFlag = true;
        boolean rFlag = false;

        LeftAndRightQueue queue = new LeftAndRightQueue(lQueue, rQueue, lFlag, rFlag);

        for (int i = 1; i <= 98; i++) {
            ThreadWorker w = new ThreadWorker(queue);
            w.start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ThreadMaster master = new ThreadMaster(queue);
        master.start();

        ThreadPacker packer = new ThreadPacker(queue);
        packer.start();
    }
}
