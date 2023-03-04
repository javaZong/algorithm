package com.algorithm.backtrack;

import java.util.*;

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
    public int multiQueens(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return multiQueensProcess(record, 0, n);
    }

    /**
     * @param record 用于存放之前行上存放的皇后位置  0—>i-1;
     * @param i      当前行数
     * @param n      截止行数
     * @return
     */
    public int multiQueensProcess(int[] record, int i, int n) {
        if (i == n) {
            System.out.println("multiQueensProcess");
            return 1;
        }
        // 当前行下遍历计算每列的位置
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValidQueueLocation(record, i, j)) {
                record[i] = j;
                res += multiQueensProcess(record, i + 1, n);
            }
        }
        return res;
    }

    /**
     * 拿当前i行j列的位置与之前i-1行的位置比较，是否符合规则
     *
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


    public List<List<String>> solveNQueens(int n) {
        List<List<String>> totalList = new ArrayList<>();
        int[] record = new int[n];
        solveNQueensProcess(record, 0, n, totalList);
        return totalList;
    }

    public void solveNQueensProcess(int[] record, int i, int n, List<List<String>> totalList) {
        if (i == n) {
            System.out.println("solveNQueensProcess");
            List list = buildList(record);
            totalList.add(list);
            return;
        }
        for (int j = 0; j < n; j++) {
            if (isValidQueueLocation(record, i, j)) {
                record[i] = j;
                solveNQueensProcess(record, i + 1, n, totalList);
            }
        }

    }


    public List buildList(int[] record) {
        int n = record.length;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int queenLocation = record[i];
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (queenLocation == j) {
                    str.append("Q");
                } else {
                    str.append(".");
                }
            }
            list.add(str.toString());
        }
        return list;
    }

    /**
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     * <p>
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     * <p>
     * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     * <p>
     * https://leetcode.cn/problems/combination-sum/description/
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> totalList = new ArrayList<>();
        Arrays.sort(candidates);
        int[] array = new int[candidates.length];
        combinationSumProcess(0, candidates, target, array, totalList);
        return totalList;
    }

    private void combinationSumProcess(int i, int[] candidates, int target, int[] array, List<List<Integer>> totalList) {
        if (target == 0) {
            // 添加集合
            List list = buildList(array, candidates);
            totalList.add(list);
            return;
        }
        if (target < 0) {
            return;
        }

        for (int j = i; j < candidates.length; j++) {
            target -= candidates[j];
            if (target < 0) {
                break;
            }
            array[j] += 1;
            // 一维数组直接用户当前下的位置即可
            combinationSumProcess(j, candidates, target, array, totalList);
            array[j] -= 1;
            target += candidates[j];

        }
    }

    private List<Integer> buildList(int[] array, int[] candidates) {
        List<Integer> list = new ArrayList();
        for (int i = 0; i < array.length; i++) {
            int n = array[i];
            while (n > 0) {
                list.add(candidates[i]);
                n--;
            }
        }
        return list;
    }


    /**
     * 全排列（不包含重复数字）
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> totalList = new ArrayList();
        int[] array = Arrays.copyOf(nums, nums.length);
        process(0, nums, array, totalList);
        return totalList;
    }

    private void process(int i, int[] nums, int[] array, List<List<Integer>> totalList) {
        if (i == nums.length) {
            totalList.add(addList(array));
            return;
        }
        for (int j = i; j < nums.length; j++) {
            swap(array, i, j);
            process(i + 1, nums, array, totalList);
            swap(array, j, i);
        }
    }

    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    private List<Integer> addList(int[] array) {
        List<Integer> list = new ArrayList(array.length);
        for (int n : array) {
            list.add(n);
        }
        return list;
    }

    /**
     * 全排列(包含重复数字）
     * https://leetcode.cn/problems/permutations/solutions/9914/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteWithRepeatNum(int[] nums) {
        List<List<Integer>> totalList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        permuteProcessWithRepeatNum(0, nums, list, used, totalList);
        return totalList;
    }

    /**
     * @param curDepth  当前深度
     * @param nums      当前数组
     * @param list      状态变量  存放遍历路径下的节点
     * @param used      状态变量  记录那些节点已经使用过了（遍历过了）
     * @param totalList 返回的目标列表
     */
    private void permuteProcessWithRepeatNum(int curDepth, int[] nums, List<Integer> list, boolean[] used, List<List<Integer>> totalList) {
        if (curDepth == nums.length) {
            totalList.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            list.add(nums[i]);
            permuteProcessWithRepeatNum(curDepth + 1, nums, list, used, totalList);
            list.remove(list.size() - 1);
            used[i] = false;
        }
    }


    public static void main(String[] args) {
        BacktrackSolution solution = new BacktrackSolution();
        System.out.println(solution.multiQueens(4));
        System.out.println(solution.solveNQueens(4).size());
    }

}
