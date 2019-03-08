package Amazon;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if(n <= 0) {
            return result;
        }

        char[][] board = new char[n][n];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                board[i][j] = '.';
            }
        }

        backTrack(result, board, 0);
        return result;
    }


    private static void backTrack(List<List<String>> result, char[][] board, int col) {
        //place queens from left upper most
        if(col >= board[0].length) {
            result.add(getBoard(board));
            return;
        }

        //place queens in this columb one by one
        for(int i = 0; i < board[0].length; i++) {
            if(isValid(board, i, col)) {
                board[i][col] = 'Q';
                backTrack(result, board, col + 1);
                board[i][col] = '.';
            }
        }
    }

    private static List<String> getBoard(char[][] board) {
        List<String> res = new ArrayList<>();
        for(int i = 0; i < board.length; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
//            sb.append(",");
            res.add(sb.toString());
        }
        return res;
    }

    private static boolean isValid(char[][] board, int i, int j) {
        //check left row
        for(int col = j - 1; col >= 0; col--) {
            if(board[i][col] == 'Q') {
                return false;
            }
        }

        //check upper left diagonal
        for(int row = i - 1, col = j - 1; row >= 0 && col >= 0; row--, col--) {
            if(board[row][col] == 'Q') {
                return false;
            }
        }

        //check lower left diagonal
        for(int row = i + 1, col = j - 1; row < board.length && col >= 0; row++, col--) {
            if(board[row][col] == 'Q') {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }
}
