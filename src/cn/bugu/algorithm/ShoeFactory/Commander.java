package cn.bugu.algorithm.ShoeFactory;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Commander implements Runnable {

    private List<Pipeline> pList;
    private BlockingQueue<Shoe> Lbucket;
    private BlockingQueue<Shoe> Rbucket;
    private boolean stopFlag;

    public boolean isStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(boolean stopFlag) {
        this.stopFlag = stopFlag;
    }

    public Commander(List<Pipeline> pList, BlockingQueue<Shoe> Lbucket,
                     BlockingQueue<Shoe> Rbucket) {
        this.pList = pList;
        this.Lbucket = Lbucket;
        this.Rbucket = Rbucket;
    }

    @Override
    public void run() {
        while (true) {
            if (LRShoesFactory.appStopFalg) {
                break;
            }
            if (Lbucket.size() <= 3) {
                LRShoesFactory.adjust(pList, "L", 1);
            } else if (Rbucket.size() <= 1) {
                LRShoesFactory.adjust(pList, "R", 3);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
