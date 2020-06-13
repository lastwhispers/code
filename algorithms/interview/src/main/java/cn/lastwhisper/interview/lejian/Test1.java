package cn.lastwhisper.interview.lejian;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 最长对称子序列，本质上是在找最长公共子序列
 *
 * @author gaojun
 *
 */
public class Test1 {

    /**
     * 输入一个字符串s，我们可以删除字符串s中的任意字符，让剩下的字符串形成一个对称字符串，且该字符串为最长对称字符串。
     *  如：输入google，则找到最长对称字符串为goog；
     *  如：输入abcda则能找到3个最长对称字符串为aba/aca/ada。
     * 若最长对称字符串存在多个，则输出多个相同长度的最长对称字符串，且字符串中不包含特殊字符。
     *
     */
    public static void main(String[] args) {
        // TODO 输出最长对称字符串：goog
        String input1 = "google";
        System.out.println(findMaxSymmetry(input1));
        // TODO 输出3个最长对称字符串：aba/aca/ada
        String input2 = "abcda";
        System.out.println(findMaxSymmetry(input2));
        // TODO 输出2个最长对称字符串：pop/upu，不会输出特殊字符的对称字符串p-p
        String input3 = "pop-upu";
        System.out.println(findMaxSymmetry(input3));
    }

    /**
     * 遍历生成的二维数组，递归查找最长路径
     * @param a 第一个字符串
     * @param b 第二个字符串
     * @param mux 二维中间数组
     * @param i 二维数组raw位置
     * @param j 二维数组column位置
     * @param path 一次查找的字符串
     * @param paths 最终结果集合
     */
    private static void getAllLcs(String a, String b, int[][] mux, int i, int j, String path, Set<String> paths) {
        StringBuilder pathBuilder = new StringBuilder(path);
        while (i > 0 && j > 0) {
            if (a.charAt(i - 1) == b.charAt(j - 1)) {
                pathBuilder.append(a.charAt(i - 1));
                --i;
                --j;
            } else {
                if (mux[i - 1][j] > mux[i][j - 1]) {
                    --i;
                } else if (mux[i - 1][j] < mux[i][j - 1]) {
                    --j;
                } else {
                    getAllLcs(a, b, mux, i - 1, j, pathBuilder.toString(), paths);
                    getAllLcs(a, b, mux, i, j - 1, pathBuilder.toString(), paths);
                    return;
                }
            }
        }
        paths.add(pathBuilder.toString());
    }

    /**
     * 查找最大公共子序列
     */
    private static String findLCS(String input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        String reverse = new StringBuilder(input).reverse().toString();
        int len = input.length();
        // 0行0列分别表示空串和input、reverse的 lcs 长度
        // 初始状态
        int[][] dp = new int[len + 1][len + 1];

        for (int i = 1; i < len + 1; i++) {
            for (int j = 1; j < len + 1; j++) {
                if (input.charAt(i - 1) == reverse.charAt(j - 1)) {
                    // 找到一个 lcs 的元素
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 找 lcs 长度最长的。
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 查找路径时，存在多个路径是一个字符串的情况，set去重。
        Set<String> paths = new HashSet<>();
        getAllLcs(input, reverse, dp, input.length(), reverse.length(), "", paths);

        return String.join("/", paths);
    }

    /**
     * 包括对特殊字符，结果格式生成
     * @param input 待处理字符串
     */
    public static String findMaxSymmetry(String input) {
        String[] prepare = Pattern.compile("[^a-zA-Z0-9]").split(input);
        StringBuilder sb = new StringBuilder();
        for (String str : prepare) {
            String result = findLCS(str);
            sb.append(result);
            sb.append("/");
        }
        return sb.substring(0, sb.length() - 1);
    }

}