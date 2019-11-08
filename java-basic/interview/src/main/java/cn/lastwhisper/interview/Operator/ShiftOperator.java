package cn.lastwhisper.interview.Operator;

/**
 * 移位运算符
 * @author lastwhisper
 */
public class ShiftOperator {
    public static void main(String[] args) {
        int a = -2 << 3;// -16
        int b = 15 >> 2;// 3
        int c = -4 >> 2;// -1
        int d = 4 >>> 2;// 1
        int e = -5 >>> 1;// 2147483645
        System.out.println(15 >> 1);//7
        System.out.println(14 >> 1);//7
    }
}
