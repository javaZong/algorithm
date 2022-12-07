package com.algorithm.array;

import java.util.*;

/**
 * 多数之和
 */
public class MutilNumSum {


    /**
     * 三数之和
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> list=new ArrayList();
        if(nums==null||nums.length<3){
            return list;
        }

        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            if(i!=0&&nums[i]==nums[i-1]){
                continue;
            }
            int initLeft=i+1;
            int left=initLeft;
            int initRight=nums.length-1;
            int right=initRight;
            while(left<right){
                if(left!=initLeft&&nums[left]==nums[left-1]){
                    left++;
                    continue;
                }
                if(right<initRight&&nums[right]==nums[right+1]){
                    right--;
                    continue;
                }
                int sum=nums[i]+nums[left]+nums[right];
                if(sum==0){
                    List<Integer> itemList=new ArrayList();
                    itemList.add(nums[i]);
                    itemList.add(nums[left]);
                    itemList.add(nums[right]);
                    list.add(itemList);
                    left++;
                    right--;
                    continue;
                }
                if (sum<0){
                    left++;
                    continue;
                }
                right--;
            }

        }
        return list;
    }
}
