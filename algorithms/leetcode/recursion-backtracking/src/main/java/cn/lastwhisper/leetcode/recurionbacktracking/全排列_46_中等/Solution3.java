package cn.lastwhisper.leetcode.recurionbacktracking.全排列_46_中等;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static cn.lastwhisper.leetcode.common.print.PrintUtil.printLists;


public class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
     * -------------------------------------------------------------------
     * 思考：
     *  https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
     * -------------------------------------------------------------------
     * 思路：树形问题-递归
     * -------------------------------------------------------------------
     * 时间复杂度：O(n*n!)
     * 空间复杂度：O(n*n!)
     */
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>(factorial(len));
        if (len == 0) {
            return res;
        }

        int used = 0;
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(nums, path, used, res);
        return res;
    }

    private int factorial(int n) {
        int res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    private void dfs(int[] nums,
                     Deque<Integer> path, int used,
                     List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (((used >> i) & 1) == 0) {
                path.addLast(nums[i]);
                used ^= (1 << i);

                dfs(nums, path, used, res);
                used ^= (1 << i);
                path.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        printLists(new Solution3().permute(new int[]{1, 2, 3}));
    }
}
