package com.algorithm.loadbalance;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询
 */
public class RoundLoad {
    public static final List<String> LIST = Arrays.asList(
            "192.168.0.1",
            "192.168.0.2",
            "192.168.0.3",
            "192.168.0.4",
            "192.168.0.5"
    );

    /**
     * 完全随机
     */
    private static Integer pos = 0;


    public String round() {
        if (pos >= LIST.size()) {
            pos = 0;
        }
        String ip = LIST.get(pos);
        pos++;

        return ip;
    }



    public static final Map<String, Integer> WEIGHT_LIST = new HashMap<>();

    static {
        WEIGHT_LIST.put("192.168.0.1", 1);
        WEIGHT_LIST.put("192.168.0.2", 8);
        WEIGHT_LIST.put("192.168.0.3", 3);
        WEIGHT_LIST.put("192.168.0.4", 6);
        WEIGHT_LIST.put("192.168.0.5", 5);
    }

    private static final AtomicInteger reqId=new AtomicInteger(0);
    public static String roundWeight() {
        int totalWeight = 0;
        for (Map.Entry<String, Integer> entry : WEIGHT_LIST.entrySet()) {
            totalWeight += entry.getValue();
        }
        int requestId = reqId.getAndIncrement();
        int pos = requestId % totalWeight;
        for (Map.Entry<String, Integer> entry : WEIGHT_LIST.entrySet()) {
            int weight = entry.getValue();
            if (pos <= weight) {
                return entry.getKey();
            }
            pos = pos - weight;
        }
        return "";
    }

    /**
     * 平滑加权轮询算法
     * 平滑加权轮询算法是一种使请求更加均匀分配的负载均衡算法。它通过为每个后端服务器分配一个当前权重和一个初始权重值，每次请求到达时，选择当前权重最大的服务器进行请求转发，并将该服务器的当前权重减去所有服务器的总权重。这样，在一段时间内，每个服务器都能够接收到相对均匀的请求，即使服务器的性能和负载不同。当服务器的当前权重减为0时，会重新设置为初始权重，并继续参与请求的分发。
     */
    public static class SmoothWeightedRoundRobin {
        private List<Server> servers;
        private int currentIndex;

        public SmoothWeightedRoundRobin(List<Server> servers) {
            this.servers = servers;
        }

        public synchronized Server getNextServer() {
            int totalWeight = 0;
            for (Server server : servers) {
                totalWeight += server.getWeight();
                server.setCurrentWeight(server.getCurrentWeight() + server.getWeight());
            }

            Server selectedServer = null;
            int maxWeight = 0;
            for (Server server : servers) {
                if (server.getCurrentWeight() > maxWeight) {
                    maxWeight = server.getCurrentWeight();
                    selectedServer = server;
                }
            }
            selectedServer.setCurrentWeight(selectedServer.getCurrentWeight() - totalWeight);
            return selectedServer;
        }


    }

   static class Server {
        private String name;
        private int weight;
        private int currentWeight;

        public Server(String name, int weight) {
            this.name = name;
            this.weight = weight;
            this.currentWeight = 0;
        }

        public String getName() {
            return name;
        }

        public int getWeight() {
            return weight;
        }

        public int getCurrentWeight() {
            return currentWeight;
        }

        public void setCurrentWeight(int currentWeight) {
            this.currentWeight = currentWeight;
        }
    }


    public static void main(String[] args) {
        // 创建服务器列表
        List<Server> servers = new ArrayList<>();
        servers.add(new Server("Server1", 2));  // 权重为4
        servers.add(new Server("Server2", 1));  // 权重为2
//        servers.add(new Server("Server3", 1));  // 权重为1

        // 创建平滑加权轮询对象
        SmoothWeightedRoundRobin swr = new SmoothWeightedRoundRobin(servers);

        // 模拟请求分发
        for (int i = 0; i < 10; i++) {
            Server server = swr.getNextServer();
            System.out.println("Request " + (i + 1) + " dispatched to " + server.getName());
        }
    }
}
