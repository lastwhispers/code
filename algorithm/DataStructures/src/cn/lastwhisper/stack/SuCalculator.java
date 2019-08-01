package cn.lastwhisper.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 后缀表达式
 * @author lastwhisper
 */
public class SuCalculator {
    public static void main(String[] args) {
        // 1+((2+3)*4)-5 ——》
        String infixExpression = "111+((2+3)*4)-5";
        //String suffixExpression = "";
        // 中缀String串转中缀List "1+((2+3)*4)-5" ——》[1,+,(,(,2,+,3,),*,4,),-,5]
        //List<String> infixList = infixStrConvertInfixList(infixExpression);
        //for (int i = 0; i < infixList.size(); i++) {
        //    String str = infixList.get(i);
        //    if (i == infixList.size() - 1) {
        //        System.out.print(str);
        //    } else {
        //        System.out.print(str + " ");
        //    }
        //}
        // 前缀表达式转为后缀表达式 "1+((2+3)*4)-5"——》"1 2 3 + 4 * + 5 -"
        String suffixExpression = infixConverSufix(infixExpression);
        System.out.println("前缀：" + infixExpression);
        System.out.println("后缀：" + suffixExpression);
        // 后缀表达式解析计算
        int result = calcSufix(suffixExpression);
        System.out.println("计算结果：" + result);
    }

    /**
     * 中缀表达式转后缀表达式
     *
     * @param infixExpression
     * @return java.lang.String
     */
    public static String infixConverSufix(String infixExpression) {
        List<String> infixExpressionList = infixStrConvertInfixList(infixExpression);
        // 1、初始化两个栈，s1运算符栈，s2中间结果栈
        Stack<String> s1 = new Stack<>();
        //说明：因为s2 这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里可以不用 Stack<String> 直接使用 List<String> s2
        Stack<String> s2 = new Stack<>();
        // 2、循环遍历前缀表达式
        for (String infix : infixExpressionList) {
            // 3、遇到运算符infix,与s1栈顶运算符比较优先级 ")">"*"="/">"+"="-">"("
            //  （1）、如果s1栈顶是空，或者是"("，直接入栈
            //  （2）、如果infix优先级大于s1栈顶运算符，直接入栈
            //  （3）、如果infix优先级小于或等同于s1栈顶运算符，将s1栈顶运算符压入s2栈。再将oper重复（1）（2）（3）
            if (isOper(infix)) {
                boolean flag = true;
                while (flag) {
                    // .trim()是因为，3（3）会将s1栈顶运算符压入s2栈，而s1栈中元素都加上空格了
                    if (s1.isEmpty() || s1.peek().trim().equals("(")) {
                        s1.push(infix + " ");
                        flag = false;
                    } else if (priority(infix) > priority(s1.peek().trim())) {
                        s1.push(infix + " ");
                        flag = false;
                    } else if (priority(infix) <= priority(s1.peek().trim())) {
                        s2.push(s1.pop());
                    }
                }
                // 4、遇到括号
            } else if (infix.equals("(")) {
                // （1）、左括号直接入s1栈
                s1.push(infix);
            } else if (infix.equals(")")) {
                // （2）、右括号。依次弹出s1栈顶运算符，压入s2中，直到遇到左括号位置，此时将左括号丢弃
                //while (!s1.peek().equals(")")) {
                //    s2.push(s1.pop() + " ");
                //}
                while (true) {
                    s2.push(s1.pop().trim() + " ");
                    if (s1.peek().equals("(")) {
                        break;
                    }
                }
                s1.pop();
            } else if (infix.matches("\\d+")) {
                // 5、遇到数字，直接入s2
                s2.push(infix + " ");
            }

        }
        // 6、将s1剩余的运算符压入s2中
        while (!s1.empty()) {
            s2.push(s1.pop().trim());
        }
        // 7、依次弹出s2中的元素，即后缀表达式
        StringBuffer sb = new StringBuffer();
        for (String s : s2) {
            sb.append(s);
        }
        return sb.toString().trim();
    }

    /**
     * 中缀表达式String转中缀表达式Lsit
     *
     * @param infixExpression
     * @return java.util.List<java.lang.String>
     */
    public static List<String> infixStrConvertInfixList(String infixExpression) {
        List<String> infixList = new ArrayList<>();
        // 记录多位数
        String keepNum = "";
        for (int i = 0; i < infixExpression.length(); i++) {
            String subStr = infixExpression.substring(i, i + 1);
            if (isOper(subStr)) {
                // 运算符
                infixList.add(subStr);
            } else if (subStr.equals("(") || subStr.equals(")")) {
                infixList.add(subStr);
            } else {
                // 数字
                keepNum += subStr;
                // 前缀表达式的最后一个字符
                if (i == infixExpression.length() - 1) {
                    infixList.add(keepNum);
                } else {
                    // 非前缀表达式的最后一个字符
                    // 后一位是字符
                    String subnextStr = infixExpression.substring(i + 1, i + 2);
                    if (isOper(subnextStr) || subnextStr.equals("(") || subnextStr.equals(")")) {
                        infixList.add(keepNum);
                        keepNum = "";
                    }
                }
            }

        }
        return infixList;
    }

    /**
     * 解析并计算后缀表达式
     *
     * @param suffixExpression
     * @return int
     */
    public static int calcSufix(String suffixExpression) {
        String[] expressions = suffixExpression.split(" ");
        Stack<Integer> stack = new Stack<>();
        // 1、遍历表达式
        for (int i = 0; i < expressions.length; i++) {
            String str = expressions[i];
            if (isOper(str)) {
                // 2、遇到运算符，弹出栈顶两个数，进行运算入栈
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                int sum = calc(num1, num2, str);
                stack.push(sum);
            } else {
                // 3、将数字push入栈
                stack.push(Integer.valueOf(str));
            }
        }
        return stack.pop();
    }

    /**
     * 判断是否是运算符
     *
     * @param oper
     * @return boolean
     */
    public static boolean isOper(String oper) {
        return "*".equals(oper) || "/".equals(oper) || "+".equals(oper) || "-".equals(oper);
    }

    /**
     * 计算两个num
     *
     * @param num1
     * @param num2
     * @param oper
     * @return int
     */
    public static int calc(int num1, int num2, String oper) {
        int res = 0;
        switch (oper) {
            case "*":
                res = num1 * num2;
                break;
            case "/":
                res = num1 / num2;
                break;
            case "+":
                res = num1 + num2;
                break;
            case "-":
                res = num2 - num1;
                break;
            default:
                break;
        }
        return res;
    }

    /**
     * 计算运算符的优先级
     *  ")">"*"="/">"+"="-">"("
     * @param oper
     * @return int
     */
    public static int priority(String oper) {
        int res = -1;
        if (oper.equals(")")) {
            res = 3;
        } else if (oper.equals("*") || oper.equals("/")) {
            res = 2;
        } else if (oper.equals("+") || oper.equals("-")) {
            res = 1;
        } else if (oper.equals("(")) {
            res = 0;
        } else {
            res = -1;
        }
        return res;
    }
}
