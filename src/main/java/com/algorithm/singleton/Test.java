package com.algorithm.singleton;

import java.util.HashMap;
import java.util.Map;

public class Test {


    public static void main(String[] args) {
        int[] array=new int[10];
        System.out.println(array.length);

        Map<String,String> map=new HashMap<>(16);
        map.put("1","1");
        System.out.println(map.size());

    }
}
