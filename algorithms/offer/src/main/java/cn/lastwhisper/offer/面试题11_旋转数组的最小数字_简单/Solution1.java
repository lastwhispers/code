package cn.lastwhisper.offer.面试题11_旋转数组的最小数字_简单;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *  一、遍历
     *  二、二分
     * -------------------------------------------------------------------
     * 时间复杂度：O(logn)
     * 空间复杂度：O(n)
     */
    public int minArray(int[] numbers) {
        int left = 0, right = numbers.length - 1, middle = left;

        while (numbers[left] >= numbers[right]) {
            // 找到最小值
            if (right - left == 1) {
                middle = right;
                break;
            }
            middle = (right - left) / 2 + left;

            // 无法界定middle属于前数组还是后数组 {1, 0, 1, 1, 1};//01111
            if (numbers[left] == numbers[middle] && numbers[right] == numbers[middle]) {
                return sequenceSearch(left, right, numbers);
            }

            if (numbers[middle] >= numbers[left]) {
                left = middle;
            } else if (numbers[middle] <= numbers[right]) {
                right = middle;
            }
        }
        return numbers[middle];
    }

    // 顺序查找
    public int sequenceSearch(int left, int right, int[] numbers) {
        int min = numbers[left];
        for (int i = left + 1; i <= right; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{3, 4, 5, 1, 2};
        //int[] nums = new int[]{0,0,0,0};
        //int[] nums = new int[]{1, 0, 1, 1, 1};//01111
        //int[] nums = new int[]{1,1,1,0,1};//01111
        int[] nums = new int[]{ 0, 1, 1, 1,1};//01111
        System.err.println(new Solution1().minArray(nums));
    }
}