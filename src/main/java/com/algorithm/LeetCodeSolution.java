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

    public boolean isValidSudoku(char[][] board) {
        boolean[][] column = new boolean[board.length][board.length];
        boolean[][] row = new boolean[board.length][board.length];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boolean[] nine = new boolean[9];
                int cIndex = i * 3;
                int cEnd = cIndex + 3;
                int rowStart = j * 3;
                int rowEnd = rowStart + 3;
                for (; cIndex < cEnd; cIndex++) {
                    for (int rowIndex = rowStart; rowIndex < rowEnd; rowIndex++) {
                        if (board[cIndex][rowIndex] == '.') {
                            continue;
                        }
                        int n = board[cIndex][rowIndex] - '0' - 1;
                        if (nine[n] || column[cIndex][n] || row[rowIndex][n]) {
                            return false;
                        }
                        nine[n] = true;
                        column[cIndex][n] = true;
                        row[rowIndex][n] = true;
                    }
                }

            }
        }
        return true;
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int threeSumClosest = 0;
        int min = Integer.MAX_VALUE;
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int subSum = nums[i] + nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    int temp = subSum + nums[k];

                    int diff = Math.abs(temp - target);
                    if (diff <= min) {
                        min = diff;
                        threeSumClosest = temp;
                    } else {
                        System.out.println(temp + " " + diff + " " + i + " " + j + " " + k + " " + min);
                        break;
                    }
                }

            }
        }
        return threeSumClosest;
    }

    public static void main(String[] args) {
        LeetCodeSolution solution = new LeetCodeSolution();
        boolean[] booleans = new boolean[1];

        int[] nums = {0, 3, 97, 102, 200};

//        System.out.println(solution.threeSumClosest(nums, 300));
        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                if (j == 4) {
                    break;
                }
                System.out.println(j);
            }
        }
    }
}
