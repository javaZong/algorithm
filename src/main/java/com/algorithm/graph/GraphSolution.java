package com.algorithm.graph;

public class GraphSolution {

    /**
     * 构造有向图
     *
     * @param array
     * @return
     */
    public static Graph buildGraph(int[][] array) {
        if (array == null || array.length < 1) {
            return null;
        }
        Graph graph = new Graph();
        for (int i = 0; i < array.length; i++) {
            Integer from = array[i][0];
            Integer to = array[i][1];
            Integer weight = array[i][2];
            GraphNode fromNode = graph.getNodeMap().get(from);
            if (fromNode == null) {
                fromNode = new GraphNode(from);
                graph.getNodeMap().put(from, fromNode);
            }
            GraphNode toNode = graph.getNodeMap().get(to);
            if (toNode == null) {
                toNode = new GraphNode(to);
                graph.getNodeMap().put(to, toNode);
            }
            GraphSide side = new GraphSide();
            side.setFrom(fromNode);
            side.setTo(toNode);
            side.setWeight(weight);
            fromNode.getSideList().add(side);
            fromNode.getNextList().add(toNode);
            fromNode.setOut(fromNode.getOut() + 1);
            toNode.setIn(toNode.getIn() + 1);
            graph.getSideSet().add(side);

        }
        return graph;
    }
}
