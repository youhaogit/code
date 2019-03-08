package Indeed;

public class AppendTag {

//    题目是：“all” ，“There is all in all, two alls”
//    输出：“There is <b>all</b> in <b>all</b>, two alls”;

    public static String appendTag(String str, String key) {
        char[] arr = str.toCharArray();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == key.charAt(0)) {
                if(isValid(arr, key, i, i + key.length())) {
                    sb.append("<b>");
                    sb.append(key);
                    sb.append("</b>");

                    i += key.length();
                }
            }
            sb.append(arr[i]);
        }

        return sb.toString();
    }

    private static boolean isValid(char[] arr, String key, int left, int right) {
        int j = 0;
        for(int i = left; i < right; i++) {
            if(arr[i] != key.charAt(j++)) {
                return false;
            }
        }

        return !Character.isLetterOrDigit(arr[right]);
    }

    public static void main(String[] args) {
        String s = "There is all in all, two alls";
        System.out.println(appendTag(s, "all in all"));
    }

}
