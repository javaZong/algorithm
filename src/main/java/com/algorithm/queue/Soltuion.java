package com.algorithm.queue;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 两个栈实现队列
 * Created by java_zong on 2019/5/18.
 */
public class Soltuion {

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {

        while (!stack2.empty()) {
            stack1.push(stack2.pop());
        }
        stack1.push(node);
    }

    public int pop() throws Exception {
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        if (stack2.empty()) {
            throw new EmptyStackException();
        }
        return stack2.pop();

    }
}
