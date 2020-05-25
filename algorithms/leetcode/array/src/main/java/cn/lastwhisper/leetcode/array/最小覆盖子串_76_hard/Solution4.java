package cn.lastwhisper.leetcode.array.最小覆盖子串_76_hard;

class Solution4 {
    public String minWindow(String s, String t) {
        //把字符串转换为字符数组
        char[] sChars = s.toCharArray();
        char[] pChars = t.toCharArray();
        //
        int[] pMap = new int[128];
        //左指针，右指针
        int i = 0, j = 0; // 考察窗口[i,j-1]
        //还没有覆盖的字符数量
        int count = pChars.length;

        //初始化记录答案的变量
        int minLen = s.length() + 1, l = 0, r = 0;

        //记录t串中每种字符出现的次数
        for (char pChar : pChars)
            pMap[pChar]++;

        while (j < sChars.length) {

            //减小计数
            if (pMap[sChars[j]] > 0)
                count--;
            pMap[sChars[j]]--;
            j++;

            //计数为 0说明区间[i,j-1] 包含 p
            while (count == 0) {

                //更新答案
                if (j - i < minLen) {
                    minLen = j - i;
                    l = i;
                    r = j;
                }

                pMap[sChars[i]]++;
                // 增加计数
                if (pMap[sChars[i]] > 0)
                    count++;
                i++;
            }
        }
        return minLen == s.length() + 1 ? "" : s.substring(l, r);
    }
}