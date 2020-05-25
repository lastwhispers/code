package cn.lastwhisper.leetcode.array.找到字符串中所有字母异位词_438_中等;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
     * 编号：438
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：双指针（滑动窗口）
     *  （1）双指针 l、r 维护一个滑动窗口，初始窗口扩容至模式串 p 的长度
     *  （2）判断当前 [l,r] 是否满足 异位词，如果满足，记录当前 l 的下标，
     *  （3）窗口整体右移
     *  （4）循环2、3，条件 r 未越界，r 未越界，l 肯定未越界
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2) //n 是 s 的长度、m 是 p 的长度
     * 空间复杂度：O(1)
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        // 特判
        if (s == null || s.length() < p.length()) {
            return result;
        }
        // 直接让 [l,r] 满足模式串长度
        int l = 0, r = p.length() - 1;
        int[] visited = new int[26];

        while (r < s.length()) {
            // 检查是否是异位词
            if (anagram(s, l, r, p, visited)) {
                result.add(l);
            }
            l++;
            r++;
        }
        return result;
    }

    public boolean anagram(String s, int l, int r, String p, int[] visited) {
        Arrays.fill(visited, 0);

        for (int i = l; i <= r; i++) {
            visited[s.charAt(i) - 'a']++;
            visited[p.charAt(i - l) - 'a']--;
        }

        for (int count : visited) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";
        //String s = "abab", p = "ab";
        System.out.println(new Solution1().findAnagrams(s, p));
    }
}