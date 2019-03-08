import java.util.Arrays;

public class MergeSortedArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums1 == null || nums2 == null) {
            return;
        }


        int idx1 = m - 1, idx2 = n - 1, j =  m + n - 1;
        while(idx1 >= 0 && idx2 >= 0) {
            nums1[j--] = nums2[idx2] > nums1[idx1] ? nums2[idx2--] : nums1[idx1--];
        }

        while(idx1 >= 0) {
            nums1[j--] = nums1[idx1--];
        }

        while(idx2 >= 0) {
            nums1[j--] = nums2[idx2--];
        }

        return;

    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        merge(nums, 3, nums2, 3);
        for(int a: nums) {
            System.out.println(a);
        }
    }
}
