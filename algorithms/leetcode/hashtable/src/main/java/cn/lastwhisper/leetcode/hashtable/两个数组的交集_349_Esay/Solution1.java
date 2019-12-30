package cn.lastwhisper.leetcode.hashtable.两个数组的交集_349_Esay;

import java.util.HashSet;
import java.util.Set;

class Solution1 {
    /**
     *
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> recorde = new HashSet<Integer>();
        for (int num : nums1) {
            recorde.add(num);
        }
        Set<Integer> resSet = new HashSet<Integer>();
        for (int num : nums2) {
            if (recorde.contains(num)) {
                resSet.add(num);
            }
        }
        int[] res = new int[resSet.size()];

        int index = 0;
        for (Integer num : resSet)
            res[index++] = num;
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] res = (new Solution1()).intersection(nums1, nums2);

        for (int re : res) {
            System.out.println(re);
        }
    }
}