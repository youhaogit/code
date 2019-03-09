package Leetcode.p711_NumberofDistinctIslandsII;

import java.util.*;

public class Solution {

    class Point {
        public int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] transform = {{1,1}, {1,-1}, {-1,1}, {-1,-1}};

    public int numDistinctIslands2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length, m = grid[0].length;
        Set<String> res = new HashSet<>();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) {
                    List<Point> island = new ArrayList<>();
                    dfs(grid, i, j, island);
                    res.add(getUnique(island));
                }
            }
        }

        return res.size();
    }

    private void dfs(int[][]grid, int i, int j, List<Point> island) {
        int n = grid.length;
        int m = grid[0].length;

        if(i < 0 || i >= n || j < 0 || j >= m || grid[i][j] == 0) {
            return;
        }

        grid[i][j] = 0;
        island.add(new Point(i, j));

        dfs(grid, i + 1, j, island);
        dfs(grid, i - 1, j, island);
        dfs(grid, i, j + 1, island);
        dfs(grid, i, j - 1, island);

    }

    private String getUnique(List<Point> island) {
        List<String> sameIslands = new ArrayList<>();

        int[][] trans={{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        for (int i = 0; i < 4; ++i) {
            List<Point> l1 = new ArrayList<>(), l2 = new ArrayList<>();

            for (Point point : island) {
                int x = point.x, y = point.y;
                l1.add(new Point(x * trans[i][0], y * trans[i][1]));
                l2.add(new Point(y * trans[i][0], x * trans[i][1]));
            }
            sameIslands.add(getStr(l1));
            sameIslands.add(getStr(l2));
        }

        Collections.sort(sameIslands);
        return sameIslands.get(0);
    }

    private String getStr(List<Point> island) {

        Collections.sort(island, new Comparator<Point>() {
            public int compare(Point a, Point b) {
                if (a.x != b.x) {
                    return a.x - b.x;
                }
                return a.y - b.y;
            }
        });

        StringBuilder sb = new StringBuilder();
        int x = island.get(0).x, y = island.get(0).y;

        for (Point point : island) {
            sb.append((point.x - x) + " " + (point.y - y) + " ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        int[][] grid = new int[][] {{1,1,0,0,0}, {1,0,0,0,0}, {0,0,0,0,1}, {0,0,0,1,1}};
        System.out.println(s.numDistinctIslands2(grid));
    }

}
