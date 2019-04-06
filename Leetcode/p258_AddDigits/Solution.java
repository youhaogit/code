package Leetcode.p258_AddDigits;

public class Solution {

//    Example:
//
//    Input: 38
//    Output: 2
//    Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2.
//    Since 2 has only one digit, return it.

    public int addDigits(int num) {
        int cur = num;
        int sum = 0;
        boolean oneDigit = false;
        while(!oneDigit) {
            while(cur / 10 != 0) {
                sum += cur % 10;
                cur /= 10;
            }
            sum += cur;

            if(sum / 10 == 0) {
                oneDigit = true;
            }else {
                cur = sum;
                sum = 0;
            }
        }

        return sum;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.addDigits(34));
    }
}