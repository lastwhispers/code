package cn.lastwhisper.leetcode.array.其他.有效的字母异位词_242_Easy;

class Solution2 {
    /**
     * https://leetcode-cn.com/problems/valid-anagram/submissions/
     * hash
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        byte[] freq = new byte[256];
        for (int i = 0; i < sChar.length; i++) {
            freq[sChar[i]]++;
        }

        for (int i = 0; i < tChar.length; i++) {
            freq[tChar[i]]--;
        }
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "anagram", t = "nagaram";
        //String s = "rat", t = "car";

        System.out.println(new Solution2().isAnagram(s, t));
    }

}