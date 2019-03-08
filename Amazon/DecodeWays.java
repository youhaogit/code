package Amazon;

public class DecodeWays {

    public static int numDecodings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        char[] digits = s.toCharArray();

        return helper(digits, digits.length);
    }

    private static int helper(char[] digits, int n) {
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = digits[0] == '0' ? 0 : 1;
        for(int i = 2; i <= n; i++) {

            if(digits[i - 1] != '0') {
                dp[i] = dp[i - 1];
            }
            if(digits[i - 2] == '1' || digits[i - 2] == '2' && digits[i - 1] < '7') {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];

    }


    public static void main(String[] args) {
        String s = "226";
        System.out.println(numDecodings(s));
    }
}
