package cn.lastwhisper.leetcode.hashtable.根据字符出现频率排序_451_Middle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution1 {
    /**
     * https://leetcode-cn.com/problems/sort-characters-by-frequency/
     * 核心思想：桶排序,数组下标代表频率
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 执行用时 :32 ms, 在所有 Java 提交中击败了74.69%的用户
     * 内存消耗 :38.9 MB, 在所有 Java 提交中击败了95.29%的用户
     */
    public String frequencySort(String s) {
        if (s == null || s.equals("")) return "";
        Map<Character, Integer> map = new HashMap<>();
        // 记录字符出现的最大频率
        int maxFreq = 0;
        // 统计所有字符出现的频率
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
            Integer freq = map.get(c);
            maxFreq = freq > maxFreq ? freq : maxFreq;
        }
        // 按频率下标将字符存入对应桶中
        List<Character>[] buckets = new ArrayList[maxFreq + 1];
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Integer freq = entry.getValue();
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(entry.getKey());
        }
        // 倒序组装字符串
        StringBuilder sb = new StringBuilder();
        for (int i = maxFreq ; i > 0; i--) {
            List<Character> bucket = buckets[i];
            if (bucket != null) {
                for (Character c : bucket) {
                    for (int j = 0; j < i; j++) {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(new Solution1().frequencySort("tree"));
        System.out.println(new Solution1().frequencySort("cccaaa"));
        System.out.println(new Solution1().frequencySort("Aabb"));
    }
}