package cn.bugu.algorithm.ShoeFactory;

import java.util.concurrent.BlockingQueue;

public class Pipeline implements Runnable {
    private int LcostMS;
    private int RcostMS;

    private boolean stopFlag;

    private BlockingQueue<Shoe> Lbucket;
    private BlockingQueue<Shoe> Rbucket;

    private String lineName;

    public Pipeline(int LcostMS, int RcostMS, BlockingQueue<Shoe> Lbucket,
                    BlockingQueue<Shoe> Rbucket, String LR) {
        this.LcostMS = LcostMS;
        this.RcostMS = RcostMS;
        this.stopFlag = false;
        this.Lbucket = Lbucket;
        this.Rbucket = Rbucket;
        this.lineName = LR;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public boolean isStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(boolean stopFlag) {
        this.stopFlag = stopFlag;
    }

    @Override
    public void run() {
        exec();
    }

    private void exec() {
        try {
            while (true) {
                if (stopFlag) {
                    break;
                }
// System.out.println(new Date());
                innerRun();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Throwable e1) {
            e1.printStackTrace();
        }
    }

    private void costCPU(int costMS) throws InterruptedException {
        Thread.sleep(costMS);
    }

    protected void innerRun() throws InterruptedException {

        if (this.lineName.equals("L")) {
            costCPU(this.LcostMS);
            Lbucket.put(new LShoe());
// System.out.println("innerRun : L :" + bucket.size());

        } else if (this.lineName.equals("R")) {
            costCPU(this.RcostMS);
            Rbucket.put(new RShoe());
// System.out.println("innerRun : R :" + bucket.size());

        } else {
            System.out.println("ERROR : lineName ");
        }

    }
}
