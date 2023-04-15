package com.algorithm.leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * \
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 * <p>
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class Solution56 {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> mergeList = new ArrayList<>(intervals.length);
        int[] lastListArray = null;
        for (int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];
            if (mergeList.isEmpty() || left > lastListArray[1]) {
                lastListArray = new int[]{left, right};
                mergeList.add(lastListArray);
                continue;
            }
            lastListArray[1] = Math.max(right, lastListArray[1]);
        }
        return mergeList.toArray(new int[0][]);
    }


    public boolean isRobotBounded(String instructions) {
        int x = 0;
        int y = 0;
        int j = 4;
        int k = 0;
        char[] chars = instructions.toCharArray();
        while (k < 160) {
            for (char c : chars) {
                if ('G' == c) {
                    int i = j % 4;
                    if (i == 0) {
                        y++;
                        j = 4;
                    } else if (i == 1) {
                        x--;
                    } else if (i == 2) {
                        y--;
                    } else if (i == 3) {
                        x++;
                    }

                } else if ('L' == c) {
                    j--;
                } else if ('R' == c) {
                    j++;
                }
            }
            System.out.println("x=" + x + " y=" + y + " j=" + j);
            if (x == 0 && y == 0 && j % 4 == 0) {
                return true;
            }
            k++;
        }

        return false;
    }

    public static void main(String[] args) {
        Solution56 solution = new Solution56();
        System.out.println(solution.isRobotBounded("GLRLGLLGLGRGLGL"));


        String str="69766955|69766919|69766902|69766907|69766958|69766965|69766966|69766967|69766971|69766972|69766974|69766964|69766975|69766976|69766968|69766969|69766980|69766981|69766984|69766982|69766985|69766988";
        System.out.println(str.replaceAll("\\|","','"));
    }
}
