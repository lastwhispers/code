package cn.lastwhisper.jvm.tmp.classload;

/**
 * 作用：解释类初始化顺序，这里举的例子并不完整
 */
public class ClinitTest2 {
    static class Father{
        public static int A = 1;
        static{
            A = 2;
        }
    }

    static class Son extends Father{
        public static int B = A;
    }

    public static void main(String[] args) {
        //加载Father类，其次加载Son类。
        System.out.println(Son.B);//2
    }
}