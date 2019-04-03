package Leetcode.p212_WordSearchII;

import java.util.ArrayList;
import java.util.List;

public class Solution {
//
//    Example:

//    Input:
//    board = [
//            ['o','a','a','n'],
//            ['e','t','a','e'],
//            ['i','h','k','r'],
//            ['i','f','l','v']
//            ]
//    words = ["oath","pea","eat","rain"]
//
//    Output: ["eat","oath"]

    class TrieNode {
        TrieNode[] children;
        String word;

        public TrieNode() {
            children = new TrieNode[26];
            word = null;
        }
    }


    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if(board == null || words == null) {
            return result;
        }

        // init trie with words
        TrieNode root = new TrieNode();
        for(String word: words) {
            TrieNode cur = root;
            for(int i = 0; i < word.length(); i++) {
                int key = word.charAt(i) - 'a';
                if(cur.children[key] == null) {
                    cur.children[key] = new TrieNode();
                }
                cur = cur.children[key];
            }
            cur.word = word;
        }

        // dfs to find the words in board
        int n = board.length;
        int m = board[0].length;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                dfs(root, board, result, new boolean[n][m], i, j);
            }
        }

        return result;
    }

    private void dfs(TrieNode root, char[][] board, List<String> result, boolean[][] visited, int i, int j) {
        int idx = board[i][j] - 'a';

        if(root.children[idx] != null) {
            root = root.children[idx];
            if(root.word != null) {
                result.add(root.word);
                // don't add repeated word for same prefix so far
                root.word = null;

                // don't just return, coz it's not the end. A node with word doesn't mean it is leaf node
            }

            visited[i][j] = true;

            if(i + 1 < board.length && !visited[i + 1][j]) {
                dfs(root, board, result, visited, i + 1, j);
            }
            if(i - 1 >= 0 && !visited[i - 1][j]) {
                dfs(root, board, result, visited, i - 1, j);
            }
            if(j + 1 < board[0].length && !visited[i][j + 1]) {
                dfs(root, board, result, visited, i, j + 1);
            }
            if(j - 1 >= 0 && !visited[i][j - 1]) {
                dfs(root, board, result, visited, i, j - 1);
            }

            visited[i][j] = false;
        }
    }


    public static void main(String[] args) {
        Solution s = new Solution();
    }
}