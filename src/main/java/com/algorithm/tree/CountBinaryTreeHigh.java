package com.algorithm.tree;

import com.algorithm.model.TreeNode;

import java.util.*;

/**
 * 二叉树深度
 * Created by java_zong on 2019/5/17.
 */
public class CountBinaryTreeHigh {

    /**
     * 层次遍历-树的最大深度
     *
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int curLevelSize = 0;
        int curDepth = 0;
        while (!queue.isEmpty()) {
            curLevelSize = queue.size();
            while (curLevelSize > 0) {
                root = queue.poll();
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
                curLevelSize--;
            }
            // 当curLevelSize=0时，意味着要进入下一层了
            curDepth++;
        }
        return curDepth;
    }

    /**
     * 前序遍历-树的最大深度
     *
     * @param root
     * @return
     */
    public static int maxDepthByPreForeach(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        // 记录当前节点所在的层数
        Map<TreeNode, Integer> map = new HashMap<>();
        stack.push(root);
        map.put(root, 1);
        int maxDepth = 0;
        while (!stack.empty()) {
            root = stack.pop();
            Integer curDepth = map.get(root);
            maxDepth = Math.max(maxDepth, curDepth);
            if (root.right != null) {
                stack.push(root.right);
                map.put(root.right, curDepth + 1);
            }
            if (root.left != null) {
                stack.push(root.left);
                map.put(root.left, curDepth + 1);
            }
        }
        return maxDepth;
    }


}
