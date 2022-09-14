package com.algorithm.juc;

public class ReentrantLockMain {


    public static void main(String[] args) throws InterruptedException {
        ReentrantLockStudy service = new ReentrantLockStudy();
        Runnable runnable1 = new ReentrantLockThreadTest1(service);
        Runnable runnable2 = new ReentrantLockThreadTest2(service);

        new Thread(runnable1, "a").start();
        new Thread(runnable2, "b").start();

        // 线程sleep2秒钟
        Thread.sleep(2000);
        // 唤醒所有持有conditionA的线程
        service.signalA();

        Thread.sleep(2000);
        // 唤醒所有持有conditionB的线程
        service.signalB();
    }


}
