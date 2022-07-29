package cn.cunchang.全排列_46_中等;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] num) {
        // 记录「路径」
        LinkedList<Integer> subResult = new LinkedList<>();
        // 「路径」中的元素会被标记为 true，避免重复使用
        boolean[] visited = new boolean[num.length];
        permute0(num, subResult, visited);
        return result;
    }

    private void permute0(int[] num, LinkedList<Integer> subResult, boolean[] visited) {
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
            permute0(num, subResult, visited);
            // 撤销选择
            subResult.removeLast();
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().permute(new int[]{1, 2, 3}));
    }

}