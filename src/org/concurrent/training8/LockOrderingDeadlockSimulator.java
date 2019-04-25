package org.concurrent.training8;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * LockOrderingDeadlockSimulator
 * @author Pierre-Hugues Charbonneau
 *
 */
public class LockOrderingDeadlockSimulator {

    /**
     * Main program
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Hidden lock-ordering deadlock simulator");
        System.out.println("Author: Pierre-Hugues Charbonneau");
        System.out.println("http://javaeesupportpatterns.blogspot.com");

        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch endSignal = new CountDownLatch(3);

        for (int i=0; i<1; i++) {

            Task newTask = new Task();
            ExecutorService executor = Executors.newFixedThreadPool(3);

            /** Assign the Map at your convenience **/
            Runnable deadlockDetector = new ThreadDeadlockDetector(newTask, startSignal, endSignal);
            Runnable worker1 = new WorkerThread1(newTask, startSignal, endSignal);
            Runnable worker2 = new WorkerThread2(newTask, startSignal, endSignal);

            executor.execute(worker1);
            executor.execute(worker2);
            executor.execute(deadlockDetector);

            // This will make the executor accept no new threads
            // and finish all existing threads in the queue
            executor.shutdown();

            startSignal.countDown();

            // Wait until all threads are finish
            while (!executor.isTerminated()) {
                try {
                    endSignal.await();
                } catch (InterruptedException e) {}
            }

            System.out.println("LockOrderingDeadlockSimulator done!");
        }
    }
}