package cn.lastwhisper.leetcode.hashtable.同构字符串_205_Esay;

import java.util.HashMap;
import java.util.Map;

class Solution1 {
    /**
     * https://leetcode-cn.com/problems/isomorphic-strings/
     * 核心思想：map
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                // key存在,value不相同false
                if (map.get(s.charAt(i)) != t.charAt(i)) return false;
            } else {
                // key不存在,value相同false
                if (map.containsValue(t.charAt(i))) return false;
                map.put(s.charAt(i), t.charAt(i));
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().isIsomorphic("egg", "add"));
        System.out.println(new Solution1().isIsomorphic("foo", "bar"));
        System.out.println(new Solution1().isIsomorphic("paper", "title"));
        System.out.println(new Solution1().isIsomorphic("ab", "bb"));
    }
}