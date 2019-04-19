package cn.bugu.algorithm.shoe;

import java.util.Vector;

public class LeftAndRightQueue {
    Vector<Shoe> lQueue;
    Vector<Shoe> rQueue;
    volatile boolean lFlag;
    volatile boolean rFlag;

    public LeftAndRightQueue(Vector<Shoe> lQueue, Vector<Shoe> rQueue, boolean lFlag, boolean rFlag) {
        this.lQueue = lQueue;
        this.rQueue = rQueue;
        this.lFlag = lFlag;
        this.rFlag = rFlag;
    }

    public static class Shoe {
        final static int LEFT = 0;
        final static int RIGHT = 1;
        int type;

        public Shoe(int type) {
            this.type = type;
        }
    }
}
