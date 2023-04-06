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

    /**
     * 152. 乘积最大子数组
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     * <p>
     * 测试用例的答案是一个 32-位 整数。
     * <p>
     * 子数组 是数组的连续子序列。
     * <p>
     * 最小数*当前数是有可能变成最大值的，如负负相乘
     * 最大数*当前数有可能变成最小值，如正负相乘
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int max = nums[0];
        int min = max;
        int maxProduct = max;
        for (int i = 1; i < nums.length; i++) {
            int tempMax = max;
            int tempMin = min;
            max = Math.max(tempMax * nums[i], Math.max(nums[i], tempMin * nums[i]));
            min = Math.min(tempMax * nums[i], Math.min(nums[i], tempMin * nums[i]));
            maxProduct = Math.max(max, maxProduct);
        }
        return maxProduct;
    }

    /**
     * 198 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * <p>
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length < 2) {
            return nums[0];
        }

        int prev = 0;
        int curr = 0;

        // 每次循环，计算“偷到当前房子为止的最大金额”
        for (int i : nums) {
            // 循环开始时，curr 表示 dp[k-1]，prev 表示 dp[k-2]
            // dp[k] = max{ dp[k-1], dp[k-2] + i }
            int temp = Math.max(curr, prev + i);
            prev = curr;
            curr = temp;
            // 循环结束时，curr 表示 dp[k]，prev 表示 dp[k-1]
        }

        return curr;
    }

    public int rob1(int[] nums) {
        if (nums.length < 2) {
            return nums[0];
        }

        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 2; i <= nums.length; i++) {

            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);

        }

        return dp[nums.length];
    }

    public int robTemp(int[] nums) {
        return Math.max(select(0, nums, 0), noSelect(0, nums, 0));
    }

    public int select(int i, int[] nums, int curAmount) {
        if (i == nums.length) {
            return 0;
        }
        curAmount += nums[i];

        return Math.max(curAmount, noSelect(i + 1, nums, curAmount));
    }

    public int noSelect(int i, int[] nums, int curAmount) {
        if (i == nums.length) {
            return 0;
        }

        return select(i + 1, nums, curAmount);
    }

    public static void main(String[] args) {
        DynamicSolution dynamicSolution = new DynamicSolution();
        int[] coins = {2, 7, 9, 6, 1};
        System.out.println(dynamicSolution.rob(coins));
    }


}
