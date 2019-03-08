package Amazon;

public class ReverseWordsII {

    public static void reverseWords(char[] str) {
        if(str == null || str.length == 0) {
            return;
        }

        //1. reverse whole string
        reverse(str, 0, str.length - 1);

        //2. reverse each word
        int start = 0, end = 0;
        for(int i = 0; i < str.length; i++) {
            if(str[i] == ' ') {
                end = i;
                reverse(str, start, end - 1);
                start = i + 1;
            }
        }

        //3. reverse last word
        reverse(str, start, str.length - 1);

        return;
    }

    private static void reverse(char[] str, int start, int end) {
        while(start < end) {
            char temp = str[end];
            str[end] = str[start];
            str[start] = temp;

            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        char[] str = {'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        reverseWords(str);
        System.out.println(str);

    }
}
