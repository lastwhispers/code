package cn.lastwhisper.leetcode.hashtable.两个数组的交集_349_esay;

import java.util.HashSet;
import java.util.Set;

class Solution1 {
    /**
     *
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> recorde = new HashSet();
        for (int i = 0; i < nums1.length; i++) {
            recorde.add(nums1[i]);
        }
        Set<Integer> resSet = new HashSet();
        for (int i = 0; i < nums2.length; i++) {
            if(recorde.contains(nums2[i])){
                resSet.add(nums2[i]);
            }
        }
        int[] res = new int[resSet.size()];

        int index = 0;
        for(Integer num: resSet)
            res[index++] = num;
        return res;
    }
    
    public static void main(String[] args){
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] res = (new Solution1()).intersection(nums1, nums2);

        for (int i = 0; i < res.length; i++) {
            int re = res[i];
            System.out.println(re);
        }
    }
}