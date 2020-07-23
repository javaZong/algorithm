package com.algorithm.array;

import java.util.Arrays;

/**
 * 最大子序列相关算法
 *
 * @author java_zong
 */
public class MaxSubNum {

    /**
     * 最大子序列和
     *
     * @param targetArray
     * @return
     */
    public static int findMaxSubSum(int[] targetArray) {
        int maxIndex;
        if (targetArray == null || (maxIndex = targetArray.length - 1) < 0) {
            return 0;
        }
        int maxSubSum = 0;
        int currentSubSum = 0;
        for (int i = 0; i <= maxIndex; i++) {
            currentSubSum += targetArray[i];
            if (currentSubSum > maxSubSum) {
                maxSubSum = currentSubSum;
            } else if (currentSubSum < 0) {
                currentSubSum = 0;
            }
        }
        return maxSubSum;
    }

    /**
     * 最大子序列
     *
     * @param srcArray
     * @return
     */
    public static int[] findMaxSub(int[] srcArray) {
        int maxIndex;
        if (srcArray == null || (maxIndex = srcArray.length - 1) < 0) {
            return null;
        }
        int maxSubSum = 0;
        int currentSubSum = 0;
        int maxSubBeginIndex = -1;
        int maxSubEndIndex = -1;
        // 需要有个标识，来标记，是不是出现了新的较大大的子序列
        boolean tmp = false;
        for (int i = 0; i <= maxIndex; i++) {
            currentSubSum += srcArray[i];
            if (currentSubSum > maxSubSum) {
                maxSubSum = currentSubSum;
                if (tmp) {
                    // 一个新的大的子序列
                    maxSubBeginIndex = i;
                    maxSubEndIndex = i;
                } else {
                    if (maxSubBeginIndex < 0) {
                        maxSubBeginIndex = i;
                    } else {
                        maxSubEndIndex = i;
                    }
                }
                tmp = false;
            } else if (currentSubSum < 0) {
                currentSubSum = 0;
                tmp = true;
            }
        }
        if (maxSubBeginIndex < 0) {
            return null;
        }
        if (maxSubBeginIndex > maxSubEndIndex) {
            maxSubEndIndex = maxIndex;
        }
        int subSize = maxSubEndIndex - maxSubBeginIndex + 1;
        int[] sub = new int[subSize];
        for (int i = 0; i < subSize; i++) {
            sub[i] = srcArray[maxSubBeginIndex++];
        }
        return sub;
    }

    public static void main(String[] args) {
        int[] srcArray = {-2, 11, -4, 13, -5, -21, 0};
        System.out.println(findMaxSubSum(srcArray));
        System.out.println(Arrays.toString(findMaxSub(srcArray)));
    }

}
