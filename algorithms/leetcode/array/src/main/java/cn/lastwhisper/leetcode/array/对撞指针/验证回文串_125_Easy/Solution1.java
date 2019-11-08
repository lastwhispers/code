package cn.lastwhisper.leetcode.array.对撞指针.验证回文串_125_Easy;


class Solution1 {
    /**
     * https://leetcode-cn.com/problems/valid-palindrome/
     * 指针对撞
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean isPalindrome(String s) {
        String tempStr = s.toUpperCase();
        char[] chars = tempStr.toCharArray();
        int l = 0, r = tempStr.length() - 1;
        while (l < r) {
            while (l < r && !isWordOrNum(chars[l])) l++;
            while (l < r && !isWordOrNum(chars[r])) r--;
            if (chars[l] != chars[r]) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    // 是否是字母和数字字符
    public boolean isWordOrNum(char c) {
        return ((c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122));
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        //String s = "race a car";
        System.out.println(new Solution1().isPalindrome(s));
        //System.out.println(s.toUpperCase());
    }
}