package Amazon;

import java.util.*;

public class WordLadderII {

    static class Node {
        String val;
        Node parent;
        List<Node> children;

        public Node (String val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }


    //naive approach would be
    // 1. bfs to construct the graph
    // 2. dfs to build the result
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        if(!wordList.contains(endWord)) {
            return result;
        }

        Node root = new Node(beginWord);
        Set<String> dict = new HashSet<>(wordList);
        // 1. bfs to construct the graph
        bfs(root, dict, endWord);

        // 2. dfs to build the result
        dfs(root, result, new ArrayList<String>());

        int length = Integer.MAX_VALUE;
        for(List<String> path: result) {
            if(path.size() < length) {
                length = path.size();
            }
        }

        List<List<String>> onDelete = new ArrayList<>();
        for(List<String> path: result) {
            if(path.size() > length) {
                onDelete.add(path);
            }
        }

        for(List<String> path: onDelete) {
            result.remove(path);
        }

        return result;
    }

    private static void dfs(Node root, List<List<String>> result, List<String> path) {
        if(root == null) {
            return;
        }
        if(root.children.size() == 0) {
            path.add(root.val);
            result.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }

        path.add(root.val);
        for(Node child: root.children) {
            dfs(child, result, path);
        }
        path.remove(path.size() - 1);
    }


    private static void bfs(Node root, Set<String> dict, String endWord) {

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        dict.remove(root.val);

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if(cur.val.equals(endWord)) {
                    return;
                }

                List<Node> children = getChildren(cur, dict, endWord);
                for(Node child: children) {
                    child.parent = cur;
                    cur.children.add(child);
                    queue.offer(child);
                }
            }

            if(queue.size() == 0) {
                queue.add(new Node("NULL"));
                return;
            }
        }

    }

    private static List<Node> getChildren(Node root, Set<String> dict, String endWord) {
        List<Node> list = new ArrayList<>();

        char[] arr = root.val.toCharArray();
        for(int i = 0; i < arr.length; i++) {
            char temp = arr[i];
            for(char c = 'a'; c <= 'z'; c++) {
                arr[i] = c;
                String candidate = String.valueOf(arr);
                if(dict.contains(candidate)) {
                    list.add(new Node(candidate));
                    if(!candidate.equals(endWord)) {
                        dict.remove(candidate);
                    }
                }
            }
            arr[i] = temp;
        }

        return list;
    }




    public static void main(String[] args) {
        String begin = "hot";
        String end = "dog";
        List<String> set = new ArrayList<>();
        set.add("hot");
        set.add("cog");
        set.add("dog");
        set.add("tot");
        set.add("hog");
        set.add("hop");
        set.add("pot");
        set.add("dot");




        System.out.println(findLadders(begin, end, set));
    }
}
