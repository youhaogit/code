package Leetcode.p636_ExclusiveTimeofFunctions;

import java.util.*;

public class Solution {

//    Input:
//    n = 2
//    logs =
//    ["0:start:0",
//    "1:start:2",
//    "1:end:5",
//    "0:end:6"]
//    Output:[3, 4]

    class Log {
        int id;
        boolean isStart;
        int time;

        Log(String log) {
            String[] detail = log.split(":");
            this.id = Integer.parseInt(detail[0]);
            this.isStart = detail[1].equals("start");
            this.time = Integer.parseInt(detail[2]);
        }
    }

    public int[] exclusiveTime(int n, List<String> logs) {

        int[] result = new int[n];
        if(logs == null || logs.size() == 0) {
            return result;
        }

        // use stack to process logs by timestamp
        Deque<Log> stack = new ArrayDeque<>();

        for(String content: logs) {
            // find matching log, update duration
            Log log = new Log(content);
            if(log.isStart) {
                stack.push(log);
            }else {
                Log head = stack.poll();
                int time = log.time - head.time + 1;
                result[head.id] += time;
                if(!stack.isEmpty()) {
                    result[stack.peek().id] -= time;
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 1;
        String[] logs = {"0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"};
        List<String> ss = new ArrayList<>(Arrays.asList(logs));
        int[] res = s.exclusiveTime(n, ss);

        for(int num: res) {
            System.out.println(num);
        }
    }
}