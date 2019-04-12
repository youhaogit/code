package Amazon;

import java.util.Deque;
import java.util.LinkedList;

public class JumpGameII {

    public static int jump(int[] nums) {

        if(nums.length == 1)
            return 0;

        int level=0, currentMax=0, i=0, nextMax=0;
        while(currentMax - i + 1 > 0) {
            level++;
            for(; i <= currentMax; i++) {
                nextMax = Math.max(nextMax, i + nums[i]);
                if(nextMax >= nums.length - 1) {
                    return level;
                }
            }
            currentMax = nextMax;
        }

        return -1;
    }

    public static int jumpI(int[] nums) {
        // bfs
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int curMax = 0;
        int i = 0;

        int step = 0;
        while(curMax - i + 1 > 0) {
            step++;

            int nextMax = 0;
            for(; i <= curMax; i++) {
                nextMax = Math.max(nextMax, i + nums[i]);
                if(nextMax >= nums.length - 1) {
                    return step;
                }
            }
            curMax = nextMax;
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] nums1 = {2,3,1,1,4};
        System.out.println(jump(nums1));
        System.out.println(jumpI(nums1));
    }
}

