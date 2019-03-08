package Amazon;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPDFS {

    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backTrack(s, result, new ArrayList<String>(), 0);
        return result;
    }

    private static void backTrack(String s, List<String> result, List<String> temp, int idx) {
        if(temp.size() == 4) {
            if(idx != s.length()) {
                return;
            }
            String res = String.join(".", temp);
            result.add(res);
        }

        for(int i = idx; i < idx + 3 && i < s.length(); i++) {
            String substr = s.substring(idx, i + 1);
            if(isValid(substr)) {
                temp.add(substr);
                backTrack(s, result, temp, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
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
