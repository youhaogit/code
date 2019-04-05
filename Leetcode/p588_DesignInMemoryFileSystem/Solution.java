package Leetcode.p588_DesignInMemoryFileSystem;

import java.util.*;

public class Solution {

    static class FileSystem {

        class File {
            Map<String, File> files;
            boolean isFile;
            String content;

            File() {
                files = new HashMap<>();
                isFile = false;
                content = "";
            }
        }

        File root;
        public FileSystem() {
            root = new File();
        }

        public List<String> ls(String path) {
            List<String> res = new ArrayList<>();
            File cur = root;

            if(!path.equals("/")) {
                String[] dirs = path.split("/");
                for(int i = 1; i < dirs.length; i++) {
                    cur = cur.files.get(dirs[i]);
                }

                if(cur.isFile) {
                    res.add(dirs[dirs.length - 1]);
                    return res;
                }
            }

            res.addAll(cur.files.keySet());
            Collections.sort(res);
            return res;
        }

        public void mkdir(String path) {
            if(path.equals("/")) {
                return;
            }

            String[] dirs = path.split("/");
            File cur = root;
            for(int i = 1; i < dirs.length; i++) {
                if(!cur.files.containsKey(dirs[i])) {
                    cur.files.put(dirs[i], new File());
                }
                cur = cur.files.get(dirs[i]);
            }
        }

        public void addContentToFile(String filePath, String content) {
            String[] dirs = filePath.split("/");
            File cur = root;
            for(int i = 1; i < dirs.length; i++) {
                if(!cur.files.containsKey(dirs[i])) {
                    cur.files.put(dirs[i], new File());
                }
                cur = cur.files.get(dirs[i]);
            }

            cur.isFile = true;
            cur.content += content;
        }

        public String readContentFromFile(String filePath) {
            String[] dirs = filePath.split("/");
            File cur = root;
            for(int i = 1; i < dirs.length; i++) {
                cur = cur.files.get(dirs[i]);
            }

            return cur.content;
        }
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        FileSystem fs = new FileSystem();
        System.out.println(fs.ls("/"));
        fs.mkdir("/a/b/c");
        fs.addContentToFile("/a/b/c/d", "hello");
        System.out.println(fs.ls("/"));
        System.out.println(fs.readContentFromFile("/a/b/c/d"));
    }
}