package com.algorithm;

public class TestMain {

    public static void main(String[] args) {
        int[] nums = {-3, -2, 0, -1};
        System.out.println(maxSubArray(nums));
        String a = "aa";
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
}
