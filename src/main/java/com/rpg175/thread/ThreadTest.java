package com.rpg175.thread;

import java.util.concurrent.FutureTask;

public class ThreadTest {
    static long counter = 100;
    public static void main(String[] args) throws Exception {
        final Thread thread = new Thread(new FutureTask<>(()->{
            for (long i =1;i<100000000000L;i++) {
                for (long j=1;j<100000000000L;j++) {
                    for (long k=1;j<100000000000L;j++) {
                        counter += counter%i%j%k%3333;
                    }
                }
            }
        },null));
        thread.start();
        Thread.currentThread().sleep(1000);
        System.out.println("exit");
    }
}
