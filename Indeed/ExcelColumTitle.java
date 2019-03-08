package Indeed;

public class ExcelColumTitle {

    public static String convertToTitle(int n) {
        if(n <= 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            n--;
            int digit = n % 26;
            sb.append((char)('A' + digit));
            n = n / 26;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToTitle(28));
    }
}
