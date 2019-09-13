package cn.lastwhisper.offer.interview_5;

/**
 * 面试题 5：替换空格
 * @author cn.lastwhisper
 */
public class Solution_5_1_1 {
    public static void main(String[] args) {
        String str = "We are happy";
        String space = new Solution_5_1_1().replaceSpace(str.toCharArray());
        System.out.println(space);
    }

    public String replaceSpace(char[] chars) {
        // 校验数据
        if (chars == null || chars.length == 0) {
            return "";
        }
        // 原数组总长度
        int originalLength = chars.length;
        // 统计空格数
        int blankCount = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                blankCount++;
            }
        }
        // 替换空格后的字符串长度为原来长度+2*空格数
        int newLength = originalLength + 2 * blankCount;
        char[] cache = new char[newLength];
        // 原字符串尾指针
        int indexOfOriginal = originalLength-1;
        // 新字符串尾指针
        int indexOfNew = newLength-1;
        // 从两个数组尾部进行copy字符,碰到字符不copy,而是填充"20%"
        while (indexOfOriginal >= 0) {
            if (chars[indexOfOriginal] == ' ') {
                cache[indexOfNew--] = '0';
                cache[indexOfNew--] = '2';
                cache[indexOfNew--] = '%';
            } else {
                cache[indexOfNew--] = chars[indexOfOriginal];
            }
            indexOfOriginal--;
        }
        return new String(cache);
    }
}
