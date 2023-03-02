package com.algorithm.greedy;

/**
 * 贪心算法
 */
public class GreedySolution {
    /**
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     *
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * 返回容器可以储存的最大水量。
     * area=min(height(start),height(end))*(end-start)
     * 能够接多少水取决于较短的线，
     * 想要找到最优解
     * 如果
     * height(start)<height(end)
     * height(start+1)>height(start)
     * 则min(height(start+1),height(end))*(end-start)>min(height(start),height(end))*(end-start)的
     * 那么min(height(start+1),height(end))*(end-start-1)有可能是大于前一次的结果的
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        if (height == null || height.length < 1) {
            return 0;
        }
        int start = 0;
        int end = height.length - 1;
        int max = 0;
        int area = 0;
        while (start < end) {
            int size = end - start;
            if (height[start] <= height[end]) {
                area = height[start] * size;
                start++;
            } else {
                area = height[end] * size;
                end--;
            }
            if (area > max) {
                max = area;
            }
        }
        return max;
    }
}
