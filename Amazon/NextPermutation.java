package Amazon;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class NextPermutation {

    public static void nextPermutation(int[] nums) {
        List<List<Integer>> all = new LinkedList<>();
        generateAll(nums, all, new LinkedList<Integer>());
        for(List<Integer> a: all) {
            for(Integer b: a) {
                System.out.print(b);
            }
            System.out.println("\n");
        }
    }

    private static void generateAll(int[] nums, List<List<Integer>> all, LinkedList<Integer> subset) {
        if(subset.size() == nums.length) {
            all.add(new LinkedList<>(subset));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if(subset.contains(nums[i])) {
                continue;
            }
            subset.add(nums[i]);
            generateAll(nums, all, subset);
            subset.remove(subset.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        nextPermutation(nums);
    }
}
