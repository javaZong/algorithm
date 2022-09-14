package com.algorithm.juc;

public class ReentrantLockThreadTest2 implements Runnable {
    private ReentrantLockStudy service;

    public ReentrantLockThreadTest2(ReentrantLockStudy service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.awaitB();
    }
}
