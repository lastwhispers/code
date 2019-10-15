package cn.lastwhisper.leetcode.array.滑动窗口.找到字符串中所有字母异位词_438_Medium;

import java.util.ArrayList;
import java.util.List;

class Solution2 {

    public List<Integer> findAnagrams(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        // 保存当前窗口的字符出现次数的哈希表
        char[] sLetters = new char[26];
        // 保存目标字符串字符出现次数的哈希表
        char[] pLetters = new char[26];
        ArrayList<Integer> ans = new ArrayList<>(sl / pl);
        // 填充目标字符串的哈希表
        for (int i = 0; i < pl; i++) {
            pLetters[pArr[i] - 'a']++;
        }
        // 滑动窗口
        int left = 0;
        int right = 0;
        while (right < sl) {
            // 当前字符在数组中的索引
            int cur = sArr[right++] - 'a';
            // 记录窗口中字符的出现次数
            sLetters[cur]++;
            // 如果在窗口中的当前字符的出现次数大于目标字符串中该字符的出现次数，即不符合条件，则右移窗口。
            // 无论是字符出现次数多余目标字符串还是字符不在目标字符串数组中，右移窗口后都可以解决。
            while (pLetters[cur] < sLetters[cur]) {
                // 这里将位于left上的字符（滑动窗口最左边的字符）从sLetters(记录窗口中字符出现次数的哈希表）中减去（值减一，抽象上等同于把该字符移出滑动窗口），同时left++，完成窗口右移。
                sLetters[sArr[left++] - 'a']--;
            }
            // 窗口调整完成后，判断当前窗口是否满足条件，即窗口长度和目标字符串长度是否相等。
            if (right - left == pl) ans.add(left);
        }

        return ans;
    }

    public static void main(String[] args) {
        //new Solution2().findAnagrams("cbaebabacd", "abc").forEach(System.out::print);
        //System.out.println();
        new Solution2().findAnagrams("abab", "ab").forEach(System.out::print);
        System.out.println();
        //new Solution2().findAnagrams("", "a").forEach(System.out::print);//错误数据
    }
}