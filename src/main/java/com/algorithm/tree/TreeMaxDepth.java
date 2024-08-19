package com.algorithm.tree;

import com.algorithm.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 计算二叉树最大深度
 */
public class TreeMaxDepth {
    public int maxDepthByForeach(TreeNode root) {
        int max = 0;
        if (root == null) {
            return max;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            while (levelSize > 0) {
                TreeNode node = queue.poll();
                levelSize--;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            max++;
        }
        return max;
    }
}
