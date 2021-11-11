package cn.lastwhisper.jvm.tmp.methodarea.java;

/**
 *  测试设置方法区大小参数的默认值
 *
 *  jdk7及以前：
 *  -XX:PermSize=100m -XX:MaxPermSize=100m
 *
 *  jinfo -flag PermSize
 *  jinfo -flag MaxPermSize
 *
 *  jdk8及以后：
 *  -XX:MetaspaceSize=100m  -XX:MaxMetaspaceSize=100m
 *
 *
 *
 *
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  12:16
 */
public class MethodAreaDemo {
    public static void main(String[] args) {
        System.out.println("start...");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end...");
    }
}
