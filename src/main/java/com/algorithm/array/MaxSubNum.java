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

    /**
     * 从一个从左到右递增、从上到下递增的二维数组中是否存在特定元素
     * 时间复杂度 O(N)
     * @param arrays 从左到右递增、从上到下递增的二维数组中
     * @param targetNum 目标元素
     * @return true=存在目标元素
     */
    public static boolean isExistSameNumInMatrix(int[][] arrays, int targetNum) {
        if (arrays == null || arrays.length <= 0) {
            return false;
        }
        // 列长度
        int columnLength = arrays.length;
        // 行长度
        int rowsLength = arrays[0].length;
        // 列的开始索引
        int columnIndex = columnLength - 1;
        // 行的开始索引
        int rowsIndex = 0;
        while (columnIndex >= 0 && rowsIndex <= rowsLength - 1) {
            int midNum = arrays[columnIndex][rowsIndex];
            if (midNum == targetNum) {
                return true;
            }
            if (midNum > targetNum) {
                columnIndex--;
            } else {
                rowsIndex++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] srcArray = {-2, 11, -4, 13, -5, -21, 0};
        System.out.println(findMaxSubSum(srcArray));
        System.out.println(Arrays.toString(findMaxSub(srcArray)));

        int[][] matrixArray = {{1, 2}, {3, 4}, {5, 6}};
        System.out.println(isExistSameNumInMatrix(matrixArray, 4));
        ;
    }

}
