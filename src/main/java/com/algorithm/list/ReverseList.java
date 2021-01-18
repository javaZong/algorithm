package com.algorithm.list;

import com.algorithm.list.bo.ListNode;

import java.util.ArrayList;
import java.util.List;

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
     * @param node
     * @return
     */

    private static ListNode reversionListByRecursion(ListNode node) {
        if (node == null) {
            return node;
        }
        List<ListNode> rootContainer = new ArrayList<>(1);
        reversionListByRecursion(node, rootContainer);
        return rootContainer.get(0);
    }

    /**
     * 递归，在反转当前节点之前先反转后续节点
     * @param node
     * @param rootContainer
     * @return
     */
    private static ListNode reversionListByRecursion(ListNode node, List<ListNode> rootContainer) {
        if (node.next == null) {
            rootContainer.add(node);
            return node;
        }

        ListNode operateNode=reversionListByRecursion(node.next,rootContainer);
        node.next.next = node;
        node.next=null;
        return operateNode;
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
//       ListNode targetNode= reversionList(node);

        ListNode targetNode1= reversionListByRecursion(node);
        System.out.println("a="+targetNode1.val);

    }
}


