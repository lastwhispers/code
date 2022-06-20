package cn.cunchang.接雨水_42_困难;

import org.junit.Assert;

class Solution1 {
    /**
     * 题目地址：https://leetcode.cn/problems/container-with-most-water/
     * -------------------------------------------------------------------
     * 思考：只看局部柱子最多盛放水量
     * -------------------------------------------------------------------
     * 思路（磨刀不误砍柴工，先写思路，再写代码）：
     * 数组n个柱子，当前柱子i，每个柱子可
     * 1、遍历每个柱子，找每个柱子左右最高层数
     * 2、找i左右最高层数
     * （1）i=0 or i=n，左最高和右最高=0；第一个柱子和最后一个柱子不管多高，自身都无法盛水
     * （2）遍历[0,i)，记录左最高层数
     * （3）遍历(i,n-1]，记录右最高层数
     * 3、i可盛放水量计算
     * （1）如果左最高或右最高=0，说明当前柱子无法盛水，当前柱子盛水量=0
     * （2）i自身高度大于或等于左右高度，当前柱子盛水量=0
     * （3）i最多盛放水量=左右最高里面最低的哪个层数-当前柱子层数
     * 4、[0,n-1]对应盛水量汇总
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < height.length; i++) {
            KV kv = getMax(i, height);
            if (kv.leftMax == 0 || kv.rightMax == 0) {
                continue;
            }
            if (height[i] >= kv.leftMax || height[i] >= kv.rightMax) {
                continue;
            }
            int low = kv.leftMax > kv.rightMax ? kv.rightMax : kv.leftMax;
            // i最多盛放水量
            count += low - height[i];
        }
        return count;
    }

    private KV getMax(int i, int[] height) {
        int leftMax = 0;
        for (int j = 0; j < i; j++) {
            if (leftMax < height[j]) {
                leftMax = height[j];
            }
        }
        int rightMax = 0;
        for (int j = height.length - 1; j > i; j--) {
            if (rightMax < height[j]) {
                rightMax = height[j];
            }
        }
        return new KV(leftMax, rightMax);
    }

    class KV {
        int leftMax;
        int rightMax;

        public KV(int leftMax, int rightMax) {
            this.leftMax = leftMax;
            this.rightMax = rightMax;
        }
    }

    public static void main(String[] args) {
//        Assert.assertEquals(2, new Solution1().maxDistance(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}));
        Solution1 solution1 = new Solution1();
        Assert.assertEquals(6, solution1.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}