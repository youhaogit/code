package Indeed;

import java.util.*;

public class SummaryRangeNotSorted {

    static class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public String toString() {
            return this.start + "->" + this.end;
        }
    }

    public static List<String> summaryRangesNotSorted(int[] nums) {
        List<String> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }

        // create intervals
        PriorityQueue<Interval> pq = new PriorityQueue<>((Interval a, Interval b) -> (a.start - b.start));
        for(int start = 0, end = 0; end < nums.length; end++) {
            if(end + 1 < nums.length && (nums[end] == nums[end + 1] || nums[end] + 1 == nums[end + 1])) {
                continue;
            }

            if(start == end) {
                pq.offer(new Interval(nums[start], nums[start]));
            }else {
                pq.offer(new Interval(nums[start], nums[end]));
            }

            start = end + 1;
        }

        for(Interval intt: pq) {
            System.out.println(intt.toString());
        }


        Interval pre = pq.poll();
        result.add(pre.toString());
        while(!pq.isEmpty()) {

            Interval cur = pq.poll();
            //merge intervals
            if(cur.start == pre.end || cur.start == pre.end + 1) {
                result.remove(result.size() - 1);
                Interval mergedInterval = new Interval(pre.start, cur.end);
                result.add(mergedInterval.toString());
                pre = mergedInterval;
            }else {
                result.add(cur.toString());
                pre = cur;
            }
        }

        return result;
    }


    public static List<String> summaryRangesNotSortedII(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();
        for(int n: nums) {
            map.put(n, true);
        }

        List<String> result = new ArrayList<>();
        for(Integer key: map.keySet()) {
            if(map.get(key)) {
                int l = key, r = key;
                while(map.containsKey(l - 1) && map.get(l - 1)) {
                    l--;
                    map.put(l, false);
                }
                while(map.containsKey(r + 1) && map.get(r + 1)) {
                    r++;
                    map.put(r, false);
                }

                if(l == r) {
                    result.add(l + "");
                }else {
                    result.add(l + "->" + r);
                }
            }

        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-3, -2, 1, 2, 3, 5, 8, 10, 11, 12, 15};
        System.out.println(summaryRangesNotSorted(nums));
        System.out.println(summaryRangesNotSortedII(nums));
    }
}
