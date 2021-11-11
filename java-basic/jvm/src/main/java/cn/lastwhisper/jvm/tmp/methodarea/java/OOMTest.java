package cn.lastwhisper.jvm.tmp.methodarea.java;

import com.sun.xml.internal.ws.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * jdk6/7中：
 * -XX:PermSize=10m -XX:MaxPermSize=10m
 *
 * jdk8中：
 * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  22:24
 */
public class OOMTest extends ClassLoader {

    /**
     * jdk7
     * 9429
     * Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
     * 	at java.lang.ClassLoader.defineClass(ClassLoader.java:800)
     * 	at java.lang.ClassLoader.defineClass(ClassLoader.java:643)
     * 	at cn.cunchang.methodarea.OOMTest.main(OOMTest.java:45)
     *
     *
     * jdk8
     * 3331
     * Exception in thread "main" java.lang.OutOfMemoryError: Compressed class space
     * 	at java.lang.ClassLoader.defineClass1(Native Method)
     * 	at java.lang.ClassLoader.defineClass(ClassLoader.java:756)
     * 	at java.lang.ClassLoader.defineClass(ClassLoader.java:635)
     * 	at cn.lastwhisper.jvm.tmp.methodarea.java.OOMTest.main(OOMTest.java:29)
     *
     * @param args
     */
    public static void main(String[] args) {
        int j = 0;
        try {
            OOMTest test = new OOMTest();
            for (int i = 0; i < 10000; i++) {
                //创建ClassWriter对象，用于生成类的二进制字节码
                ClassWriter classWriter = new ClassWriter(0);
                //指明版本号，修饰符，类名，包名，父类，接口
                classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
                //返回byte[]
                byte[] code = classWriter.toByteArray();
                //类的加载
                test.defineClass("Class" + i, code, 0, code.length);//Class对象
                j++;
            }
        } finally {
            System.out.println(j);
        }
    }
}
