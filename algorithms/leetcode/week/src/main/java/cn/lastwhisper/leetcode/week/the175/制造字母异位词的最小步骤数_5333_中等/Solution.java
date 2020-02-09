package cn.lastwhisper.leetcode.week.the175.制造字母异位词的最小步骤数_5333_中等;

class Solution {
    public int minSteps(String s, String t) {
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 97]++;
            map[t.charAt(i) - 97]--;
        }
        int count = 0;
        for (int value : map) {
            if (value > 0) {
                count += value;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        //String s = "bab", t = "aba";
        String s = "leetcode", t = "practice";
        System.out.println(new Solution().minSteps(s, t));
    }
}