package cn.lastwhisper.offer.面试题20_表示数值的字符串;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean isNumber(String s) {
        s = s.trim();
        boolean pointScan = false, eScan = false, numberScan = false, numberAfterEScan = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c <= '9' && c >= '0') {
                // 出现0~9
                numberScan = true;
                numberAfterEScan = true;
            } else if (c == 'e') {
                // 出现'e'，true：1.23e10、0.5e-10、0.5e04、05047e+6
                // e不能出现两次||number出现后e才可以出现
                if (eScan || !numberScan) {
                    return false;
                }
                // num之后找到e
                numberAfterEScan = false;
                eScan = true;
            } else if (c == '.') {
                // 出现'.'，true：0.、123.5、0.5e04、0.5e-10
                // '.'前面不能有点||不能有e
                if (eScan || pointScan) {
                    return false;
                }
                pointScan = true;
            } else if (c == '-' || c == '+') {
                // '-'、、'+'必须出现在第一位，同时第二位不能是e
                if (i != 0 && s.charAt(i - 1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }

        }

        return numberScan && numberAfterEScan;
    }

    public static void main(String[] args) {
        String expression = "";

        System.err.println(new Solution1().isNumber(expression));
    }
}