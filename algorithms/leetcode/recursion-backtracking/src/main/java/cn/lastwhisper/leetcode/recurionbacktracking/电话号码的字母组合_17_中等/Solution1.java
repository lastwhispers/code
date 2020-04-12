package cn.lastwhisper.leetcode.recurionbacktracking.电话号码的字母组合_17_中等;

import java.util.ArrayList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.print.PrintUtil.printList;

class Solution1 {
    private String[] letterMap = {
            " ",    //0
            "",     //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };

    /**
     * 题目地址：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：树形问题
     * -------------------------------------------------------------------
     * 时间复杂度：3^n=O(2^n)
     * 空间复杂度：
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits != null && !"".equals(digits)) {
            findCombination(digits, 0, "", result);
        }
        return result;
    }

    /**
     * @param digits 数字字符串
     *  digits=23
     * @param index 数字索引
     *  index=0,对应数字2
     * @param s 数字对应字母字符串的组合
     *  数字2对应的字母"abc",s可能是abc中的一个，也可能是空串或者ad等
     * @param result 返回的结果
     *
     */
    private void findCombination(String digits, int index, String s, List<String> result) {
        //System.out.println("index=" + index + " ：s=" + s);
        if (index == digits.length()) {
            result.add(s);
            return;
        }
        // 数字
        char c = digits.charAt(index);
        String letter = letterMap[c - '0'];
        for (int i = 0; i < letter.length(); i++) {
            findCombination(digits, index + 1, s + letter.charAt(i), result);
        }
    }

    public static void main(String[] args) {
        printList(new Solution1().letterCombinations("23"));
    }
}