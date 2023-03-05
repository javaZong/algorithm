package com.algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 有向图是否有环（拓扑排序）
     *
     * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
     *
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
     *
     * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     *
     *https://leetcode.cn/problems/course-schedule/description/?favorite=2cktkvj
     */
    boolean valid=true;
    Map<Integer, List<Integer>> map=new HashMap();
    int[] visited=null;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        visited=new int[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            map.put(i,new ArrayList());
        }
        for(int i=0;i<prerequisites.length;i++){
            int[] temp=prerequisites[i];
            List<Integer> list=map.get(temp[0]);
            list.add(temp[1]);
        }

        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    public void dfs(int i){
        visited[i]=1;
        List<Integer> toList=map.get(i);

        for(int v:toList){
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[i] = 2;
    }
}
