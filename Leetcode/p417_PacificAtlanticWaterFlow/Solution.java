package Leetcode.p417_PacificAtlanticWaterFlow;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

//    Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
//    the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
//
//    Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
//
//    Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
//
//            Note:
//    The order of returned grid coordinates does not matter.
//    Both m and n are less than 150.
//    Example:
//
//    Given the following 5x5 matrix:
//
//    Pacific ~   ~   ~   ~   ~
//            ~  1   2   2   3  (5) *
//            ~  3   2   3  (4) (4) *
//            ~  2   4  (5)  3   1  *
//            ~ (6) (7)  1   4   5  *
//            ~ (5)  1   1   2   4  *
//            *   *   *   *   * Atlantic
//
//    Return:
//
//            [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<>();
        if(matrix == null || matrix.length == 0) {
            return result;
        }

        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] pacific = new boolean[n][m];
        boolean[][] atlantic = new boolean[n][m];

        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();

        //1. add boarders into queue
        for(int i = 0; i < n; i++) {
            pQueue.add(new int[] {i, 0});
            aQueue.add(new int[] {i, m - 1});

            pacific[i][0] = true;
            atlantic[i][m - 1] = true;
        }
        for(int j = 0; j < m; j++) {
            pQueue.add(new int[] {0, j});
            aQueue.add(new int[] {n - 1, j});

            pacific[0][j] = true;
            atlantic[n - 1][j] = true;
        }

        bfs(pQueue, pacific, matrix);
        bfs(aQueue, atlantic, matrix);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(pacific[i][j] && atlantic[i][j]) {
                    result.add(new int[] {i, j});
                }
            }
        }

        return result;
    }

    private static void bfs(Queue<int[]> queue, boolean[][] visited, int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for(int[] d: dir) {
                    int nr = cur[0] + d[0];
                    int nc = cur[1] + d[1];
                    if(nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc] || matrix[cur[0]][cur[1]] > matrix[nr][nc]) {
                        continue;
                    }

                    visited[nr][nc] = true;
                    queue.offer(new int[] {nr, nc});
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,2,3,5}, {3,2,3,4,4}, {2,4,5,3,1}, {6,7,1,4,5}, {5,1,1,2,4}};
        for(int[] loc: pacificAtlantic(matrix)) {
            System.out.println(loc[0] + " " + loc[1]);
        }
    }
}
