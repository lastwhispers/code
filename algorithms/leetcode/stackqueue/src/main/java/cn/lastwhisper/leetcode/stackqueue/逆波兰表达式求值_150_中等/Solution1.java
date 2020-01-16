package cn.lastwhisper.leetcode.stackqueue.逆波兰表达式求值_150_中等;

import java.util.Stack;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：安装NPN规则即可
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int evalRPN(String[] tokens) {
        Stack<String> numStack = new Stack<>();
        for (String token : tokens) {
            if (isNum(token)) {
                numStack.push(token);
            } else {
                String num2 = numStack.pop();
                String num1 = numStack.pop();
                numStack.push(calculate(num1, num2, token));
            }
        }

        return Integer.parseInt(numStack.pop());
    }

    /**
     * 判断该字符串是不是数字
     */
    public boolean isNum(String token) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^([-+])?\\d+(\\.\\d+)?$");
        return pattern.matcher(token).matches();
    }

    /**
     * 计算
     */
    public String calculate(String num1, String num2, String expressions) {
        switch (expressions) {
            case "+":
                return String.valueOf(Integer.parseInt(num1) + Integer.parseInt(num2));
            case "-":
                return String.valueOf(Integer.parseInt(num1) - Integer.parseInt(num2));
            case "*":
                return String.valueOf(Integer.parseInt(num1) * Integer.parseInt(num2));
            case "/":
                return String.valueOf(Integer.parseInt(num1) / Integer.parseInt(num2));
            default:
                return "";
        }
    }

    public static void main(String[] args) {
        //String[] tokens = new String[]{"2", "1", "+", "3", "*"};
        String[] tokens = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};

        System.out.println(new Solution1().evalRPN(tokens));
    }
}