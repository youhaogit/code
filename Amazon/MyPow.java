package Amazon;

public class MyPow {

    public static double myPow(double x, int n) {
        if(n < 0) {
            n = -n;
            x = 1.0 / x;
        }

        return fastPow(x, n);
    }

    private static double fastPow(double x, int n) {
        if(n == 0) {
            return 1.0;
        }

        double half = fastPow(x, n/2);
        if(n % 2 == 0) {
            return half * half;
        }else{
            return half * half * x;
        }
    }

    public static void main(String[] args) {
        System.out.println(myPow( 1.00000,
                2147483647));
    }
}
