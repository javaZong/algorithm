package com.algorithm.juc;

public class ReentrantLockThreadTest1 implements Runnable {
    private ReentrantLockStudy service;

    public ReentrantLockThreadTest1(ReentrantLockStudy service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.awaitA();
    }
}
