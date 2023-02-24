package com.algorithm.util;

import com.algorithm.model.ListNode;

import java.util.Arrays;
import java.util.Random;

public class AlgorithmUtils {

    public static int[] buildRandomArray(int length) {
        Random random = new Random();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(length);
        }
        return array;
    }

    public static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static ListNode buildListNode(int length) {
        ListNode head = new ListNode(0);
        ListNode node = new ListNode(1);
        head.next = node;
        for (int i = 2; i <= length; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        return head.next;
    }

    public static ListNode buildListNode(int[] nums) {
        ListNode head = new ListNode(0);
        ListNode node = new ListNode(nums[0]);
        head.next = node;
        for (int i = 1; i < nums.length; i++) {
            node.next = new ListNode(nums[i]);
            node = node.next;
        }
        head = head.next;
        return head;
    }

    public static void main(String[] args) {
        int[] array = buildRandomArray(5);
        System.out.println(Arrays.toString(array));
    }
}
