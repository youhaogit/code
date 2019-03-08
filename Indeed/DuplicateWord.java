package Indeed;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicateWord {
//    问题是输入一组word，找出duplicate
//    例如输入 "abc def ghi abc"，输出abc即可
    public static String findDuplicate(String str) {
        String[] strs = str.split(" ");
        Set<String> set = new HashSet<>();
        for(String s: strs) {
            if(set.contains(s)) {
                return s;
            }
            set.add(s);
        }

        return null;
    }



//    1. “java python python java" 返回python因为走到python是先出现了duplicate
//    2. “java python python java" 返回java因为在有duplicate的单词中，java第一次出现的最早
    static class Location {
        int pos;
        boolean repeated;
        public Location(int pos) {
            this.pos = pos;
            this.repeated = false;
        }
    }

    public static String earliestDuplicate(String s) {
        String[] strs = s.split(" ");
        Map<String, Location> map = new HashMap<>();

        int idx = 1;
        for(String str: strs) {
            if(map.containsKey(str)) {
                Location loc = map.get(str);
                if(!loc.repeated) {
                    loc.repeated = true;
//                    map.put(str, loc);
                }
            }else {
                map.put(str, new Location(idx));
            }
            idx++;
        }

        String ans = null;
        int pos = Integer.MAX_VALUE;
        for(Map.Entry<String, Location> entry: map.entrySet()) {
            if (entry.getValue().repeated && entry.getValue().pos < pos) {
                ans = entry.getKey();
                pos = entry.getValue().pos;
            }
        }

        return ans;
    }

    public static String earliestDuplicateII(String s) {
        String[] strs = s.split(" ");
        Map<String, Integer> map = new HashMap<>();
        Set<String> visited = new HashSet<>();

        int idx = 1;
        for(String str: strs) {
            if(!map.containsKey(str)) {
                map.put(str, idx);
            }else {
                visited.add(str);
            }
            idx++;
        }

        String ans = null;
        int loc = Integer.MAX_VALUE;
        for(String str: visited) {
            if(map.get(str) < loc) {
                ans = str;
                loc = map.get(str);
            }
        }


        return ans;
    }

    public static void main(String[] args) {
        String str = "cat dog cat";
        System.out.println(earliestDuplicateII(str));
    }

}
