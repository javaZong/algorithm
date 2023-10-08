package com.algorithm.doublepointer;

/**
 * 双指针
 *
 * @author zongchao
 */
public class DoublePointerSolution {
    public int trap(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                ++left;
            } else {
                ans += rightMax - height[right];
                --right;
            }
        }
        return ans;
    }


    /**
     * 849. 到最近的人的最大距离
     * 中等
     * 266
     * 相关企业
     * 给你一个数组 seats 表示一排座位，其中 seats[i] = 1 代表有人坐在第 i 个座位上，seats[i] = 0 代表座位 i 上是空的（下标从 0 开始）。
     *
     * 至少有一个空座位，且至少有一人已经坐在座位上。
     *
     * 亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。
     *
     * 返回他到离他最近的人的最大距离。
     * https://leetcode.cn/problems/maximize-distance-to-closest-person/description/
     * @param seats
     * @return
     */
    public int maxDistToClosest(int[] seats) {
        int max = 0;
        // 慢指针
        int slow = 0;
        // 快指针
        int fast = 0;
        while (fast < seats.length) {
            // 快指针遇到1则停止，此时在快慢指针中间有可能存在最大距离
            if (seats[fast] == 1) {
                int dist=fast-slow;
                if(seats[slow]==1){
                    // 慢指针指向1，距离=（fast-slow）/2
                    // 慢指针指向0，距离=（fast-slow）
                    dist = dist >> 1;
                }
                if (dist > max) {
                    max = dist;
                }
                slow = fast;
            }
            fast++;
        }
        if (seats[fast - 1] == 0) {
            // 快指针指向0，也有可能存在最大距离
            int dist = fast - 1 - slow;
            if (dist > max) {
                max = dist;
            }
        }
        return max;

    }

    public static void main(String[] args) {
        DoublePointerSolution solution = new DoublePointerSolution();
    }
}
