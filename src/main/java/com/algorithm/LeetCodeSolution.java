package com.algorithm;

import com.algorithm.util.AlgorithmUtils;
import com.alibaba.fastjson.JSON;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCodeSolution {

    /**
     * 定义一个函数 f(s)，统计 s  中（按字典序比较）最小字母的出现频次 ，其中 s 是一个非空字符串。
     * <p>
     * 例如，若 s = "dcce"，那么 f(s) = 2，因为字典序最小字母是 "c"，它出现了 2 次。
     * <p>
     * 现在，给你两个字符串数组待查表 queries 和词汇表 words 。对于每次查询 queries[i] ，需统计 words 中满足 f(queries[i]) < f(W) 的 词的数目 ，W 表示词汇表 words 中的每个词。
     * <p>
     * 请你返回一个整数数组 answer 作为答案，其中每个 answer[i] 是第 i 次查询的结果。
     * <p>
     * 输入：queries = ["cbd"], words = ["zaaaz"]
     * 输出：[1]
     * 解释：查询 f("cbd") = 1，而 f("zaaaz") = 3 所以 f("cbd") < f("zaaaz")。
     *
     * @param queries
     * @param words
     * @return
     */
    public int[] numSmallerByFrequency(String[] queries, String[] words) {

        int[] wFrequencyNums = frequencyNums(words);
        int[] targetNums = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int qFrequencyNum = f(queries[i]);
            if (qFrequencyNum >= 10) {
                continue;
            }
            targetNums[i] = wFrequencyNums[qFrequencyNum + 1];
        }
        return targetNums;
    }

    private int[] frequencyNums(String[] strs) {
        int[] array = new int[11];
        for (int i = 0; i < strs.length; i++) {
            int index = f(strs[i]);
            array[index]++;
        }

        for (int i = 9; i >= 0; i--) {
            array[i] = array[i + 1] + array[i];
        }
        return array;
    }

    private int f(String str) {
        int length = str.length();
        char minChar = 'z';
        int minSize = 0;
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (c == minChar) {
                minSize++;
            }
            if (c < minChar) {
                minChar = c;
                minSize = 1;
            }
        }
        return minSize;
    }

    /**
     * 给你一个整数数组，返回它的某个 非空 子数组（连续元素）在执行一次可选的删除操作后，所能得到的最大元素总和。换句话说，你可以从原数组中选出一个子数组，并可以决定要不要从中删除一个元素（只能删一次哦），
     * （删除后）子数组中至少应当有一个元素，然后该子数组（剩下）的元素总和是所有子数组之中最大的。
     * <p>
     * 注意，删除一个元素后，子数组 不能为空。
     * <p>
     * https://leetcode.cn/problems/maximum-subarray-sum-with-one-deletion/
     *
     * @param arr
     * @return
     */
    public int maximumSum(int[] arr) {
        // dp[i][k] 代表以下标i结尾，被删了k次的子数组的最大值
//        int[][] dp = new int[arr.length][2];
//        dp[0][0] = arr[0];
//        int maxSum = arr[0];
//        for (int i = 1; i < arr.length; i++) {
//            dp[i][0] = Math.max(dp[i - 1][0], 0) + arr[i];
//            dp[i][1] = Math.max(dp[i - 1][1] + arr[i], dp[i - 1][0]);
//            maxSum = Math.max(maxSum, Math.max(dp[i][0], dp[i][1]));
//        }
//        return maxSum;
        int delZeroMaxSum = 0;
        int delOneMaxSum = 0;
        int preDelZeroMaxSum = arr[0];
        int preDelOneMaxSum = 0;
        int maximumSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            delZeroMaxSum = Math.max(preDelZeroMaxSum, 0) + arr[i];
            delOneMaxSum = Math.max(preDelOneMaxSum + arr[i], preDelZeroMaxSum);
            preDelZeroMaxSum = delZeroMaxSum;
            preDelOneMaxSum = delOneMaxSum;
            maximumSum = Math.max(maximumSum, Math.max(delZeroMaxSum, delOneMaxSum));
        }
        return maximumSum;
    }

    public static void main(String[] args) {
        LeetCodeSolution solution = new LeetCodeSolution();
        int[] array = {1, -4, -5, -2, 5, 0, -1, 2};
        System.out.println(solution.maximumSum(array));
    }
}
