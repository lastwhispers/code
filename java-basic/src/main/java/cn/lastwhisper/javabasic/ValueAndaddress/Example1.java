package cn.lastwhisper.javabasic.ValueAndaddress;

/**
 * @author gaojun
 * @desc 形参为基本数据类型
 * @email 15037584397@163.com
 */
public class Example1 {
    public static void main(String[] args) {
        int i = 10;

        pass(i);

        System.out.println("print in main , i is " + i);
    }

    public static void pass(int j) {
        j = 20;
        System.out.println("print in pass , j is " + j);
    }
}
