package cn.lastwhisper.leetcode.hashtable.字母异位词分组_49_中等;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/group-anagrams/
     * -------------------------------------------------------------------
     * 思路：排序字符串相等时，两个字符串是字母异位词
     * -------------------------------------------------------------------
     * 时间复杂度：O(N*K*logK) 其中 N 是 strs 的长度，而 K 是 strs 中字符串的最大长度
     * 空间复杂度：O(N*K)
     * -------------------------------------------------------------------
     * 执行用时 :11 ms, 在所有 java 提交中击败了97.16%的用户
     * 内存消耗 :42.2 MB, 在所有 java 提交中击败了97.29%的用户
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // 空值处理
        if (strs.length == 0) return new ArrayList<List<String>>();

        // key：排序后的字符串、value：未排序的字符串集List
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            // 排序后如果是字母异位词都长一个样
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        new Solution1().groupAnagrams(strs).forEach(list -> {
            System.out.println();
            list.forEach(System.out::print);
        });
    }
}