package com.algorithm.dichotomy;

import java.util.HashMap;
import java.util.Map;

public class DichotomySolution {
    /**
     * 寻找升序数组中最左侧大于等于target的位置
     *
     * @param array
     * @param target
     * @return
     */
    public int findLeftmostIndex(int[] array, int target) {
        if (array == null || array.length < 1) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        int targetIndex = -1;
        while (left <= right) {
            int center = left + (right - left) / 2;
            if (array[center] < target) {
                left = center + 1;
            } else {
                // 标记下符合要求的位置
                targetIndex = center;
                right = center - 1;
            }

        }

        return targetIndex;
    }

    /**
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     * <p>
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
     * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     * <p>
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 0~mid之间是升序的
            if (nums[0] <= nums[mid]) {
                // target在0~mid中间
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
                continue;
            }
            // 0~mid中间是无序的，mid~最右侧是升序的 如果target在中间位置
            if (nums[mid] < target && target <= nums[n - 1]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
     * 例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
     * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
     * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     * <p>
     * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
     * <p>
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int min = nums[0];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < min) {
                min = nums[mid];
            }
            if (nums[mid] < nums[right]) {
                // mid~right升序，最小值一定在mid左侧
                right = mid - 1;
            } else {
                // mid~right 无序，最小值一定在mid~right中间
                left = mid + 1;
            }
        }
        return min;
    }

    /**
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。
     * 请你找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int[] targetArray = {-1, -1};
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }else {
                // 最左侧
                right=mid+1;

            }
        }
        return targetArray;
    }



    public static void main(String[] args) {
        DichotomySolution dichotomySolution = new DichotomySolution();
        int[] array = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(dichotomySolution.findMin(array));
        Map map=new HashMap();

    }
}
