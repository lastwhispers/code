package cn.lastwhisper.leetcode.hashtable.同构字符串_205_Esay;

class Solution2 {
    /**
     * https://leetcode-cn.com/problems/isomorphic-strings/
     * 核心思想：位图
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if(map[sc[i]]!=map[tc[i]+128]){
                return false;
            }
            map[sc[i]]=map[tc[i]+128]=t.charAt(i);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().isIsomorphic("egg", "add"));
        System.out.println(new Solution2().isIsomorphic("foo", "bar"));
        System.out.println(new Solution2().isIsomorphic("paper", "title"));
        System.out.println(new Solution2().isIsomorphic("ab", "bb"));
    }
}