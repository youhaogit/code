package DataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MonotoneQueue_Stack {

//    6 4 10 10 8 6 4 2 12 14
//    给定一个数列，从左至右输出每个长度为m的数列段内的最小数和最大数。
//    数列长度：N<=106，m<=N

    public static List<Integer> MonotonoQueue(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if(nums == null || nums.length == 0 || k <= 0) {
            return result;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < nums.length; i++) {
            if(i < k - 1) {
                while(!queue.isEmpty() && nums[queue.peek()] <= nums[i]) {
                    queue.poll();
                }
                queue.offer(i);
            }else {


                // poll out invalid index which is out of range
                while(!queue.isEmpty() && queue.peek() <= i - k) {
                    queue.poll();
                }

                //offer new index iff it is smaller than peek
                while(!queue.isEmpty() && nums[queue.peek()] <= nums[i]) {
                    queue.poll();
                }
                queue.offer(i);

                result.add(nums[queue.peek()]);
            }
        }


        return result;
    }

    public static void main(String[] args) {
//        int[] nums = {6, 4, 10, 10, 8, 6, 4, 2, 12, 14};
//        System.out.println(MonotonoQueueGetMin(nums, 3));
    }
}
