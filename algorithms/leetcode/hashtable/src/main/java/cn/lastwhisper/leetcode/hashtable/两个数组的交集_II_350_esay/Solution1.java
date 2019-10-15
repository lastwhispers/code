package cn.lastwhisper.hashtable.两个数组的交集II;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution1 {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer ,Integer> record = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            int num = nums1[i];
            if(!record.containsKey(num)){
                record.put(num,1);
            }else {
                record.put(num, record.get(num) + 1);
            }

        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            int num = nums2[i];
            if(record.containsKey(num) && record.get(num)>0){
               result.add(num);
                record.put(num, record.get(num) - 1);
           }

        }

        int[] res = new int[result.size()];
        int index=0;
        for (Integer integer : result) {
            res[index] = integer;
            index++;
        }
        
        return res;
    }

    public static void main(String[] args){
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] res = (new Solution1()).intersect(nums1, nums2);

        for (int i = 0; i < res.length; i++) {
            int re = res[i];
            System.out.println(re);
        }
    }
}