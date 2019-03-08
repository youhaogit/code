package Indeed;

import java.util.ArrayList;
import java.util.List;

public class MergeSortedArrayWithoutDuplicate {

    public static int[] mergeSortedArray(int[] nums1, int[] nums2) {
        if(nums1 == null) {
            return nums2;
        }else if(nums2 == null) {
            return nums1;
        }

        List<Integer> list = new ArrayList<>();
        int idx1 = 0, idx2 = 0;
        while(idx1 < nums1.length && idx2 < nums2.length) {
            if(nums1[idx1] < nums2[idx2]) {
                list.add(nums1[idx1++]);
            }else if(nums1[idx1] > nums2[idx2]) {
                list.add(nums2[idx2++]);
            }else {
                list.add(nums1[idx1++]);
                idx2++;
            }
        }

        while(idx1 < nums1.length) {
            list.add(nums1[idx1++]);
        }

        while(idx2 < nums2.length) {
            list.add(nums2[idx2++]);
        }

        int[] result = new int[list.size()];
        int idx = 0;
        for(int n: list) {
            result[idx++] = n;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3};
        int[] nums2 = {2,3,4};
        int[] result = mergeSortedArray(nums1, nums2);
        for(int n: mergeSortedArray(nums1, nums2)) {
            System.out.println(n);
        }
    }
}
