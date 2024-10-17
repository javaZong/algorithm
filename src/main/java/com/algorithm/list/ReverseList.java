package com.algorithm.list;

import com.algorithm.model.ListNode;

import java.util.ArrayList;

/**
 * Created by java_zong on 2019/5/16.
 */
public class ReverseList {

    /**
     * 按链表值从尾到头的顺序返回一个ArrayList
     *
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode == null) {
            return new ArrayList<>();
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        printListFromTailToHeadRecursion(listNode, arrayList);
        return arrayList;
    }

    private void printListFromTailToHeadRecursion(ListNode listNode, ArrayList<Integer> arrayList) {
        if (listNode == null) {
            return;
        }
        printListFromTailToHeadRecursion(listNode.next, arrayList);
        arrayList.add(listNode.val);
    }

    /**
     * 反转链表-递归
     *
     * @param head
     * @return
     */

    private static ListNode reversionListByRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reversionListByRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }


    /**
     * 反转链表
     * 窗口滑动，但是要提前保留下次要活动的位置，tmpNode
     *
     * @param listNode
     * @return
     */
    public static ListNode reversionList(ListNode listNode) {
        if (listNode == null) {
            return listNode;
        }
        ListNode currentNode = null;
        ListNode nextNode = listNode;
        ListNode tmpNode;
        while (nextNode != null) {
            tmpNode = nextNode.next;
            nextNode.next = currentNode;
            currentNode = nextNode;
            nextNode = tmpNode;
        }
        return currentNode;

    }

    /**
     * 合并两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }

    /**
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     * https://leetcode.cn/problems/swap-nodes-in-pairs/description/
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newRootNode = head.next;
        ListNode preNode = new ListNode(0);
        preNode.next = head;
        ListNode nextNode = null;
        ListNode curNode = head;
        while (curNode != null && curNode.next != null) {
            curNode = preNode.next;
            nextNode = curNode.next.next;
            curNode.next.next = curNode;
            preNode.next = curNode.next;
            curNode.next = nextNode;
            preNode = curNode;
            curNode = curNode.next;

        }
        return newRootNode;
    }

    /**
     * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     * <p>
     * 解题思路：快慢指针
     * 快指针先移动k次位置，则快指针领先慢指针k次位置，当指针移动到链表最后一个节点时
     * 慢指针所在节点在移动后变成最后一个节点，慢指针的下一个节点变成新的首节点
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (k <= 0 || head.next == null) {
            return head;
        }
        ListNode fastNode = head;
        ListNode slowNode = head;
        int i = 0;
        while (i < k) {
            i++;
            fastNode = fastNode.next;
            if (fastNode == null) {
                fastNode = head;
            }
        }
        while (fastNode.next != null) {
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }
        fastNode.next = head;
        ListNode newHead = slowNode.next;
        slowNode.next = null;
        return newHead;
    }

    /**
     * 寻找链表中的中间节点
     *
     * @param head
     * @return
     */
    public static ListNode findMidNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 节点数为偶数时，初始化试，快指针领先慢指针一步
        // 慢指针小标(从1开始)=（length）/2
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast.next != null) {
                fast = fast.next;
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        System.out.println(2000000000 % 3);
        ListNode newNode = findMidNode(buildListNode(4));
        System.out.println(newNode.val);

    }

    private static ListNode buildListNode(int length) {
        ListNode head = new ListNode(0);
        ListNode node = new ListNode(1);
        head.next = node;
        for (int i = 2; i <= length; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        return head.next;
    }
}


