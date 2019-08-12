package cn.lastwhisper.jvm.classloading;

/**
 * 测试静态语句块和静态变量的顺序
 */
public class StaticCodeTest {
    static {
        i = 0;
        //System.out.println(i);
    }
    static int i = 1;
}