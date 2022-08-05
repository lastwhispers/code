package cn.lastwhisper.jvm.tmp.stack.java;

/**
 * @author shkstart
 * @create 2020 下午 8:32
 */
public class StackTest {

    public static void main(String[] args) {
        StackTest test = new StackTest();
        test.methodA();
    }

    // 栈帧示例
    public void methodA() {
        int i = 10;
        int j = 20;

        methodB();
    }

    public void methodB(){
        int k = 30;
        int m = 40;
    }
}
