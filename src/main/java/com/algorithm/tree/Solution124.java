package com.algorithm.tree;

import com.algorithm.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/
 * Created by java_zong on 2019/5/17.
 */
public class Solution124 {

    public static void main(String[] args) {
        int[] minArray = new int[1];
        System.out.println(minArray.length);
        TreeNode root = new TreeNode(0);

    }

    int maxSum = -1001;

    public int maxPathSum(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int leftMaxSum = maxPathSum(root.left);
        int rightMaxSum = maxPathSum(root.right);

        int max = Math.max(leftMaxSum, rightMaxSum);
        maxSum = Math.max(max, maxSum);
        max = max + root.val;
        maxSum = Math.max(max, maxSum);
        return max;
    }
}
