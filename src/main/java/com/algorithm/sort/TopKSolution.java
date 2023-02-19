package com.algorithm.sort;

import com.algorithm.util.ArrayUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * topk问题
 *
 * @author zong
 */
public class TopKSolution {

    public int[] findTopKByHeapSort(int[] nums, int k) {
        // 优先级队列本质上就是最小堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(nums.length);
        for (int n : nums) {
            priorityQueue.add(n);
        }

        int[] targetNums = new int[k];
        for (int i = 0; i < k && !priorityQueue.isEmpty(); i++) {
            targetNums[i] = priorityQueue.poll();
        }
        return targetNums;
    }

    public int[] findTopKByQuickSort(int[] nums, int k) {
        if (k < 1) {
            return new int[0];
        }
        if (k >= nums.length) {
            return Arrays.copyOf(nums, nums.length);
        }
        Queue<int[]> queue = new LinkedList<>();
        int[] array = {0, nums.length - 1};
        queue.add(array);
        int targetIndex = k - 1;
        while (!queue.isEmpty()) {
            array = queue.poll();
            int left = array[0];
            int right = array[1];
            int pointIndex = findPointIndex(nums, left, right);
            if (pointIndex == targetIndex) {
                break;
            }
            if (pointIndex > targetIndex) {
                array[0] = left;
                array[1] = pointIndex - 1;
                queue.add(array);
            }
            if (pointIndex < targetIndex) {
                array[0] = pointIndex + 1;
                array[1] = right;
                queue.add(array);
            }
        }
        System.out.println(targetIndex);
        int[] targetNums = new int[k];
        System.arraycopy(nums, 0, targetNums, 0, k);
        return targetNums;
    }

    private int findPointIndex(int[] nums, int left, int right) {

        int temp = nums[left];
        // 快慢指针，当快指针遇到一个比temp小的值时，与慢指针交换
        int slowIndex = left + 1;
        for (int fastIndex = slowIndex; fastIndex <= right; fastIndex++) {
            if (nums[fastIndex] < temp) {
                ArrayUtils.swap(nums, slowIndex, fastIndex);
                slowIndex++;
            }
        }
        int pointIndex = slowIndex - 1;
        ArrayUtils.swap(nums, left, pointIndex);
        return pointIndex;
    }

    public static void main(String[] args) {
        TopKSolution solution = new TopKSolution();
        int[] nums = ArrayUtils.buildRandomArray(10);
        System.out.println("args = " + Arrays.toString(nums));
        System.out.println("target = " + Arrays.toString(solution.findTopKByQuickSort(nums, 5)));
    }
}
