package Amazon;

public class LongestPalindromeSubarray {

    static int maxLen = 0;
    static int lo = 0;
    public static String longestPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }

        for(int i = 0; i < s.length(); i++) {
            expandFromCenter(s, i , i);
            expandFromCenter(s, i, i + 1);
        }

        return s.substring(lo, lo + maxLen);
    }

    private static void expandFromCenter(String s, int left, int right) {
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if(right - left + 1 > maxLen) {
                maxLen = right - left + 1;
                lo = left;
            }
            left--;
            right++;
        }
    }

    public static void main(String[] args) {
        String s = "a";
        System.out.println(longestPalindrome(s));
    }
}
