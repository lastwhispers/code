package cn.lastwhisper.jvm.tmp.stringtable.java2;

/**
 * 使用intern()测试执行效率：空间使用上
 *
 * 不使用常量池
 *  1000万次创建一定比例重复的字符串，花费6744ms，占用500MB
 * 使用常量池
 *  1000万次创建一定比例重复的字符串，花费2069ms，占用20MB
 *
 *  两者都会创建完整的字符串，使用常量池时仅会触发minor gc（-XX:+PrintGCDetails），因为会创建大量未使用的对象
 *   不使用常量池会触发full gc，因为会创建大量关联gc root的对象
 *
 *
 * 结论：对于程序中大量使用的字符串，其中存在很多重复字符串时，使用intern()可以节省内存空间。
 *
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  21:17
 */
public class StringIntern2 {
    static final int MAX_COUNT = 1000 * 10000;
    static final String[] arr = new String[MAX_COUNT];

    public static void main(String[] args) {
        Integer[] data = new Integer[]{1,2,3,4,5,6,7,8,9,10};

        long start = System.currentTimeMillis();
        for (int i = 0; i < MAX_COUNT; i++) {

            arr[i] = new String(String.valueOf(data[i % data.length])); //不使用常量池
//            arr[i] = new String(String.valueOf(data[i % data.length])).intern(); //使用常量池

        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为：" + (end - start));

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.gc();
    }
}
