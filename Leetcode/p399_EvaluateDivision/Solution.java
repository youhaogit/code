package Leetcode.p399_EvaluateDivision;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        //dfs to find path from one node to other node

        // 1. build grpah
        Map<String, Map<String, Double>> graph = new HashMap<>();

        int n = values.length;
        for(int i = 0; i < n; i++) {
            String[] equation = equations[i];
            String from = equation[0];
            String to = equation[1];

            graph.putIfAbsent(from, new HashMap<>());
            graph.putIfAbsent(to, new HashMap<>());
            graph.get(from).put(to, values[i]);
            graph.get(to).put(from, 1.0 / values[i]);
        }

        // 2. dfs for each query
        double[] res = new double[queries.length];
        for(int i = 0; i < queries.length; i++) {
            String[] query = queries[i];
            String begin = query[0];
            String end = query[1];

            if(!graph.containsKey(begin) || !graph.containsKey(end)) {
                res[i] = -1.0;
            }else {
                Set<String> visited = new HashSet<>();
                res[i] = dfs(graph, begin, end, visited);
            }
        }

        return res;
    }

    private double dfs(Map<String, Map<String, Double>> graph, String begin, String end, Set<String> visited) {
        if(begin.equals(end)) {
            return 1.0;
        }

        visited.add(begin);
        Map<String, Double> neighbors = graph.get(begin);
        for(String neighbor: neighbors.keySet()) {
            if(!visited.contains(neighbor)) {
                double res = dfs(graph, neighbor, end, visited);
                if(res != -1) {
                    return neighbors.get(neighbor) * res;
                }
            }
        }

        return -1;
    }


//    [ ["a","b"],["b","c"] ]
//            [2.0,3.0]
//            [ ["a","c"],["b","c"],["a","e"],["a","a"],["x","x"] ]
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] equations = {{"a", "b"}, {"b", "c"}};
        double[] values = {2.0, 3.0};
        String[][] queries = {{"a", "c"}, {"b", "c"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};

        System.out.println(s.calcEquation(equations, values, queries));
    }
}
