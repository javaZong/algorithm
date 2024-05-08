package com.algorithm.dynamic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 *
 * 子数组是数组中元素的连续非空序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * https://leetcode.cn/problems/subarray-sum-equals-k/?envType=study-plan-v2&envId=top-100-liked
 */
public class LeetCode560 {
    public static int subarraySum1(int[] nums, int k) {

        int[] sum = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            sum[i]=nums[i];
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (j != 0) {
                    sum[j] = sum[j - 1] + nums[j];
                }
                if (sum[j] == k) {
                    count++;
                }
            }
        }
        return count;
    }
    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int preSum=0;
        Map<Integer,Integer> map=new HashMap();
        map.put(0,1);
        for(int i=0;i<nums.length;i++){
            preSum=preSum+nums[i];
            int targetKey=preSum-k;
            Integer targetValue=map.get(targetKey);
            if(targetValue!=null){
                count=count+targetValue;
            }
            map.put(preSum,map.getOrDefault(preSum,0)+1);
        }
        return count;

    }

    public static void main(String[] args) {
        int[] arrays=new int[2];
    }
}
