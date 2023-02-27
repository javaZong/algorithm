package com.algorithm.tree;

import com.algorithm.model.TreeNode;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化与反序列化
 * https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/description/
 * @author zongchao
 */
public class TreeCodeSerialize implements Serializable {
    /**
     * 通过前序遍历的方式进行序列化
     * @param root
     * @return
     */
    public String serializeByPre(TreeNode root) {
        if(root==null){
            // 代表当前树的结束
            return "#_";
        }
        String str=root.val+"_";
        str+=serializeByPre(root.left);
        str+=serializeByPre(root.right);
        return str.toString();
    }


    /**
     * 通过前序遍历的方式进行反序列化
     * @param data
     * @return
     */
    public TreeNode deserializeByPre(String data) {
        if(data==null||data.length()<1){
            return null;
        }
        String[] strs=data.split("_");
        Queue<String> queue = new LinkedList<>(Arrays.asList(strs));
        return deserializeByPreRecursion(queue);
    }

    private TreeNode deserializeByPreRecursion(Queue<String> queue){
        if(queue.isEmpty()){
            return null;
        }
        String str=queue.poll();
        if(str.equals("#")){
            return null;
        }
        TreeNode node=new TreeNode(Integer.parseInt(str));
        node.left=deserializeByPreRecursion(queue);
        node.right=deserializeByPreRecursion(queue);
        return node;
    }
}
