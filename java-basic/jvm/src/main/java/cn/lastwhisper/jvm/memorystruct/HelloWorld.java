package cn.lastwhisper.jvm.memorystruct;

/**
 * 元空间,堆,线程独占部分间的关系-内存角度
 * @author lastwhisper
 */
public class HelloWorld {
    private static int id=10;//类变量
    private String name;//成员变量，

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        int a = 1;//局部变量
        HelloWorld hw = new HelloWorld();
        hw.setName("testStr");
    }
}
