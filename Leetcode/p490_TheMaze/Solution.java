package Leetcode.p490_TheMaze;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

//    Input 1: a maze represented by a 2D array
//
//        0 0 1 0 0
//        0 0 0 0 0
//        0 0 0 1 0
//        1 1 0 1 1
//        0 0 0 0 0
//
//    Input 2: start coordinate (rowStart, colStart) = (0, 4)
//    Input 3: destination coordinate (rowDest, colDest) = (4, 4)

//    Output: true

    static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if(maze == null || maze.length == 0 || maze[destination[0]][destination[1]] == 1) {
            return false;
        }else if(start[0] == destination[0] && start[1] == destination[1]) {
            return true;
        }

        int n = maze.length;
        int m = maze[0].length;
        boolean[][] visited = new boolean[n][m];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited[start[0]][start[1]] = true;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if (cur[0] == destination[0] && cur[1] == destination[1]) {
                        return true;
                }

                //meet bounds. need to bounce
                for (int[] d : directions) {
                    int r = cur[0] + d[0];
                    int c = cur[1] + d[1];
                    while (r >= 0 && r < n && c >= 0 && c < m && maze[r][c] == 0) {
                        r += d[0];
                        c += d[1];
                    }

                    r -= d[0];
                    c -= d[1];
                    if(!visited[r][c]) {
                        queue.offer(new int[]{r, c});
                        visited[r][c] = true;
                    }
                }
            }
        }

        return false;

    }

    public static void main(String[] args) {
        int[][] maze = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        Solution s = new Solution();
        System.out.println(s.hasPath(maze, new int[]{0, 4}, new int[]{3, 2}));
    }
}
