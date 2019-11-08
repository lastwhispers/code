package cn.lastwhisper.leetcode.array.其他.删除排序数组中的重复项_26_Easy;


import java.util.Arrays;

public class Solution1 {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        // 区间[0,...,pos]均为第一次出现元素
        int pos = 0;
        // nums[0]肯定是第一次出现，所以i从1开始
        for (int i = 1; i < len; i++) {
            // 如果第一(pos)个元素与第二(i)个元素不等（以此类推），说明是第一次出现。
            if (nums[pos] != nums[i]) {
                pos++;
                nums[pos] = nums[i];
            }
        }
        return pos + 1;
    }
    //以下为测试代码
    public static void main(String[] args) {
        int[] arr = {0,0,1,1,2,3,4,4};
        System.out.println("原数组长度：" + arr.length);
        int length = new Solution1().removeDuplicates(arr);
        System.out.println("现数组长度：" + length);
        System.out.println(Arrays.toString(arr));
    }
}