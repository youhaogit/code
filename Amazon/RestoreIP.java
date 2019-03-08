package Amazon;

import java.util.ArrayList;
import java.util.List;

public class RestoreIP {

    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        int len = s.length();
        for(int a = 1; a <= 3 && a < len - 2; a++) {
            for(int b = a + 1; b <= a + 4 && b < len -1; b++) {
                for(int c = b + 1; c <= b + 4 && c < len; c++) {

                    String sub1 = s.substring(0, a);
                    String sub2 = s.substring(a, b);
                    String sub3 = s.substring(b, c);
                    String sub4 = s.substring(c);

                    if(isValid(sub1) && isValid(sub2) && isValid(sub3) && isValid(sub4)
                        && sub1.length() + sub2.length() + sub3.length() + sub4.length() == s.length()) {
                        String ans = sub1 + '.' + sub2 + '.' + sub3 + '.' + sub4;
                        if (!result.contains(ans)) {
                            result.add(ans);
                        }

                    }
                }
            }
        }

        return result;
    }

    public static boolean isValid(String s){
        if(s.length()>3 || s.length()==0 || (s.charAt(0)=='0' && s.length()>1) || Integer.parseInt(s)>255)
            return false;
        return true;
    }

    public static void main(String[] args) {
        String s = "0000";
        System.out.println(restoreIpAddresses(s));
    }
}
