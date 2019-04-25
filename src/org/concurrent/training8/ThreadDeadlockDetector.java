package org.concurrent.training8;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.CountDownLatch;

/**
 * ThreadDeadlockDetector
 *
 * @author Pierre-Hugues Charbonneau
 */
public class ThreadDeadlockDetector implements Runnable {

    private Task task = null;
    private final CountDownLatch startSignal;
    private final CountDownLatch endSignal;

    public ThreadDeadlockDetector(Task task, CountDownLatch startSignal, CountDownLatch endSignal) {
        this.task = task;
        this.startSignal = startSignal;
        this.endSignal = endSignal;
    }

    @Override
    public void run() {

        try {
            startSignal.await();

            // Perform 10 iterations with 2 seconds elapsed time
            for (int i = 0; i < 200; i++) {

                // 1. Flat & Reetrant WRITE lock deadlock detection
                ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
                long[] threadIds = threadBean.findDeadlockedThreads();
                int deadlockedThreads = threadIds != null ? threadIds.length : 0;
                System.out.println("\n** Deadlock detectin in progress...");
                System.out.println("Deadlocked threads found by the HotSpot JVM: " + deadlockedThreads);

                // 2. Reetrant READ lock tracking
                System.out.println("READ lock count: " + task.getReentrantReadWriteLock().getReadLockCount());

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
            }

        } catch (InterruptedException e) {
        } finally {
            endSignal.countDown();
        }
    }
}