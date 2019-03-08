package Amazon;

import java.util.Arrays;
import java.util.Comparator;

public class ReorderLogFiles {

//    Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
//    Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]

//    Letter-logs come before digit-logs;
//    Letter-logs are sorted alphanumerically, by content then identifier;
//    Digit-logs remain in the same order.

    public static String[] reorderLogFiles(String[] logs) {
        if(logs == null || logs.length == 0) {
            return logs;
        }

//        String[] result = new String[logs.length];
        Arrays.sort(logs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] split1 = o1.split(" ", 2);
                String[] split2 = o2.split(" ", 2);
                boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
                boolean isDigit2 = Character.isDigit(split2[1].charAt(0));

                if(!isDigit1 && !isDigit2) {
                    int cmp = split1[1].compareTo(split2[1]);
                    if(cmp != 0) {
                        return cmp;
                    }
                    return split1[0].compareTo(split2[0]);
                }

                return isDigit1 ? (isDigit2 ? 0 : 1) : -1;

            }
        });

        return logs;
    }

    public static void main(String[] args) {
        String[] logs = {"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"};
        for(String s: reorderLogFiles(logs)) {
            System.out.println(s);
        }

        Integer a = 3;
        System.out.println(a.hashCode());
    }
}
