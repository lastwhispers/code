package cn.lastwhisper.interview.string;

/**
 *
 * @author lastwhisper
 * @date 12/27/2019
 */
public class StringTest {

    public static void main(String[] args) {
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

}
