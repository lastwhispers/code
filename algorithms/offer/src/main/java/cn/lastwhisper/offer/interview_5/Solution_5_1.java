package cn.lastwhisper.offer.interview_5;

/**
 * 面试题 5：替换空格
 * @author cn.lastwhisper
 */
public class Solution_5_1 {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        // 1.正常数据
        //sb.append("We are happy");
        // 2.前、后、连续空格
        //sb.append(" We   are happy ");
        // 3.没有空格
        //sb.append("Wearehappy");
        // 4.特殊测试数据：null、一个空格字符
        sb.append(" ");
        String space = new Solution_5_1().replaceSpace(sb);
        System.out.println(space);
    }

    /**
     * 运行时间：19ms
     * 占用内存：9576k
     * @param str
     * @return java.lang.String
     */
    public String replaceSpace(StringBuffer str) {
        // 校验数据
        if (str == null || str.length() == 0) {
            return "";
        }
        int spacenum = 0;//spacenum为计算空格数
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ')
                spacenum++;
        }
        // 新字符串长度
        int newlength = str.length() + spacenum * 2;
        // 原字符串的尾指针
        int indexOfOrigin = str.length() - 1;
        // 新字符串的尾指针
        int indexOfNew = newlength - 1;
        //使str的长度扩大到转换成%20之后的长度,防止下标越界
        str.setLength(newlength);//底层新建数组后copy元素
        for (; indexOfOrigin >= 0; indexOfOrigin--) {
            if (str.charAt(indexOfOrigin) == ' ') {  //
                str.setCharAt(indexOfNew--, '0');
                str.setCharAt(indexOfNew--, '2');
                str.setCharAt(indexOfNew--, '%');
            } else {
                str.setCharAt(indexOfNew--, str.charAt(indexOfOrigin));
            }
        }
        return str.toString();
    }
    /**
     * 运行时间：17ms
     * 占用内存：9580k
     *
     * @param str
     * @return java.lang.String
     */
    public String replaceSpace1(StringBuffer str) {
        return str.toString().replace(" ", "%20");
    }

}
