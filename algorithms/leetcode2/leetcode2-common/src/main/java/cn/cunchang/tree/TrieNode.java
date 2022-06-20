package cn.cunchang.tree;

public class TrieNode {
    // 26个字母
    private final int R = 26;
    // 不存储真实的值，TrieNode[]中不为null表示存在
    private TrieNode[] links;
    private boolean isEnd;

    public TrieNode() {
        links = new TrieNode[R];
    }


    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public TrieNode get(char ch) {
        return links[ch - 'a'];
    }

    public void put(char ch, TrieNode node) {
        links[ch - 'a'] = node;
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }
}