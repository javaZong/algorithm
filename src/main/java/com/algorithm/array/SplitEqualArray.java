package com.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 将将两个数组分成两个和相等的数组
 * 子数组是连续的
 */
public class SplitEqualArray {

    /**
     * 将两个数组分成两个和相等的数组
     * 子数组是连续的、
     *
     * 例如 {2, 3, -1, -2, 8} 则相等位置为3与-1中间，则返回2
     * @param array
     * @return 数组相等的位置，0表示不存在此种情况
     */
    public static int splitEqualSumArray(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        int mapSize = (int) (array.length / 0.75) + 1;
        Map<Integer, Integer> sumMap = new HashMap<>(mapSize);
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum = sum + array[i];
            sumMap.put(sum, i);
        }
        if (sum % 2 != 0) {
            // 总和必定是偶数
            return 0;
        }
        int subEqualSum = sum / 2;
        Integer targetIndex = sumMap.get(subEqualSum);
        return targetIndex != null ? targetIndex + 1 : 0;
    }

    public static void main(String[] args) {
//        int[] array = new int[]{2, 3, -1, 0, 8};
        int[] array = new int[]{-1, -1, -1, -1, -1};
        System.out.println(splitEqualSumArray(array));
    }
}
