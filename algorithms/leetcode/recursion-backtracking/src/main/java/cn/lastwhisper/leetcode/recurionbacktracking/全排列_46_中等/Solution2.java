package cn.lastwhisper.leetcode.recurionbacktracking.全排列_46_中等;

import java.util.ArrayList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.print.PrintUtils.printLists;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
     * -------------------------------------------------------------------
     * 思考：
     *  https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
     * -------------------------------------------------------------------
     * 思路：树形问题-递归，不进行回溯
     * -------------------------------------------------------------------
     * 时间复杂度：O(n*n!)
     * 空间复杂度：O(n*n!)
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfsPermute(nums, visited, new ArrayList<>(), result);
        return result;
    }

    /**
     * @param nums 输入数字数组
     * @param visited 数字被访问过true，没有为false
     * @param permute 排列
     * @param result 返回结果
     */
    public void dfsPermute(int[] nums, boolean[] visited, List<Integer> permute, List<List<Integer>> result) {
        if (permute.size() == nums.length) {
            // 3、不用拷贝，因为每一层传递下来的 permute 变量都是新建的
            result.add(permute);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                /*
                 *  //不能这样写，因为这样改变了上一个结点的值
                 *  permute.add(nums[i]);
                 *  visited[i] = true;
                 *
                 *  List<Integer> newPermute = new ArrayList<>(permute);
                 *  boolean[] newVisited = new boolean[nums.length];
                 *  System.arraycopy(visited, 0, newVisited, 0, nums.length);
                 */
                // 1、每一次尝试都创建新的变量表示当前的"状态"
                List<Integer> newPermute = new ArrayList<>(permute);
                newPermute.add(nums[i]);
                boolean[] newVisited = new boolean[nums.length];
                System.arraycopy(visited, 0, newVisited, 0, nums.length);
                newVisited[i] = true;

                dfsPermute(nums, newVisited, newPermute, result);
                // 2、无需回溯
            }
        }
    }

    public static void main(String[] args) {
        printLists(new Solution2().permute(new int[]{1, 2, 3}));
    }
}