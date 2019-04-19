package cn.bugu.algorithm.ShoeFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class LRShoesFactory {
    public static boolean appStopFalg;

    public static BlockingQueue<Shoe> Lbucket = new ArrayBlockingQueue<Shoe>(100);
    public static BlockingQueue<Shoe> Rbucket = new ArrayBlockingQueue<Shoe>(100);

    public static int LCost = 300;
    public static int RCost = 700;

    public static void main(String[] args) throws InterruptedException {

        long starts = System.currentTimeMillis();
        long ends = 0L;

        List<Thread> tList = new ArrayList<Thread>();
        List<Pipeline> pList = new ArrayList<Pipeline>();
        Packer per = new Packer(Lbucket, Rbucket, pList);
        Thread tp = new Thread(per);
        tp.start();

        Commander comer = new Commander(pList, Lbucket, Rbucket);
        Thread tcomer = new Thread(comer);
        tcomer.start();
        int numLeft = 0, numRight = 0;
        boolean pLeft = true, pRight = false;
        while (true) {
            if (tList.size() < 30) {
                Thread t = null;

                if (tList.size() % 3 == 0) {
                    Pipeline ler = new Pipeline(LCost, RCost, Lbucket, Rbucket,
                            "L");
                    t = new Thread(ler);
                    pList.add(ler);

                } else {
                    Pipeline rer = new Pipeline(LCost, RCost, Lbucket, Rbucket,
                            "R");
                    t = new Thread(rer);
                    pList.add(rer);
                }
                /*
                if (pLeft && numLeft < 3) {
                    Pipeline ler = new Pipeline(LCost, RCost, Lbucket, Rbucket,"L");
                    t = new Thread(ler);
                    pList.add(ler);
                    numLeft++;
                    pRight = false;
                } else {
                    numLeft = 0;
                    pRight = true;
                }

                if (pRight && numRight < 7) {
                    Pipeline ler = new Pipeline(LCost, RCost, Lbucket, Rbucket,"L");
                    t = new Thread(ler);
                    pList.add(ler);
                    numRight++;
                    pLeft = false;
                } else {
                    numRight = 0;
                    pLeft = true;
                }*/
                if (null != t) {
                    t.start();
                    tList.add(t);
                }
            } else {
                Thread.sleep(100);
                ends = System.currentTimeMillis();
                if ((ends - starts) >= 60000) {
                    stopAll(pList, Lbucket, Rbucket);
                    break;
                }
            }
        }
        System.out.println("const : " + (ends - starts));
        System.out.println("ends : Main");
    }

    private static void stopAll(List<Pipeline> pList,
                                BlockingQueue<Shoe> Lbucket, BlockingQueue<Shoe> Rbucket)
            throws InterruptedException {
        appStopFalg = true;
        for (int i = 0; i < pList.size(); i++) {
            pList.get(i).setStopFlag(appStopFalg);
        }
        PoisonShoe ps = new PoisonShoe();
        Lbucket.put(ps);
        Rbucket.put(ps);
    }

    public static String statistics(List<Pipeline> pList) {
        int L = 0;
        int R = 0;
        for (int i = 0; i < pList.size(); i++) {
            Pipeline p = pList.get(i);
            if (p.getLineName().equals("L")) {
                L++;
            }
            if (p.getLineName().equals("R")) {
                R++;
            }
        }
        return "statistics: num(left)=" + L + " , num(right)=" + R;
    }

    public static int getStatNum(List<Pipeline> pList, String LR) {

        int L = 0;
        int R = 0;
        for (int i = 0; i < pList.size(); i++) {
            Pipeline p = pList.get(i);
            if (p.getLineName().equals("L")) {
                L++;
            }
            if (p.getLineName().equals("R")) {
                R++;
            }
        }
        if (LR.equals("L")) {
            return L;
        } else if (LR.equals("R")) {
            return R;
        } else {
            return -1;
        }

    }

    public static void adjust(List<Pipeline> pList, String LR, int num) {
        for (int t = 0; t < num; t++) {
            if (LR.equals("L")) {
                if (getStatNum(pList, "R") <= 2) {
                    break;
                }
                for (int i = 0; i < pList.size(); i++) {
                    Pipeline p = pList.get(i);
                    if (p.getLineName().equals("R")) {
                        p.setLineName("L");
                        break;
                    }
                }
            }
            if (LR.equals("R")) {
                if (getStatNum(pList, "L") <= 4) {
                    break;
                }
                for (int i = 0; i < pList.size(); i++) {
                    Pipeline p = pList.get(i);
                    if (p.getLineName().equals("L")) {
                        p.setLineName("R");
                        break;
                    }
                }
            }
        }
// System.out.println("adjust : " + statistics(pList));
    }
}