package cn.lastwhisper.leetcode.array.删除排序数组中的重复项_II_80_Medium;

public class Solution1 {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //  区间[0,...,pos]每个元素最多出现两次
        int pos = 0;
        //  数字可出现的次数
        int frequency = 2;
        for (int n : nums) {
            if (pos < frequency || n > nums[pos - frequency]) {
                nums[pos++] = n;
            }
        }
        return pos;
    }

    //以下为测试代码
    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        System.out.println("原数组长度：" + arr.length);
        int length = new Solution1().removeDuplicates(arr);
        System.out.println("现数组长度：" + length);
        for (int i = 0; i < length; i++) {
            if (i != length - 1) {
                System.out.print(arr[i] + ",");
            } else {
                System.out.print(arr[i]);
            }
        }
    }

}