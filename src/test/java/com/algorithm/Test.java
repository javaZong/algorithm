package com.algorithm;

import com.algorithm.util.AlgorithmUtils;

import java.util.HashMap;
import java.util.Map;

public class Test {


    public static void main(String[] args) {

    }

    public int findPointIndex(int[] nums, int left, int right) {
        int temp = nums[left];
        int slowIndex = left + 1;
        int fastIndex = slowIndex;
        while (fastIndex <= right) {
            if (nums[fastIndex] < temp) {
                if (fastIndex != slowIndex) {
                    swap(nums, fastIndex, slowIndex);
                }
                slowIndex++;
            }
            fastIndex++;
        }

        int pointIndex = slowIndex - 1;
        AlgorithmUtils.swap(nums, left, pointIndex);
        return pointIndex;
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
