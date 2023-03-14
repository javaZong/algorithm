package com.algorithm.dynamic;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
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

    public static void main(String[] args) {
        
    }


}
