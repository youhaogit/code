package Indeed;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Strstr {

    public static int strStr(String parent, String child) {
        int ans = 0;
        if(parent == null) {
            return ans;
        }else if(child == null) {
            return ans;
        }else if(child.length() == 0) {
            return 1;
        }

        int idx = 0;
        while(idx < parent.length()) {
            int nextIdx = parent.indexOf(child, idx);
            if(nextIdx == -1) {
                return ans;
            }

            idx = nextIdx + child.length();
            ans++;

        }

        return ans;
    }

    public static void main(String[] args) {
        String parent = "abc ab abedab";
        String child = "ab";
//        System.out.println(strStr(parent, child));
        Pattern p = Pattern.compile(child);
        Matcher m = p.matcher(parent);

        String[] arr = ("+" + parent + "+").split(child);
        System.out.println(arr.length - 1);

    }
}
