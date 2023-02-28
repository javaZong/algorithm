package com.algorithm.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {

    private int value;
    /**
     * 入度
     * 如果是有向图，当前被指针指向的个数
     */
    private int in;
    /**
     * 出度
     * 如果是有向图，代表当前节点出发的指针个数
     */
    private int out;

    /**
     * 相邻节点
     */
    private List<GraphNode> nextList;
    /**
     * 边
     */
    private List<GraphSide> sideList;

    public GraphNode(int value) {
        this.value = value;
        this.in=0;
        this.out=0;
        nextList=new ArrayList<>();
        sideList=new ArrayList<>();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public List<GraphNode> getNextList() {
        return nextList;
    }

    public void setNextList(List<GraphNode> nextList) {
        this.nextList = nextList;
    }

    public List<GraphSide> getSideList() {
        return sideList;
    }

    public void setSideList(List<GraphSide> sideList) {
        this.sideList = sideList;
    }
}
