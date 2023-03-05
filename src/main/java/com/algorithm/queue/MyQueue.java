package com.algorithm.queue;

import java.util.Stack;

/**
 * 两个栈实现队列
 * Created by java_zong on 2019/5/18.
 */
public class MyQueue {

    /**
     * 用来push
     */
    Stack<Integer> stack1 = new Stack<>();
    /**
     * 用来pop
     */
    Stack<Integer> stack2 = new Stack<>();

    /**
     * stack1用来push
     * 不用判断stack2中是否还有元素，因为stack2用来出，存放顺序一定是符合先进先出的
     * @param node
     */
    public void push(int node) {
        stack1.push(node);
    }

    public int pop() throws Exception {
        if (!stack2.empty()) {
            return stack2.pop();
        }
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();

    }
}
