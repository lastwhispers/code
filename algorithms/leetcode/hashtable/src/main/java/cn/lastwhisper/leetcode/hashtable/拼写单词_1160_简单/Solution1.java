package cn.lastwhisper.leetcode.hashtable.拼写单词_1160_简单;

import org.junit.Assert;

import java.util.Arrays;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/find-words-that-can-be-formed-by-characters/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public int countCharacters(String[] words, String chars) {
        int[] charsFreq = new int[26];
        // 统计频率
        for (int i = 0; i < chars.length(); i++) {
            charsFreq[chars.charAt(i) - 'a']++;
        }
        int ans = 0;

        int[] wordFreq = new int[26];
        for (String word : words) {
            // 字母表必须比单词长，字母只许出现一次
            if (chars.length() >= word.length()) {
                Arrays.fill(wordFreq, 0);
                boolean flag = true;
                for (int i = 0; i < word.length(); i++) {
                    wordFreq[word.charAt(i) - 'a']++;
                    // 先加，在比较
                    if (wordFreq[word.charAt(i) - 'a'] > charsFreq[word.charAt(i) - 'a']) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    ans += word.length();
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Assert.assertEquals(6, new Solution1().countCharacters(new String[]{"cat", "bt", "hat", "tree"}, "atach"));
    }
}