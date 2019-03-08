package Amazon;

public class SellStock {

    //basic ides is Kadane's algorithm
    //two variables to keep track of maxEndingHere and maxSoFar

    public static int maxProfit(int[] prices) {
        int maxCur = prices[0], maxSoFar = prices[0];
        for(int i = 1; i < prices.length; i++) {
            maxCur = Math.max(prices[i], maxCur += prices[i]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,8};
        System.out.println(maxProfit(nums));
    }
}
