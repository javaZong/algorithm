package com.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by java_zong on 2019/5/17.
 */
public class Solution {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || (pre.length != in.length)) {
            return null;
        }
        return reConstructBinaryTree(pre, 0, pre.length - 1,
                in, 0, in.length - 1);
    }

    /**
     * 递归实现
     *
     * @param pre      前序遍历数组
     * @param preStart 前序遍历数组起始
     * @param preEnd
     * @param in
     * @param inStart
     * @param inEnd
     * @return
     */
    public static TreeNode reConstructBinaryTree(int[] pre, int preStart, int preEnd,
                                                 int[] in, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode node = new TreeNode(pre[preStart]);
        if (preStart == preEnd && inStart == inEnd) {
            return node;
        }

        int leftTreeSize = 0, rightTreeSize = 0;
        int i;
        for (i = inStart; i < inEnd + 1; i++) {
            if (in[i] == pre[preStart]) {
                leftTreeSize = i - inStart;
                rightTreeSize = inEnd - i;
                break;
            }
        }

        node.left = reConstructBinaryTree(pre, preStart + 1, preStart + leftTreeSize,
                in, inStart, inStart + leftTreeSize - 1);

        node.right = reConstructBinaryTree(pre, preEnd - rightTreeSize + 1, preEnd,
                in, inEnd - rightTreeSize + 1, inEnd);


        return node;
    }

//    public static void main(String[] args) {
//        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
//        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
//        TreeNode treeNode = reConstructBinaryTree(pre, in);
//        System.out.println(treeNode);
//    }

    public static void main(String[] args) {
        int[] minArray = new int[1];
        System.out.println(minArray.length);
        TreeNode root = new TreeNode(0);
        System.out.println(run(root));
    }

    public static int run(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] minArray = new int[1];

        pre(root, minArray, 0);
        return minArray[0];
    }

    private static void pre(TreeNode node, int[] array, int reExcursiveTimes) {
        if (node == null) {
            if (array.length == 0) {
                array[0] = reExcursiveTimes;
            } else if (array[0] > reExcursiveTimes) {
                array[0] = reExcursiveTimes;
            }
            return;
        }
        reExcursiveTimes++;

        pre(node.left, array, reExcursiveTimes);
        pre(node.right, array, reExcursiveTimes);
    }

    /**
     * 是否为对称二叉树
     *
     * @param root
     * @return
     */
    public static boolean isSymmetricByExcursion(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricByExcursion(root.left, root.right);
    }

    private static boolean isSymmetricByExcursion(TreeNode left, TreeNode right) {

        if (left == null && right != null) {
            return false;
        }
        if (left != null && right == null) {
            return false;
        }
        if (left == null) {
            return true;
        }
        if (left.val != right.val) {
            return false;
        }
        boolean isSymmetric = isSymmetricByExcursion(left.left, right.right);
        if (!isSymmetric) {
            return isSymmetric;
        }
        isSymmetric = isSymmetricByExcursion(left.right, right.left);
        return isSymmetric;
    }

    /**
     * 循环遍历是否为对称树
     *
     * @param root
     * @return
     */
    public static boolean isSymmetricByForeach(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right != null) {
                return false;
            }
            if (left != null && right == null) {
                return false;
            }
            if (left == null && right == null) {
                continue;
            }
            if (left.val != right.val) {
                return false;
            }
            queue.offer(left.left);
            queue.offer(right.right);

            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }

}
