package com.algorithm.tree;

import javax.swing.plaf.basic.BasicArrowButton;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 遍历二叉树
 * Created by java_zong on 2019/5/17.
 */
public class ForeachBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        TreeNode rootNode = buildBinaryTree(array);

        TreeNode rootNodeRecursion = buildBinaryTreeRecursion(array);

        preRecursion(array);

        preForeach(array);
    }

    /**
     * 循环建立二叉树
     *
     * @param array
     * @return
     */
    private static TreeNode buildBinaryTree(int array[]) {

        if (array == null || array.length < 1) {
            return null;
        }
        int index = 0;
        int maxIndex = array.length - 1;
        TreeNode root = new TreeNode(array[0]);
        QueueNode rootQueueNode = new QueueNode(root, index);
        LinkedList<QueueNode> queue = new LinkedList();
        queue.add(rootQueueNode);

        while ((rootQueueNode = queue.poll()) != null) {
            index = rootQueueNode.index;
            TreeNode parentNode = rootQueueNode.root;
            int leftIndex = index * 2 + 1;

            if (leftIndex > maxIndex) {
                break;
            }
            TreeNode leftChild = new TreeNode(array[leftIndex]);
            parentNode.left = leftChild;
            QueueNode leftQueueNode = new QueueNode(leftChild, leftIndex);
            queue.add(leftQueueNode);

            int rightIndex = leftIndex + 1;
            if (rightIndex > maxIndex) {
                break;
            }
            TreeNode rightChild = new TreeNode(array[rightIndex]);
            parentNode.right = rightChild;
            QueueNode rightQueueNode = new QueueNode(rightChild, rightIndex);
            queue.add(rightQueueNode);
        }
        return root;
    }

    /**
     * 递归建立二叉树
     *
     * @param array
     * @return
     */
    private static TreeNode buildBinaryTreeRecursion(int array[]) {

        if (array == null || array.length < 1) {
            return null;
        }
        return buildBinaryTreeRecursion(0, array);
    }

    private static TreeNode buildBinaryTreeRecursion(int index, int array[]) {

        if (index > array.length - 1) {
            return null;
        }
        TreeNode node = new TreeNode(array[index]);
        int leftIndex = index * 2 + 1;
        node.left = buildBinaryTreeRecursion(leftIndex, array);
        int rightIndex = leftIndex + 1;
        node.right = buildBinaryTreeRecursion(rightIndex, array);
        return node;
    }

    static class QueueNode {
        TreeNode root;
        int index;

        QueueNode(TreeNode node, int index) {
            this.root = node;
            this.index = index;
        }
    }

    /**
     * 前序遍历-递归
     *
     * @param array
     */
    private static void preRecursion(int[] array) {
        if (array == null || array.length < 1) {
            return;
        }
        preRecursion(0, array);
    }

    private static void preRecursion(int index, int[] array) {
        if (index > array.length - 1) {
            return;
        }
        int value = array[index];
        System.out.println(value);
        int leftIndex = index * 2 + 1;
        preRecursion(leftIndex, array);
        preRecursion(leftIndex + 1, array);
    }

    /**
     * 前序遍历-循环
     * 可以递归解决的都可以利用栈的数据结构来写成循环
     * 先进后出
     *
     * @param array
     */
    private static void preForeach(int[] array) {
        if (array == null || array.length < 1) {
            return;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        Integer index = 0;
        int maxLength = array.length - 1;
        int leftIndex;
        int rightIndex;
        while (!stack.isEmpty()) {
            index = stack.pop();
            System.out.println("preForeach-" + array[index]);
            leftIndex = index * 2 + 1;
            rightIndex = leftIndex + 1;
            if (rightIndex <= maxLength) {
                stack.push(rightIndex);
            }
            if (leftIndex <= maxLength) {
                stack.push(leftIndex);
            }


        }
    }

    /**
     * 层序遍历-循环
     *
     * @param array
     */
    private static void floorForeach(int[] array) {
        if (array == null || array.length < 1) {
            return;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        Integer index = 0;
        int maxLength = array.length - 1;
        int leftIndex;
        while ((index = queue.poll()) != null && index < maxLength) {
            System.out.println("floorForeach-" + array[index]);
            leftIndex = index * 2 + 1;
            queue.add(leftIndex);
            queue.add(leftIndex + 1);
        }
    }
}
