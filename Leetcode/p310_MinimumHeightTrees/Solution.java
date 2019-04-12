package Leetcode.p310_MinimumHeightTrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if(edges == null) {
            return res;
        }else if(edges.length == 0) {
            res.add(0);
            return res;
        }

        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] degree = new int[n];
        for(int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }

        List<Integer> leaves = new LinkedList<>();
        for(int i = 0; i < graph.size(); i++) {
            if(graph.get(i).size() == 1) {
                leaves.add(i);
            }
        }

        int step = 0;
        while(n > 2) {
            int size = leaves.size();
            n -= size;

            List<Integer> temp = new LinkedList<>();
            for(int leaf: leaves) {
                for(int parent: graph.get(leaf)) {
                    degree[parent]--;
                    if(degree[parent] == 1) {
                        temp.add(parent);
                    }
                }
            }
            step++;
            leaves = temp;
        }

        System.out.println(step);
        return leaves;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] edges = {{1,0}, {1,2}, {1,3}};
        System.out.println(s.findMinHeightTrees(4, edges));
    }
}
