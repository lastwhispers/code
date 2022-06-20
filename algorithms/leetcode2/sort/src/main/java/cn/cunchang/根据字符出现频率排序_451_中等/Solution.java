package cn.cunchang.根据字符出现频率排序_451_中等;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String frequencySort(String s) {
        // 记录字母出现的频率
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Integer freq = freqMap.get(s.charAt(i));
            if (freq == null) {
                freqMap.put(s.charAt(i), 1);
            } else {
                freqMap.put(s.charAt(i), ++freq);
            }
        }
        // 记录最大频率
        int maxTime = 0;
        for (Integer freq : freqMap.values()) {
            if (freq > maxTime) {
                maxTime = freq;
            }
        }
        // 倒序记录出现频率最大的字母
        int size = freqMap.size();
        char[] charFreqOrder = new char[freqMap.size()];
        while (size > 0) {
            for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
                if (entry.getValue() == maxTime) {
                    charFreqOrder[--size] = entry.getKey();
                }
            }
            maxTime--;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = charFreqOrder.length - 1; i >= 0; i--) {
            Integer freq = freqMap.get(charFreqOrder[i]);
            for (int j = 0; j < freq; j++) {
                sb.append(charFreqOrder[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().frequencySort("tree"));
        System.out.println(new Solution().frequencySort("cccaaa"));
    }
}