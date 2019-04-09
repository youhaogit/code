package Leetcode.p532_KdiffPairsinanArray;

import java.util.*;

public class Solution {

    public int findPairs(int[] nums, int k) {

        int ans = 0;

        if(k<0) return ans;

        Set<Integer> set1 = new HashSet<Integer> ();
        Set<Integer> set2 = new HashSet<Integer> ();

        if(k==0){
            for(int n:nums){
                if(!set1.contains(n)) {
                    set1.add(n);
                }
                else{
                    set1.remove(n);
                    if(!set2.contains(n)) ans++;
                    set2.add(n);
                }
            }
        }
        else{
            for(int n:nums){
                set1.add(n);
                set2.add(n+k);
            }
            set1.retainAll(set2);
            ans = set1.size();
        }

        return ans;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {3, 1, 4, 1, 5};
        System.out.println(s.findPairs(nums, 0));

    }
}