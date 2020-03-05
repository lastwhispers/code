package cn.lastwhisper.offer.面试题05_替换空格_简单;

class Solution1 {
    public String replaceSpace(String s) {
        // 校验数据
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(s);

        int spacenum = 0;//spacenum为计算空格数
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ' ')
                spacenum++;
        }
        // 新字符串长度
        int newlength = sb.length() + spacenum * 2;
        // 原字符串的尾指针
        int indexOfOrigin = sb.length() - 1;
        // 新字符串的尾指针
        int indexOfNew = newlength - 1;
        //使str的长度扩大到转换成%20之后的长度,防止下标越界
        sb.setLength(newlength);//底层新建数组后copy元素
        for (; indexOfOrigin >= 0; indexOfOrigin--) {
            if (sb.charAt(indexOfOrigin) == ' ') {  //
                sb.setCharAt(indexOfNew--, '0');
                sb.setCharAt(indexOfNew--, '2');
                sb.setCharAt(indexOfNew--, '%');
            } else {
                sb.setCharAt(indexOfNew--, sb.charAt(indexOfOrigin));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {

    }
}