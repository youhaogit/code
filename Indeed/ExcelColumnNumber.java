package Indeed;

public class ExcelColumnNumber {

    public static int titleToNumber(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        int ans = 0;
        for(int i = 0; i < s.length(); i++) {
            ans = ans * 26 + s.charAt(i) - 'A' + 1;
        }

        return ans;

    }

    public static void main(String[] args) {
        System.out.println(titleToNumber("ZY"));
    }
}
