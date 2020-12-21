package cn.lastwhisper.feature5.io;

import org.junit.Test;

import java.util.Arrays;


public class StringCodingDemo {

    /*
     * 计算机是如何识别什么时候该把两个字节转换为一个中文呢?
     * 在计算机中中文的存储分两个字节：
     * 		第一个字节肯定是负数。
     * 		第二个字节常见的是负数，可能有正数。但是没影响。
     */
    @Test
    public void testDoubleByte() {
        String s1 = "abcde";
        // [97, 98, 99, 100, 101]

        String s2 = "我爱你中国";
        // [-26, -120, -111, -25, -120, -79, -28, -67, -96, -28, -72, -83, -27, -101, -67]

        System.out.println(Arrays.toString(s1.getBytes()));
        System.out.println(Arrays.toString(s2.getBytes()));
    }

    /**
     * 字符编码解码
     *
     * String(byte[] bytes, String charsetName):通过指定的字符集解码字节数组
     * byte[] getBytes(String charsetName):使用指定的字符集合把字符串编码为字节数组
     *
     * 编码:把看得懂的变成看不懂的
     * String -- byte[]
     *
     * 解码:把看不懂的变成看得懂的
     * byte[] -- String
     */
    @Test
    public void testCode() {
        String s = "你好";

        // String -- byte[]
        byte[] bys = s.getBytes(); //默认GBK [-60, -29, -70, -61]
        // byte[] bys = s.getBytes("GBK");// [-60, -29, -70, -61]
        // byte[] bys = s.getBytes("UTF-8");// [-28, -67, -96, -27, -91, -67]
        System.out.println(Arrays.toString(bys));

        // byte[] -- String
        String ss = new String(bys); // 你好
        // String ss = new String(bys, "GBK"); // 你好
        // String ss = new String(bys, "UTF-8"); // ???
        System.out.println(ss);
    }

}