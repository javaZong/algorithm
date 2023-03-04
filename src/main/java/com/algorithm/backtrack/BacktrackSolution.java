package com.algorithm.backtrack;

import java.util.Arrays;

/**
 * 回溯算法
 *
 * @author zongchao
 */
public class BacktrackSolution {

    /**
     * 经典N皇后问题
     * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线(对角)上的棋子。
     * <p>
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * <p>
     * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案的个数。
     * https://leetcode.cn/problems/n-queens/
     *
     * @param n
     * @return
     */
    public int multiQueue(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return multiQueueProcess(record, 0, n);
    }

    /**
     * @param record 用于存放之前行上存放的皇后位置  0—>i-1;
     * @param i      当前行数
     * @param n      截止行数
     * @return
     */
    public int multiQueueProcess(int[] record, int i, int n) {
        if (i == n) {
            return 1;
        }
        // 当前行下遍历计算每列的位置
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValidQueueLocation(record, i, j)) {
                record[i] = j;
                res += multiQueueProcess(record, i + 1, n);
            }
        }
        return res;
    }

    /**
     * 拿当前i行j列的位置与之前i-1行的位置比较，是否符合规则
     * @param record
     * @param i
     * @param j
     * @return
     */
    public boolean isValidQueueLocation(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        BacktrackSolution solution=new BacktrackSolution();
        System.out.println(solution.multiQueue(8));
    }

}
