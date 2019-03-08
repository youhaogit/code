package Amazon;

public class InterLeaving {

//    public static boolean isInterleave(String s1, String s2, String s3) {
//        if(s3.length() != s1.length() + s2.length()) {
//            return false;
//        }
//        boolean[][] invalid = new boolean[s1.length() + 1][s2.length() + 1];
//        return dfs(s3, 0, s1, 0, s2, 0, invalid);
//    }
//
//    private static boolean dfs(String s3, int idx, String s1, int i, String s2, int j, boolean[][] invalid) {
//
//        if(invalid[i][j]) {
//            return false;
//        }
//        //base case
//        if(idx == s3.length()) {
//            return true;
//        }
//
//        boolean flag1 = false;
//        if(i < s1.length() && s3.charAt(idx) == s1.charAt(i)) {
//            flag1 = dfs(s3, idx + 1, s1, i + 1, s2, j, invalid);
//        }
//
//        boolean flag2 = false;
//        if(j < s2.length() && s3.charAt(idx) == s2.charAt(j)) {
//            flag2 = dfs(s3, idx + 1, s1, i, s2, j + 1, invalid);
//        }
//
//        boolean valid = flag1 || flag2;
//        if(!valid) {
//            invalid[i][j] = true;
//        }
//
//        return valid;
//
//    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        if(s3.length() != s1.length() + s2.length()) {
            return false;
        }

        int n = s1.length();
        int m = s2.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                dp[i][j] = false;
            }
        }

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= m; j++) {
                if(i == 0 && j == 0) {
                    dp[i][j] = true;
                }else if(i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
                }else if(j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i - 1);
                }else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
                            (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }

            }
        }

        return dp[n][m];

    }

    public static void main(String[] args) {
        String s3 = "aadbbcbcac";
        String s1 = "aabcc";
        String s2 = "dbbca";
        System.out.println(isInterleave(s1, s2, s3));
    }

}
