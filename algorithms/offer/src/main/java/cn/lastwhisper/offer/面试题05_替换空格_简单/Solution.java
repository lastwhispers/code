package cn.lastwhisper.offer.面试题05_替换空格_简单;

class Solution {
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c != ' ') sb.append(c);
            else sb.append("%20");
        }
        return sb.toString();
    }
}