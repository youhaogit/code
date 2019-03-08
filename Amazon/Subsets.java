package Amazon;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null) {
            return result;
        }else if(nums.length == 0) {
            result.add(new ArrayList<>());
            return result;
        }

        dfs(nums, result, new ArrayList<>(), 0);
        return result;
    }

    private static void dfs(int[] nums, List<List<Integer>> result, List<Integer> subset, int pos) {
        result.add(new ArrayList<>(subset));

        for(int i = pos; i < nums.length; i++) {
            if(subset.contains(nums[i])) {
                continue;
            }

            subset.add(nums[i]);
            dfs(nums, result, subset, i + 1);
            subset.remove(subset.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(subsets(nums));
    }
}
