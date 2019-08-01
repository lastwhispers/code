package cn.lastwhisper.kmp;

/**
 * kmp算法
 * @author lastwhisper
 */
public class KMP {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        //int[] next = kmpNext1("AAA"); // 不考虑调整j的大小
        //int[] next = kmpNext("ABCDABD"); // 不考虑调整j的大小
        //System.out.println(Arrays.toString(next));
        int[] next = kmpNext("ABCDABD");
        //int index = kmpSearch1(str1, "BBC", next); // 不考虑调整j的大小
        int index = kmpSearch(str1, str2, next); // 不考虑调整j的大小
        System.out.println(index);
    }

    public static int kmpSearch(String txt, String pat, int[] next) {
        // i指向str1，j指向str2
        for (int i = 0, j = 0; i < txt.length(); i++) {
            // 处理txt.charAt(i) != pat.charAt(j)
            while (j > 0 && txt.charAt(i) != pat.charAt(j)) {
                j = next[j - 1];
            }
            if (txt.charAt(i) == pat.charAt(j)) {
                j++;
            }
            if (j == pat.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    // 不考虑j的修改
    public static int kmpSearch1(String txt, String pat, int[] next) {
        // i指向str1，j指向str2
        for (int i = 0, j = 0; i < txt.length(); i++) {
            if (txt.charAt(i) == pat.charAt(j)) {
                j++;
            }
            if (j == pat.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 根据子串算出部分匹配表
     *
     * @param dest 子串
     * @return int[] 部分匹配表
     */
    public static int[] kmpNext(String dest) {
        // 初始化部分匹配表
        int[] next = new int[dest.length()];
        // 部分匹配表第一个字符串为0
        next[0] = 0;
        // i表示，j表示上次的匹配值
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // dest.charAt(i) != dest.charAt(j)，从next[j-1]获取新的j
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            // dest.charAt(i) == dest.charAt(j)，部分匹配值+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    // 不考虑调整j的大小
    public static int[] kmpNext1(String dest) {
        // 初始化部分匹配表
        int[] next = new int[dest.length()];
        // 部分匹配表第一个字符串为0
        next[0] = 0;
        // i表示，j表示匹配值
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // 暂时不考虑不等
            // dest.charAt(i) == dest.charAt(j)，部分匹配值+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }


}
