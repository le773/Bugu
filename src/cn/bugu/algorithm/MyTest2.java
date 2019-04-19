package cn.bugu.algorithm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyTest2 {

    static class MyTask {
        private static ReentrantLock rl = new ReentrantLock();
        private static Condition conditionA = rl.newCondition();
        private static Condition conditionB = rl.newCondition();
        private static Condition conditionC = rl.newCondition();
        private static int number = 0;

        public void execute() {
            rl.lock();

            try {
                while (number < 30) {
                    if (number % 3 == 0) {
                        System.out.println(Thread.currentThread().getName() + " - " + number);
                        number++;
                        conditionB.signal();
                        conditionA.await();
                    }

                    if (number % 3 == 1) {
                        System.out.println(Thread.currentThread().getName() + " - " + number);
                        number++;
                        conditionC.signal();
                        conditionB.await();
                    }

                    if (number % 3 == 2) {
                        System.out.println(Thread.currentThread().getName() + " - " + number);
                        number++;
                        conditionA.signal();
                        conditionC.await();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rl.unlock();
            }
        }
    }


    public static void main(String[] args) {
        final MyTask myTask = new MyTask();


        new Thread(new Runnable() {

            @Override
            public void run() {
                myTask.execute();
            }
        }, "B").start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                myTask.execute();
            }
        }, "C").start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                myTask.execute();
            }
        }, "A").start();

    }
}