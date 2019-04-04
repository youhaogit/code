package Leetcode.p863_AllNodesDistanceKinBinaryTree;

import util.TreeNode;

import java.util.*;

public class Solution {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        // build graph and then bfs

        List<Integer> result = new ArrayList<>();
        if(root == null || target == null || K < 0) {
            return result;
        }else if(K == 0) {
            result.add(target.val);
            return result;
        }

        // init graph
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        buildGraph(graph, root, null);

        // bfs
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);

        Set<TreeNode> set = new HashSet<>();
        while(!queue.isEmpty()) {
            K--;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                set.add(cur);

                for(TreeNode neighbor: graph.get(cur)) {
                    if(!set.contains(neighbor) && neighbor != null) {
                        queue.offer(neighbor);
                    }
                }
            }

            if(K == 0) {
                for(TreeNode node: queue) {
                    result.add(node.val);
                }
                break;
            }
        }

        return result;
    }

    private void buildGraph(Map<TreeNode, List<TreeNode>> graph, TreeNode node, TreeNode parent) {
        if(node != null) {
            List<TreeNode> node_neighbors = graph.getOrDefault(node, new ArrayList<>());
            List<TreeNode> parent_neighbors = graph.getOrDefault(parent, new ArrayList<>());

            node_neighbors.add(parent);
            parent_neighbors.add(node);

            graph.put(node, node_neighbors);
            graph.put(parent, parent_neighbors);

            buildGraph(graph, node.left, node);
            buildGraph(graph, node.right, node);
        }
    }


    public static void main(String[] args) {
        Solution s = new Solution();
    }
}