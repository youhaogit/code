package Indeed;

import java.util.Stack;

public class ValidatePython {

//    public static boolean identification(String[] strs) {
//        if (strs == null || strs.length == 0)   return true;
//        if (countTab(strs[0]) != 0)     return false;
//        Stack<Integer> stack = new Stack<>();
//        stack.push(countTab(strs[0]));
//        for (int i = 1; i < strs.length; i++) {
//            int cur = countTab(strs[i]);
//            // 需要缩进
//            if (strs[i-1].charAt(strs[i-1].length()-1) == ':') {
//                if (cur <= stack.peek())
//                    return false;
//            }
//            else {
//                // 同一个块，不用缩进
//                if (cur > stack.peek())
//                    return false;
//                // 比之前的tab少，需要把stack之前的strs pop出来
//                while (!stack.isEmpty() && stack.peek() > cur) {
//                    stack.pop();
//                }
//                if (stack.peek() != cur)
//                    return false;
//            }
//            stack.push(cur);
//        }
//        return true;
//    }

    public static boolean identification(String[] lines) {
        if(lines == null || lines.length == 0) {
            return true;
        }

        Stack<Integer> stack = new Stack<>();
        if(countTab(lines[0]) != 0) {
            return false;
        }
        stack.push(countTab(lines[0]));

        for(int i = 1; i < lines.length; i++) {

            // #...
            if(lines[i].trim().charAt(0) == '#') {
                continue;
            }

            //control statement
            int curTab = countTab(lines[i]);
            if(lines[i - 1].charAt(lines[i - 1].length() - 1) == ':') {
                if(stack.peek() >= curTab) {
                    return false;
                }
            }else {
                //same block, no need identation
                while(stack.size() > 0 && stack.peek() > curTab) {
                    stack.pop();
                }

                if(stack.peek() != curTab) {
                    return false;
                }

            }
            stack.push(curTab);
        }

        return true;
    }


    public static int countTab(String s) {
        char[] arr = s.toCharArray();
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ')     cnt++;
            else    break;
        }
        return cnt;
    }
    public static void main(String[] args) {
        String[] strs = {"def function():",
                "  print(\"Hello world\")",
                "  ## print(\"Hello world\")",
                "  print(\"Hello world\")",
                "  if i==1:",
                "    print(\"asdf\")"};
        System.out.print(identification(strs));
    }
}
