package org.concurrent.training8;

import org.concurrent.training8.Task;

import java.util.concurrent.CountDownLatch;

/**
 * WorkerThread1
 * @author Pierre-Hugues Charbonneau
 *
 */
public class WorkerThread1 implements Runnable {

    private final CountDownLatch startSignal;
    private final CountDownLatch endSignal;
    private Task task = null;

    public WorkerThread1(Task task, CountDownLatch startSignal, CountDownLatch endSignal) {
        this.task = task;
        this.startSignal = startSignal;
        this.endSignal = endSignal;
    }

    @Override
    public void run() {

        try {
            startSignal.await();

            // Execute task #1
            task.executeTask1();

        } catch (InterruptedException e) {}

        finally {
            endSignal.countDown();
        }
    }
}