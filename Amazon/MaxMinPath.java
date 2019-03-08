package Amazon;

public class MaxMinPath {

    //dfs solution
    static int max = Integer.MIN_VALUE;
    public static int MaxMinPathDFS(int[][] map) {
        if(map == null || map.length == 0 || map[0].length == 0) {
            return -1;
        }

        dfs(map, 0, 0, Integer.MAX_VALUE);
        return max;
    }

    private static void dfs(int[][] map, int i, int j, int pathMin) {
        if(i < 0 || i >= map.length || j < 0 || j >= map[i].length) {
            return;
        }

        pathMin = Math.min(pathMin, map[i][j]);
        if(i == map.length - 1 && j == map[i].length - 1) {
            max = Math.max(max, pathMin);
        }

        dfs(map, i, j + 1, pathMin);
        dfs(map, i + 1, j, pathMin);
    }

    //dp
    public static int MaxMinPathDP(int[][] map) {
        if(map == null || map.length == 0 || map[0].length == 0) {
            return -1;
        }

        int n = map.length, m = map[0].length;
        int[][] dp = new int[n][m];

        //init dp matrix;
        dp[0][0] = map[0][0];
        for(int j = 1; j < m; j++) {
            dp[0][j] = Math.min(dp[0][j - 1], map[0][j]);
        }
        for(int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i - 1][0], map[i][0]);
        }

        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                int pathMinLeft = Math.min(dp[i][j - 1], map[i][j]);
                int pathMinUp = Math.min(dp[i-1][j], map[i][j]);
                dp[i][j] = Math.max(pathMinLeft, pathMinUp);
            }
        }

        return dp[n-1][m-1];

    }

    public static void main(String[] args) {
        int[][] map = {{8,4,7}, {6,5,9}};
        System.out.println(MaxMinPathDFS(map));
        System.out.println(MaxMinPathDP(map));
    }
}
