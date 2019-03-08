package Indeed;

public class ReverseWordsInString {
//    Input: "the sky is blue",
//    Output: "blue is sky the".
    public static String reverseWords(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }

        String[] strs = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();

        for(int i = strs.length - 1; i >= 0; i--) {
            sb.append(strs[i]);
            sb.append(" ");
        }

        return sb.toString().trim();


    }

    private static String reverseStr(String str) {
        char[] arr = str.toCharArray();
        int left = 0, right = str.length() - 1;
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
        String s = "   the sky is blue   ";

        System.out.println(reverseWords(s));
    }
}
