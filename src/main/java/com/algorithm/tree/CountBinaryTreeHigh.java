package com.algorithm.tree;

/**
 * 二叉树深度
 * Created by java_zong on 2019/5/17.
 */
public class CountBinaryTreeHigh {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args) {
        int[] minArray=new int[1];
        System.out.println(minArray.length);
        TreeNode root=new TreeNode(0);
        System.out.println(run(root));
    }

    public static int run(TreeNode root) {
        if(root==null){
            return 0;
        }
        int[] minArray=new int[1];

        pre(root,minArray,0);
        return minArray[0];
    }

    private static void pre(TreeNode node,int[] array,int reExcursiveTimes){
        if(node==null){
            if(array.length==0){
                array[0]=reExcursiveTimes;
            }else if(array[0]>reExcursiveTimes){
                array[0]=reExcursiveTimes;
            }
            return;
        }
        reExcursiveTimes++;

        pre(node.left,array,reExcursiveTimes);
        pre(node.right,array,reExcursiveTimes);
    }
}
