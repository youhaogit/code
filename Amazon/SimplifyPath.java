package Amazon;

import java.util.Deque;
import java.util.LinkedList;

public class SimplifyPath {

    public static String simplifyPath(String path) {
        if(path == null || path.length() == 0) {
            return null;
        }

        String[] sub = path.split("/");
        Deque<String> stack = new LinkedList<>();
        for(String s: sub) {
            if(s.equals("/") || s.equals(".") || s.equals("")) {
                continue;
            }

            if(s.equals("..")) {
                if(stack.size() >= 1) {
                    stack.pop();
                }
            }else {
                stack.push(s);
            }
        }

        if(stack.size() == 0) {
            return "/";
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append("/");
            sb.append(stack.pollLast());
        }

        return sb.toString();

    }

    public static void main(String[] args) {
        String path = "/a//b////c/d//././/..";
        System.out.println(simplifyPath(path));
    }
}
