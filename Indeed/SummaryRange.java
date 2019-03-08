package Indeed;

import java.util.ArrayList;
import java.util.List;

public class SummaryRange {

//    Input:  [0,1,2,4,5,7]
//    Output: ["0->2","4->5","7"]
//    Consider duplicates as well
    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }

        for(int start = 0, end = 0; end < nums.length; end++) {
            if(end + 1 < nums.length && (nums[end] + 1 == nums[end + 1] || nums[end] == nums[end + 1])) {
                continue;
            }

            if(start == end) {
                result.add(nums[start] + "");
            }else {
                result.add(nums[start] + "->" + nums[end]);
            }

            start = end + 1;
        }

        return result;
    }

    public static List<String> summaryRangesStep(int[] nums) {
        List<String> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }

        int step = 0;
        for(int start = 0, end = 0; end < nums.length; end++) {
            if(end + 1 < nums.length) {
                step = nums[end + 1] - nums[end];
                while(end + 1 < nums.length && nums[end] + step == nums[end + 1]) {
                    end++;
                }
            }

            if(start == end) {
                result.add(nums[start] + "");
            }else {
                result.add(nums[start] + "->" + nums[end] + "/" + step);
            }
            start = end + 1;

        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,7,9,11, 12, 14, 16};
        System.out.println(summaryRangesStep(nums));
    }
}
