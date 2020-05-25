package cn.lastwhisper.leetcode.array.错误的集合_645_简单;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution2 {
    /**
     * https://leetcode-cn.com/problems/set-mismatch/
     * -------------------------------------------------------------------
     * 数据特征：
     *  输入：数组、1~n整数、无序、存在重复数据、不一定包含丢失数据
     *  输出：数组[重复数据,丢失数据]、1~n+1整数、
     * -------------------------------------------------------------------
     * 思路：
     *  遍历输入数组，使用map存储数组值出现的频率
     *  正常数据1~length每一个出现的频率都为1，将1~length每一个值当做key查询map，
     *      frequency=2的为重复数据，frequency=null为丢失数据
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * -------------------------------------------------------------------
     * 改进：
     *  将数组值当做数据下标，统计该值出现的频率
     *  map需要使用2n的额外空间，使用额外数组当做map，只需要使用n的额外空间
     */
    public int[] findErrorNums(int[] nums) {
        Map<Integer, Integer> numFreMap = new HashMap<>();
        int duplicate = -1, miss = -1;
        for (int num : nums) {
            numFreMap.put(num, numFreMap.getOrDefault(num, 0) + 1);
        }
        for (int i = 1; i <= nums.length; i++) {
            Integer frequency = numFreMap.get(i);
            if (frequency == null) {
                miss = i;
            } else if (frequency == 2) {
                duplicate = i;
            }
        }
        return new int[]{duplicate, miss};
    }

    public static void main(String[] args) {
        // example
        //int[] arr = {1, 2, 2, 4};//2,3
        //int[] arr = {2, 2};//2,1
        // error example
        //int[] arr = {3, 2, 2};//2,1
        //int[] arr = {1, 1};//1,2
        int[] arr = {1, 5, 3, 2, 2, 7, 6, 4, 8, 9};
        System.out.println(Arrays.toString(new Solution2().findErrorNums(arr)));
    }
}