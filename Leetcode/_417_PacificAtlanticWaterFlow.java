package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class _417_PacificAtlanticWaterFlow {

//    Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
//    the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
//
//    Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
//
//    Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
//
//            Note:
//    The order of returned grid coordinates does not matter.
//    Both m and n are less than 150.
//    Example:
//
//    Given the following 5x5 matrix:
//
//    Pacific ~   ~   ~   ~   ~
//            ~  1   2   2   3  (5) *
//            ~  3   2   3  (4) (4) *
//            ~  2   4  (5)  3   1  *
//            ~ (6) (7)  1   4   5  *
//            ~ (5)  1   1   2   4  *
//            *   *   *   *   * Atlantic
//
//    Return:
//
//            [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

    public static List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<>();
        if(matrix == null || matrix.length == 0) {
            return result;
        }

        int n = matrix.length;
        int m = matrix[0].length;
        int[][] mat1 = new int[n][m];


        //flood from Pacific
        for(int i = 0; i < mat1.length; i++) {
            mat1[i][0] = 1;
        }
        for(int j = 0; j < mat1[0].length; j++) {
            mat1[0][j] = 1;
        }
        for(int i = 1; i < mat1.length; i++) {
            for(int j = 1; j < mat1[0].length; j++) {
                if((matrix[i][j] >= matrix[i - 1][j] && mat1[i - 1][j] == 1)
                        || (matrix[i][j] >= matrix[i][j - 1] && mat1[i][j - 1] == 1)) {
                    mat1[i][j] = 1;
                }
            }
        }


        //flood from Atlantic
        int[][] mat2 = new int[n][m];
        for(int i = n - 1; i >= 0; i--) {
            mat2[i][m - 1] = 1;
        }
        for(int j = 0; j < m; j++) {
            mat2[n - 1][j] = 1;
        }
        for(int i = n - 2; i >= 0; i--) {
            for(int j = m - 2; j >= 0; j--) {
                if((matrix[i][j] >= matrix[i + 1][j] && mat2[i + 1][j] == 1)
                        || (matrix[i][j] >= matrix[i][j + 1] && mat2[i][j + 1] == 1)) {
                    mat2[i][j] += 1;
                }
            }
        }

        for(int i = 0; i < mat1.length; i++) {
            for(int j = 0; j < mat1[0].length; j++) {
                if(mat1[i][j] == 1 && mat2[i][j] == 1) {
                    result.add(new int[]{i, j});
                }
            }
        }

        return result;

    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,2,3,5}, {3,2,3,4,4}, {2,4,5,3,1}, {6,7,1,4,5}, {5,1,1,2,4}};
        System.out.println(pacificAtlantic(matrix));
    }
}
