package com.algorithm.tree;

import com.algorithm.util.ArrayUtils;

import javax.swing.plaf.basic.BasicArrowButton;
import java.util.*;

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

        midRecursion(array);

        midForeachLoop(array);

        lateRecursion(rootNode);
    }

    /**
     * 后序遍历二叉树
     *
     * @param rootNode
     */
    private static void lateRecursion(TreeNode rootNode) {
        if (rootNode.left == null) {
            return;
        }
        lateRecursion(rootNode.left);
        lateRecursion(rootNode.right);
        System.out.println("lateRecursion:" + rootNode.val);
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
     * 根节点-》遍历左子树节点—》遍历右子树节点
     * 1,2,4,8,5,3,6,7
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
     * 可以递归解决的都可以利用栈或队列的数据结构来写成循环
     * 先进后出 则使用栈
     * 先进先出 使用队列
     * 1、右子树节点先较与下一代左子树节点，先进后出则使用栈来完成循环
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
     * 中序遍历：
     * 先左子树节点，然后父节点，然后右子树节点
     *
     * @param array
     */
    public static void midRecursion(int[] array) {
        if (array == null || array.length < 1) {
            return;
        }
        midRecursion(0, array);
    }

    private static void midRecursion(int index, int[] array) {
        if (index > array.length - 1) {
            return;
        }
        int leftIndex = index * 2 + 1;
        midRecursion(leftIndex, array);
        // 不管何种遍历顺序，本质上是看节点的打印位置，
        // 是当前节点，还是下一代节点，前序遍历是打印当前节点，中序和后续遍历是打印下一代节点，也就是递归后的节点
        int nodeValue = array[index];
        System.out.println("midRecursion=" + nodeValue);
        int rightIndex = leftIndex + 1;
        midRecursion(rightIndex, array);
    }

    /**
     * 中序遍历-循环
     * 8,4,2,1,5,1,6,3,7
     * 左子树节点-父节点-右子树节点
     * 父节点先进后出
     * <p>
     * 栈：
     * peek() 查看堆顶元素但不移除
     * pop() 查看堆顶元素返回并移除
     * push() 压入堆顶
     * 需要知道之前已被访问的节点
     *
     * @param array
     */
    public static void midForeachLoop(int[] array) {
        int maxIndex;
        if (array == null || (maxIndex = array.length - 1) < 0) {
            return;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        Integer curIndex = 0;
        while (!stack.isEmpty() || curIndex <= maxIndex) {
            while (curIndex <= maxIndex) {
                // 存在左子树节点，则压入栈
                stack.push(curIndex);
                curIndex = curIndex * 2 + 1;
            }
            // 不存在，则代表左子树已遍历完,开始遍历右子树节点
            curIndex = stack.pop();
            System.out.println("midForeachLoop=" + array[curIndex]);
            curIndex = curIndex * 2 + 2;
        }
    }


    /**
     * 层序遍历-循环
     * 　add        增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
     * 　　remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
     * 　　element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
     * 　　offer       添加一个元素并返回true       如果队列已满，则返回false
     * 　　poll         移除并返问队列头部的元素    如果队列为空，则返回null
     * 　　peek       返回队列头部的元素             如果队列为空，则返回null
     * 　　put         添加一个元素                      如果队列满，则阻塞
     * 　　take        移除并返回队列头部的元素     如果队列为空，则阻塞
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

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return false;
        }
        Stack<TreeNode> stack = new Stack();
        Integer preVal = null;
        while (!stack.empty() || preVal == null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (preVal == null || preVal < root.val) {
                preVal = root.val;
            } else {
                return false;
            }
            root = root.right;
        }
        return true;
    }
}
