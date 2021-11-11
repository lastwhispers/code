package cn.lastwhisper.jvm.tmp.stack.java1;

/**
 * @author shkstart
 * @create 2020 下午 10:25
 */
public class OperandStackTest {

    // 一、操作数栈
    public void testAddOperation() {
        //byte、short、char、boolean：都以int型来保存
        byte i = 15;
        int j = 8;
        int k = i + j;

       // int m = 800;
        // 8 => bipush 8
        // 800 => sipush 800
    }

    // 二、
    public void testGetSum(){
        //获取上一个栈桢返回的结果，并保存在当前栈帧的操作数栈中
        int i = getSum(); //  istore_1
        int j = 10; // 字节码指令：return
    }

    public int getSum(){
        int m = 10;
        int n = 20;
        int k = m + n;
        return k; // 字节码指令：ireturn
    }

    /*
    程序员面试过程中， 常见的i++和++i 的区别，放到字节码篇章时再介绍。

     */
    public void add(){
        //第1类问题：
        int i1 = 10;
        i1++;

        int i2 = 10;
        ++i2;

        //第2类问题：
        int i3 = 10;
        int i4 = i3++;

        int i5 = 10;
        int i6 = ++i5;

        //第3类问题：
        int i7 = 10;
        i7 = i7++;

        int i8 = 10;
        i8 = ++i8;

        //第4类问题：
        int i9 = 10;
        int i10 = i9++ + ++i9;
    }
}
