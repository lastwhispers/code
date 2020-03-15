package cn.lastwhisper.leetcode.array.对撞指针.反转字符串_344_Easy;

/**
 * @author lastwhisper
 */
public class Solution1 {
    /**
     * https://leetcode-cn.com/problems/reverse-string/
     * 指针对撞
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l++;
            r--;
        }
    }

    public static void main(String[] args) {
        char[] reverses = new char[]{'H', 'a', 'n', 'n', 'a', 'h'};
        new Solution1().reverseString(reverses);
        for (char c : reverses) {
            System.out.printf("\"%s\",", c);
        }
    }
}
