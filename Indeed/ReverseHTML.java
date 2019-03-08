package Indeed;

public class ReverseHTML {

//    "1234&euro;567" => "765&euro;4321"
    public static String reverseHTML(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }

        s = s.trim();
        StringBuffer sb = new StringBuffer();
        for(int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }

        int left = 0, right = 0;
        for(int i = 0; i < sb.length(); i++) {
            if(sb.charAt(i) == ';') {
                left = i;
            }
            if(sb.charAt(i) == '&' && sb.charAt(left) == ';') {
                right = i;

                //reverse html entity
                while(left < right) {
                    char temp = sb.charAt(right);
                    sb.setCharAt(right--, sb.charAt(left));
                    sb.setCharAt(left++, temp);
                }
                left = i + 1;
            }
        }

        return sb.toString();
    }



    public static void main(String[] args) {
        String s = "aaa;aaa &amp; bbb;;;";
        System.out.println(reverseHTML(s));
    }
}
