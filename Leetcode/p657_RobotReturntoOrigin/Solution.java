package Leetcode.p657_RobotReturntoOrigin;

public class Solution {

//    Example 1:
//
//    Input: "UD"
//    Output: true
//    Explanation: The robot moves up once, and then down once. All moves have the same magnitude,
//    so it ended up at the origin where it started. Therefore, we return true.

    public boolean judgeCircle(String moves) {
        int U = 0;
        int R = 0;
        int L = 0;
        int D = 0;

        for(char c: moves.toCharArray()) {
            if(c == 'L') {
                L++;
            }
            if(c == 'R') {
                R++;
            }
            if(c == 'U') {
                U++;
            }
            if(c == 'D') {
                D++;
            }
        }

        return L == R && U == D;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
    }
}