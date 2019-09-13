package cn.lastwhisper.jvm.memorystruct;

/**
 * 测试栈溢出
 * VM Args：-Xss180k
 * @author lastwhisper
 */
public class StackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        StackSOF oom = new StackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
/**
 * stack length:1549
 * Exception in Thread "main" java.lang.StackOverflowError
 * 	at cn.lastwhisper.jvm.memorystruct.StackSOF.stackLeak(StackSOF.java:13)
 * 	at cn.lastwhisper.jvm.memorystruct.StackSOF.stackLeak(StackSOF.java:14)
 * 	at cn.lastwhisper.jvm.memorystruct.StackSOF.stackLeak(StackSOF.java:14)
 */