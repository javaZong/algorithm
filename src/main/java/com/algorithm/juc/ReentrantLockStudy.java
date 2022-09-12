package com.algorithm.juc;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockStudy {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new ThreadTest());
            thread.start();

            thread.setName("test" + i);
            try {
                Thread.sleep(1000 * 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadTest implements Runnable {

        @Override
        public void run() {
            lockTest();
        }
    }

    public static void lockTest() {
        try {
            lock.tryLock();
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000 * 10);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}
