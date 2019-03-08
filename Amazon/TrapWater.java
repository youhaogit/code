package Amazon;

public class TrapWater {

    public static int trap(int[] height) {
        if(height == null || height.length == 0) {
            return 0;
        }

        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int ans = 0;
        while(left < right) {
            if(height[left] < height[right]) {
                if(leftMax >= height[left]) {
                    ans += leftMax - height[left];
                }else {
                    leftMax = height[left];
                }
                left++;
            }else {
                if(rightMax >= height[right]) {
                    ans += rightMax - height[right];
                }else {
                    rightMax = height[right];
                }
                right--;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
    }
}
