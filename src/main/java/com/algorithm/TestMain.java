package com.algorithm;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class TestMain {

    public static void main(String[] args) {
        String str = "";
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        System.out.println(new String(chars));
        System.out.println(1>>31);
        System.out.println(1<<31);
        List arrayList=new ArrayList();
        
    }

    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];

            if (maxSum < sum) {
                maxSum = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }

    public static int numDifferentIntegers(String word) {
        Set<String> set = new HashSet();
        char[] chars = word.toCharArray();
        String strNum = "";
        boolean existNum = false;
        int size = 0;
        for (int i = 0; i < chars.length; i++) {
            int temp = chars[i];
            if (temp < 57) {
                if (temp == 48 & !existNum) {
                    existNum = false;
                    continue;
                }
                existNum = true;
                strNum += chars[i];
                if (i == chars.length - 1) {
                    set.add(strNum);
                    continue;
                }
            } else {
                if (existNum) {
                    set.add(strNum);
                    strNum = "";
                }
                existNum = false;
            }
        }
        return set.size();
    }

    public void nextPermutation(int[] nums) {
        if (nums.length < 2) {
            return;
        }
        int index = nums.length - 2;
        while (index >= 0) {
            if (nums[index] < nums[index + 1]) {
                break;
            }
            index--;
        }
        if (index >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[index] >= nums[j]) {
                j--;
            }
            swap(nums, index, j);
        }
        reverseNums(nums, index + 1);

    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    private void reverseNums(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
}
