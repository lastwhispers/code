package cn.lastwhisper.leetcode.week.the175.检查整数及其两倍数是否存在_简单_5332;

class Solution {
    //O(n^2)，使用二分进行优化O(nlogn)，hash优化O(n)
    public boolean checkIfExist(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (2 * arr[i] == arr[j] || arr[i] == 2 * arr[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().checkIfExist(new int[]{7, 1, 14, 11}));
        System.out.println(new Solution().checkIfExist(new int[]{10, 2, 5, 3}));
    }
}