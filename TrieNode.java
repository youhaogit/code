public class TrieNode {

    private static final int R = 26;
    TrieNode[] links;
    boolean isWord;

    public TrieNode() {
        links = new TrieNode[R];
        isWord = false;
    }

    public boolean containsKey(char key) {
        return links[key - 'a'] != null;
    }

    public TrieNode get(char key) {
        return links[key - 'a'];
    }

    public void put(char c, TrieNode node) {
        links[c - 'a'] = node;
    }

    public void setEnd() {
        this.isWord = true;
    }
}
