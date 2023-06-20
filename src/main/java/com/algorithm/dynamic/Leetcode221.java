package com.algorithm.dynamic;


import java.util.*;

public class Leetcode221 {


    /**
     * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
     * https://leetcode.cn/problems/maximal-square/?favorite=2cktkvj
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxSide = 0;
        // 代表在当前右下方的位置存在的最大的正方形的边长
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != '1') {
                    continue;
                }
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
                maxSide = Math.max(maxSide, dp[i][j]);

            }
        }
        return maxSide * maxSide;
    }

    /**
     * 1277. 统计全为 1 的正方形子矩阵
     * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
     *
     * @param matrix
     * @return
     */
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxSize = 0;
        // 代表在当前右下方的位置存在的最大正方形的边长
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 1) {
                    continue;
                }
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
                maxSize += dp[i][j];

            }
        }
        return maxSize;
    }

    /**
     * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
     * <p>
     * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {

        int[] f = new int[n + 1];
        int temp = 0;
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; (temp = j * j) <= i; j++) {
                minn = Math.min(minn, f[i - temp]);
            }
            f[i] = minn + 1;
        }
        return f[n];
    }

    public int numSquaresDp(int n) {

        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                int temp = j * j;
                if (i >= temp) {
                    dp[i] = Math.min(dp[i], dp[i - temp] + 1);
                }

            }
        }
        return dp[n];
    }

    int minSize = 0;

    private void numSquaresProcess(int n, int level, int size) {
        if (n == 0) {
            if (size < minSize || minSize == 0) {
                minSize = size;
            }
            return;
        }
        if (n < 0) {
            return;
        }
        for (int i = level; i > 0; i--) {
            int temp = i * i;
            numSquaresProcess(n - temp, i, size + 1);
        }


    }

    int[] minTem;


    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length, m = people.size();
        HashMap<String, Integer> skill_index = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            skill_index.put(req_skills[i], i);
        }
        // 满足技能集合为 i 的最小人数的数组
        int[] dp = new int[1 << n];
        Arrays.fill(dp, m);
        dp[0] = 0;
        int[] prev_skill = new int[1 << n];
        int[] prev_people = new int[1 << n];
        for (int i = 0; i < m; i++) {
            List<String> p = people.get(i);
            int cur_skill = 0;
            for (String s : p) {
                cur_skill |= 1 << skill_index.get(s);
            }
            for (int prev = 0; prev < (1 << n); prev++) {
                int comb = prev | cur_skill;
                if (dp[comb] > dp[prev] + 1) {
                    dp[comb] = dp[prev] + 1;
                    prev_skill[comb] = prev;
                    prev_people[comb] = i;
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        int i = (1 << n) - 1;
        while (i > 0) {
            res.add(prev_people[i]);
            i = prev_skill[i];
        }
        return res.stream().mapToInt(j -> j).toArray();
    }

    public int[] smallestSufficientTeamByRecursion(String[] req_skills, List<List<String>> people) {
        smallestSufficientTeamProcess(0, req_skills, people, new HashMap<>());
        return minTem;
    }

    public void smallestSufficientTeamProcess(int reqIndex, String[] reqSkills, List<List<String>> people, HashMap<String, Integer> tem) {
        if (reqIndex == reqSkills.length) {
            Set<Integer> set = new HashSet<>(tem.values());
            if (minTem == null || minTem.length > set.size()) {
                minTem = new int[set.size()];
                int i = 0;
                for (int t : set) {
                    minTem[i++] = t;
                }
            }
            return;
        }
        if (reqIndex > reqSkills.length) {
            return;
        }
        String req = reqSkills[reqIndex];
        for (int i = 0; i < people.size(); i++) {
            List<String> p = people.get(i);
            if (p.contains(req)) {
                tem.put(req, i);
                smallestSufficientTeamProcess(reqIndex + 1, reqSkills, people, tem);
                tem.remove(i);
            }
        }

    }

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        int neg = sum - target;
        if (neg < 0 || (neg & 1) != 0) {
            return 0;
        }
        neg = neg >> 1;
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = neg; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[target];
    }

    public int numDecodings(String s) {
        if (s.startsWith("0")) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length + 1];
        dp[0] = 1;
        for (int i = 1; i <= chars.length; i++) {

            if (chars[i - 1] != '0') {
                dp[i] = dp[i - 1];
            }
            if (i > 1 && chars[i - 2] != '0') {
                int sum = Character.getNumericValue(chars[i - 2]) * 10 + Character.getNumericValue(chars[i - 1]);
                if (sum <= 26) {
                    System.out.println(sum);
                    dp[i] += dp[i - 2];
                }
            }

        }
        return dp[chars.length];
    }

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int max = 0;
        int fEnd = nums.length - firstLen;
        int sEnd = nums.length - secondLen;
        int[][] dp = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (j == i) {
                    dp[i][j] = nums[j];
                } else {
                    dp[i][j] = dp[i][j - 1] + nums[j];
                }

            }
        }

        for (int i = 0; i <= fEnd; i++) {

            int end = i + firstLen - 1;
            int firstSum = dp[i][end];

            for (int j = 0; j <= sEnd; j++) {
                if (j >= i && j <= end) {
                    continue;
                }
                int secEnd = j + secondLen - 1;
                if (secEnd >= i && secEnd <= end) {
                    continue;
                }
                if (j <= i && secEnd >= end) {
                    continue;
                }
                int secSum = dp[j][secEnd];

                if (max < (firstSum + secSum)) {
                    max = firstSum + secSum;
                }
            }
        }
        return max;
    }

    /**
     * 2352. 相等行列对
     * 中等
     * <p>
     * 给你一个下标从 0 开始、大小为 n x n 的整数矩阵 grid ，返回满足 Ri 行和 Cj 列相等的行列对 (Ri, Cj) 的数目。
     * <p>
     * 如果行和列以相同的顺序包含相同的元素（即相等的数组），则认为二者是相等的。
     * <p>
     * https://leetcode.cn/problems/equal-row-and-column-pairs/
     *
     * @param grid
     * @return
     */
    public int equalPairs(int[][] grid) {

        int equalPairSize = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (judgeEqualPair(i, j, grid)) {
                    equalPairSize++;
                }
            }
        }
        return equalPairSize;
    }

    private boolean judgeEqualPair(int cIndex, int rowIndex, int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            System.out.println(i + "_" + rowIndex + "::::" + cIndex + "_" + i);
            if (grid[i][rowIndex] != grid[cIndex][i]) {
                return false;
            }
        }
        return true;
    }

    public int countSubstrings(String s) {
        // 动态规划法
        boolean[][] dp = new boolean[s.length()][s.length()];
        int ans = 0;

        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    ans++;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Leetcode221 obj = new Leetcode221();
//        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        String s = "2111";
//        int[][] nums = {0, 6, 5, 2, 2, 5, 1, 9, 4};
//        System.out.println(obj.maxSumTwoNoOverlap(nums, 1, 2));

        int[][] grid = {{3, 2, 1}, {1, 7, 6}, {2, 7, 7}};
        System.out.println(obj.equalPairs(grid));
    }


}
