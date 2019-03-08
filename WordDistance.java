import java.util.*;

public class WordDistance {

    class Pair {
        String word1;
        String word2;
        Pair(String word1, String word2) {
            this.word1 = word1;
            this.word2 = word2;
        }
    }

    private String[] list;
    private HashMap<Pair, Integer> map;

    public WordDistance(String[] words) {
        this.list = words;
    }

    public int shortest(String word1, String word2) {
        Pair newPair = new Pair(word1, word2);
        if(map.get(newPair) != null) {
            return map.get(newPair);
        }

        int idx1 = -1, idx2 = -1;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < list.length; i++) {
            if(list[i].equals(word1)) {
                idx1 = i;
            }
            if(list[i].equals(word2)) {
                idx2 = i;
            }

            if(idx1 != -1 && idx2 != -1) {
                min = Math.min(min, Math.abs(idx1 - idx2));
            }
        }

        map.put(newPair, min);
        return min;
    }

    public static void main(String[] args) {
        String[] words = {"practice","makes","perfect","coding","makes"};
        WordDistance wd = new WordDistance(words);
        System.out.println(wd.shortest("practice", "makes"));
        String s = "abc";

    }
}
