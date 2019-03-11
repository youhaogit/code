package Leetcode.p505_TheMazeII;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int n;
    static int m;

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if(maze == null || maze.length == 0 || maze[destination[0]][destination[1]] == 1) {
            return -1;
        }else if(start[0] == destination[0] && start[1] == destination[1]) {
            return 0;
        }

        n = maze.length;
        m = maze[0].length;

        int[][] distance = new int[n][m];
        for(int[] dis: distance) {
            Arrays.fill(dis, Integer.MAX_VALUE);
        }

        distance[start[0]][start[1]] = 0;
        dfs(maze, start, distance);

        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distance[destination[0]][destination[1]];
    }

    private void dfs(int[][] maze, int[] start, int[][] distance) {
        int i = start[0];
        int j = start[1];

        for(int[] dir: directions) {
            int nr = i + dir[0];
            int nc = j + dir[1];

            int count = 0;
            while(nr >= 0 && nr < n && nc >= 0 && nc < m && maze[nr][nc] == 0) {
                nr += dir[0];
                nc += dir[1];
                count++;
            }

            if(distance[start[0]][start[1]] + count < distance[nr - dir[0]][nc - dir[1]]) {
                distance[nr -dir[0]][nc - dir[1]] = distance[start[0]][start[1]] + count;
                dfs(maze, new int[]{nr - dir[0], nc - dir[1]}, distance);
            }


        }
    }

    public static void main(String[] args) {
        int[][] maze = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        Solution s = new Solution();
        System.out.println(s.shortestDistance(maze, new int[]{0, 4}, new int[]{4, 4}));
    }
}
