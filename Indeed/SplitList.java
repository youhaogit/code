package Indeed;

import java.util.*;

public class SplitList {


    public static List<List<Integer>> splitList(List<Integer> source) {
        List<List<Integer>> result = new ArrayList<>();
        while( source.size() > 0) {
            int i = 0;
            List<Integer> list = new ArrayList<>();
            Set<Integer> set = new HashSet<>();
            while(i<source.size()) {
                if(!set.contains(source.get(i))) {
                    list.add(source.get(i));
                    set.add(source.get(i));
                    source.remove(i);
                } else {
                    i++;
                }
            }
            result.add(list);
        }
        return result;
    }

//    public static List<List<Integer>> splitList(List<Integer> source) {
//        List<List<Integer>> result = new ArrayList<>();
//        if(source == null || source.size() == 0) {
//            return result;
//        }
//
//        Map<Integer, Integer> freqMap = new HashMap<>();
//        for(int n: source) {
//            freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
//        }
//
//        int maxFreq = 0;
//        int maxValue = Integer.MIN_VALUE;
//        for(Map.Entry<Integer, Integer> entry: freqMap.entrySet()) {
//            int key = entry.getKey();
//            int value = entry.getValue();
//            if(value > maxFreq) {
//                maxFreq = value;
//                maxValue = key;
//            }
//        }
//
//        for(int i = 0; i < maxFreq; i++) {
//            List<Integer> list = new ArrayList<>();
//            result.add(list);
//        }
//
//        int idx = 0;
//        for(int n: source) {
//            while(result.get(idx).contains(n)) {
//                idx++;
//                idx %= maxFreq;
//            }
//            result.get(idx).add(n);
//        }
//
//        return result;
//    }


    public static List<List<Integer>> splitListGreedy(List<Integer> source) {
        List<List<Integer>> result = new ArrayList<>();
        if(source == null || source.size() == 0) {
            return result;
        }

        while(source.size() > 0) {
            int idx = 0;
            List<Integer> list = new ArrayList<>();
            while(idx < source.size()) {
                if(!list.contains(source.get(idx))) {
                    list.add(source.get(idx));
                    source.remove(idx);
                }else {
                    idx++;
                }
            }
            result.add(list);
        }

        return result;
    }


    public static void main(String[] args) {
        Integer[] nums = {1,2,3,1,2,1,1,1,1,1,1};
        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(nums));
        System.out.println(splitListGreedy(list));
    }
}
