package cn.lastwhisper.leetcode.hashtable.单词规律_290_Easy;

import java.util.HashMap;
import java.util.Map;

class Solution1 {
    /**
     * https://leetcode-cn.com/problems/word-pattern/
     * 核心思想：map
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public boolean wordPattern(String pattern, String str) {
        if (pattern == null || str == null) return false;
        String[] strArr = str.split(" ");
        if (pattern.length() != strArr.length) return false;
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < strArr.length; i++) {
            if (map.containsKey(pattern.charAt(i))) {
                // key存在,value不相同false
                // a=dog,a=cat false
                if (!strArr[i].equals(map.get(pattern.charAt(i)))) return false;
            } else {
                // key不存在,value相同false
                // a=dog,b=dog false
                if (map.containsValue(strArr[i])) return false;
                map.put(pattern.charAt(i), strArr[i]);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().wordPattern("abba", "dog cat cat dog"));
        System.out.println(new Solution1().wordPattern("abba", "dog cat cat fish"));
        System.out.println(new Solution1().wordPattern("aaaa", "dog cat cat dog"));
        System.out.println(new Solution1().wordPattern("abba", "dog dog dog dog"));
        System.out.println(new Solution1().wordPattern("", "dog"));
    }

}