package cn.lastwhisper.leetcode.array.错误的集合_645_简单;

class Solution3 {
    public int[] findErrorNums(int[] nums) {
        int[] copy = new int[nums.length + 1];
        for (int n : nums) {
            copy[n]++;
        }

        int[] res = new int[2];
        for (int i = 1; i < copy.length; i++) {
            if (copy[i] == 0)
                res[1] = i;
            if (copy[i] >= 2)
                res[0] = i;
        }
        return res;
    }
}