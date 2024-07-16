package com.algorithm.list;

import com.algorithm.model.ListNode;

import javax.print.DocFlavor;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并链表
 *
 * @author zongchao
 */
public class MergeList {

    /**
     * 合并两个有序链表
     *
     * @param node1
     * @param node2
     * @return
     */
    public ListNode mergeList(ListNode node1, ListNode node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        ListNode newHeadNode = new ListNode(0);
        ListNode preNode = newHeadNode;
        while (node1 != null && node2 != null) {
            if (node1.val > node2.val) {
                preNode.next = node2;
                preNode = node2;
                node2 = node2.next;
            } else {
                preNode.next = node1;
                preNode = node1;
                node1 = node1.next;
            }
        }
        preNode.next = node1 != null ? node1 : node2;
        return newHeadNode.next;
    }

    public ListNode mergeKListsByForeach(ListNode[] lists) {
        ListNode targetList = null;
        for (ListNode list : lists) {
            targetList = mergeList(targetList, list);
        }
        return targetList;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for(ListNode node:lists){
            if(node==null){
                continue;
            }
            queue.offer(node);
        }
        ListNode newHead=new ListNode(0);
        ListNode preNode=newHead;
        while (!queue.isEmpty()){
            ListNode node=queue.poll();
            preNode.next=node;
            preNode=preNode.next;
            if(node.next!=null){
                queue.offer(node.next);
            }
        }
        return newHead.next;
    }
}
