package com.algorithm.divide;

import com.algorithm.model.ListNode;
import com.algorithm.util.AlgorithmUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 分治思想
 *
 * @author zongchao
 */
public class DivideSolution {

    /**
     * 递归排序
     *
     * @param nums
     */
    public void mergeSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        mergeSort(nums, 0, nums.length);
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) >> 1;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int i = 0;
        while (p1 <= mid && p2 <= right) {
            help[i++] = nums[p1] <= nums[p2] ? nums[p1++] : nums[p2++];
        }
        while (p1 <= mid) {
            help[i++] = nums[p1++];
        }
        while (p2 <= right) {
            help[i++] = nums[p2++];
        }
        for (i = 0; i < help.length; i++) {
            nums[left + i] = help[i];
        }
    }

    /**
     * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "aaabb", k = 3
     * 输出：3
     * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
     * 示例 2：
     * <p>
     * 输入：s = "ababbc", k = 2
     * 输出：5
     * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        if (k <= 0) {
            return s.length();
        }
        char[] chars = s.toCharArray();
        return longestSubstring(chars, 0, chars.length - 1, k);
    }

    private int longestSubstring(char[] chars, int left, int right, int k) {
        int length = right - left + 1;
        if (length < k) {
            return 0;
        }
        int initSize = (int) (length / 0.75) + 1;
        Map<Character, Integer> map = new HashMap<>(initSize);
        for (int i = left; i <= right; i++) {
            Integer size = map.get(chars[i]);
            size = size != null ? size + 1 : 1;
            map.put(chars[i], size);
        }
        for (int i = left; i <= right; i++) {
            Integer size = map.get(chars[i]);
            // 当前字符所在位置出现的次数不满足k的要求，，则目标子串一定在左侧，或者右侧
            if (size < k) {
                int res = 0;
                res = Math.max(res, longestSubstring(chars, left, i - 1, k));
                res = Math.max(res, longestSubstring(chars, i + 1, right, k));
                return res;
            }
        }
        // 当前子串中字符的个数均>=k
        return length;
    }


    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode smallListHead = new ListNode(0);
        ListNode smallListTail = smallListHead;
        ListNode largerListHead = new ListNode(0);
        ListNode largerListTail = largerListHead;
        while (head != null) {
            ListNode temp=head.next;
            if (head.val < x) {
                smallListTail.next = head;
                smallListTail=smallListTail.next;
                smallListTail.next=null;
            } else {
                largerListTail.next = head;
                largerListTail=largerListTail.next;
                largerListTail.next=null;
            }
            head = temp;
        }
        smallListTail.next = largerListHead.next;
        return smallListHead.next;
    }

    public static void main(String[] args) {
        DivideSolution divideSolution = new DivideSolution();
        int[] nums = {1};
        ListNode listNode = divideSolution.partition(AlgorithmUtils.buildListNode(nums), 0);
        System.out.println(listNode.val);
    }
}
