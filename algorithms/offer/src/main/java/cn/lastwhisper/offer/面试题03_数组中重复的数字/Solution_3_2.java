package cn.lastwhisper.offer.面试题03_数组中重复的数字;

/**
 * 数组中重复的数字
 * 题目二：不修改数组找出数组中重复的数字
 * @author cn.lastwhisper
 */
public class Solution_3_2 {

    public static void main(String[] args) {
        // 1.长度为n的数组包含一个或多个重复数字
        int[] arr1 = {2, 3, 5, 4, 3, 2, 6, 7};
        // 2.数组中不包含重复数字
        int[] arr2 = {1, 2, 3, 4, 7, 7, 7};
        // 3.空指针
        int[] arr3 = null;
        System.out.println(new Solution_3_2().getDuplicate(arr2));
    }

    /**
     * 找到数组中一个重复的数字
     * 返回-1代表无重复数字或者输入无效
     */
    public int getDuplicate(int[] arr) {
        //校验数据
        if (arr == null || arr.length == 0) {
            System.out.println("数组未初始化");
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            //n+1个数，取值范围1~n
            if (arr[i] < 1 || arr[i] > arr.length - 1) {
                System.out.println("数组数字不符合要求：n+1个数，取值范围1~n");
                return -1;
            }
        }
        // start从1起始,因为0时只有一个数无法重复;
        int start = 1, end = arr.length - 1, middle;
        while (end >= start) {
            // (start-end)/2+end==(start+end)/2
            middle = ((end - start) >> 1) + start;
            // 二分起始范围出现次数count
            // 第一步：如：{2,3,5,4,3,2,6,7}在[1...4],start=1;end=7;middle=4;上出现的次数count=5(4个数出现5次，肯定重复，无法确定是那个数)
            // 第二步：如：{2,3,5,4,3,2,6,7}在[1...2],start=1;end=4;middle=2;上出现的次数count=2(2个数出现2次，无重复；实际上重复了，但是目前无法检测)
            // 第三步：如：{2,3,5,4,3,2,6,7}在[3...4],start=3;end=4;middle=3;上出现的次数count=2(2个数出现2次，无重复)
            // 第四步：如：{2,3,5,4,3,2,6,7}在[3...3],start=3;end=3;middle=3;上出现的次数count=2(1个数出现2次，肯定重复，可以确定是哪个数)
            int count = rangeCount(arr, start, middle);
            // 结束了返回重复下标
            if (start == end) {
                if (count > 1) {
                    return start;
                } else {
                    break;
                }
            }
            // 第一步：5>4-1+1成立,说明数组在该范围[1...4]内出现重复值，缩短end范围
            // 第二步：2>2-1+1不成立,说明数组在该范围[1...2]内没有出现重复值，缩短start范围
            // 第二步：2>3-3+1成立,说明数组在该范围[3...4]内出现重复值，缩短end范围
            if (count > (middle - start + 1)) {
                end = middle;
            } else {
                //否则，在该范围没有重复值
                start = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 计算某数组所有元素在某范围出现的次数
     * 如：{2,3,5,4,3,2,6,7}在[1...3]上出现的次数count=4
     * @param arr 某数组
     * @param start 起始范围
     * @param end 结束范围
     * @return int
     */
    private int rangeCount(int[] arr, int start, int end) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= start && arr[i] <= end) {
                count++;
            }
        }
        return count;
    }
}