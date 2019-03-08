package Amazon;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;

public class SetZeros {
    public static void setZeroes(int[][] matrix) {
        if(matrix == null || matrix.length == 0) {
            return;
        }

        LinkedList<Integer> rows = new LinkedList<>();
        LinkedList<Integer> cols = new LinkedList<>();
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for(Integer row: rows) {
            for(int j = 0; j < matrix[0].length; j++) {
                matrix[row][j] = 0;
            }
        }

        for(Integer col: cols) {
            for(int i = 0; i < matrix.length; i++) {
                matrix[i][col] = 0;
            }
        }

        return;
    }

    public static void main(String[] args) {

    }
}
