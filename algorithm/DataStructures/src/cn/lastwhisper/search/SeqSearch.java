package cn.lastwhisper.search;

/**
 * @author lastwhisper
 */
public class SeqSearch {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int i = orderSearch(arr, 10);
        if (i == -1) {
            System.out.println("没有找到");
        } else {
            System.out.printf("找到了！下标为：%d，数值为：%d", i, arr[i]);
        }
    }

    /**
     * 线性查找
     *
     * @param
     * @return int 数组下标
     */
    public static int orderSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

}
