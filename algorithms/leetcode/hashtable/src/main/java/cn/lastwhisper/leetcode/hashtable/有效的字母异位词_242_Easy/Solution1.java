package cn.lastwhisper.leetcode.hashtable.有效的字母异位词_242_Easy;

import java.util.HashMap;
import java.util.Map;

class Solution1 {
    public boolean isAnagram(String s, String t) {
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < sChar.length; i++) {
            Integer num = map.get(sChar[i]);
            if (num == null) {
                map.put(sChar[i], 1);
            } else {
                map.put(sChar[i], ++num);
            }
        }

        for (int i = 0; i < tChar.length; i++) {
            Integer num = map.get(tChar[i]);
            if (num == null) {
                return false;
            } else {
                map.put(tChar[i], --num);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //String s = "anagram", t = "nagaram";
        String  s = "rat", t = "car";

        System.out.println(new Solution1().isAnagram(s, t));
    }

}