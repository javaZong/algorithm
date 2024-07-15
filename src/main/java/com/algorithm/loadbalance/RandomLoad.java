package com.algorithm.loadbalance;

import java.util.*;

/**
 * 随机
 */
public class RandomLoad {
    public static final List<String> LIST = Arrays.asList(
            "192.168.0.1",
            "192.168.0.2",
            "192.168.0.3",
            "192.168.0.4",
            "192.168.0.5"
    );

    /**
     * 完全随机
     */
    public static String random() {
        Random random = new Random();
        int randomValue = random.nextInt(LIST.size());
        return LIST.get(randomValue);
    }

    public static final Map<String, Integer> WEIGHT_LIST = new HashMap<>();

    static {
        WEIGHT_LIST.put("192.168.0.1", 1);
        WEIGHT_LIST.put("192.168.0.2", 8);
        WEIGHT_LIST.put("192.168.0.3", 3);
        WEIGHT_LIST.put("192.168.0.4", 6);
        WEIGHT_LIST.put("192.168.0.5", 5);
    }

    public static String randomWeight() {
        int totalWeight = 0;
        for (Map.Entry<String, Integer> entry : WEIGHT_LIST.entrySet()) {
            totalWeight += entry.getValue();
        }
        Random random = new Random();
        int pos = random.nextInt(totalWeight);
        for (Map.Entry<String, Integer> entry : WEIGHT_LIST.entrySet()) {
            int weight = entry.getValue();
            if (pos <= weight) {
                return entry.getKey();
            }
            pos = pos - weight;
        }
        return "";
    }

}
