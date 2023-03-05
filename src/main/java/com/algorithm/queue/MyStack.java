package com.algorithm.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 两个队列实现一个栈
 */
public class MyStack {
    Queue<Integer> popQueue;
    Queue<Integer> pushQueue;
    public MyStack(){
        popQueue=new LinkedList<>();
        pushQueue=new LinkedList<>();
    }

    public void push(int val){
        pushQueue.offer(val);
        while(!popQueue.isEmpty()){
            pushQueue.offer(popQueue.poll());
        }
        // 交换队列是为了保证，popQueue队列里的顶点是最新进入的。
        Queue<Integer> temp=popQueue;
        popQueue=pushQueue;
        pushQueue=temp;
    }

    public int pop(){
        return popQueue.poll();
    }
}
