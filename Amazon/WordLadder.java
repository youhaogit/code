package Amazon;

import java.util.*;

public class WordLadder {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(beginWord.equals(endWord)) {
            return 0;
        }

        boolean[] visited = new boolean[wordList.size()];
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int step = 0;
        while(!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String cur = queue.poll();
                if(cur.equals(endWord)) {
                    return step;
                }
                for(int j = 0; j < wordList.size(); j++) {
                    if(!visited[j] && editOneChar(wordList.get(j), cur)) {
                        visited[j] = true;
                        queue.offer(wordList.get(j));
                    }
                }
            }

            if(queue.size() == 0) {
                return 0;
            }
        }

        return step;
    }

    private static boolean editOneChar(String s1, String s2) {
        for(int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                return s1.substring(i + 1).equals(s2.substring(i + 1));
            }
        }
        return false;
    }


    public static void main(String[] args) {

    }
}
