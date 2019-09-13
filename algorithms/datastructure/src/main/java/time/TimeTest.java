package time;

/**
 * @author cn.lastwhisper
 */
public class TimeTest {

    public void test1() {
        int x = 1;
        for (int i = 0; i < 10000; i++) {
            x++;
        }
    }

    public void test2(int n) {
        int i = 1;
        while (i < n) {
            i = i * 2;
        }
    }

    public void test3(int n) {
        //for (i = 0; i < n; i++) {
        //    for (j = 0; j < i; j++) {
        //        for (k = 0; k < j; k++)
        //            x = x + 2;
        //    }
        //}
    }
}
