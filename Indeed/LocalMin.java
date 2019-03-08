package Indeed;

public class LocalMin {

    public static int localMin(int[] nums) {
        if(nums == null || nums.length == 0) {
            return -1;
        }

        if(nums.length == 1) {
            return 0;
        }

        int i = 1;
        for(; i < nums.length; i++) {
            if(nums[i - 1] < nums[i]) {
                return i - 1;
            }
        }

        return nums[i - 1] == nums[i - 2] ? -1 : i - 1;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1};
        System.out.println(localMin(nums));
    }
}
