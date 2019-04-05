package Leetcode.p977_SquaresofaSortedArray;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {

    public int[] sortedSquares(int[] A) {

        for(int i = 0; i < A.length; i++){
            A[i] = A[i] * A[i];
        }

        Arrays.sort(A);

        return A;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        int[] A = {-4,-1,0,3,10};
        s.sortedSquares(A);
        for(int n: A) {
            System.out.println(n);
        }

    }
}