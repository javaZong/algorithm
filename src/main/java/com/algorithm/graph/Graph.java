package com.algorithm.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class Graph {
    /**
     * 节点集合
     */
    private Map<Integer, GraphNode> nodeMap;
    /**
     * 边集
     */
    private HashSet<GraphSide> sideSet;

    public Graph() {
        this.nodeMap = new HashMap<>();
        this.sideSet = new HashSet<>();
    }

    public Map<Integer, GraphNode> getNodeMap() {
        return nodeMap;
    }

    public void setNodeMap(Map<Integer, GraphNode> nodeMap) {
        this.nodeMap = nodeMap;
    }

    public HashSet<GraphSide> getSideSet() {
        return sideSet;
    }

    public void setSideSet(HashSet<GraphSide> sideSet) {
        this.sideSet = sideSet;
    }
}
