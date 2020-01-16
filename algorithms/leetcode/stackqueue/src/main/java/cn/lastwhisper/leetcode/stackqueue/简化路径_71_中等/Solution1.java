package cn.lastwhisper.leetcode.stackqueue.简化路径_71_中等;

import java.util.Stack;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/simplify-path/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public String simplifyPath(String path) {
        // 按"/"分隔，进行处理
        String[] pathArr = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String p : pathArr) {
            // 处理"."和""
            if (p.equals("") || p.equals(".")) {
                continue;
            }
            // ".."返回上一级
            if (p.equals("..")) {
                if (!stack.empty()) {
                    stack.pop();
                }
            } else {
                // "path"正常路径
                stack.push(p);
            }
        }
        // 说明path是一个"/"或者"/../"等
        if (stack.empty()) {
            return "/";
        }
        // 拼接处理后的路径
        StringBuilder lastPath = new StringBuilder();
        for (String p : stack) {
            lastPath.append("/");
            lastPath.append(p);
        }
        return lastPath.toString();
    }

    public static void main(String[] args) {
        //String path = "/a/../../b/../c//.//";
        //String path = "/a/./b/../../c/";
        //String path ="/";
        String path ="/../";
        System.out.println(new Solution1().simplifyPath(path));
        //System.out.println(Arrays.toString("/a/../../b/../c//.//".replaceAll("//", "/").split("/")));
    }
}