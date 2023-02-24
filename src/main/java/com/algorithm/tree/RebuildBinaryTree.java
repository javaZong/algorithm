package com.algorithm.tree;

/**
 * Created by java_zong on 2019/5/17.
 */
public class RebuildBinaryTree {
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
        return reConstructBinaryTreeByRecursive(pre, 0,
                in, 0, in.length);
    }


    /**
     * 递归实现
     *
     * @param pre        前序遍历数组
     * @param preStart   前序遍历数组起始
     * @param in
     * @param inStart
     * @param treeLength 当前树节点个数
     * @return
     */
    private static TreeNode reConstructBinaryTreeByRecursive(int[] pre, int preStart,
                                                             int[] in, int inStart,
                                                             int treeLength) {
        if (treeLength < 1) {
            return null;
        }
        TreeNode subRootNode = new TreeNode(pre[preStart]);
        if (treeLength == 1) {

            return subRootNode;
        }
        int leftTreeLength = 0;
        int rightTreeLength = 0;

        int newInStart = inStart;
        for (int i = 0; i < treeLength; i++) {
            if (in[newInStart] == pre[preStart]) {
                leftTreeLength = newInStart - inStart;
                rightTreeLength = treeLength - leftTreeLength - 1;
                break;
            }
            newInStart++;
        }

        int preLeftTreeStart = preStart + 1;

        subRootNode.left = reConstructBinaryTreeByRecursive(pre, preLeftTreeStart,
                in, inStart, leftTreeLength);

        subRootNode.right = reConstructBinaryTreeByRecursive(pre, preLeftTreeStart + leftTreeLength,
                in, newInStart + 1, rightTreeLength);
        return subRootNode;
    }

    public TreeNode buildTreeByPreAndIn(int[] preorder, int preStart,
                                        int[] inorder, int inStart, int nodeSize) {
        if (nodeSize == 0 || preStart >= preorder.length || inStart >= preorder.length) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        int leftSize = 0;
        int rightSize = 0;
        int end = nodeSize + inStart - 1;
        int i = inStart;
        for (; i <= end; i++) {
            if (preorder[preStart] == inorder[i]) {
                leftSize = i - inStart;
                rightSize = nodeSize - leftSize - 1;
                break;
            }
        }
        root.left = buildTreeByPreAndIn(preorder, preStart + 1, inorder, inStart, leftSize);
        root.right = buildTreeByPreAndIn(preorder, preStart + leftSize + 1, inorder, i + 1, rightSize);
        return root;

    }

    public  TreeNode buildTreeByInAndPost(int[] inorder, int inStart,
                                          int[] postorder, int postEnd,
                                          int nodeSize) {
        if (nodeSize == 0 || inStart >= inorder.length || postEnd <0) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postEnd]);
        int leftSize = 0;
        int rightSize = 0;
        int end = nodeSize + inStart - 1;
        int i = inStart;
        for (; i <= end; i++) {
            if (root.val == inorder[i]) {
                leftSize = i - inStart;
                rightSize = nodeSize - leftSize - 1;
                break;
            }
        }
        root.left = buildTreeByInAndPost(inorder, inStart, postorder, postEnd-rightSize-1, leftSize);
        root.right = buildTreeByInAndPost(inorder, inStart + leftSize + 1, postorder, postEnd - 1, rightSize);
        return root;
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode treeNode = reConstructBinaryTree(pre, in);
        System.out.println(treeNode);
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
}
