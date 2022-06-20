package cn.cunchang.颜色分类_75_中等;

import cn.cunchang.array.ArrayUtil;

import java.util.Arrays;

class Solution {
    public void sortColors(int[] nums) {
        // 下标表示对应数字，值表示频率
        int[] freq = new int[3];
        for (int num : nums) {
            freq[num]++;
        }
        int idx = 0;
        for (int i = 0; i < freq.length; i++) {
            while (freq[i]-- > 0) {
                // freq[i]表i这个数字出现了freq[i]次；freq[0]=2，说明0出现了两次
                nums[idx++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] arrays = ArrayUtil.createArrays(2, 0, 2, 1, 1, 0);
        new Solution().sortColors(arrays);
        System.out.println(Arrays.toString(arrays));
    }
}