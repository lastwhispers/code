package cn.lastwhisper;

/**
 * @author lastwhisper
 */
public class ArrayUtil {

    public static int[] generateArrByRandom(int max) {
        int[] arr = new int[max];
        for (int i = 0; i < max; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0,8000000) 的一个数
        }
        return arr;
    }

    public static int[] generateArrByOrder(int max) {
        int[] arr = new int[max];
        for (int i = 0; i < max; i++) {
            arr[i] = i + 1;
        }

        return arr;
    }
}
