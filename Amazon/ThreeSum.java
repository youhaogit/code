package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }

        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i++) {
            if(i == 0 || i > 0 && nums[i] != nums[i - 1]) {
                int target = -nums[i];
                int left = i + 1;
                int right = nums.length - 1;
                while(left < right) {
                    int sum = nums[left] + nums[right];
                    if(sum < target) {
                        left++;
                    }else if(sum > target) {
                        right--;
                    }else {
                        List<Integer> set = new ArrayList<>();
                        set.add(nums[i]);
                        set.add(nums[left]);
                        set.add(nums[right]);
                        result.add(set);

                        while(left < right && nums[left + 1] == nums[left]) {
                            left++;
                        }
                        while(left < right && nums[right - 1] == nums[right]) {
                            right--;
                        }

                        // remember to increment two pointers after discarding duplicate values
                        left++;
                        right--;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));
    }
}
