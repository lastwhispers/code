package cn.lastwhisper.leetcode.binarysearch.二分查找_704_简单;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-search/
     * -------------------------------------------------------------------
     * 思考：
     *  二分搜索防加法溢出，遍历和递归两种写法
     * -------------------------------------------------------------------
     * 思路：
     *  遍历
     * -------------------------------------------------------------------
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     */
    public int search(int[] nums, int target) {
        /*
         * 为什么是[0,nums.length - 1]?
         *  仅仅是数组范围
         */
        int start = 0, end = nums.length - 1, middle;
        /*
         * 为什么是end >= start?
         *  由于middle+1和middle-1的存在，所以合法的[start,end]范围内肯定是待搜索的
         */
        while (end >= start) {
            /*
             * 如何避免middle = (end + start) / 2;加法溢出?
             */
            middle = (end - start) / 2 + start;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target) {
                /*
                 * 为什么middle - 1?(两个方面)
                 *  1.为了跳出while循环
                 *  2.nums[middle] > target时，middle本身不在搜索范围
                 */
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        //int target = 9;
        int target = 2;
        System.out.println(new Solution1().search(nums, target));
    }
}