package Amazon;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class WordSearch {
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean exist(char[][] board, String word) {
        if(board == null || word == null) {
            return false;
        }
        if(board.length == 0 && word.length() == 0) {
            return true;
        }


        //init queue for bfs
        Deque<Point> queue = new LinkedList<>();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(0)) {
                    queue.offer(new Point(i, j));
                }
            }
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < visited.length; i++) {
            for(int j = 0; j < visited[0].length; j++) {
                visited[i][j] = false;
            }
        }

        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            if(bfs(cur, board, new boolean[board.length][board[0].length], word)) {
                return true;
            }
        }

        return false;
    }



    private static boolean bfs(Point point, char[][] board, boolean[][] visited, String word) {
        Deque<Point> queue = new LinkedList<>();
        queue.offer(point);
        int idx = 0;
        while(!queue.isEmpty()) {
            idx++;
            if(idx == word.length()) {
                return true;
            }
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Point cur = queue.poll();
                visited[cur.x][cur.y] = true;
                List<Point> adjList = getAdjList(cur);
                for(Point p: adjList) {
                    if(isValid(p, board, idx, word, visited)) {
                        queue.offer(p);
                    }
                }
            }
        }

        return false;
    }

    private static List<Point> getAdjList(Point cur) {
        List<Point> list = new ArrayList<>();
        list.add(new Point(cur.x - 1, cur.y));
        list.add(new Point(cur.x + 1, cur.y));
        list.add(new Point(cur.x, cur.y - 1));
        list.add(new Point(cur.x, cur.y + 1));

        return list;
    }

    private static boolean isValid(Point p, char[][] board, int idx, String word, boolean[][] visited) {
        if(p.x < 0 || p.x >= board.length || p.y < 0 || p.y >= board[0].length || visited[p.x][p.y]) {
            return false;
        }
        if(board[p.x][p.y] != word.charAt(idx)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {{'A','B','C', 'E'}, {'S','F','E', 'S'}, {'A','D','E', 'E'}};
        System.out.println(exist(board, "ABCESEEEFS"));
    }
}
