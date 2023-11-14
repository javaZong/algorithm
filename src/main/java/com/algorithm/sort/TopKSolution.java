package com.algorithm.sort;

import com.algorithm.util.AlgorithmUtils;

import java.util.*;

/**
 * topk问题
 *
 * @author zong
 */
public class TopKSolution {

    public int[] findTopKByHeapSort(int[] nums, int k) {
        // 优先级队列本质上就是最小堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k);
        for (int n : nums) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(n);
            } else if (n > priorityQueue.peek()) {
                // 如果当前数大于，最小堆里的最小值，则当前数是有可能在topk里的
                priorityQueue.poll();
                priorityQueue.add(n);
            }
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
        // 快慢指针，当快指针遇到一个不小于temp的值时，与慢指针交换
        int slowIndex = left + 1;
        for (int fastIndex = slowIndex; fastIndex <= right; fastIndex++) {
            if (nums[fastIndex] >= temp) {
                AlgorithmUtils.swap(nums, slowIndex, fastIndex);
                slowIndex++;
            }
        }
        int pointIndex = slowIndex - 1;
        AlgorithmUtils.swap(nums, left, pointIndex);
        return pointIndex;
    }

    /**
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     * https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/description/
     *
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        int slowIndex = 0;
        int maxIndex = nums.length - 1;
        for (int fastIndex = slowIndex; fastIndex <= maxIndex; fastIndex++) {
            if ((nums[fastIndex] & 1) == 1) {
                AlgorithmUtils.swap(nums, slowIndex, fastIndex);
                slowIndex++;
            }
        }
        return nums;
    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     *
     * @param nums
     * @return
     */
    public void reOrderArray(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int oddSize = 0;
        for (int n : array) {
            if ((n & 1) == 1) {
                oddSize += 1;
            }
        }
        System.out.println(oddSize);
        int[] nums = new int[array.length];
        int oddIndex = 0;
        int evenIndex = oddSize;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & 1) == 1) {
                nums[oddIndex++] = array[i];
            } else {
                nums[evenIndex++] = array[i];
            }
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = nums[i];
        }

    }

    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length < 2) {
            return nums;
        }
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] < o2[0] ? -1 : 1;
            }
        });
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int size = 1;
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                size++;
                i++;
            }
            int[] subNums = null;
            if (priorityQueue.size() < k) {
                subNums = new int[2];
                subNums[0] = size;
                subNums[1] = nums[i];
                priorityQueue.add(subNums);
                continue;
            }
            subNums = priorityQueue.peek();
            if (subNums[0] < size) {
                priorityQueue.poll();
                subNums = new int[2];
                subNums[0] = size;
                subNums[1] = nums[i];
                priorityQueue.add(subNums);
            }
        }
        int[] targetNum = new int[k];
        for (int i = 0; i < k; i++) {
            targetNum[i] = priorityQueue.poll()[1];
        }
        return targetNum;
    }

    public static void main(String[] args) {
        TopKSolution solution = new TopKSolution();
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("args = " + Arrays.toString(nums));
//        solution.reOrderArray(nums);
        System.out.println("target = " + Arrays.toString(solution.findTopKByQuickSort(nums,3)));
    }
}
