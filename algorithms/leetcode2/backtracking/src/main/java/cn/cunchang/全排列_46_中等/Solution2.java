package cn.cunchang.全排列_46_中等;

import java.util.ArrayList;
import java.util.List;

class Solution2 {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] num) {
        // 记录「路径」
        List<Integer> subResult = new ArrayList<>();
        // 「路径」中的元素会被标记为 true，避免重复使用
        boolean[] visited = new boolean[num.length];
        permute0(num, subResult, visited, 0);
        return result;
    }

    /**
     *
     * @param num 数字序列
     * @param subResult 当前路径排列
     * @param visited 保存访问过的下标
     * @param idx idx用于撤销当前操作
     */
    private void permute0(int[] num, List<Integer> subResult, boolean[] visited, int idx) {
        // 回溯结束，记录结果
        if (num.length == subResult.size()) {
            result.add(new ArrayList<>(subResult));
        }
        // 遍历选择列表
        for (int i = 0; i < num.length; i++) {
            if (visited[i]) {
                continue;
            }
            // 选择值
            subResult.add(num[i]);
            visited[i] = true;
            permute0(num, subResult, visited, idx + 1);
            // 撤销选择
            subResult.remove(idx);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().permute(new int[]{1, 2, 3}));
    }

}