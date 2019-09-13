package cn.lastwhisper.jvm.memorystruct;

/**
 * 虚拟机栈中的局部变量表和操作数栈
 * @author lastwhisper
 */
public class ByteCodeSample {
    public static int add(int a, int b) {
        int c = 0;
        c = a + b;
        return c;
    }
}
