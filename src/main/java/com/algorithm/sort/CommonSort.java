package com.algorithm.sort;

import com.algorithm.util.ArrayUtils;

import java.util.Arrays;

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
            int minLength = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minLength]) {
                    minLength = j;
                }
            }
            swap(array, i, minLength);
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
            for (int j = i ; j > -1; j--) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
            System.out.println(Arrays.toString(array));
        }
    }

    public static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void main(String[] args) {
        int[] array = ArrayUtils.buildRandomArray(5);
        insertionSore(array);
    }
}
