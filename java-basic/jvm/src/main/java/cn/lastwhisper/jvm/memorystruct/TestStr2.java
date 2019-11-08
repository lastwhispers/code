package cn.lastwhisper.jvm.memorystruct;

/**
 * @author lastwhisper
 */
public class TestStr2 {
    /**
     * 共产生几个String对象？5个
     */
    public static void main(String[] args) {
        String s = "a";
        s = s + "b" + "c";
        s = s + "b" + "c" + "d";
        System.out.println(s);
    }
}
