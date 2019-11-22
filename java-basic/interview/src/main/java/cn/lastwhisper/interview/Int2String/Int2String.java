package cn.lastwhisper.interview.Int2String;

/**
 * 数字类型转为String
 * @author lastwhisper
 * @date 2019/11/14
 */
public class Int2String {

    public void longToString(int n) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            long num = 123456789L + n;
            String s = Long.toString(num);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(n + "次long转String，使用longToString耗时：" + (endTime - startTime));
    }

    public void stringValueOf(int n) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            long num = 123456789L + n;
            String s = String.valueOf(num);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(n + "次long转String，使用stringValueOf耗时：" + (endTime - startTime));
    }

    public void stringAdd(int n) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            long num = 123456789L + n;
            String s = num + "";
        }
        long endTime = System.currentTimeMillis();
        System.out.println(n + "次long转String，使用stringAdd耗时：" + (endTime - startTime));
    }

    public void stringBuilder(int n) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            long num = 123456789L + n;
            StringBuilder sb = new StringBuilder();
            sb.append(num);
            String s = sb.toString();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(n + "次long转String，使用stringBuilder耗时：" + (endTime - startTime));
    }

    /**
     * 10000000次long转String，使用stringValueOf耗时：334
     * 10000000次long转String，使用stringBuilder耗时：395
     */
    public static void main(String[] args) {
        int n = 10000000;
        Int2String int2String = new Int2String();
        int2String.longToString(n);
        int2String.stringValueOf(n);
        int2String.stringBuilder(n);
        int2String.stringAdd(n);
    }

}
