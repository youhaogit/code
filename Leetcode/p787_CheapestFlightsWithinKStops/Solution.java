package Leetcode.p787_CheapestFlightsWithinKStops;

import java.util.*;

public class Solution {

//    public int dijkstra(int n, int[][] flights, int src, int dst, int K) {
//
//        // Dijkstra solution
//        // 1.init cost matrix
//        int[][] matrix = new int[n][n];
//        for(int[] row: matrix) {
//            Arrays.fill(row, 10001);
//        }
//        for(int[] flight: flights) {
//            int from = flight[0];
//            int to = flight[1];
//            int price = flight[2];
//            matrix[from][to] = price;
//        }
//
//        // 2. init distance, visited and preVertex matrix
//
//        boolean[] visited = new boolean[n]; // find best path or not
//        int[] preVertex = new int[n]; // record previous vertex
//        int[] cost = new int[n]; // record the best cost for each vertex
//
//        for(int i = 0; i < n; i++) {
//            preVertex[i] = src;
//        }
//        for(int i = 0; i < n; i++) {
//            cost[i] = matrix[src][i];
//        }
//        cost[src] = 0;
//        visited[src] = true;
//
//        //3. start loop
//        int count = 0; // allowed stop
//        int v = src; // last visited node
//
//        while(count < n) {
//            // from set U, find the vertex which has the min cost
//            int d = 100001;
//            for(int i = 0; i < n; i++) {
//                if(!visited[i] && cost[i] < d) {
//                    d = cost[i];
//                    v = i;
//                }
//            }
//
//            // no more update on cost, means no more path can be added to set S
//            if(d == 100001) {
//                break;
//            }
//            visited[v] = true;
//
//            // check if the dst reached maximum stop
//
//            // update remaining vertex according to the latest added vertex v
//            for(int i = 0; i < n; i++) {
//                if(!visited[i]) {
//                    d = cost[v] + matrix[v][i];
//                    if(d < cost[i]) {
//                        preVertex[i] = v;
//                        cost[i] = d;
//                    }
//                }
//            }
//
//            count++;
//        }
//
//        return cost[dst] == 10001 ? -1: cost[dst];
//
//    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int[] flight: flights) {
           if(map.containsKey(flight[0])) {
               map.put(flight[0], new HashMap<>());
           }
           map.get(flight[0]).put(flight[1], flight[2]);
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) ->(a[0] - b[0]));
        heap.offer(new int[]{0, src, k + 1});

        while(!heap.isEmpty()) {
            int[] cur = heap.poll();
            int cost = cur[0];
            int city = cur[1];
            int stop = cur[2];

            if(city == dst) {
                return cost;
            }

            if(stop > 0) {
                Map<Integer, Integer> adj = map.get(city);
                for(int nextCity: adj.keySet()) {
                    heap.offer(new int[] {cost + adj.get(nextCity), nextCity, stop - 1});
                }
            }
        }

        return -1;
    }


    public int findCheapestPriceBellmanFord(int n, int[][] flights, int src, int dst, int k) {

        // 1. init cost matrix
        int INF = 0x3F3F3F3F;
        int[] cost = new int[n];
        Arrays.fill(cost, INF);
        cost[src] = 0;

        // 2. iterate for k + 1 times to relax all the edges
        int ans = cost[dst];
        for(int i = 0; i <= k; i++) {
            int[] curCost = new int[n];
            Arrays.fill(curCost, INF);

            for(int[] flight: flights) {
                int u = flight[0];
                int v = flight[1];
                if(cost[v] > cost[u] + flight[2]) {
                    cost[v] = cost[u] + flight[2];
                }
            }

            ans = Math.min(ans, cost[dst]);
        }

        return ans == INF ? -1 : cost[dst];
    }

    public int findCheapestPriceBF_DP(int n, int[][] flights, int src, int dst, int k) {

        // 1. init cost matrix
        int INF = 0x3F3F3F3F;
        int[][] dp = new int[k + 2][n];
        for(int[] row: dp) {
            Arrays.fill(row, INF);
        }
        for(int i = 0; i < k + 2; i++) {
            dp[i][src] = 0;
        }


        for(int i = 1; i < k + 2; i++) {
            for(int[] flight: flights) {
                int from = flight[0];
                int to = flight[1];
                dp[i][to] = Math.min(dp[i][to], dp[i - 1][from] + flight[2]);
            }
        }

        return dp[k + 1][dst] >= INF ? -1 : dp[k + 1][dst];
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] matrix = new int[][]{{0,1,1}, {0,2,5},{1,2,1}, {2,3,1}};
        System.out.println(s.findCheapestPriceBF_DP(4, matrix, 0, 3, 1));

    }
}