package cn.lastwhisper.interview.string;

import org.junit.Test;

/**
 *
 * @author lastwhisper
 * @date 12/27/2019
 */
public class StringMethodTest {

    /**
     * 测试indexOf与lastIndexOf方法
     */
    @Test
    public void indexTest() {
        //String str = "JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJXXXJJJ";
        String str = "XXXJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ";
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            str.indexOf("XXXJJJ");//17 7
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println(endTime1 - startTime);
        for (int i = 0; i < 100000; i++) {
            str.lastIndexOf("XXXJJJ");//8 54
        }
        System.out.println(System.currentTimeMillis() - endTime1);
    }

    /**
     * 测试subString方法
     */
    @Test
    public void subStringTest() {
        String s = "010010";
        System.out.println(s.substring(0,1));
        System.out.println(s.substring(1,1));
        System.out.println(s.substring(1,2));
        System.out.println(s.substring(2,3));
        System.out.println(s.substring(3));
    }

}
