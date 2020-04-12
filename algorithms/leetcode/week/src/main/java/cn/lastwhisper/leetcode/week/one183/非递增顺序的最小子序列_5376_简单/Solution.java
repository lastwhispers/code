package cn.lastwhisper.leetcode.week.one183.非递增顺序的最小子序列_5376_简单;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    public List<Integer> minSubsequence(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        Integer[] arr = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
        }
        Arrays.sort(arr, (o1, o2) -> o2 - o1);

        List<Integer> ans = new ArrayList<>();
        int nowSum = 0;
        for (Integer num : arr) {
            nowSum += num;
            ans.add(num);
            if (nowSum > (sum - nowSum)) {
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        new Solution().minSubsequence(new int[]{4, 3, 10, 9, 8}).forEach(System.out::print);
    }

}