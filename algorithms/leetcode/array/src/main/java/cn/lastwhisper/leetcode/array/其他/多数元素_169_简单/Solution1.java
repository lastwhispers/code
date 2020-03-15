package cn.lastwhisper.leetcode.array.其他.多数元素_169_简单;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/majority-element/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：hash表
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int majorityElement(int[] nums) {
        int half = nums.length / 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer count = map.get(num);
            count = count == null ? 1 : ++count;
            map.put(num, count);
            if (count > half) {
                return num;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        //Assert.assertEquals(3, new Solution1().majorityElement(new int[]{3, 2, 3}));
        Assert.assertEquals(2, new Solution1().majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));

    }
}