package Leetcode.p694_NumberofDistinctIslands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {


    public int numDistinctIslands(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }

        Set<List<Integer>> shapes = new HashSet<>();

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    List<Integer> shape = new ArrayList<>();
                    dfs(grid, i, j, shape, 0);

                    if(!shapes.contains(shape)) {
                        shapes.add(shape);
                    }
                }
            }
        }

        return shapes.size();
    }

    private void dfs(int[][] grid, int i, int j, List<Integer> shape, int command) {
        int n = grid.length;
        int m = grid[0].length;

        if(i < 0 || i >= n || j < 0 || j >= m || grid[i][j] == 0) {
            return;
        }

        grid[i][j] = 0;
        shape.add(command);

        dfs(grid, i + 1, j, shape, 1);
        dfs(grid, i - 1, j, shape, 2);
        dfs(grid, i, j + 1, shape, 3);
        dfs(grid, i, j - 1, shape, 4);

        shape.add(command);

    }
}
