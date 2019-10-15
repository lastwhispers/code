package cn.lastwhisper.leetcode.array.滑动窗口.最小覆盖子串_76_hard;

class Solution1 {
    /**
     * https://leetcode-cn.com/problems/minimum-window-substring/
     * 核心思想：滑动窗口
     * 超时
     */
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";//"a", "aa"
        if (s.equals(t)) return s;//"a", "a"
        //  用于判断s子串是否包含t
        int[] map = new int[128];
        String target = "";//"abc", "ac"；"a", "b"
        int l = 0, r = 0;
        while (l <= s.length() && r <= s.length()) {
            if (!isSub(s.substring(l, r), t, map)) {
                // 不断增加 r 指针扩大窗口 [l, r]，直到窗口中的字符串符合要求
                r++;
            } else {
                // 不断增加 l 指针缩小窗口 [l, r]，直到窗口中的字符串不再符合要求
                if ("".equals(target)) {
                    target = s.substring(l, r);
                } else {
                    target = target.length() > s.substring(l, r).length() ? s.substring(l, r) : target;
                }
                l++;
            }

        }
        return target;
    }

    /**
     * sub是否包含t
     */
    public boolean isSub(String sub, String t, int[] map) {
        if (sub.length() < t.length()) return false;//过滤肯定不包含
        boolean flag = true;
        for (int i = 0; i < sub.length(); i++) {
            map[sub.charAt(i)] += 1;
        }
        // 检验t在sub是否完全出现过
        for (int i = 0; i < t.length(); i++) {
            if (map[t.charAt(i)] > 0) {
                //t在sub出现过
                map[t.charAt(i)] -= 1;
            } else {
                //t在sub没出现过
                flag = false;
            }
        }
        // 检验sub是否完全包含t
        for (int i = 0; i < t.length(); i++) {
            if (map[t.charAt(i)] < 0) {
                flag = false;
            }
        }
        // 清空
        for (int i = 0; i < sub.length(); i++) {
            map[sub.charAt(i)] = 0;
        }
        return flag;
    }

    public static void main(String[] args) {
        System.out.println("target:" + new Solution1().minWindow("ADOBECODEBANC", "ABC"));
        System.out.println("target:" + new Solution1().minWindow("a", "a"));
        System.out.println("target:" + new Solution1().minWindow("a", "aa"));
        System.out.println("target:" + new Solution1().minWindow("a", "b"));
        System.out.println("target:" + new Solution1().minWindow("abc", "ac"));
        System.out.println("target:" + new Solution1().minWindow("bbaa", "aba"));
        System.out.println("target:" + new Solution1().minWindow("abbc", "abc"));
        //int[] arr = new int[128];
        //System.out.println(new Solution1().isSub("ADCB", "ABC", arr));
        //System.out.println(new Solution1().isSub("ADQCB", "ABC", arr));
        //System.out.println(new Solution1().isSub("ADeB", "ABC", arr));
        //System.out.println(new Solution1().isSub("abbc", "abc", arr));
        //System.out.println(new Solution1().isSub("bbaa", "aba", arr));
    }
}