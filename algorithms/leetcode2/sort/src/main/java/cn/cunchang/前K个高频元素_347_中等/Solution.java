package cn.cunchang.前K个高频元素_347_中等;

import cn.cunchang.array.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {

    public int[] topKFrequent(int[] nums, int k) {
        // 记录数字出现的频率
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            Integer freq = freqMap.get(num);
            if (freq == null) {
                freqMap.put(num, 1);
            } else {
                freqMap.put(num, ++freq);
            }
        }
        // 记录最大频率
        int maxTime = 0;
        for (Integer freq : freqMap.values()) {
            if (freq > maxTime) {
                maxTime = freq;
            }
        }

        int[] res = new int[k];
        while (k > 0) {
            for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
                if (entry.getValue() == maxTime) {
                    res[--k] = entry.getKey();
                }
            }
            maxTime--;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().
                topKFrequent(ArrayUtil.createArrays(1, 1, 1, 2, 2, 3), 2)));
    }
}