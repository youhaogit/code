package Amazon;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

    static class KeyArr {
        int[] nums;
        public KeyArr(int[] nums) {
            this.nums = nums;
        }

        @Override
        public boolean equals(Object arr) {
            if(!(arr instanceof KeyArr)) {
                return false;
            }

            KeyArr otherKey = (KeyArr) arr;
            for(int i = 0; i < this.nums.length; i++) {
                if(nums[i] != ((KeyArr) arr).nums[i]) {
                    return false;
                }
            }
            return  true;
        }

        @Override
        public int hashCode() {
            int code = 1;
            for(int i = 0; i < nums.length; i++) {
                code = code * 31 + nums[i];
            }

            return code;
        }
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if(strs == null) {
            return result;
        }
        if(strs.length == 0) {
            result.add(new ArrayList<>());
            return result;
        }

        Map<KeyArr, List<String>> map = new HashMap<>();
        for(String str: strs) {
            KeyArr key = encodeStr(str);
            if(map.containsKey(key)) {
                map.get(key).add(str);
            }else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            }
        }

        for(List<String> list: map.values()) {
            result.add(list);
        }

        return result;
    }

    private static KeyArr encodeStr(String str) {
        int[] encode = new int[256];
        for(char c: str.toCharArray()) {
            encode[c]++;
        }

        return new KeyArr(encode);
    }

    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        KeyArr s1 = encodeStr("eat");
        KeyArr s2 = encodeStr("tea");
        System.out.println(s1.equals(s2));

        System.out.println(groupAnagrams(strs));
    }
}
