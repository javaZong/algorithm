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
     * 要考虑start和end相等的情况
     * @param array
     * @return
     */
    public int minNumberInRotateArrayDichotomy(int[] array) {
        if (array == null || array.length < 1) {
            return 0;
        }
        int start = 0;
        int end = array.length - 1;

        while (start < end - 1) {
            if (array[start] == array[end]) {
                start++;
                end--;
                continue;
            }

            int mid = (end + start) >> 1;
            if (array[mid] >= array[start]) {
                start = mid;
            } else if (array[mid] <= array[end]) {
                end = mid;
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

    /**
     * 寻找两个正序数组的中位数
     *
     * @param nums1 数组1
     * @param nums2 数组2
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1==null&&nums2==null){
            return 0.00;
        }
        if(nums1==null){
            return findMedian(nums2);
        }
        if(nums2==null){
            return findMedian(nums1);
        }
        int i=0;
        int j=0;
        int m=nums1.length;
        int n=nums2.length;
        int sum=m+n;
        int[] array=new int[sum];

        for(int k=0;k<sum;k++){

            if(i==m&&j<n){
                array[k]=nums2[j];
                j++;
                continue;
            }
            if(j==n&&i<m){
                array[k]=nums1[i];
                i++;
                continue;
            }
            if(nums1[i]<=nums2[j]){
                array[k]=nums1[i];
                i++;
            }else {
                array[k]=nums2[j];
                j++;
            }
        }
        return findMedian(array);

    }

    private static double findMedian(int[] array){
        int midIndex=(array.length-1)/2;
        if (array.length%2!=0){

            return array[midIndex];
        }
        int midNextIndex=midIndex+1;
        return (array[midIndex]+array[midNextIndex])/2.00;
    }

    public static void main(String[] args) {
        int[] targetArray = new int[2];
        System.out.println(targetArray.length);
        int[] srcArray = new int[2];
        int targetArrayIndex = 0;
        int srcArrayIndex = 0;
        for (int i = 1; i < 5; i++) {
            if (i % 2 == 0) {
                srcArray[srcArrayIndex++] = i;
            } else {
                targetArray[targetArrayIndex++] = i;
            }
        }
        targetArray=new int[]{1,3};
        srcArray=new int[]{2};
        System.out.println( findMedianSortedArrays(targetArray, srcArray));
    }
}
