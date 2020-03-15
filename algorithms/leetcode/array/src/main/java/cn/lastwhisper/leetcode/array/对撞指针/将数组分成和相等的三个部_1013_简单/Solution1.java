package cn.lastwhisper.leetcode.array.对撞指针.将数组分成和相等的三个部_1013_简单;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/partition-array-into-three-parts-with-equal-sum/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：三个和相等的非空部分，0~i == i+1~j == j-1~length-1。
     *  观察规律：
     *      1.分成三等分，说明sum%3==0。
     *      2.sum%3==0有没有可能不能划分成三等分？
     *          有可能，比如[6,7,8]，必须把8拆一份给6才行，这是不允许的。
     *  坑：
     *      非空部分！！！不一定要把数组里面值全部都使用
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
        // 双指针
        int left = 0, right = A.length - 1;
        int peer = sum / 3, leftSum = A[left], rightSum = A[right];
        // 使用left + 1 < right 的原因，防止只能将数组分成两个部分
        // 例如：[1,-1,1,-1]，使用left < right作为判断条件就会出错
        while (left + 1 < right) {
            if (leftSum == peer && rightSum == peer) {
                return true;
            }
            while (left + 1 < right && leftSum != peer) {
                left++;
                leftSum += A[left];
            }
            while (left + 1 < right && rightSum != peer) {
                right--;
                rightSum += A[right];
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{0,2,1,-6,6,-7,9,1,2,0,1};
        //int[] nums = new int[]{0, 2, 1, -6, 6, 7, 9, -1, 2, 0, 1};
        //int[] nums = new int[]{3,3,6,5,-2,2,5,1,-9,4};
        int[] nums = new int[]{3, 3, 6, 5, -2, 2, 5, 1, -9, 4};
        System.out.println(new Solution1().canThreePartsEqualSum(nums));
    }
}