package com.algorithm.sort;

import com.algorithm.util.AlgorithmUtils;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author zongchao
 */
public class HeapSort {

    public static void heapSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        buildMaxHeap(array);
        for (int i = array.length - 1; i > 0; i--) {
            AlgorithmUtils.swap(array, 0, i);
            heapify(array, 0, i);
        }
    }

    public static void buildMaxHeap(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int i = (int) Math.floor(array.length / 2);
        while (i >= 0) {
            heapify(array, i, array.length);
            i--;
        }
    }

    /**
     * 逐级构建最值堆
     *
     * @param array     数组
     * @param i         当前节点小标
     * @param arraySize 最大小标-1 控制不能越界
     */
    private static void heapify(int[] array, int i, int arraySize) {

        int rightIndex = 0;
        int half=arraySize>>1;
        while (i < half) {
            int leftIndex = (i << 1) + 1;
            rightIndex = leftIndex + 1;
            int largestIndex = rightIndex < arraySize && array[rightIndex] > array[leftIndex] ? rightIndex : leftIndex;
            largestIndex = array[largestIndex] > array[i] ? largestIndex : i;
            if (largestIndex == i) {
                return;
            }
            AlgorithmUtils.swap(array, i, largestIndex);
            i = largestIndex;
        }
    }

    public static void main(String[] args) {
        int length = 10;
        int[] array = AlgorithmUtils.buildRandomArray(length);
        System.out.println(Arrays.toString(array));
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }
}
