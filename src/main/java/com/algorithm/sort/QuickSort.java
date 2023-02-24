package com.algorithm.sort;

import com.algorithm.util.AlgorithmUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class QuickSort {

    public static void quickSortByForeach(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        Queue<int[]> queue = new LinkedList<>();
        int[] indexNums = {0, array.length - 1};
        queue.add(indexNums);
        while (!queue.isEmpty()) {
            indexNums = queue.remove();
            int left = indexNums[0];
            int right = indexNums[1];
            int pointKey = findPointIndex(array, left, right);
            if (pointKey > left) {
                indexNums = new int[2];
                indexNums[0] = left;
                indexNums[1] = pointKey - 1;
                queue.add(indexNums);
            }
            if (pointKey < right) {
                indexNums = new int[2];
                indexNums[0] = pointKey + 1;
                indexNums[1] = right;
                queue.add(indexNums);
            }

        }

    }

    /**
     * 快排递归
     *
     * @param array
     * @param left
     * @param right
     */
    public static void quickSortByRecursion(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int pointIndex = findPointIndex(array, left, right);
        quickSortByRecursion(array, left, pointIndex - 1);
        quickSortByRecursion(array, pointIndex + 1, right);
    }


    private static int findPointIndex(int[] nums, int left, int right) {
        int temp = nums[left];
        // 快慢指针，当快指针遇到一个比temp小的值时，与慢指针交换
        int slowIndex = left + 1;
        for (int fastIndex = slowIndex; fastIndex <= right; fastIndex++) {
            if (nums[fastIndex] < temp) {
                AlgorithmUtils.swap(nums, slowIndex, fastIndex);
                slowIndex++;
            }
        }
        int pointIndex = slowIndex - 1;
        AlgorithmUtils.swap(nums, left, pointIndex);
        return pointIndex;
    }

    public static void main(String[] args) {
        int length = 10;
        int[] array = AlgorithmUtils.buildRandomArray(length);
        System.out.println(Arrays.toString(array));
//        quickSortByRecursion(array, 0, array.length - 1);
        quickSortByForeach(array);
        System.out.println(Arrays.toString(array));
    }
}
