package cn.lastwhisper.leetcode.binarytree.单词的压缩编码_820_中等;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/short-encoding-of-words/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *  按长度排序，逆序插入字典树，
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int minimumLengthEncoding(String[] words) {
        int len = 0;
        // 先对单词列表根据单词长度由长到短排序
        Arrays.sort(words, (s1, s2) -> s2.length() - s1.length());
        Trie trie = new Trie();
        // 单词插入trie，返回该单词增加的编码长度
        for (String word : words) {
            len += trie.insert(word);
        }
        return len;
    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        public TrieNode() {}
    }

    static class Trie {

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public int insert(String word) {
            TrieNode cur = root;
            boolean isNew = false;
            // 倒着插入单词
            for (int i = word.length() - 1; i >= 0; i--) {
                int c = word.charAt(i) - 'a';
                if (cur.children[c] == null) {
                    isNew = true;
                    cur.children[c] = new TrieNode();
                }
                cur = cur.children[c];
            }
            // +1是#
            return isNew ? word.length() + 1 : 0;
        }
    }

    public static void main(String[] args) {

        Assert.assertEquals(10,new Solution1().minimumLengthEncoding(new String[]{"time", "me", "bell"}));
    }
}