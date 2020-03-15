package cn.lastwhisper.leetcode.recurionbacktracking.复原IP地址_93_中等;

import java.util.ArrayList;
import java.util.List;

class Solution3 {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        int n = s.length();
        backtrack(0, "", 4, s, res, n);
        return res;
    }

    private void backtrack(int i, String tmp, int flag, String s, List<String> res, int n) {
        if (i == n && flag == 0) {
            res.add(tmp.substring(0, tmp.length() - 1));
            return;
        }
        if (flag < 0) return;
        for (int j = i; j < i + 3; j++) {
            if (j < n) {
                if (i == j && s.charAt(j) == '0') {
                    backtrack(j + 1, tmp + s.charAt(j) + ".", flag - 1, s, res, n);
                    break;
                }
                if (Integer.parseInt(s.substring(i, j + 1)) <= 255)
                    backtrack(j + 1, tmp + s.substring(i, j + 1) + ".", flag - 1, s, res, n);
            }
        }
    }
}