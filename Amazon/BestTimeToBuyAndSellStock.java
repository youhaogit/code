package Amazon;

public class BestTimeToBuyAndSellStock {

    //general case for k transactions
    public static int maxProfit(int[] prices, int k) {
        //dp solution, dp[k][i] to represent maxProfit could gain till ith day with at most k transactions
        int n = prices.length;
        int[][] dp = new int[k + 1][n];

        //for 0 transaction, no profit
        for(int i = 0; i < n; i++) {
            dp[0][i] = 0;
        }

        //for 0 day, no profit as well
        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }

        //for ith day, consider two case
        //1. hold the (i - 1)th day stock, and do nothing -> dp[k][i] = dp[k][i-1]
        //2. sold the stock at ith day, profit would be dp[k - 1][j] - prices[j] + prices[i] for j in 1...i-1
        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < n; j++) {
                int hold = dp[i][j - 1];
                int sold = Integer.MIN_VALUE;
                for(int m = 0; m < j; m++) {
                    sold = Math.max(sold, dp[i-1][m] - prices[m] + prices[j]);
                }
                dp[i][j] = Math.max(hold, sold);
            }
        }

        return dp[k][n-1];
    }

    public static void main(String[] args) {
        int[] nums = {3,3,5,0,0,3,1,4};
        System.out.println(maxProfit(nums, 2));
    }
}
