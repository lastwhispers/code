package cn.lastwhisper.leetcode.array.删除排序数组中的重复项_26_简单;


import org.junit.Assert;

public class Solution2 {

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //  区间[0,...,pos]均为第一次出现元素
        int pos = 0;
        //  数字可出现的次数
        int frequency = 1;
        for (int n : nums) {
            if (pos < frequency || n > nums[pos - frequency]) {
                nums[pos++] = n;
            }
        }
        return pos;
    }

    //以下为测试代码
    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 2, 3, 4, 4};
        Assert.assertEquals(5, new Solution2().removeDuplicates(nums));
    }

}