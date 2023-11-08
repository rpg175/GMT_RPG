import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestBlockingQueue {
    public static void main(String[] args) {
        TestBlockingQueue testBlockingQueue = new TestBlockingQueue();
        testBlockingQueue.test1();
    }

    private void test1() {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                    blockingQueue.offer(i);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }

            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 10; i < 20; i++) {
                try {
                    Thread.sleep(1000);
                    blockingQueue.offer(i);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            while (true) {
                try {
                    Integer val = blockingQueue.take();
                    System.out.println("获得数值 " + val);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
