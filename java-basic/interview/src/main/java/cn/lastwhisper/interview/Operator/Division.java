package cn.lastwhisper.interview.Operator;

/**
 * @author lastwhisper
 */
public class Division {
    public static void main(String[] args) {
        int start = 1, end = Integer.MAX_VALUE;
        //System.out.println(Integer.MAX_VALUE);
        System.out.println((end + start) / 2); // 溢出
        System.out.println(start + (end - start) / 2);
        System.out.println(start + ((end - start) >> 1));
    }
}
