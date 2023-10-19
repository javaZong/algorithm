package com.algorithm.lru;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private Map<Integer, DLinkedNode> map;
    private int capacity;
    DLinkedNode headNode;
    DLinkedNode tailNode;

    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        DLinkedNode node = map.get(key);
        if (node == null) {
            return -1;
        }
        if (node == tailNode) {
            return node.value;
        }
        adjustTailNode(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = map.get(key);
        if (node == null) {
            node = new DLinkedNode(key, value, null, null);
            map.put(key, node);
            if (headNode == null) {
                headNode = node;
                tailNode = node;
                return;
            }
            if (headNode == tailNode) {
                headNode.next = node;
                node.pre = headNode;
            } else {
                node.pre = tailNode;
                tailNode.next = node;
            }
            tailNode = node;
            if (map.size() > capacity) {
                map.remove(headNode.key);
                headNode = headNode.next;
                headNode.pre = null;
            }
            return;
        }
        node.value = value;
        adjustTailNode(node);
    }

    private void adjustTailNode(DLinkedNode node) {
        if (node == tailNode) {
            return;
        }
        if (node == headNode) {
            headNode = node.next;
            headNode.pre = null;
        } else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        tailNode.next = node;
        node.pre = tailNode;
        tailNode = node;
        tailNode.next = null;
    }

    class DLinkedNode {
        int key;
        int value;

        DLinkedNode pre;
        DLinkedNode next;

        public DLinkedNode(int key, int value, DLinkedNode pre, DLinkedNode next) {
            this.key = key;
            this.value = value;
            this.pre = pre;
            this.next = next;
        }
    }


    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.get(1);
        lruCache.put(3, 3);
        lruCache.put(4, 4);
        lruCache.get(1);
        lruCache.get(3);
        lruCache.get(4);
    }
}
