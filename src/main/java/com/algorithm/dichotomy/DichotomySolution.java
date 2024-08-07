package com.algorithm.dichotomy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

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
            int center = (right + left) / 2;
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
        int[] ranges = {-1, -1};
        process(target, nums, 0, nums.length - 1, ranges);
        return ranges;
    }

    private void process(int target, int[] nums, int left, int right, int[] ranges) {
        if (left > right) {
            return;
        }
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int midNum = nums[mid];
            if (midNum == target) {
                if (mid < ranges[0] || ranges[0] == -1) {
                    ranges[0] = mid;
                    process(target, nums, left, mid - 1, ranges);
                }
                if (mid > ranges[1]) {
                    ranges[1] = mid;
                    process(target, nums, mid + 1, right, ranges);
                }

                return;
            }
            if (midNum > target) {
                process(target, nums, left, mid - 1, ranges);
                return;
            }
            process(target, nums, mid + 1, right, ranges);
            return;
        }

    }

    public int[] searchRangeByForeach(int[] nums, int target) {
        int[] array = {-1, -1};
        Queue<int[]> queue = new LinkedList();
        int[] temp = {0, nums.length - 1};
        queue.add(temp);
        while (!queue.isEmpty()) {
            temp = queue.poll();
            int start = temp[0];
            int end = temp[1];
            if (start > end) {
                continue;
            }
            int mid = start + ((end - start) >> 1);
            if (nums[mid] == target) {
                if (mid < array[0] || array[0] == -1) {
                    array[0] = mid;
                }
                if (mid > array[1]) {
                    array[1] = mid;
                }
                temp[1] = mid - 1;
                queue.add(temp);
                temp = new int[2];
                temp[0] = mid + 1;
                temp[1] = end;
                queue.add(temp);

            } else if (nums[mid] > target) {
                temp[1] = mid - 1;
                queue.add(temp);
            } else {
                temp[0] = mid + 1;
                queue.add(temp);
            }
        }
        return array;
    }


    public static void main(String[] args) {
        DichotomySolution dichotomySolution = new DichotomySolution();
        int[] array = {1, 4, 7, 11, 15};
        System.out.println(dichotomySolution.binarySearch(array, 5));
        Map map = new HashMap();

    }

    public static int binarySearch(int[] nums, int target) {
        // 初始化双闭区间 [0, n-1] ，即 i, j 分别指向数组首元素、尾元素
        int i = 0, j = nums.length - 1;
        // 循环，当搜索区间为空时跳出（当 i > j 时为空）
        while (i <= j) {
            int m = i + (j - i) / 2; // 计算中点索引 m
            if (nums[m] < target) // 此情况说明 target 在区间 [m+1, j] 中
                i = m + 1;
            else if (nums[m] > target) // 此情况说明 target 在区间 [i, m-1] 中
                j = m - 1;
            else // 找到目标元素，返回其索引
                return m;
        }
        // 未找到目标元素，返回 -1
        return -1;
    }

}
