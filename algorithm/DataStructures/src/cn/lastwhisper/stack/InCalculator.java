package cn.lastwhisper.stack;

/**
 * 中缀表达式
 * @author lastwhisper
 */
public class InCalculator {
    public static void main(String[] args) {
        // 符号栈、数值栈
        CalcArrayStack operStack = new CalcArrayStack(10);
        CalcArrayStack numStack = new CalcArrayStack(10);
        // 计算结果、数值1、数值2、运算符、当前值
        int res = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int ch = 0;
        String keepNum = "";
        // index辅助遍历表达式
        int index = 0;
        // 中缀表达式
        //String expression = "7*2*2-5+1-5+3-4";
        String expression = "30+2*6-2";
        // 解析中缀表达式
        while (index != expression.length()) {
            // 1、通过index遍历表达式
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) {
                // 2、如果是oper进入符号栈，入符号栈分为两种情况
                //      （1）、当前符号栈为空，直接入栈
                //      （2）、当前符号栈不为空，peek出栈顶运算符，进行运算符优先级比较
                //          如果当前oper优先级大于栈顶运算符，直接入栈
                //          如果小于，弹出符号栈栈顶运算符，弹出两个数值，进行运算并将运算结果push数值栈，再将oper压入符号栈
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                } else {
                    oper = operStack.peek();
                    if (operStack.priority(ch) > operStack.priority(oper)) {
                        operStack.push(ch);
                    } else {
                        oper = operStack.pop();
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        res = numStack.calc(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }
                }
            } else {
                // 3、如果是num进入数值栈
                //numStack.push(ch - 48);
                // 处理多位数'1'+'5'——》15
                keepNum += ch - 48;
                // ch是否是最后一个字符
                if (index == expression.length() - 1) {
                    numStack.push(Integer.valueOf(keepNum));
                } else {
                    // 如果后一位是运算符，则将当前keepNum入栈
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.valueOf(keepNum));
                        // 每次清空多位数
                        keepNum = "";
                    }
                }
            }
            index++;
        }
        // 4、解析完表达式后，弹出符号栈、数值栈对应的运算符和数值进行运算，再将结果入栈
        //  重复第四步直至数值栈中只剩一个运算结果
        while (!operStack.isEmpty()) {
            oper = operStack.pop();
            num1 = numStack.pop();
            num2 = numStack.pop();
            res = numStack.calc(num1, num2, oper);
            numStack.push(res);
        }
        System.out.printf("%s=%d", expression, numStack.pop());
    }
}


class CalcArrayStack {
    private int[] arr;
    private int n = 0;

    public CalcArrayStack(int initialCapacity) {
        this.arr = new int[initialCapacity];
    }
    // 栈满

    // 入栈
    public void push(int data) {
        if (n > 0 && n == arr.length) {
            resize(2 * arr.length);
        }
        arr[n++] = data;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        if (n > 0 && n == (arr.length / 4)) {
            resize(arr.length / 2);
        }
        int data = arr[--n];
        return data;
    }

    /**
     * 显示栈中情况
     */
    public void list() {
        for (int i = n; i > 0; i--) {
            System.out.printf("%d\t", arr[i]);
        }
    }

    // 栈空
    public boolean isEmpty() {
        return n == 0;
    }

    // 查看栈顶元素
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int temp = n;
        return arr[--temp];
    }

    // size
    public int size() {
        return n;
    }

    // resize
    public void resize(int maxSize) {
        int[] newArr = new int[maxSize];
        for (int i = 0; i < size(); i++) {
            newArr[i] = arr[i];
        }
        this.arr = newArr;
    }

    // 返回运算符的优先级
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    // 判断是不是一个运算符
    public boolean isOper(int oper) {
        return oper == '*' || oper == '/' || oper == '+' || oper == '-';
    }

    // 计算方法接收两个数和一个运算符
    public int calc(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '*':
                res = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("非法的除零运算");
                }
                break;
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            default:
                break;
        }
        return res;
    }
}
