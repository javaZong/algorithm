package com.algorithm.sort;

import com.algorithm.util.ArrayUtils;

import java.util.*;

/**
 * 八大排序算法
 *
 * @author zongchao
 */
public class CommonSort {

    /**
     * 冒泡排序
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     * 时间复杂度O（n^2）
     *
     * @param array
     */
    public static void bubbleSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        System.out.println("bubbleSort-init=" + Arrays.toString(array));
        for (int j = array.length - 1; j > 0; j--) {
            int i = 0;
            while (i < j) {
                int right = i + 1;
                if (array[i] > array[right]) {
                    swap(array, i, right);
                }
                i = right;
            }
            System.out.println(Arrays.toString(array));
        }
        System.out.println(Arrays.toString(array));

    }

    /**
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
     * <p>
     * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * <p>
     * 重复第二步，直到所有元素均排序完毕。
     *
     * @param array
     */
    public static void selectionSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        System.out.println("selectionSort-init=" + Arrays.toString(array));
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
            System.out.println(Arrays.toString(array));
        }
    }

    /**
     * 插入排序
     * 将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
     * <p>
     * 从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。
     * （如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）
     *
     * @param array
     */
    public static void insertionSore(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        System.out.println("insertionSore-init=" + Arrays.toString(array));
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i; j > -1; j--) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
            System.out.println(Arrays.toString(array));
        }
    }

    /**
     * 选择一个增量序列 t1，t2，……，tk，其中 ti > tj, tk = 1；
     * <p>
     * 按增量序列个数 k，对序列进行 k 趟排序；
     * <p>
     * 每趟排序，根据对应的增量 ti，将待排序列分割成若干长度为 m 的子序列，分别对各子表进行直接插入排序。
     * 仅增量因子为 1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {
        int length = arr.length;
        int temp;
        for (int step = length / 2; step >= 1; step /= 2) {
            for (int i = step; i < length; i++) {
                temp = arr[i];
                int j = i - step;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j + step] = temp;
            }
        }
    }

    /**
     * 归并排序
     * 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列；
     *
     * 设定两个指针，最初位置分别为两个已经排序序列的起始位置；
     *
     * 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置；
     *
     * 重复步骤 3 直到某一指针达到序列尾；
     *
     * 将另一序列剩下的所有元素直接复制到合并序列尾。
     * @param array
     * @param left
     * @param right
     */
    public static void mergeSortRecursion(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortRecursion(array, left, mid);
        mergeSortRecursion(array, mid + 1, right);
        mergeTwoSubArray(array, left, mid, mid + 1, right);
    }


    private static void mergeTwoSubArray(int[] array, int leftA, int rightA,
                                         int leftB, int rightB) {
        int tempArrayLength = (rightA - leftA) + 1 + (rightB - leftB) + 1;
        if (tempArrayLength < 2) {
            return;
        }
        int[] temp = new int[tempArrayLength];
        boolean aArrayForeachNotFinish = false;
        boolean bArrayForeachNotFinish = false;
        int firstArrayIndex = leftA;
        for (int i = 0; i < tempArrayLength; i++) {
            aArrayForeachNotFinish = leftA <= rightA;
            bArrayForeachNotFinish = leftB <= rightB;
            if (aArrayForeachNotFinish && bArrayForeachNotFinish) {
                if (array[leftA] < array[leftB]) {
                    temp[i] = array[leftA];
                    leftA++;
                    continue;
                }
                temp[i] = array[leftB];
                leftB++;
                continue;
            }
            if (aArrayForeachNotFinish) {
                temp[i] = array[leftA];
                leftA++;
                continue;
            }
            if (bArrayForeachNotFinish) {
                temp[i] = array[leftB];
                leftB++;
            }
        }
        for (int i = 0; i < tempArrayLength; i++) {
            array[firstArrayIndex] = temp[i];
            firstArrayIndex++;
        }
    }

    public static void quickSortByForeach(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        Queue<SortItemObject> queue = new LinkedList<>();
        SortItemObject item = new SortItemObject(0, array.length - 1);
        queue.add(item);
        while (!queue.isEmpty()) {
            item = queue.remove();
            int left = item.left;
            int right = item.right;
            int pointKey = execPartSort(array, left, right);
            if (pointKey > left) {
                queue.add(new SortItemObject(left, pointKey - 1));
            }
            if (pointKey < right) {
                queue.add(new SortItemObject(pointKey + 1, right));
            }

        }

    }

    private static class SortItemObject {
        int left;
        int right;

        SortItemObject(int left, int right) {
            this.left = left;
            this.right = right;
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

        int pointIndex = execPartSort(array, left, right);
        quickSortByRecursion(array, left, pointIndex - 1);
        quickSortByRecursion(array, pointIndex + 1, right);
    }

    private static int execPartSort(int[] array, int left, int right) {
        if (left >= right) {
            return left;
        }
        int point = array[left];
        int slowIndex = left + 1;
        // 快慢指针
        for (int fastIndex = slowIndex; fastIndex <= right; fastIndex++) {
            // 慢指针所在的数字一定是大于基准数的（快指针已经走过了），当快指针的数字出现比基准数字小时
            if (array[fastIndex] < point) {
                swap(array, fastIndex, slowIndex);
                slowIndex++;
            }
        }
        int targetIndex = slowIndex - 1;
        swap(array, left, targetIndex);
        return targetIndex;
    }


    public static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void main(String[] args) {
        int length = 10;
        int[] array = ArrayUtils.buildRandomArray(length);
        System.out.println(Arrays.toString(array));
        quickSortByForeach(array);
        System.out.println(Arrays.toString(array));
    }
}
