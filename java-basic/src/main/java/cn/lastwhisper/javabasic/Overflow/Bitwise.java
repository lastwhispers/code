package cn.lastwhisper.javabasic.Overflow;


import org.junit.Test;

/**
 * @author lastwhisper
 */
public class Bitwise {
    @Test
    public void bitwise() {
        int i = -999999;
        int a = i / 2;
        int b = i >> 1;
        System.out.printf("a:%s\nb:%s", a, b);
    }

    @Test
    public void overflow() {
        int x = 1999999998;
        int y = 1999999998;
        int mid = (x + y) / 2;
        int mid2 = x + (y - x) / 2;
        System.out.println(mid);    //-147483650
        System.out.println(mid2);  //1999999998
    }

}
