package cn.lastwhisper.jvm.tmp.classload;


/**
 * 这个demo用于解释 准备=》初始化 阶段
 * @author cunchang
 */
public class ClinitTest {

    private static int num = 1;

    static{
        num = 2;
        number = 20;
        //System.out.println(num);
        //System.out.println(number);//报错：非法的前向引用。
    }

    private static int number = 10;  //linking之prepare: number = 0 --> initial: 20 --> 10

    public static void main(String[] args) {
        System.out.println(ClinitTest.num);//2
        System.out.println(ClinitTest.number);//10
    }
}