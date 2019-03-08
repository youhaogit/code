package Indeed;

public class ReverseStringII {

//    Input: s = "abcdefg", k = 2
//    Output: "bacdfeg"
    public static String reverseStr(String s, int k) {
        if(s.length() < k) {
            return helper(s, 0, s.length() - 1);
        }else if(s.length() >=k && s.length() < 2 * k) {
            return helper(s, 0, k - 1);
        }else {
            s = helper(s, 0, k - 1);
            return s.substring(0, 2 * k) + reverseStr(s.substring(2 * k), k);
        }
    }

    private static String helper(String s, int left, int right) {
        char[] arr = s.toCharArray();
        while(left < right) {
            char temp = arr[right];
            arr[right] = arr[left];
            arr[left] = temp;
            left++;
            right--;
        }
        return String.valueOf(arr);
    }

    public static void main(String[] args) {
        String s = "abcdefg";
        System.out.println(reverseStr(s, 2));
    }
}
