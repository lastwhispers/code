package cn.lastwhisper.leetcode.other.二进制求和_67_简单;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/add-binary/submissions/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：倒叙相加即可
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        // 进位
        int carry = 0;
        int aIndex = a.length() - 1, bIndex = b.length() - 1;

        while (aIndex >= 0 || bIndex >= 0) {
            int x, y, sum;
            x = aIndex >= 0 ? a.charAt(aIndex) - '0' : 0;
            y = bIndex >= 0 ? b.charAt(bIndex) - '0' : 0;

            sum = carry + x + y;
            carry = sum / 2;
            sb.append(sum % 2);

            aIndex--;
            bIndex--;
        }

        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String a = "11", b = "11";
        //String a = "1010", b = "1011";
        //String a = "11", b = "1";
        System.out.println(new Solution1().addBinary(a, b));
    }
}