package com.algorithm.array;

import com.algorithm.util.ArrayUtils;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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
     * <p>
     * 要考虑start和end相等的情况
     *
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
        if (nums1 == null && nums2 == null) {
            return 0.00;
        }
        if (nums1 == null) {
            return findMedian(nums2);
        }
        if (nums2 == null) {
            return findMedian(nums1);
        }
        int i = 0;
        int j = 0;
        int m = nums1.length;
        int n = nums2.length;
        int sum = m + n;
        int[] array = new int[sum];

        for (int k = 0; k < sum; k++) {

            if (i == m && j < n) {
                array[k] = nums2[j];
                j++;
                continue;
            }
            if (j == n && i < m) {
                array[k] = nums1[i];
                i++;
                continue;
            }
            if (nums1[i] <= nums2[j]) {
                array[k] = nums1[i];
                i++;
            } else {
                array[k] = nums2[j];
                j++;
            }
        }
        return findMedian(array);

    }

    private static double findMedian(int[] array) {
        int midIndex = (array.length - 1) / 2;
        if (array.length % 2 != 0) {

            return array[midIndex];
        }
        int midNextIndex = midIndex + 1;
        return (array[midIndex] + array[midNextIndex]) / 2.00;
    }

    public static void main(String[] args) {
        int[] nums={2,3,7};
        int target=7;
        List<List<Integer>> list=combinationSum(nums,target);
        System.out.println(list);
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> arrayList = new ArrayList();
        if (nums == null || nums.length < 4) {
            return arrayList;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                long firstSum = nums[i] + nums[j];
                System.out.println(firstSum);

                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    if (left > j + 1 && nums[left] == nums[left - 1]) {
                        left++;
                        continue;
                    }
                    if (right < nums.length - 1 && nums[right] == nums[right + 1]) {
                        right--;
                        continue;
                    }
                    long sum = firstSum + nums[left] + nums[right];
                    if (sum == target) {
                        List<Integer> list = new ArrayList(4);
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        arrayList.add(list);
                        left++;
                        right--;
                        continue;
                    }
                    if (sum < target) {
                        left++;
                        continue;
                    }
                    right--;
                }
            }

        }
        return arrayList;
    }

    /**
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     * <p>
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 返回容器可以储存的最大水量。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/container-with-most-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 题解：左右指针，比较大小 谁小谁往下走
     * 容纳的水量是由
     * 两个指针指向的数字中较小值 * 指针之间的距离
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        int area = 0;
        while (left < right) {
            int length = right - left;
            if (height[left] <= height[right]) {
                area = height[left] * length;
                left++;
            } else {
                area = height[right] * length;
                right--;
            }
            if (area > max) {
                max = area;
            }
        }
        return max;
    }


    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> totalList=new ArrayList();
        Arrays.sort(candidates);
        for(int i=0;i<candidates.length;i++){
            List<Integer> list=new ArrayList();
            isContinue=false;
            combination(candidates,target,list,i,totalList);
        }
        return totalList;
    }

    static  boolean isContinue=false;
    public static void combination(int[] candidates,int target,List<Integer> list,int startIndex,List<List<Integer>>totalList){
        for(int i=startIndex;i<candidates.length;i++){
            if (isContinue){
                isContinue=false;
                continue;
            }
            int item=candidates[i];
            int s=target-item;
            if(s==0){
                List<Integer> itemList = new ArrayList(list);
                itemList.add(item);
                totalList.add(itemList);
                System.out.println(itemList);
                isContinue=true;
                return;
            }
            if(s<0){
                list.remove(list.size()-1);
                isContinue=true;
                return;
            }
            list.add(item);
            combination(candidates,s,list,i,totalList);
        }
    }


}
