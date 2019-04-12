package Leetcode.p934_ShortestBridge;

import java.util.*;

class Solution {
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int shortestBridge(int[][] A) {
        // general idea it to dfs to find a island first, and then bfs to reach another island
        Queue<int[]> queue = new LinkedList<>();
        boolean found = false;
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < A[0].length; j++) {
                if(found) {
                    break;
                }
                if(A[i][j] == 1) {
                    dfs(queue, A, i, j);
                    found = true;
                }
            }
        }

        // bfs to find the next island
        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int r = cur[0];
                int c = cur[1];

                for(int[] dir: dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if(nr >= 0 && nr < A.length && nc >= 0 && nc < A[0].length && A[nr][nc] != -1) {
                        if(A[nr][nc] == 1) {
                            return step;
                        }
                        A[nr][nc] = -1;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
            step++;
        }

        return -1;
    }

    private void dfs(Queue<int[]> queue, int[][] A, int i, int j) {
        if(i < 0 || i >= A.length || j < 0 || j >= A[0].length || A[i][j] != 1) {
            return;
        }

        A[i][j] = -1;
        queue.offer(new int[]{i, j});

        dfs(queue, A, i + 1, j);
        dfs(queue, A, i - 1, j);
        dfs(queue, A, i, j - 1);
        dfs(queue, A, i, j + 1);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] grid = {{0, 1}, {1,0}};
        System.out.println(s.shortestBridge(grid));
    }
}

