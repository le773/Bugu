package cn.bugu.algorithm;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {
    public static void main(String args[]) {

        final SynchronousQueue<String> queue = new SynchronousQueue<String>();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        synchronized (SynchronousQueueDemo.class) {
                            String input = queue.take();
                            String output = TestDo.doSome(input);
                            System.out.println(Thread.currentThread().getName() + " take , " + output);
                        }

                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();
        }


        System.out.println("begin:");

        for (int i = 0; i < 10; i++) {
            String input = i + "";
            try {
                queue.put(input);
                System.out.println(i + " put finished");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

class TestDo {

    public static String doSome(String input) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String output = " " + input + ", " + System.currentTimeMillis() / 1000;
        return output;
    }

}

