package com.algorithm.list;

import java.util.ArrayList;

/**
 * Created by java_zong on 2019/5/16.
 */
public class Solution {
   static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

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
        printListFromTailToHeadExcurive(listNode, arrayList);
        return arrayList;

    }

    private void printListFromTailToHeadExcurive(ListNode listNode, ArrayList<Integer> arrayList) {
        if (listNode == null) {
            return;
        }
        printListFromTailToHeadExcurive(listNode.next, arrayList);
        arrayList.add(listNode.val);
    }

    /**
     * 反转链表
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

    public static void main(String[] args) {
        ListNode node=new ListNode(1);
        ListNode secNode=new ListNode(2);
        node.next=secNode;
        ListNode thirdNode=new ListNode(3);
        secNode.next=thirdNode;
       ListNode targetNode= reversionList(node);

        System.out.println("a");

    }
}


