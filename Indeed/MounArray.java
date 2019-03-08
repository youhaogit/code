package Indeed;

public class MounArray {

    public static int peakIndexInMountainArray(int[] A) {
        if(A == null || A.length < 3) {
            return -1;
        }

        int left = 0, right = A.length - 1;
        while(left < right) {
            int mid = (left + right) / 2;
            if(A[mid] < A[mid + 1]) {
                left = mid + 1;
            }else {
                if(mid - 1 >= 0 && A[mid] > A[mid + 1] && A[mid] > A[mid - 1]) {
                    return mid;
                }else {
                    right = mid;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0,0,3,2};
        System.out.println(peakIndexInMountainArray(nums));
    }
}
