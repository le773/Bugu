package cn.bugu.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

public class MyTest6 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(5);
        LinkedBlockingQueue<LuckyMoney> luckyMoneys = new LinkedBlockingQueue<>();
        List<FutureTask> futureTasks = new ArrayList<>();
        //准备抢红包
        for (int i = 0; i < 5; i++) {
            FutureTask<Object> futureTask = new FutureTask<>(new CatchLuckMoney(luckyMoneys, "name" + i/*, downLatch*/), null);
            new Thread(futureTask, "name" + i).start();
            futureTasks.add(futureTask);
        }
        Thread.sleep(5);//确保抢红包线程准备就绪
        //downLatch.await();
        Random random = new Random(100);
        //发5个红包
        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        try {
                            //String msg = "红包" + i + " thread=" + Thread.currentThread().getName();
                            luckyMoneys.put(new LuckyMoney("红包" + i, random.nextInt(100) + 1));
                            //System.out.println(msg);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        /*
        //等到红包抢完
        while (!luckyMoneys.isEmpty()) {
            Thread.sleep(1);
        }
        //终止抢红包线程
        for (FutureTask futureTask : futureTasks) {
            futureTask.cancel(true);
        }
        */
    }
}

class CatchLuckMoney implements Runnable {

    public CatchLuckMoney(LinkedBlockingQueue<LuckyMoney> luckyMoneys, String name) {
        this.luckyMoneys = luckyMoneys;
        this.name = name;
    }

    public CatchLuckMoney(LinkedBlockingQueue<LuckyMoney> luckyMoneys, String name, CountDownLatch downLatch) {
        this.luckyMoneys = luckyMoneys;
        this.name = name;
        this.downLatch = downLatch;
    }

    private LinkedBlockingQueue<LuckyMoney> luckyMoneys;
    private String name;
    private CountDownLatch downLatch;

    @Override
    public void run() {
        if (null != downLatch)
            downLatch.countDown();
        System.out.println(Thread.currentThread().getName() + ", time=" + System.currentTimeMillis());
        while (!Thread.currentThread().isInterrupted()) {
            LuckyMoney redPackage = null;
            try {
                redPackage = luckyMoneys.take();
            } catch (InterruptedException e) {
//                e.printStackTrace();
                break;
            }
            System.out.println(name + "抢到了-->" + redPackage);
            luckyMoneys.remove(redPackage);
        }
        System.out.println("end>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + Thread.currentThread().getName());
    }
}

class LuckyMoney {

    public LuckyMoney(String name, Integer money) {
        this.name = name;
        this.money = money;
    }

    private String name;
    private Integer money;

    @Override
    public String toString() {
        return "LuckyMoney{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}