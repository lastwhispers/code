package cn.lastwhisper.bf;

/**
 * BF算法
 * @author lastwhisper
 */
public class BF {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int search = search(str1, str2, 0);
        //int search = search1(str1, str2, 0);
        System.out.println(search);
    }


    public static int search(String txt, String pat, int pos) {
        int m = pat.length();
        int n = txt.length();
        for (int i = 0; i < n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                return i;
            }
        }
        return n;
    }

    /**
     * @param txt 主串
     * @param pat 子串
     * @param pos 匹配起始位置
     * @return int
     */
    public static int search1(String txt, String pat, int pos) {
        char[] txtChar = txt.toCharArray();
        char[] patChar = pat.toCharArray();
        // 主串指针
        int i = pos;
        // 子串指针
        int j = 0;

        while (i < txtChar.length && j < patChar.length) {
            if (txtChar[i] == patChar[j]) {
                i++;
                j++;
            } else {
                //没有匹配成功
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == patChar.length) {
            return i - j;
        }
        return -1;
    }
}
