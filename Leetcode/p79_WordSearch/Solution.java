package Leetcode.p79_WordSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
//    Example:
//
//    board =
//            [
//            ['A','B','C','E'],
//            ['S','F','C','S'],
//            ['A','D','E','E']
//            ]
//
//    Given word = "ABCCED", return true.
//    Given word = "SEE", return true.
//    Given word = "ABCB", return false.

    public boolean exist(char[][] board, String word) {
        if(board == null || word == null) {
            return false;
        }

        int n = board.length;
        int m = board[0].length;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(dfs(board, word, i, j, new boolean[n][m], 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, boolean[][] visited, int pos) {
        if(pos == word.length()) {
            return true;
        }
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] || word.charAt(pos) != board[i][j]) {
            return false;
        }

        visited[i][j] = true;
        boolean res = dfs(board, word, i + 1, j, visited, pos + 1)
                || dfs(board, word,i - 1, j, visited, pos + 1)
                || dfs(board, word, i, j + 1, visited, pos + 1)
                || dfs(board, word, i, j - 1, visited, pos + 1);

        if(res == true) {
            return true;
        }

        visited[i][j] = false;

        return false;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

    }
}