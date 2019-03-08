package Amazon;

public class LAS {

    public static int getLAS(int[] arr) {
        if(arr == null || arr.length == 0 || arr.length < 3) {
            return 0;
        }

        int diff = Integer.MAX_VALUE;
        int count = 0;
        int res = 0;
        int left = 0;
        for(int i = 1; i < arr.length; i++) {
            int curDiff = arr[i] - arr[i - 1];
            if(curDiff == diff) {
                count += i - left > 1 ? i - left - 1 : 0;
            }else {
                diff = curDiff;
                left = i - 1;
                res += count;
                count = 0;
            }
        }

        res += count;
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {0,1,2,3,2,1};
        System.out.println(getLAS(arr));
    }
}
