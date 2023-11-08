import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestMyExecutorService {

    public static void main(String[] args) {
        TestMyExecutorService testMyExecutorService = new TestMyExecutorService();
        testMyExecutorService.test1();
    }


    private void test1() {
        MyExecutorService es = new MyExecutorService();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                    final int val = i;
                    es.submit(() -> {
                        System.out.println("取出数值:" + val);
                    });
                } catch (InterruptedException e) {
                    System.out.println(e);
                }

            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 10; i < 20; i++) {
                try {
                    Thread.sleep(1000);
                    final int val = i;
                    es.submit(() -> {
                        System.out.println("取出数值:" + val);
                    });
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        });


        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    class MyExecutorService {
        private final BlockingQueue<Runnable> _blockingQueue = new LinkedBlockingQueue<>();
        private final Thread _thread;

        MyExecutorService() {
            this._thread = new Thread(() -> {
                while (true) {
                    try {
                        Runnable r = this._blockingQueue.take();
                        if (null != r) {
                            r.run();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            this._thread.start();
        }

        public void submit(Runnable r) {
            if (null != r) {
                this._blockingQueue.offer(r);
            }
        }
    }
}
