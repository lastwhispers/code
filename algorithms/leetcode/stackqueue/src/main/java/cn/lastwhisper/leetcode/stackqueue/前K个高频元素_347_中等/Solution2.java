package cn.lastwhisper.leetcode.stackqueue.前K个高频元素_347_中等;

import java.util.*;
import java.util.stream.Collectors;

class Solution2 {
    static class Pair implements Comparable<Pair> {
        public Integer num;
        public Integer freq;

        public Pair(Integer num, Integer freq) {
            this.num = num;
            this.freq = freq;
        }

        // 倒序
        @Override
        public int compareTo(Pair o) {
            return o.freq - this.freq;
        }
    }

    /**
     * 题目地址：https://leetcode-cn.com/problems/top-k-frequent-elements/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：Map统计频率，倒序排序频率，取前k个
     * -------------------------------------------------------------------
     * 时间复杂度：O(nlogn)
     * 空间复杂度：
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        // 1、Map统计频率
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 2、倒序排序频率
        List<Pair> list = new ArrayList<>(map.size());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list.add(new Pair(entry.getKey(), entry.getValue()));
        }
        Collections.sort(list);
        // 3、取前k个
        return list.stream().map(pair -> pair.num).limit(k).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        new Solution2().topKFrequent(nums, k).forEach(System.out::println);
    }
}