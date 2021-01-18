package com.algorithm.array;

/**
 * Created by java_zong on 2019/5/18.
 */
public class Solution {

    /**
     * 二分法
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0
     *
     * @param array
     * @return
     */
    public int minNumberInRotateArrayDichotomy(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int start = 0, end = array.length - 1;
        int center;
        while (start < end - 1) {
            if (array[start] > array[end]) {
                center = (start + end) / 2;
                if (array[center] >= array[start]) {
                    start = center;
                } else if (array[center] <= array[end]) {
                    end = center;
                }

            } else if (array[start] == array[end]) {
                start++;
                end--;
            } else {
                return array[start];
            }
        }
        return array[start] > array[end] ? array[end] : array[start];
    }


    /**
     * 线性
     *
     * @param array
     * @return
     */
    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int start = 0, end = array.length - 1;

        while (start < end) {
            if (array[start] >= array[end]) {
                start++;
            } else {
                break;
            }
        }
        return array[start];
    }


    /**
     * 在一个二维数组中（每个一维数组的长度相同），
     * 每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * <p>
     * 一般涉及到这种有序数组的查找可以优先考虑二分查找
     *
     * @param target
     * @param array
     * @return
     */
    public boolean Find(int target, int[][] array) {

        int maxIndex;
        if (array == null || array.length == 0 || (maxIndex = array[0].length - 1) < 0) {
            return false;
        }

        int rowIndex = 0;
        int colIndex = maxIndex;
        boolean contains = false;
        while (rowIndex <= maxIndex & colIndex >= 0) {

            if (target > array[rowIndex][colIndex]) {
                ++rowIndex;
            } else if (target < array[rowIndex][colIndex]) {
                --colIndex;
            } else {
                contains = true;
                break;
            }
        }
        return contains;
    }

    /**
     * 将两个有序数组合并成一个有序数组
     *
     * @param targetArray 数组1 其内存足够大
     * @param srcArray    数组2
     */
    public static void mergeArray(int[] targetArray, int[] srcArray) {
        if (targetArray == null || srcArray == null) {
            return;
        }

        int targetArrayIndex = 0;
        int srcArrayIndex = 0;

    }

    public static void main(String[] args) {
        int[] targetArray = new int[20];
        System.out.println(targetArray.length);
        int[] srcArray = new int[10];
        int targetArrayIndex = 0;
        int srcArrayIndex = 0;
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                srcArray[srcArrayIndex++] = i;
            } else {
                targetArray[targetArrayIndex++] = i;
            }
        }
        mergeArray(targetArray,srcArray);
    }
}
