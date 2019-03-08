package Amazon;

import java.util.HashSet;
import java.util.Set;

public class Sudoku {
    public static boolean isValidSudoku(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0 || board.length != 9 || board[0].length != 9) {
            return false;
        }

        //check row
        for(int i = 0; i < 9; i++) {
            Set<Character> set = new HashSet<>();
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {
                    continue;
                }
                if(!Character.isDigit(board[i][j]) || !set.add(board[i][j])) {
                    return false;
                }
            }
        }

        //check col
        for(int i = 0; i < 9; i++) {
            Set<Character> set = new HashSet<>();
            for(int j = 0; j < 9; j++) {
                if(board[j][i] == '.') {
                    continue;
                }
                if(!Character.isDigit(board[j][i]) || !set.add(board[j][i])) {
                    return false;
                }
            }
        }

        //check cell
        for(int i = 0; i < 9; i += 3) {
            for(int j = 0; j < 9; j += 3) {
                Set<Character> set = new HashSet<>();
                for(int k = 1; k < 9; k++) {
                    if(board[i + k / 3][j + k % 3] == '.') {
                        continue;
                    }
                    if(!Character.isDigit(board[i + k / 3][j + k % 3]) || !set.add(board[i + k / 3][j + k % 3])) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {

    }
}
