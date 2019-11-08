package cn.lastwhisper.leetcode.array.对撞指针.反转字符串中的元音字母_345_Easy;

class Solution1 {
    /**
     * 指针对撞
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int l = 0, r = chars.length - 1;
        while (l < r) {
            while (l < r && !isVowel(chars[l])) l++; //找到左边的元音
            while (l < r && !isVowel(chars[r])) r--; //找到右边的元音
            if (l < r) {
                // 找到了交换
                char temp = chars[l];
                chars[l] = chars[r];
                chars[r] = temp;
                l++;
                r--;
            }
        }
        return new String(chars);
    }

    public boolean isVowel(char c) {
        if (c == 'a' || c == 'A') return true;
        else if (c == 'e' || c == 'E') return true;
        else if (c == 'i' || c == 'I') return true;
        else if (c == 'o' || c == 'O') return true;
        else if (c == 'u' || c == 'U') return true;
        return false;
    }


    public static void main(String[] args) {
        String s = "leetcode";
        //String s = "aA";
        System.out.println(new Solution1().reverseVowels(s));
        ;
    }
}