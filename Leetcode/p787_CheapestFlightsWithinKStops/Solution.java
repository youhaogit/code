package Leetcode.p787_CheapestFlightsWithinKStops;

import java.util.*;

public class Solution {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

        // Dijkstra solution
        // 1.init cost matrix
        int[][] matrix = new int[n][n];
        for(int[] row: matrix) {
            Arrays.fill(row, 10000);
        }
        for(int[] flight: flights) {
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            matrix[from][to] = price;
        }

        // 2. start vertex is src

        boolean[] found = new boolean[n]; // find best path or not
        int[] preVertex = new int[n]; // record previous vertex
        int[] cost = new int[n]; // record the best cost for each vertex

        for(int i = 0; i < n; i++) {
            preVertex[i] = src;
        }
        for(int i = 0; i < n; i++) {
            cost[i] = matrix[src][i] == Integer.MAX_VALUE ? 10000 : matrix[src][i];
        }
        found[src] = true;

        //3. start loop
        int count = 0; //allowed stop
        int v = src;

        while(count < K) {
            // find the most nearest vertex from previous vertex
            int d = Integer.MAX_VALUE;
            for(int i = 0; i < n; i++) {
                if(!found[i] && cost[i] < d) {
                    d = cost[i];
                    v = i;
                }
            }

            if(d >= 10000) {
                return -1;
            }

            found[v] = true;

            for(int i = 0; i < n; i++) {
                if(!found[i]) {
                    d = cost[v] + matrix[v][i];
                    if(d < cost[i]) {
                        preVertex[i] = v;
                        cost[i] = d;
                    }
                }
            }

            count++;
        }

        return cost[dst] >= 10000 ? -1: cost[dst];

    }


    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] matrix = new int[][]{{4,1,1}, {1,2,3},{0,3,2}, {0,4,10}, {3,1,1}, {1,4,3}};
        System.out.println(s.findCheapestPrice(5, matrix, 2, 1, 1));

    }
}