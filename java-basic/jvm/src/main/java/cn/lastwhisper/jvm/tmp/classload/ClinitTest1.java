package cn.lastwhisper.jvm.tmp.classload;

/**
 * 作用：任何一个类声明以后，内部至少存在一个类的构造方法（init）
 */
public class ClinitTest1 {


    private int a = 1;

//    private static int c = 3;

    public ClinitTest1(){
        a = 10;
        int d = 20;
    }

    public static void main(String[] args) {
        int b = 2;
    }

}
