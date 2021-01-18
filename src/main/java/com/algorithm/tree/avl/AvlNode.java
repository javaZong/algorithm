package com.algorithm.tree.avl;

/**
 * AVL-平衡二叉树
 */
public class AvlNode<T> {

   private T element;
   private AvlNode<T> left;
   private AvlNode<T> right;
   private int height;

    public AvlNode(T element) {
       this(element,null,null);
    }

    public AvlNode(T element, AvlNode<T> left, AvlNode<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.height=0;
    }


    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public AvlNode<T> getLeft() {
        return left;
    }

    public void setLeft(AvlNode<T> left) {
        this.left = left;
    }

    public AvlNode<T> getRight() {
        return right;
    }

    public void setRight(AvlNode<T> right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
