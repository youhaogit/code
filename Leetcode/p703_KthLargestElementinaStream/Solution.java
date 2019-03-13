package Leetcode.p703_KthLargestElementinaStream;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {

    static class KthLargest {

        PriorityQueue<Integer> heap;
        int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            this.heap = new PriorityQueue<>();

            for(int n: nums) {
                add(n);
            }
        }

        public int add(int val) {
            if(heap.size() < k) {
                heap.offer(val);
            }else {
                if(heap.peek() < val) {
                    heap.poll();
                    heap.offer(val);
                }
            }

            return heap.peek();
        }
    }

    public static void main(String[] args) {
//        Solution s = new Solution();
//        KthLargest kthLargest = new KthLargest(3, new int[]{4,5,8,2});
//        System.out.println(kthLargest.add(3));
//        System.out.println(kthLargest.add(5));
//        System.out.println(kthLargest.add(10));
//        System.out.println(kthLargest.add(9));
//        System.out.println(kthLargest.add(4));

        int[] A = new int[]{1,2,3,4};
        int[] B = Arrays.copyOf(A, A.length);

        for(int n: B) {
            System.out.println(n);
        }

    }
}
