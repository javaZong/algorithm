package com.algorithm.tree;

import com.algorithm.model.TreeNode;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化与反序列化
 * https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/description/
 *
 * @author zongchao
 */
public class TreeCodeSerialize implements Serializable {
    /**
     * 通过前序遍历的方式进行序列化
     *
     * @param root
     * @return
     */
    public String serializeByPre(TreeNode root) {
        if (root == null) {
            // 代表当前树的结束
            return "#_";
        }
        String str = root.val + "_";
        str += serializeByPre(root.left);
        str += serializeByPre(root.right);
        return str.toString();
    }


    /**
     * 通过前序遍历的方式进行反序列化
     *
     * @param data
     * @return
     */
    public TreeNode deserializeByPre(String data) {
        if (data == null || data.length() < 1) {
            return null;
        }
        String[] strs = data.split("_");
        Queue<String> queue = new LinkedList<>(Arrays.asList(strs));
        return deserializeByPreRecursion(queue);
    }

    private TreeNode deserializeByPreRecursion(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String str = queue.poll();
        if ("#".equals(str)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(str));
        node.left = deserializeByPreRecursion(queue);
        node.right = deserializeByPreRecursion(queue);
        return node;
    }
    private static final TreeNode defaultNode=new TreeNode(1);
    /**
     * 通过层次遍历的方式进行序列化
     *
     * @param root
     * @return
     */
    public String serializeByLevel(TreeNode root) {
        if (root == null) {
            // 代表当前树的结束
            return "";
        }
        TreeNode node = root;
        StringBuilder str = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            node = queue.poll();
            if(node==defaultNode){
                str.append("#_");
                continue;
            }

            str.append(node.val).append("_");
            if (node.left != null) {
                queue.add(node.left);
            } else {
                queue.add(defaultNode);
            }
            if (node.right != null) {
                queue.add(node.right);
            } else {
                queue.add(defaultNode);
            }
        }
        return str.toString();
    }

    /**
     * 通过层次遍历的方式进行反序列化
     *
     * @param data
     * @return
     */
    public TreeNode deserializeByLevel(String data) {
        if (data == null || data.length() < 1) {
            return null;
        }
        String[] strs = data.split("_");
        TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
        TreeNode node = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        for (int i = 1; i < strs.length;) {
            node = queue.poll();
            if(node==null){
                break;
            }
            String leftStr = strs[i];
            if (!"#".equals(leftStr)) {
                TreeNode leftNode = new TreeNode(Integer.parseInt(leftStr));
                node.left = leftNode;
                queue.add(leftNode);
            }
            ++i;
            if (i>=strs.length){
                break;
            }
            String rightStr = strs[i];
            if (!"#".equals(rightStr)) {
                TreeNode rightNode = new TreeNode(Integer.parseInt(rightStr));
                node.right = rightNode;
                queue.add(rightNode);
            }
            i++;
        }
        return root;
    }
}
