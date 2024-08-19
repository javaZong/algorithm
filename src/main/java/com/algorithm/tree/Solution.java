package com.algorithm.tree;

import com.algorithm.model.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by java_zong on 2019/5/17.
 */
public class Solution {

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

    /**
     * 二叉树的最近公共祖先
     *
     * @param root
     * @param p    节点1
     * @param q    节点2
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            // 遇到目标节点，直接返回即可
            return root;
        }
        // 可能是p节点，可能是q节点，也可能是任意一节点的父节点
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        // 都不为空，说明已经找到了p节点和q节点，遇到一个最近的公共祖先了
        if (leftNode != null && rightNode != null) {
            return root;
        }
        return leftNode != null ? leftNode : rightNode;
    }

    /**
     * 437. 路径总和 III
     * https://leetcode.cn/problems/path-sum-iii/description/?envType=study-plan-v2&envId=top-100-liked
     * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
     * <p>
     * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        Map<Long, Integer> map = new HashMap();
        map.put(0L, 1);
        pathNumWithPreSum(root, 0L, map, targetSum);
        return pathSum;
    }

    private int pathSum;

    private void pathNumWithPreSum(TreeNode node, long preSum, Map<Long, Integer> map, int targetSum) {
        if (node == null) {
            return;
        }
        preSum = preSum + node.val;
        pathSum = pathSum + map.getOrDefault(preSum - targetSum, 0);
        int nodeSum = map.getOrDefault(preSum, 0) + 1;
        map.put(preSum, nodeSum);
        pathNumWithPreSum(node.left, preSum, map, targetSum);
        pathNumWithPreSum(node.right, preSum, map, targetSum);
        map.put(preSum, nodeSum - 1);
    }
}
