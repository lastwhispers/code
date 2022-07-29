package cn.cunchang;

import java.util.Scanner;

/**
 * @author cunchang
 * @date 2022/7/6 8:12 PM
 */
public class Main2 {

    /**
     * 有n个人围成一圈，顺序排号。
     * 从第1个人开始报数（从1到3报数），凡报到3的人退出圈子，问最后留下的是原来第几号的那位，起始索引位置为0。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        boolean[] visited = new boolean[n];//true表示访问了

        int outCount = n;
        int idx = 0;// 访问下标
        int outIdx = 1;// 剔除计数
        while (outCount > 1) {
            if (visited[idx % n]) {
                idx++;
                continue;
            }
            if (outIdx == 3) {
                visited[idx % n] = true;
                outCount--;
                outIdx = 0;
            }
            idx++;
            outIdx++;
        }

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                System.out.println(i + 1);
            }
        }
    }

}
