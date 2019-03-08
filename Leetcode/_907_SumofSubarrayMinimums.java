package Leetcode;

public class _907_SumofSubarrayMinimums {

    public static int sumSubarrayMins(int[] A) {
        if(A == null || A.length == 0) {
            return 0;
        }

        int n = A.length;
        int[] matrix = new int[n];

        long sum = 0;
        for(int j = 0; j < n; j++) {
            matrix[j] = A[j];
            sum += matrix[j];
            sum %= (Math.pow(10, 9) + 7);
        }

        for(int i = 1; i < n; i++) {
            int[] temp = new int[n];
            for(int j = 0; j < n - 1; j++) {
                temp[j] = Math.min(matrix[j], matrix[j + 1]);
                sum += temp[j];
                sum %= (Math.pow(10, 9) + 7);
            }
            matrix = temp;
        }

        return (int)(sum % (Math.pow(10, 9) + 7));

    }

    public static void main(String[] args) {
        int[] A = {3,1,2,4};
        System.out.println(sumSubarrayMins(A));
    }
}
