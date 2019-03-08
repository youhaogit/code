package Amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KSubstring {

//    find all substring with k distinct characters && don't include duplicate substring
    public static List<String> KDistinctSubstring(String str, int k) {
        List<String> result = new ArrayList<>();
        if(str == null || str.length() == 0 || k <= 0) {
            return result;
        }

        Set<Character> set = null;
        Set<String> duplicate = new HashSet<>();

        for(int i = 0; i < str.length(); i++) {
            set = new HashSet<>();
            for(int j = i; j < str.length(); j++) {
                set.add(str.charAt(j));
                if(set.size() == k) {
                    String candidate = str.substring(i, j + 1);
                    if(!duplicate.contains(candidate)) {
                        duplicate.add(candidate);
                        result.add(candidate);
                    }
                }
            }
        }

        return result;
    }

    //all substring with k distinct character, consider duplicated substring
    public static List<String> KSubstring(String str, int k) {
        List<String> result = new ArrayList<>();
        if(str == null || str.length() == 0 || k <= 0) {
            return result;
        }

        Set<Character> set = null;
        for(int i = 0; i < str.length(); i++) {
            set = new HashSet<>();
            for(int j = i; j < str.length(); j++) {
                set.add(str.charAt(j));
                if(set.size() == k) {
                    String candidate = str.substring(i, j + 1);
                    result.add(candidate);
                }
            }
        }

        return result;
    }


    public static List<String> KSubstringWithLengthK(String str, int k) {
        List<String> result = new ArrayList<>();
        if(str == null || str.length() == 0 || k <= 0) {
            return result;
        }

        Set<Character> set = new HashSet<>();
        Set<String> duplicate = new HashSet<>();

        int left = 0;
        for(int right = 0; right < str.length(); right++) {
            set.add(str.charAt(right));
            if(right - left + 1 == k && set.size() == k) {
                String candidate = str.substring(left, left + k);
                if(!duplicate.contains(candidate)) {
                    duplicate.add(candidate);
                    result.add(candidate);
                }
                set.remove(str.charAt(left++));
            }
        }

        return result;
    }


    public static void main(String[] args) {
        String str = "abcbaa";
        System.out.println(KDistinctSubstring(str, 3));
        System.out.println(KSubstring(str, 3));
        System.out.println(KSubstringWithLengthK(str, 3));
    }
}


