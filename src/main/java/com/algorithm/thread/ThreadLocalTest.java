package com.algorithm.thread;

public class ThreadLocalTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("aaaaa");
        threadLocal.set("bbbbb");

        System.out.println(threadLocal.get());
    }
}
