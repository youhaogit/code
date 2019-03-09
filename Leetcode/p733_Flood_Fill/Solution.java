package Leetcode.p733_Flood_Fill;

public class Solution {

    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }

        int res = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 1) {
                    int area = dfs(grid, i, j);
                    res = Math.max(res, area);
                }
            }
        }

        return res;
    }

    private int dfs(int[][] grid, int i, int j) {
        int n = grid.length;
        int m = grid[0].length;

        if(i < 0 || i >= n || j < 0 || j >= m || grid[i][j] == 0) {
            return 0;
        }

        grid[i][j] = 0;

        int area = 1;
        area += dfs(grid, i + 1, j);
        area += dfs(grid, i - 1, j);
        area += dfs(grid, i, j + 1);
        area += dfs(grid, i, j - 1);

        return area;

    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0,1,0},{1,1,1}};
        Solution s = new Solution();
        System.out.println(s.maxAreaOfIsland(grid));
    }
}
