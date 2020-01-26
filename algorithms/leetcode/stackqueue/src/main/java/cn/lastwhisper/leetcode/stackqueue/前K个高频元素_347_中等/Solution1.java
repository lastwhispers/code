package cn.lastwhisper.leetcode.stackqueue.前K个高频元素_347_中等;

import java.util.*;
import java.util.stream.Collectors;

class Solution1 {
    static class Pair implements Comparable<Pair> {
        public Integer num;
        public Integer freq;

        public Pair(Integer num, Integer freq) {
            this.num = num;
            this.freq = freq;
        }

        @Override
        public int compareTo(Pair o) {
            return this.freq - o.freq;
        }
    }

    /**
     * 题目地址：https://leetcode-cn.com/problems/top-k-frequent-elements/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：Map统计频率，PriorityQueue维护大小为K的队列（最小堆）
     * -------------------------------------------------------------------
     * 时间复杂度：O(nlogk)
     * 空间复杂度：
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        // 1、Map统计频率
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 2、PriorityQueue维护大小为K的队列
        Queue<Pair> queue = new PriorityQueue<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.add(new Pair(entry.getKey(), entry.getValue()));
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.stream().map(pair -> pair.num).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        new Solution1().topKFrequent(nums, k).forEach(System.out::println);
    }
}