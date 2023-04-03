package com.algorithm.dynamic;

import java.util.*;

public class DynamicSolution {
    private static char[][] ccs = {{'L', 'R'}, {'U', 'D'}};

    public static String alphabetBoardPath(String target) {
        if (target == null || target.length() < 1) {
            return null;
        }
        char[] chars = target.toCharArray();
        int preCharColumnIndex = 0;
        int preCharRowIndex = 0;
        int[][] array = new int[chars.length][2];
        boolean isZ = false;
        char preChar = 'a';
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int colunmIndex = (c - 'a') / 5;
            int rowIndex = (c - 'a') % 5;
            array[i][0] = colunmIndex - preCharColumnIndex;
            array[i][1] = rowIndex - preCharRowIndex;
            if (preChar == c) {
                continue;
            }
            preCharColumnIndex = colunmIndex;
            preCharRowIndex = rowIndex;
            preChar = c;
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            int upOrDownMoveTime = array[i][0];
            int leftOrRightMoveTimes = array[i][1];
            if (upOrDownMoveTime < 0) {
                append(str, ccs[1], upOrDownMoveTime);
                append(str, ccs[0], leftOrRightMoveTimes);
                str.append("!");
                continue;
            }
            if (leftOrRightMoveTimes < 0) {
                append(str, ccs[0], leftOrRightMoveTimes);
                append(str, ccs[1], upOrDownMoveTime);
                str.append("!");
                continue;
            }
            append(str, ccs[1], upOrDownMoveTime);
            append(str, ccs[0], leftOrRightMoveTimes);
            str.append("!");
        }
        return str.toString();
    }


    private static void append(StringBuilder stringBuilder, char[] chars, int times) {
        char c;
        if (times < 0) {
            c = chars[0];
        } else if (times > 0) {
            c = chars[1];
        } else {
            return;
        }
        times = Math.abs(times);
        for (int i = 0; i < times; i++) {
            stringBuilder.append(c);
        }
    }

    /**
     * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
     * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
     *
     * @param grid
     * @return
     */
    public int maxValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
                if (j > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }
                dp[i][j] = dp[i][j] + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }


    /**
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     * <p>
     * 你可以认为每种硬币的数量是无限的。
     * <p>
     * https://leetcode.cn/problems/gaM7Ch/
     *
     * @param coins
     * @param amount
     * @return
     */
    int minSize = -1;

    public int coinChange1(int[] coins, int amount) {

        process(0, 0, coins, amount, 0);
        return minSize;
    }

    private void process(int curAmount, int num, int[] coins, int amount, int size) {
        if (curAmount == amount) {
            if (minSize == -1 || size < minSize) {
                minSize = size;
            }
            return;
        }
        if (curAmount > amount) {
            return;
        }
        for (int i = num; i < coins.length; i++) {
            process(curAmount + coins[i], i, coins, amount, size + 1);

        }
    }


    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        DynamicSolution dynamicSolution = new DynamicSolution();
        int[] coins = {1, 2, 5};
        System.out.println(dynamicSolution.coinChange(coins, 11));
        System.out.println(dynamicSolution.coinChange1(coins, 11));
    }


}
