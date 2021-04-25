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
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     * 本质在于寻找差值最大的递增子序列
     * 类似：最大子序列和 最大子序列
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if(prices==null||prices.length<1){
            return 0;
        }
        int max=0;
        int left=0;
        int right=1;
        int difference=0;
        while(right<prices.length){
            difference=prices[right]-prices[left];
            if(difference<0){
                left=right;
                right++;
                continue;
            }
            right++;
            if(difference>max){
                max=difference;
            }
        }
        return max;
    }

    /**
     * 最大子序列
     * {-2, 11, -4, 13, -5, -21, 0}
     * @param srcArray
     * @return
     */
    public static int[] findMaxSub1(int[] srcArray) {
        int maxIndex;
        if (srcArray == null || (maxIndex = srcArray.length - 1) < 0) {
            return null;
        }
        int maxSubSum = 0;
        int currentSubSum = 0;
        int left = 0;
        int maxSubBeginIndex=0;
        int maxSubEndIndex=0;
        for (int right = 0; right <= maxIndex; right++) {
            currentSubSum += srcArray[right];
            if (currentSubSum > maxSubSum) {
                maxSubSum = currentSubSum;
                maxSubEndIndex=right;
                maxSubBeginIndex=left;
            } else if (currentSubSum < 0) {
                currentSubSum = 0;
                left=right+1;
            }
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
        int[] srcArray = {-2, 11, -4, 13, -5, 21, 0};
        System.out.println(findMaxSubSum(srcArray));
        System.out.println(Arrays.toString(findMaxSub1(srcArray)));

        int[][] matrixArray = {{1, 2}, {3, 4}, {5, 6}};
        System.out.println(isExistSameNumInMatrix(matrixArray, 4));
        ;
    }

}
