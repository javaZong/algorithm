package com.algorithm.singleton;

public enum TestEnum {
    INSTANCE;

    TestEnum(){
        System.out.println("test");
    }

    public static void test(){
        System.out.println("enum-test()");
    }
}
