package cn.lastwhisper.leetcode.array.对撞指针.将数组分成和相等的三个部_1013_简单;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/partition-array-into-three-parts-with-equal-sum/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for (int a : A) {
            sum += a;
        }
        if (sum % 3 != 0) {
            return false;
        }

        int freq = 0;
        int count = 0;
        for (int a : A) {
            count += a;
            if (count == sum / 3) {
                freq++;
                count = 0;
            }
        }
        // [10,-10,10,-10,10,-10,10,-10]
        return freq >= 3;
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{0,2,1,-6,6,-7,9,1,2,0,1};
        //int[] nums = new int[]{0, 2, 1, -6, 6, 7, 9, -1, 2, 0, 1};
        //int[] nums = new int[]{3,3,6,5,-2,2,5,1,-9,4};
        int[] nums = new int[]{3, 3, 6, 5, -2, 2, 5, 1, -9, 4};
        System.out.println(new Solution2().canThreePartsEqualSum(nums));
    }
}