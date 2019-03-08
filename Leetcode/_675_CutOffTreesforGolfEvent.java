package Leetcode;

import java.util.*;

public class _675_CutOffTreesforGolfEvent {

//    You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map,
//    in this map:
//
//    0 represents the obstacle can't be reached.
//    1 represents the ground can be walked through.
//    The place with number bigger than 1 represents a tree can be walked through,
//    and this positive number represents the tree's height.

//    You are asked to cut off all the trees in this forest in the order of tree's height
//    - always cut off the tree with lowest height first. And after cutting,
//    the original place has the tree will become a grass (value 1)

    static int[][] dir = {{0,1}, {0, -1}, {1, 0}, {-1, 0}};

    public static int cutOffTree(List<List<Integer>> forest) {
        if(forest == null || forest.size() == 0) {
            return 0;
        }else if(forest.get(0).get(0) == 0) {
            return -1;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->(a[2] - b[2]));

        for(int i = 0; i < forest.size(); i++) {
            for(int j = 0; j < forest.get(i).size(); j++) {
                if(forest.get(i).get(j) > 1) {
                    pq.offer(new int[]{i, j, forest.get(i).get(j)});
                }
            }
        }

        int[] start = new int[]{0, 0};
        int sum = 0;
        while(!pq.isEmpty()) {
            int[] target = pq.poll();
            int step = traverse(forest, start, target);
            if(step == -1) {
                return -1;
            }

            sum += step;
            start = target;
        }

        return sum;
    }

    private static int traverse(List<List<Integer>> forest, int[] start, int[] target) {

        int m = forest.size();
        int n =forest.get(0).size();

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];


        int step = 0;
        queue.offer(new int[]{start[0], start[1]});

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if(cur[0] == target[0] && cur[1] == target[1]) {
                    return step;
                }


                for(int[] d: dir) {
                    int nr = cur[0] + d[0];
                    int nc = cur[1] + d[1];
                    if(nr >= 0 && nr <m && nc >= 0 && nc < n && forest.get(nr).get(nc) > 0 && !visited[nr][nc]) {
                        queue.offer(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }

            step++;
        }

        return -1;

    }



    public static void main(String[] args) {
        List<List<Integer>> forest = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1,2,3));
        List<Integer> l2 = new ArrayList<>(Arrays.asList(0,0,0));
        List<Integer> l3 = new ArrayList<>(Arrays.asList(7,6,5));

        forest.add(l1);
        forest.add(l2);
        forest.add(l3);

        System.out.println(cutOffTree(forest));
    }
}
