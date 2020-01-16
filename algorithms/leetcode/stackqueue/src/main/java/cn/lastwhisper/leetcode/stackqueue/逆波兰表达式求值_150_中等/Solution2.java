package cn.lastwhisper.leetcode.stackqueue.逆波兰表达式求值_150_中等;

import java.util.Stack;

class Solution2 {
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
        Stack<Integer> numStack = new Stack<>();
        for (String token : tokens) {
            //if ("+".equals(token)) {
            //    //第一个弹出来的数字，要做被+-*/数
            //    Integer num = numStack.pop();
            //    numStack.push(numStack.pop() + num);
            //} else if ("-".equals(token)) {
            //    Integer num = numStack.pop();
            //    numStack.push(numStack.pop() - num);
            //} else if ("*".equals(token)) {
            //    Integer num = numStack.pop();
            //    numStack.push(numStack.pop() * num);
            //} else if ("/".equals(token)) {
            //    Integer num = numStack.pop();
            //    numStack.push(numStack.pop() / num);
            //} else {
            //    numStack.push(Integer.parseInt(token));
            //}
            Integer num;
            switch (token){
                case "+":
                    num = numStack.pop();
                    numStack.push(numStack.pop() +num);
                    break;
                case "-":
                    num = numStack.pop();
                    numStack.push(numStack.pop() - num);
                    break;
                case "*":
                    num = numStack.pop();
                    numStack.push(numStack.pop() * num);
                    break;
                case "/":
                    num = numStack.pop();
                    numStack.push(numStack.pop() / num);
                    break;
                default:
                    numStack.push(Integer.parseInt(token));
            }

        }
        return numStack.pop();
    }


    public static void main(String[] args) {
        //String[] tokens = new String[]{"2", "1", "+", "3", "*"};
        String[] tokens = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};

        System.out.println(new Solution2().evalRPN(tokens));
    }
}