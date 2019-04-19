package cn.bugu.algorithm.ShoeFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Packer implements Runnable {

    private BlockingQueue<Shoe> Lbucket;
    private BlockingQueue<Shoe> Rbucket;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd, HH-mm-ss-SSS");

    private List<Pipeline> pList;
    public int count = 0;

    public Packer(BlockingQueue<Shoe> Lbucket, BlockingQueue<Shoe> Rbucket,
                  List<Pipeline> pList) {
        this.Lbucket = Lbucket;
        this.Rbucket = Rbucket;
        this.pList = pList;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (LRShoesFactory.appStopFalg) {
                    break;
                }
                Shoe ls = this.Lbucket.take();
                Shoe rs = this.Rbucket.take();

                if (ls instanceof PoisonShoe || rs instanceof PoisonShoe) {
                    break;
                }

                if (!(ls instanceof LShoe) || !(rs instanceof RShoe)) {
                    System.out.println("ERROR-Thread-NO-SAFE");
                }

                System.out.println(formatter.format(new Date(System.currentTimeMillis())) + " num(packer)=" + count + ", bucket(left)="
                        + Lbucket.size() + ", bucket(right)=" + Rbucket.size()
                        + "; ThreadInfo: " + LRShoesFactory.statistics(pList));
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

}
